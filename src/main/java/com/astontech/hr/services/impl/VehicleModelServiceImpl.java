package com.astontech.hr.services.impl;

import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;
import com.astontech.hr.exceptions.DuplicateEntityException;
import com.astontech.hr.repositories.VehicleModelRepository;
import com.astontech.hr.repositories.VehicleRepository;
import com.astontech.hr.services.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleModelServiceImpl implements VehicleModelService {

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    // Find a vehicle model by its make and name, ignoring case
    @Override
    public VehicleModel findByVehicleMakeAndModelNameIgnoreCase(VehicleMake vehicleMake, String modelName) {
        return vehicleModelRepository.findByVehicleMakeAndModelNameIgnoreCase(vehicleMake, modelName);
    }

    @Override
    public VehicleModel findVehicleModelByModelNameIgnoreCase(String model) {
        return vehicleModelRepository.findVehicleModelByModelNameIgnoreCase(model);
    }

    @Override
    public VehicleModel findVehicleModelById(int vehicleModelId) {
        return vehicleModelRepository.findOne(vehicleModelId);
    }

    @Override
    public Iterable<VehicleModel> getAllVehicleModels() {
        return vehicleModelRepository.findAll();
    }

    @Override
    public VehicleModel getVehicleModelById(int id) {
        return vehicleModelRepository.findOne(id);
    }

    @Override
    @Transactional
    public VehicleModel saveVehicleModel(VehicleModel vehicleModel) {
        VehicleModel temp = vehicleModelRepository.findVehicleModelByModelNameIgnoreCase(vehicleModel.getModelName());
        if (temp != null) {
            System.out.println(temp.toString());
            throw new DuplicateEntityException("Vehicle model already exists: " + vehicleModel.getModelName());
        }
        return vehicleModelRepository.save(vehicleModel);
    }

    @Override
    public Iterable<VehicleModel> saveVehicleModelList(Iterable<VehicleModel> vehicleModels) {
        return vehicleModelRepository.save(vehicleModels);
    }

    @Override
    public void deleteById(int id) {
        vehicleModelRepository.delete(id);
    }

    @Override
    public void updateVehicleModel(VehicleModel vehicleModel) {
        VehicleModel temp = vehicleModelRepository.findAllByModelNameIgnoreCase(vehicleModel.getModelName());
        if (temp != null && temp.getId() != vehicleModel.getId()) {
            throw new DuplicateEntityException("Vehicle model already exists: " + vehicleModel.getModelName());
        }
        vehicleModelRepository.save(vehicleModel);
    }

    @Override
    public VehicleModel findAllByModelNameIgnoreCase(String model) {
        return vehicleModelRepository.findAllByModelNameIgnoreCase(model);
    }

    @Override
    public void deleteVehicleModel(int modelId) {
        // Step 1: Delete all vehicles associated with this model
        vehicleRepository.deleteByVehicleModelId(modelId);

        // Step 2: Delete the model itself
        vehicleModelRepository.deleteById(modelId);
    }
}
