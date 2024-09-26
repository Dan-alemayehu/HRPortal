package com.astontech.hr.services;

import com.astontech.hr.domain.VehicleMake;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleMakeService {

    Iterable<VehicleMake> getAllVehicleMakes();

    VehicleMake getVehicleMakeById(int id);

    VehicleMake saveVehicleMake(VehicleMake vehicleMake);

    void updateVehicleMake(VehicleMake vehicleMake);

    VehicleMake findVehicleMakeById(int id);

    Iterable<VehicleMake> saveVehicleMakeList(Iterable<VehicleMake> vehicleMakes);

    //custom repo methods
    VehicleMake findByVehicleMakeNameIgnoreCase(String vehicleMakeName);

    List<VehicleMake> findAllVehicleMakesWithModels();

    public void deleteById(int id);
}
