//package com.astontech.hr.controllers;
//
//import com.astontech.hr.domain.Vehicle;
//import com.astontech.hr.domain.VehicleModel;
//import com.astontech.hr.domain.VehicleMake;
//import com.astontech.hr.domain.VO.VehicleVO;
//import com.astontech.hr.services.VehicleCleanupService;
//import com.astontech.hr.services.VehicleService;
//import com.astontech.hr.services.VehicleModelService;
//import com.astontech.hr.services.VehicleMakeService;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//public class VehicleController {
//
//    @Autowired
//    private VehicleService vehicleService;
//
//    @Autowired
//    private VehicleModelService vehicleModelService;
//
//    @Autowired
//    private VehicleMakeService vehicleMakeService;
//
//    @Autowired
//    private VehicleCleanupService vehicleCleanupService;
//
//
//    private Logger log = Logger.getLogger(VehicleController.class);
//
//    // Display form to add a new vehicle
//    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.GET)
//    public String adminVehicleGet(Model model) {
//        model.addAttribute("vehicleVO", new VehicleVO());
//        model.addAttribute("warningAlert", "visible");
//        return "admin/vehicle/vehicle_add";
//    }
//
//    // Handle POST request to add a new vehicle
////    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.POST)
////    public String adminVehiclePost(Model model, VehicleVO vehicleVO) {
////        logVehicleVO(vehicleVO);
////
////        // Get the result status from saveVehicleFromVO
////        String status = saveVehicleFromVO(vehicleVO);
////
////
////        // Check the status and update the model accordingly
////        if ("duplicate".equals(status)) {
////            // If there was a duplicate, show a warning
////            model.addAttribute("warningAlert", "visible");
////            model.addAttribute("warningMessage", "A vehicle with the same VIN already exists.");
////        } else {
////            // If the vehicle was saved successfully, show a success message
////            model.addAttribute("successAlert", "visible");
////            model.addAttribute("successMessage", "Vehicle added successfully!");
////        }
////
////        // Reset the form
////        model.addAttribute("vehicleVO", new VehicleVO());
////
////        return "admin/vehicle/vehicle_add";
////    }
//
//    @RequestMapping(value = "/admin/vehicle/add", method = RequestMethod.POST)
//    public String adminVehiclePost(Model model, VehicleVO vehicleVO) {
//        logVehicleVO(vehicleVO);
//
//        // Check if a vehicle with the same VIN already exists
//        Vehicle existingVehicle = vehicleService.findVehicleByVinIgnoreCase(vehicleVO.getNewVehicleVin());
//        if (existingVehicle != null) {
//            // If there was a duplicate, show a warning
//            model.addAttribute("warningAlert", "visible");
//            model.addAttribute("warningMessage", "A vehicle with the same VIN already exists.");
//        } else {
//            // Create a new Vehicle object and map fields from VehicleVO
//            Vehicle newVehicle = new Vehicle();
//            newVehicle.setYear(Integer.parseInt(vehicleVO.getNewVehicleYear()));
//            newVehicle.setColor(vehicleVO.getNewVehicleColor());
//            newVehicle.setLicensePlate(vehicleVO.getNewVehicleLicensePlate());
//            newVehicle.setVin(vehicleVO.getNewVehicleVin());
////            newVehicle.setPurchase(vehicleVO.getNewIsPurchase());
////            newVehicle.setPurchaseDate(vehicleVO.getNewPurchaseDate());
////            newVehicle.setPurchasePrice(vehicleVO.getNewVehiclePrice());
//
//            // Retrieve or create a new VehicleMake object
//            VehicleMake vehicleMake = vehicleMakeService.findByVehicleMakeNameIgnoreCase(vehicleVO.getNewVehicleMake());
//            if (vehicleMake == null) {
//                vehicleMake = new VehicleMake();
//                vehicleMake.setVehicleMakeName(vehicleVO.getNewVehicleMake());
//                vehicleMakeService.saveVehicleMake(vehicleMake);
//            }
//
//            // Retrieve or create a new VehicleModel object
//            VehicleModel vehicleModel = vehicleModelService.findAllByModelNameIgnoreCase(vehicleVO.getNewVehicleModel());
//            if (vehicleModel == null) {
//                vehicleModel = new VehicleModel();
//                vehicleModel.setModelName(vehicleVO.getNewVehicleModel());
//                vehicleModel.setVehicleMake(vehicleMake);
//                vehicleModelService.saveVehicleModel(vehicleModel);
//            }
//
//            // Set the VehicleModel to the new Vehicle
//            newVehicle.setVehicleModel(vehicleModel);
//
//            // Save the new Vehicle object
//            vehicleService.saveVehicle(newVehicle);
//
//            // Show success message
//            model.addAttribute("successAlert", "visible");
//            model.addAttribute("successMessage", "Vehicle added successfully!");
//        }
//
//        // Reset the form
//        model.addAttribute("vehicleVO", new VehicleVO());
//
//        return "admin/vehicle/vehicle_add";
//    }
//
//
//
//    // List all vehicles
//    @RequestMapping(value = "/admin/vehicle/list", method = RequestMethod.GET)
//    public String adminVehicleList(Model model) {
//        model.addAttribute("vehicleList", vehicleService.getAllVehicles());
//        return "admin/vehicle/vehicle_list";
//    }
//
//    // Display form to edit an existing vehicle
//    @RequestMapping(value = "/admin/vehicle/edit/{id}", method = RequestMethod.GET)
//    public String vehicleEdit(@PathVariable int id, Model model) {
//        Vehicle vehicle = vehicleService.getVehicleById(id);
//
//        model.addAttribute("vehicle", vehicle);
//        return "admin/vehicle/vehicle_edit";
//    }
//
//    // Handle POST request to update a vehicle
////    @RequestMapping(value = "/admin/vehicle/update", method = RequestMethod.POST)
////    public String vehicleUpdate(Model model, @ModelAttribute("vehicle") Vehicle vehicle){
////
////        // Check if VIN is already in use by another vehicle
////        Vehicle existingVehicle = vehicleService.findVehicleByVinIgnoreCase(vehicle.getVin());
////        if (existingVehicle != null && !Objects.equals(existingVehicle.getId(), vehicle.getId())) {
////            // Duplicate VIN detected, return to edit page with a warning message
////            model.addAttribute("vehicle", vehicle);
////            model.addAttribute("errorAlert", "visible");
////            model.addAttribute("errorMessage", "VIN already in use by another vehicle.");
////            return "admin/vehicle/vehicle_edit";
////        }
////
////        String newMake = vehicle.getVehicleModel().getVehicleMake().getVehicleMakeName();
////        String newModel = vehicle.getVehicleModel().getModelName();
////        // Update the vehicle make and model if new data is provided
////        VehicleMake vehicleMake = vehicleMakeService.findByVehicleMakeNameIgnoreCase(newMake);
////        if (vehicleMake == null) {
////            vehicleMake = new VehicleMake();
////            vehicleMake.setVehicleMakeName(newMake);
////            vehicleMakeService.saveVehicleMake(vehicleMake);
////        }
////
////        VehicleModel vehicleModel = vehicleModelService.findAllByModelNameIgnoreCase(newModel);
////        if (vehicleModel == null) {
////            vehicleModel = new VehicleModel();
////            vehicleModel.setModelName(newModel);
////            vehicleModel.setVehicleMake(vehicleMake);
////            vehicleModelService.saveVehicleModel(vehicleModel);
////        }
////
////        vehicle.setVehicleModel(vehicleModel);
////
////        vehicleService.saveVehicle(vehicle);
////
////        // Clean up any unused models or makes
////        vehicleCleanupService.cleanUpUnusedModelsAndMakes();
////        return "redirect:/admin/vehicle/edit/" + vehicle.getId();
////    }
//
//    @RequestMapping(value = "/admin/vehicle/update", method = RequestMethod.PUT)
//    public String vehicleUpdate(Model model, @ModelAttribute Vehicle vehicle) {
//
//        // Convert the Vehicle object to a VehicleVO object for validation
//        VehicleVO vehicleVO = new VehicleVO();
//        vehicleVO.setNewVehicleMake(vehicle.getVehicleModel().getVehicleMake().getVehicleMakeName());
//        vehicleVO.setNewVehicleModel(vehicle.getVehicleModel().getModelName());
//        vehicleVO.setNewVehicleYear(String.valueOf(vehicle.getYear()));
//        vehicleVO.setNewVehicleColor(vehicle.getColor());
//        vehicleVO.setNewVehicleLicensePlate(vehicle.getLicensePlate());
//        vehicleVO.setNewVehicleVin(vehicle.getVin());
////        vehicleVO.setNewIsPurchase(vehicle.isPurchase());
////        vehicleVO.setNewPurchaseDate(vehicle.getPurchaseDate());
////        vehicleVO.setNewVehiclePrice(vehicle.getPurchasePrice());
//
//        // Use the helper method for updating the vehicle (pass the vehicle ID)
//        String status = saveVehicleFromVO(vehicleVO, vehicle.getId());
//
//        if ("duplicate".equals(status)) {
//            model.addAttribute("vehicle", vehicle);
//            model.addAttribute("errorAlert", "visible");
//            model.addAttribute("errorMessage", "VIN already in use by another vehicle.");
//            return "admin/vehicle/vehicle_edit";
//        }
//
//        model.addAttribute("successAlert", "visible");
//        model.addAttribute("successMessage", "Vehicle updated successfully!");
//
//        return "redirect:/admin/vehicle/edit/" + vehicle.getId();
//    }
//
//
//
//    // Handle vehicle deletion
//    @RequestMapping(value = "/admin/vehicle/delete/{id}", method = RequestMethod.GET)
//    public String vehicleDelete(@PathVariable int id) {
//        vehicleService.deleteVehicleById(id);
//
//        // Clean up unused models / makes after deletion
//        vehicleCleanupService.cleanUpUnusedModelsAndMakes();
//        return "redirect:/admin/vehicle/list";
//    }
//
//    //region HELPER METHODS
//    private String saveVehicleFromVO(VehicleVO vehicleVO, Integer vehicleId) {
//
//        // Check if a vehicle with the same VIN already exists, excluding the current vehicle for updates
//        Vehicle existingVehicle = vehicleService.findVehicleByVinIgnoreCase(vehicleVO.getNewVehicleVin());
//        if (existingVehicle != null && (vehicleId == null || !existingVehicle.getId().equals(vehicleId))) {
//            return "duplicate";
//        }
//
//        VehicleMake vehicleMake = vehicleMakeService.findByVehicleMakeNameIgnoreCase(vehicleVO.getNewVehicleMake());
//        if (vehicleMake == null) {
//            vehicleMake = new VehicleMake();
//            vehicleMake.setVehicleMakeName(vehicleVO.getNewVehicleMake());
//            vehicleMakeService.saveVehicleMake(vehicleMake);
//        }
//
//        VehicleModel vehicleModel = vehicleModelService.findAllByModelNameIgnoreCase(vehicleVO.getNewVehicleModel());
//        if (vehicleModel == null) {
//            vehicleModel = new VehicleModel();
//            vehicleModel.setModelName(vehicleVO.getNewVehicleModel());
//            vehicleModel.setVehicleMake(vehicleMake);
//            vehicleModelService.saveVehicleModel(vehicleModel);
//        }
//
//        Vehicle newVehicle = new Vehicle();
//        newVehicle.setYear(Integer.parseInt(vehicleVO.getNewVehicleYear()));
//        newVehicle.setColor(vehicleVO.getNewVehicleColor());
//        newVehicle.setLicensePlate(vehicleVO.getNewVehicleLicensePlate());
//        newVehicle.setVin(vehicleVO.getNewVehicleVin());
//        newVehicle.setVehicleModel(vehicleModel);
////        newVehicle.setPurchase(vehicleVO.getNewIsPurchase());
////        newVehicle.setPurchaseDate(vehicleVO.getNewPurchaseDate());
////        newVehicle.setPurchasePrice(vehicleVO.getNewVehiclePrice());
//
//        vehicleService.saveVehicle(newVehicle);
//
//        return "sucess";
//
//    }
//
//    private void logVehicleVO(VehicleVO vehicleVO) {
//        log.info("New Vehicle Make: " + vehicleVO.getNewVehicleMake());
//        log.info("New Vehicle Model: " + vehicleVO.getNewVehicleModel());
//        log.info("New Vehicle Year: " + vehicleVO.getNewVehicleYear());
//        log.info("New Vehicle Color: " + vehicleVO.getNewVehicleColor());
//        log.info("New Vehicle License Plate: " + vehicleVO.getNewVehicleLicensePlate());
//        log.info("New Vehicle VIN: " + vehicleVO.getNewVehicleVin());
//    }
//    //endregion
//}

