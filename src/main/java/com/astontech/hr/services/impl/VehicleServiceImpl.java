package com.astontech.hr.services.impl;

import com.astontech.hr.domain.Vehicle;
import com.astontech.hr.domain.VehicleModel;
//import com.astontech.hr.exceptions.DuplicateVehicleException;
import com.astontech.hr.repositories.VehicleModelRepository;
import com.astontech.hr.repositories.VehicleRepository;
import com.astontech.hr.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Override
    public List<Vehicle> findAllByVehicleModel_ModelNameContainingIgnoreCase(String modelName){
        return vehicleRepository.findAllByVehicleModel_ModelNameContainingIgnoreCase(modelName);
    }

    @Override
    public List<Vehicle> findAllByVehicleModel_VehicleMake_VehicleMakeNameContainingIgnoreCase(String vehicleMakeName) {
        return vehicleRepository.findAllByVehicleModel_VehicleMake_VehicleMakeNameContainingIgnoreCase(vehicleMakeName);
    }


    @Override
    public Iterable<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleById(int id) {
        return vehicleRepository.findOne(id);
    }

    @Override
    public String saveVehicle(Vehicle vehicle) {
        // Check for a duplicate VIN but ignore the current vehicle ID
        if (vehicle.getVin() != null) {
            Vehicle existingVehicle = vehicleRepository.findVehicleByVinIgnoreCase(vehicle.getVin());
            if (existingVehicle != null && !existingVehicle.getId().equals(vehicle.getId())) {
                return "duplicate";
            }
        }

        // Retrieve the existing VehicleModel from the vehicle object
        VehicleModel existingVehicleModel = vehicleModelRepository.findOne(vehicle.getVehicleModel().getId());
        if (existingVehicleModel == null) {
            return "error: VehicleModel not found";
        }

        // Assign the existing VehicleModel to the vehicle before saving
        vehicle.setVehicleModel(existingVehicleModel);

        // Save the vehicle object
        vehicleRepository.save(vehicle);
        return "success";
    }


    @Override
    public Iterable<Vehicle> saveVehicleList(Iterable<Vehicle> vehicles) {
        return vehicleRepository.save(vehicles);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        System.out.println("deleting vehicle with id " + id);
        vehicleRepository.deleteById(id);
    }

    @Override
    public Vehicle findVehicleByLicensePlateIgnoreCase(String licensePlate) {
        return vehicleRepository.findVehicleByLicensePlateIgnoreCase(licensePlate);
    }

    @Override
    public Vehicle findVehicleByVinIgnoreCase(String vin) {
        return vehicleRepository.findVehicleByVinIgnoreCase(vin);
    }

    @Override
    public List<Vehicle> findAllByColorIgnoreCase(String color) {
        return vehicleRepository.findAllByColorIgnoreCase(color);
    }

    @Override
    public List<Vehicle> findByVehicleModel_Id(int vehicleModelId){
        return vehicleRepository.findByVehicleModel_Id(vehicleModelId);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        // Save the updated vehicle, this works as an update if the vehicle ID is already present.
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicleModel(int modelId) {
        // Step 1: Delete all vehicles associated with this model
        vehicleRepository.deleteByVehicleModelId(modelId);

        // Step 2: Delete the model itself
        vehicleModelRepository.deleteById(modelId);
    }

//    @Override
//    public List<Vehicle> findAllVehiclesWithMakeAndModel() {
//        return vehicleRepository.findAllVehiclesWithMakeAndModel();
//    }
}
