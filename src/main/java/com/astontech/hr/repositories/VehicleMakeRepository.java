package com.astontech.hr.repositories;

import com.astontech.hr.domain.VehicleMake;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface VehicleMakeRepository extends CrudRepository<VehicleMake, Integer> {

    VehicleMake findVehicleMakeByVehicleMakeNameEqualsIgnoreCase(String vehicleMakeName);

    @Query("SELECT vm FROM VehicleMake vm JOIN vm.vehicleModelList")
    List<VehicleMake> findAllVehicleMakesWithModels();

    List<VehicleMake> findAll();

    void deleteById(int makeId);
}