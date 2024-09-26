package com.astontech.hr.bootstrap;

import com.astontech.hr.domain.*;
import com.astontech.hr.services.ElementService;
import com.astontech.hr.services.ElementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ElementService elementService;

    @Autowired
    private ElementTypeService elementTypeService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        generateElement();
    }

    private void generateElement() {

        //laptop
        ElementType laptopType = new ElementType("Laptop");

        List<Element> elementList = new ArrayList<>();
        elementList.add(new Element("Acer"));
        elementList.add(new Element("Dell"));
        elementList.add(new Element("Samsung"));
        elementList.add(new Element("Apple"));
        elementList.add(new Element("Asus"));

        laptopType.setElementList(elementList);

        elementTypeService.saveElementType(laptopType);

        //email
        ElementType emailType = new ElementType("Email");

        List<Element> emailList = new ArrayList<>();
        emailList.add(new Element("Work"));
        emailList.add(new Element("Personal"));
        emailList.add(new Element("Delegated"));

        emailType.setElementList(emailList);

        elementTypeService.saveElementType(emailType);

        //vehicle make
        VehicleMake Toyota = new VehicleMake("Toyota");

        //vehicle models
        List<VehicleModel> vehicleModelList = new ArrayList<>();
        vehicleModelList.add(new VehicleModel("Avalon"));
        vehicleModelList.add(new VehicleModel("Camery"));
        vehicleModelList.add(new VehicleModel("Rav4"));
        vehicleModelList.add(new VehicleModel("Corolla"));
        vehicleModelList.add(new VehicleModel("Supra"));

        Toyota.setVehicleModels(vehicleModelList);

        //vehicles
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("KRS-100", 2009, "M23243253465462", "Black"));
        vehicles.add(new Vehicle("DSF-123", 2006, "M06756486624567", "Green"));
        vehicles.add(new Vehicle("FRES-999", 2022, "M7345624565376", "Red"));


        // Assign vehicles to the VehicleModel "Avalon"
        for (VehicleModel model : vehicleModelList) {
            if (model.getModelName().equals("Avalon")) {
                model.setVehicles(vehicles);
                break;
            }
        }

        // Save the VehicleMake, which will save the models and their associated vehicles
        // using the correct service for VehicleMake or VehicleModel if you have it.
        // vehicleMakeService.saveVehicleMake(Toyota);
    }
}
