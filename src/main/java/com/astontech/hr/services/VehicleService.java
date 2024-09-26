package com.astontech.hr.services;

import com.astontech.hr.domain.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {

    List<Vehicle> findAllByVehicleModel_ModelNameContainingIgnoreCase(String modelName);

    List<Vehicle> findAllByVehicleModel_VehicleMake_VehicleMakeNameContainingIgnoreCase(String vehicleMakeName);


    Iterable<Vehicle> getAllVehicles();

    Vehicle getVehicleById(int id);

    String saveVehicle(Vehicle vehicle);

    Iterable<Vehicle> saveVehicleList(Iterable<Vehicle> vehicles);

    void deleteById(Integer id);

    //custom repo methods
    Vehicle findVehicleByLicensePlateIgnoreCase(String licensePlate);

    Vehicle findVehicleByVinIgnoreCase(String vin);

    List<Vehicle> findAllByColorIgnoreCase(String color);

    List<Vehicle> findByVehicleModel_Id(int vehicleModelId);

    Vehicle updateVehicle(Vehicle vehicle);

}

//    @Query("SELECT v FROM Vehicle v JOIN v.vehicleModel vm JOIN vm.vehicleMake make")
//    List<Vehicle> findAllVehiclesWithMakeAndModel();
//}
