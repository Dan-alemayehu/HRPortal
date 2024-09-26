package com.astontech.hr.domain;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Vehicle {

    //region ATTRIBUTE
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VehicleId")
    private Integer id;

    @Version
    private Integer version;

    private Integer year;
    private String licensePlate;
    private String vin;
    private String color;
    private Boolean isPurchase;
    private LocalDate purchaseDate;
    private Integer purchasePrice;

    @ManyToOne
    @JoinColumn(name = "vehicle_model_id")
    private VehicleModel vehicleModel;
    //endregion

    //region CONSTRUCTORS
    public Vehicle() {};
    public Vehicle(String licensePlate, Integer year, String vin, String color) {
        this.licensePlate = licensePlate;
        this.year = year;
        this.vin = vin;
        this.color = color;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getIsPurchase() {
        return isPurchase;
    }

    public void setPurchase(Boolean purchase) {
        isPurchase = purchase;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public Integer getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Integer purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    //endregion

}