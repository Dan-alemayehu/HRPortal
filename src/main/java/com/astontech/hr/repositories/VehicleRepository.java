package com.astontech.hr.repositories;

import com.astontech.hr.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> findAllByVehicleModel_ModelNameContainingIgnoreCase(String modelName);

    List<Vehicle> findAllByVehicleModel_VehicleMake_VehicleMakeNameContainingIgnoreCase(String vehicleMakeName);


    Vehicle findVehicleByLicensePlateIgnoreCase(String licensePlate);

    Vehicle findVehicleByVinIgnoreCase(String vin);

    Vehicle findVehicleById(int id);

    void deleteById(Integer id);

    List<Vehicle> findAllByColorIgnoreCase(String color);

    List<Vehicle> findByVehicleModel_Id(int vehicleModelId);

//    @Query("SELECT v FROM Vehicle v JOIN v.vehicleMake vm WHERE LOWER(vm.makeName) = LOWER(:make)")
//    List<Vehicle> findByMakeName(String make);


    void deleteByVehicleModelId(int modelId);

//    @Query("SELECT v FROM Vehicle v JOIN v.vehicleModel vm JOIN vm.vehicleMake make")
//    List<Vehicle> findAllVehiclesWithMakeAndModel();

}
