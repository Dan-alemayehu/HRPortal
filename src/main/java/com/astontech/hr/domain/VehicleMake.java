package com.astontech.hr.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class VehicleMake {

    //region ATTRIBUTE
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VehicleMakeId")
    private Integer id;

    @Version
    private Integer version;
    private String vehicleMakeName;
    private LocalDate createDate;
    @OneToMany(mappedBy ="vehicleMake", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<VehicleModel> vehicleModelList = new ArrayList<>();
    //endregion

    //region CONTRUCTORS
    public VehicleMake() {};
    public VehicleMake(String vehicleMakeName) {
        this.setVehicleMakeName(vehicleMakeName);
    }
    public VehicleMake(String vehicleMakeName, List<VehicleModel> vehicleModelList) {
        this.setVehicleMakeName(vehicleMakeName);
        this.setVehicleModels(vehicleModelList);
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

    public String getVehicleMakeName() {
        return vehicleMakeName;
    }

    public void setVehicleMakeName(String vehicleMakeName) {
        this.vehicleMakeName = vehicleMakeName;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public List<VehicleModel> getVehicleModelList() {
        return vehicleModelList;
    }

    public void setVehicleModels(List<VehicleModel> vehicleModelList) {
        vehicleModelList.forEach(model -> model.setVehicleMake(this));
        this.vehicleModelList = vehicleModelList;
    }
    //endregion
}