package com.astontech.hr.repositories;

import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VehicleModelRepository extends JpaRepository<VehicleModel, Integer> {

    // Find a vehicle model by its make and name, ignoring case
    VehicleModel findByVehicleMakeAndModelNameIgnoreCase(VehicleMake vehicleMake, String modelName);

    VehicleModel findAllByModelNameIgnoreCase(String model);

    VehicleModel findVehicleModelByModelNameIgnoreCase(String model);

    @Modifying
    @Transactional
    void deleteByVehicleMakeId(int makeId);

    @Query("SELECT vm FROM VehicleModel vm WHERE vm.vehicles IS EMPTY")
    List<VehicleModel> findUnusedModels();

    void deleteById(int modelId);

    List<VehicleModel> findByVehicleMakeId(int makeId);
}