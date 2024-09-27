package com.astontech.hr.controllers;

import com.astontech.hr.domain.*;
import com.astontech.hr.domain.VO.ElementVO;
import com.astontech.hr.domain.VO.VehicleMakeVO;
import com.astontech.hr.domain.VO.VehicleVO;
import com.astontech.hr.exceptions.DuplicateEntityException;
import com.astontech.hr.services.VehicleMakeService;
import com.astontech.hr.services.VehicleModelService;
import com.astontech.hr.services.VehicleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
//@RequestMapping("admin/vehicleMake")
public class VehicleMakeController {

    @Autowired
    private VehicleMakeService vehicleMakeService;

    @Autowired
    private VehicleModelService vehicleModelService;

    @Autowired
    private VehicleService vehicleService;


    private static final Logger logger = Logger.getLogger(VehicleMakeController.class);

    //Error page
    @RequestMapping(value = "/admin/vehicleMake/error", method = RequestMethod.GET)
    public String showErrorPage() {
        return "/admin/vehicleMake/vehicleMake_error"; // This should be the path to your error JSP page or HTML file
    }

    //List all vehicle makes
    @RequestMapping(value = "/admin/vehicleMake/list", method = RequestMethod.GET)
    public String listVehicleMakes(Model model) {
        List<VehicleMake> vehicleMakes = vehicleMakeService.findAllVehicleMakesWithModels(); // Fetch vehicle makes with models
        model.addAttribute("vehicleMakeList", vehicleMakeService.getAllVehicleMakes());
        logger.info("VehicleMake list: " + vehicleMakeService.getAllVehicleMakes());
        return "admin/vehicleMake/vehicleMake_list";

    }

    //List all vehicles
    @RequestMapping(value = "/admin/vehicle/list", method = RequestMethod.GET)
    public String listVehicles(Model model) {
        List<Vehicle> vehicles = (List<Vehicle>) vehicleService.getAllVehicles();
        model.addAttribute("vehicles", vehicles);
        logger.info("Vehicle list: " + vehicleService.getAllVehicles());
        logger.info("Fetched vehicles: " + vehicles.toString());
        return "admin/vehicle/vehicle_list";
    }

    //Display form to add a new vehicle make
    @RequestMapping(value = "/admin/vehicleMake/add", method = RequestMethod.GET)
    public String addVehicleMakeForm(Model model) {
        model.addAttribute("vehicleMakeVO", new VehicleMakeVO());
        logger.info("Displaying form to add a new vehicle make.");
        return "admin/vehicleMake/vehicleMake_add";
    }

    //Handle form submission to add a new vehicle make
    @RequestMapping(value = "/admin/vehicleMake/add", method = RequestMethod.POST)
    public String addVehicleMakePost(Model model, VehicleMakeVO vehicleMakeVO) {
        vehicleMakeVO.splitNewVehicleModelIntoArray();
        try {
            saveMakesAndModelsFromVO(vehicleMakeVO);
        } catch (DuplicateEntityException e) {
            return "redirect:/admin/vehicleMake/error";
        }

        return "redirect:/admin/vehicleMake/list";
    }

    //Display form to edit an existing vehicle make
    @RequestMapping(value ="/admin/vehicleMake/edit/{id}", method = RequestMethod.GET)
    public String updateVehicleMakeForm(@PathVariable int id, Model model) {
        VehicleMake vehicleMake = vehicleMakeService.getVehicleMakeById(id);

        if (vehicleMake != null) {
            // Debugging: Print the list of vehicle models to the console
            if (vehicleMake.getVehicleModelList() != null) {
                logger.info("Vehicle Models: " + vehicleMake.getVehicleModelList().toString());
            } else {
                logger.info("No Vehicle Models associated with this VehicleMake.");
            }
            model.addAttribute("vehicleMake", vehicleMake);
        } else {
            logger.info("Vehicle Make not found.");
        }
        return "admin/vehicleMake/vehicleMake_edit";
    }

    //Handle form submission to update vehicle make
    @RequestMapping(value = "/admin/vehicleMake/edit", method = RequestMethod.POST)
    public String VehicleMakeUpdate(Model model, VehicleMake vehicleMake){
        try {
            vehicleMakeService.updateVehicleMake(vehicleMake);
            return "redirect:/admin/vehicleMake/list/";
        } catch (DuplicateEntityException e) {
            return "redirect:/admin/vehicleMake/error";
        }
    }

    //Add a new model to the existing vehicleMake
    @RequestMapping(value = "/admin/vehicleMake/addModel", method = RequestMethod.POST)
    public String addVehicleModel(@RequestParam("vehicleMakeId") int vehicleMakeId,
                                  @RequestParam("newModelName") String newModelName,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {
        // Fetch the VehicleMake object by ID
        VehicleMake vehicleMake = vehicleMakeService.findVehicleMakeById(vehicleMakeId);

        // Check to see if an empty field was submitted
        if (vehicleMake == null) {
            redirectAttributes.addFlashAttribute("errorAlert", "Vehicle Make not found.");
            return "redirect:/admin/vehicleMake/list";
        }

        // Create new VehicleModel
        if (newModelName != null && !newModelName.trim().isEmpty()) {
            VehicleModel newModel = new VehicleModel();
            newModel.setModelName(newModelName);
            newModel.setVehicleMake(vehicleMake);

            // Save the new model to the database first
            vehicleModelService.saveVehicleModel(newModel);

            // Re-fetch the updated VehicleMake object to avoid stale state issues
            vehicleMake = vehicleMakeService.findVehicleMakeById(vehicleMakeId);

            // Add the new model to the vehicle make's model list
            vehicleMake.getVehicleModelList().add(newModel);

            // Save the updated vehicle make to the database
            vehicleMakeService.updateVehicleMake(vehicleMake);

            redirectAttributes.addFlashAttribute("successAlert", "Vehicle model added successfully.");
        } else {
            redirectAttributes.addFlashAttribute("errorAlert", "Model name cannot be empty.");
        }

        return "redirect:/admin/vehicleMake/list";
    }



    //Display form to add a new vehicle model
    @RequestMapping(value = "/admin/vehicle/add/{modelId}", method = RequestMethod.GET)
    public String newVehicle(@PathVariable("modelId") int modelId, Model model) {
        // Fetch the VehicleModel object using the provided modelId
        VehicleModel vehicleModel = vehicleModelService.getVehicleModelById(modelId);

        if (vehicleModel == null) {
            // Handle the case where the VehicleModel is not found
            model.addAttribute("error", "VehicleModel not found for ID: " + modelId);
            return "admin/error"; // Redirect to an error page or handle accordingly
        }

        // Create a new Vehicle object and associate it with the fetched VehicleModel
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleModel(vehicleModel);

        // Add both vehicle and vehicleModel objects to the model
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("vehicleModel", vehicleModel);

        // Return the JSP view name where the form for creating the vehicle will be rendered
        return "admin/vehicle/vehicle_add"; // Ensure the path matches your JSP directory structure
    }

    //Method to post vehicle data to the database
    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.POST)
    public String saveVehicle(@ModelAttribute Vehicle vehicle, Model model) {

                // Check if VIN is already in use by another vehicle
        Vehicle existingVehicle = vehicleService.findVehicleByVinIgnoreCase(vehicle.getVin());
        if (existingVehicle != null && !Objects.equals(existingVehicle.getId(), vehicle.getId())) {
            // Duplicate VIN detected, return to edit page with a warning message
            model.addAttribute("vehicle", vehicle);
            model.addAttribute("errorAlert", "visible");
            model.addAttribute("errorMessage", "VIN already in use by another vehicle.");
            return "admin/vehicle/vehicle_add";
        }
        // Save or update the vehicle object using your service layer
        vehicleService.saveVehicle(vehicle);

        // Redirect to a list or detail view after saving
        return "redirect:/admin/vehicle/list"; // Adjust this to your desired view or URL
    }

    // Display form to edit an existing vehicle
    @RequestMapping(value = "/admin/vehicle/edit/{id}", method = RequestMethod.GET)
    public String vehicleEdit(@PathVariable int id, Model model) {
        Vehicle vehicle = vehicleService.getVehicleById(id);

        model.addAttribute("vehicle", vehicle);
        return "admin/vehicle/vehicle_edit";
    }

    // Post the updated vehicle to the database
    @RequestMapping(value = "/admin/vehicle/edit", method = RequestMethod.POST)
    public String vehicleEdit(@ModelAttribute("vehicle") Vehicle vehicle, @RequestParam("id") int id, RedirectAttributes redirectAttributes, Model model) {

            // Check if VIN is already in use by another vehicle
            Vehicle existingVehicle = vehicleService.findVehicleByVinIgnoreCase(vehicle.getVin());
            if (existingVehicle != null && !Objects.equals(existingVehicle.getId(), vehicle.getId())) {
                // Duplicate VIN detected, return to edit page with a warning message
                model.addAttribute("vehicle", vehicle);
                model.addAttribute("errorAlert", "visible");
                model.addAttribute("errorMessage", "VIN already in use by another vehicle.");
                return "admin/vehicle/vehicle_edit";
            }

        vehicle.setId(id);
        vehicleService.saveVehicle(vehicle);

        return "redirect:/admin/vehicle/list";
    }


    //Handle vehicle make deletion
    @RequestMapping(value = "admin/vehicleMake/delete/{id}", method = RequestMethod.GET)
    public String deleteVehicleMake(@PathVariable("id") int id) {
        vehicleMakeService.deleteById(id);
//        model.addAttribute("successMessage", "Vehicle Make deleted Successfully.");
        logger.info("Vehicle Make deleted: " + id);
        return "redirect:/admin/vehicleMake/list";
    }

    //Handle vehicle Model deletion
    @RequestMapping(value = "admin/vehicleModel/delete/{id}", method = RequestMethod.GET)
    public String deleteModelVehicle(@PathVariable int id) {
        vehicleModelService.deleteById(id);
        VehicleModel temp = vehicleModelService.getVehicleModelById(id);
        VehicleMake tempMake = vehicleMakeService.getVehicleMakeById(temp.getVehicleMake().getId());
        tempMake.getVehicleModelList().remove(temp);
        vehicleMakeService.updateVehicleMake(tempMake);
//        model.addAttribute("successMessage", "Vehicle Make deleted Successfully.");
        logger.info("Vehicle Model deleted: " + id);
        return "redirect:/admin/vehicleMake/list";
    }

    //Handle vehicle deletion
    @RequestMapping(value = "/admin/vehicle/delete/{id}", method = RequestMethod.GET)
    public String deleteVehicle(@PathVariable int id) {
        Vehicle temp = vehicleService.getVehicleById(id);
        VehicleModel tempModel = vehicleModelService.getVehicleModelById(temp.getVehicleModel().getId());
        tempModel.getVehicles().remove(temp);
        vehicleModelService.saveVehicleModel(tempModel);
        vehicleService.deleteById(id);
//        model.addAttribute("successMessage", "Vehicle Make deleted Successfully.");
        logger.info("Vehicle deleted: " + id);
        return "redirect:/admin/vehicle/list";
    }

    //List vehicles by model
    @RequestMapping(value = "/admin/vehicle/search", method = RequestMethod.GET)
    public String listVehiclesByModel(Model model, @RequestParam("modelName") String modelName) {
        List<Vehicle> vehicles = vehicleService.findAllByVehicleModel_ModelNameContainingIgnoreCase(modelName);
        model.addAttribute("vehicles", vehicles);
        logger.info("Vehicle list: " + vehicleService.getAllVehicles());
        logger.info("Fetched vehicles: " + vehicles.toString());
        return "admin/vehicle/vehicle_list";
    }

    //List vehicles by make
    @RequestMapping(value = "/admin/vehicle/searchByMake", method = RequestMethod.GET)
    public String listVehiclesByMake(Model model, @RequestParam("makeName") String vehicleMakeName) {
        List<Vehicle> vehicles = vehicleService.findAllByVehicleModel_VehicleMake_VehicleMakeNameContainingIgnoreCase(vehicleMakeName);
        model.addAttribute("vehicles", vehicles);
        logger.info("Vehicle list: " + vehicleService.getAllVehicles());
        logger.info("Fetched vehicles: " + vehicles.toString());
        return "admin/vehicle/vehicle_list";
    }


    //region HELPER METHODS
    private void saveMakesAndModelsFromVO(VehicleMakeVO vehicleMakeVO) {
        List<VehicleModel> newVehicleModels = new ArrayList<>();
        VehicleMake newVehicleMake = new VehicleMake(vehicleMakeVO.getNewVehicleMakeName());
        Set<String> modelNames = new HashSet<>();

        for (String str : vehicleMakeVO.getNewVehicleModelArray()) {
            // Check if the model name already exists in the set (case insensitive)
            if (modelNames.contains(str.trim().toLowerCase())) {
                throw new DuplicateEntityException("Duplicate vehicle model found: " + str);
            }

            // Add model name to the set
            modelNames.add(str.trim().toLowerCase());

            VehicleModel vehicleModel = new VehicleModel(str.trim());
            vehicleModel.setVehicleMake(newVehicleMake);
            newVehicleModels.add(vehicleModel);
        }

        newVehicleMake.setVehicleModels(newVehicleModels);

        vehicleMakeService.saveVehicleMake(newVehicleMake);
    }


}