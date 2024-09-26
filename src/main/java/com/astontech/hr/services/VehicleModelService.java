package com.astontech.hr.services;

import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;

public interface VehicleModelService {

    // Find a vehicle model by its make and name, ignoring case
    VehicleModel findByVehicleMakeAndModelNameIgnoreCase(VehicleMake vehicleMake, String modelName);

    VehicleModel findVehicleModelByModelNameIgnoreCase(String model);

    VehicleModel findVehicleModelById(int id);
    Iterable<VehicleModel> getAllVehicleModels();
    VehicleModel getVehicleModelById(int id);
    VehicleModel saveVehicleModel(VehicleModel vehicleModel);
    Iterable<VehicleModel> saveVehicleModelList(Iterable<VehicleModel> vehicleModelList);
    void deleteById(int id);

    void updateVehicleModel(VehicleModel vehicleModel);

    //custom repo methods
    VehicleModel findAllByModelNameIgnoreCase(String modelName);

    void deleteVehicleModel(int modelId);
}
