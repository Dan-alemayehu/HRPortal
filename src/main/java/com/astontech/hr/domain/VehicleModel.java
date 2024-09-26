package com.astontech.hr.domain;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
public class VehicleModel {

    //region ATTRIBUTE
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VehicleModelId")
    private Integer id;

    @Version
    private Integer version;
    private String modelName;

    @OneToMany(mappedBy = "vehicleModel", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Vehicle> vehicles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "vehicle_make_id")
    private VehicleMake vehicleMake;

    //region CONSTRUCTORS
    public VehicleModel() {};
    public VehicleModel(String modelName) {
        this.setModelName(modelName);
    }
    public VehicleModel(String modelName, List<Vehicle> vehicles) {
        this.setModelName(modelName);
        this.setVehicles(vehicles);
    }
    //endregion

    //region SETTERS / GETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public VehicleMake getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(VehicleMake vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    //endregion
}