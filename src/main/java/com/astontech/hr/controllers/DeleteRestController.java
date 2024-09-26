package com.astontech.hr.controllers;

import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.repositories.VehicleMakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DeleteRestController {

    @Autowired
    private VehicleMakeRepository vehicleMakeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<VehicleMake>> getVehicleMakes() {
        Iterable<VehicleMake> vehicleMakes = vehicleMakeRepository.findAll();
        return ResponseEntity.ok(vehicleMakes);
    }
}
