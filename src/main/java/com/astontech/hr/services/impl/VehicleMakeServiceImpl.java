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
import java.util.List;
import java.util.Objects;

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

        // Checking to see if another entity has the same name but a different id
        VehicleMake existingVM = vehicleMakeRepository.findVehicleMakeByVehicleMakeNameEqualsIgnoreCase(vehicleMake.getVehicleMakeName());
        if (existingVM != null && !Objects.equals(existingVM.getId(), temp.getId())) {
            throw new DuplicateEntityException("Vehicle make already exists: " + vehicleMake.getVehicleMakeName());
        }

        temp.setVehicleMakeName(vehicleMake.getVehicleMakeName());
        temp.setVehicleModels(vehicleMake.getVehicleModelList());
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