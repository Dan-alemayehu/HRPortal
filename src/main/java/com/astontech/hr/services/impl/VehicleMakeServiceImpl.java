package com.astontech.hr.services.impl;

import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;
import com.astontech.hr.exceptions.DuplicateEntityException;
import com.astontech.hr.repositories.VehicleMakeRepository;
import com.astontech.hr.repositories.VehicleModelRepository;
import com.astontech.hr.services.VehicleMakeService;
import com.astontech.hr.services.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class VehicleMakeServiceImpl implements VehicleMakeService {

    @Autowired
    private VehicleModelService vehicleModelService;

    @Autowired
    private VehicleModelRepository vehicleModelRepository;
    @Autowired
    private VehicleMakeRepository vehicleMakeRepository;


    @Override
    public Iterable<VehicleMake> getAllVehicleMakes() {
        return vehicleMakeRepository.findAll();
    }

    @Override
    public VehicleMake getVehicleMakeById(int id) {
        return vehicleMakeRepository.findOne(id);
    }

    @Override
    public VehicleMake saveVehicleMake(VehicleMake vehicleMake) {
        VehicleMake temp = vehicleMakeRepository.findVehicleMakeByVehicleMakeNameEqualsIgnoreCase(vehicleMake.getVehicleMakeName());
        if (temp != null) {
            System.out.println(temp.toString());
            throw new DuplicateEntityException("Vehicle make already exists: " + vehicleMake.getVehicleMakeName());
        }
        return vehicleMakeRepository.save(vehicleMake);
    }

    @Override
    public void updateVehicleMake(VehicleMake vehicleMake) {
        VehicleMake temp = vehicleMakeRepository.findOne(vehicleMake.getId());
        if (temp == null)
            throw new EntityNotFoundException(String.format("Entity with id { %s } does not exist!", vehicleMake.getId()));

        // Check for duplicate make name with a different id
        VehicleMake existingVM = vehicleMakeRepository.findVehicleMakeByVehicleMakeNameEqualsIgnoreCase(vehicleMake.getVehicleMakeName());
        if (existingVM != null && !Objects.equals(existingVM.getId(), temp.getId())) {
            throw new DuplicateEntityException("Vehicle make already exists: " + vehicleMake.getVehicleMakeName());
        }

        // Check for duplicate model names in the updated list
        Set<String> modelNames = new HashSet<>();
        for (VehicleModel model : vehicleMake.getVehicleModelList()) {
            // Check if the model name already exists in the set
            if (modelNames.contains(model.getModelName().toLowerCase())) {
                throw new DuplicateEntityException("Duplicate model name found: " + model.getModelName());
            }
            // Add model name to the set
            modelNames.add(model.getModelName().toLowerCase());
        }

        // Update each VehicleModel to have a reference to the current VehicleMake
        for (VehicleModel model : vehicleMake.getVehicleModelList()) {
            model.setVehicleMake(vehicleMake); // Set the relationship correctly

            // Ensure the model is a managed entity before saving the vehicle make
            if (model.getId() == 0) {
                vehicleModelService.saveVehicleModel(model); // Save only new models to persist their IDs
            }
        }

        // Update the vehicle make's name
        temp.setVehicleMakeName(vehicleMake.getVehicleMakeName());

        // Remove models from temp not in vehicleMake to handle orphan removal
        temp.getVehicleModelList().removeIf(model -> !vehicleMake.getVehicleModelList().contains(model));

        // Add or update the models in the vehicle make
        for (VehicleModel model : vehicleMake.getVehicleModelList()) {
            if (!temp.getVehicleModelList().contains(model)) {
                temp.getVehicleModelList().add(model); // Add new models to the list
            } else {
                VehicleModel existingModel = temp.getVehicleModelList().stream()
                        .filter(m -> m.getId() == model.getId())
                        .findFirst()
                        .orElse(null);
                if (existingModel != null) {
                    existingModel.setModelName(model.getModelName()); // Update the model name if it exists
                }
            }
        }

        vehicleMakeRepository.save(temp);
    }


    @Override
    public VehicleMake findVehicleMakeById(int vehicleMakeId) {
        return vehicleMakeRepository.findOne(vehicleMakeId);
    }

    @Override
    public Iterable<VehicleMake> saveVehicleMakeList(Iterable<VehicleMake> vehicleMakes) {
        return vehicleMakeRepository.save(vehicleMakes);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        vehicleMakeRepository.deleteById(id);
    }

    @Override
    public VehicleMake findByVehicleMakeNameIgnoreCase(String vehicleMakeName) {
        return vehicleMakeRepository.findVehicleMakeByVehicleMakeNameEqualsIgnoreCase(vehicleMakeName);
    }

    @Override
    public List<VehicleMake> findAllVehicleMakesWithModels() {
        return vehicleMakeRepository.findAll(); // Custom query to fetch VehicleMakes with VehicleModels
    }

    public void deleteVehicleMake(int makeId) {
        // Step 1: Get all models associated with this make
        List<VehicleModel> models = vehicleModelRepository.findByVehicleMakeId(makeId);

        // Step 2: Delete all models and their vehicles
        for (VehicleModel model : models) {
            vehicleModelService.deleteVehicleModel(model.getId());
        }

        // Step 3: Delete the make itself
        vehicleMakeRepository.deleteById(makeId);
    }
}