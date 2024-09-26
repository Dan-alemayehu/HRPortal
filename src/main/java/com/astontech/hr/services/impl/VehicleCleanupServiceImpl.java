package com.astontech.hr.services.impl;

import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;
import com.astontech.hr.repositories.VehicleMakeRepository;
import com.astontech.hr.repositories.VehicleModelRepository;
import com.astontech.hr.services.VehicleCleanupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VehicleCleanupServiceImpl implements VehicleCleanupService {
    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private VehicleMakeRepository vehicleMakeRepository;

    @Override
    public void cleanUpUnusedModelsAndMakes() {
        List<VehicleModel> unusedModels = vehicleModelRepository.findUnusedModels();
        for (VehicleModel model : unusedModels) {
            vehicleModelRepository.delete(model);
        }

//        List<VehicleMake> unusedMakes = vehicleMakeRepository.findUnusedMakes();
//        for (VehicleMake make : unusedMakes) {
//            vehicleMakeRepository.delete(make);
//        }
    }
}
