package com.astontech.hr.domain.VO;
import com.astontech.hr.domain.VehicleMake;
import com.astontech.hr.domain.VehicleModel;


public class VehicleVO {

    //region ATTRIBUTES
    private Integer id;
    private String newVehicleMakeName;
    private String newVehicleModelName;
    private String newVehicleYear;
    private String newVehicleColor;
    private String newVehicleLicensePlate;
    private String newVehicleVin;
//    private Boolean newIsPurchase;
//    private LocalDate newPurchaseDate;
//    private Integer newVehiclePrice;

     private VehicleMake newVehicleMake;
     private VehicleModel newVehicleModel;
    //endregion

    //region CONSTRUCTORS
    public VehicleVO() {};
    //endregion

    //region SETTERS / GETTERS
    public Integer getId(){return id;}

    public void setId(Integer id){this.id = id;}

    public String getNewVehicleMakeName() {
        return newVehicleMakeName;
    }

    public void setNewVehicleMakeName(String newVehicleMakeName) {
        this.newVehicleMakeName = newVehicleMakeName;
    }

    public String getNewVehicleModelName() {
        return newVehicleModelName;
    }

    public void setNewVehicleModelName(String newVehicleModelName) {
        this.newVehicleModelName = newVehicleModelName;
    }

    public String getNewVehicleYear() {
        return newVehicleYear;
    }

    public void setNewVehicleYear(String newVehicleYear) {
        this.newVehicleYear = newVehicleYear;
    }

    public String getNewVehicleColor() {
        return newVehicleColor;
    }

    public void setNewVehicleColor(String newVehicleColor) {
        this.newVehicleColor = newVehicleColor;
    }

    public String getNewVehicleLicensePlate() {
        return newVehicleLicensePlate;
    }

    public void setNewVehicleLicensePlate(String newVehicleLicensePlate) {
        this.newVehicleLicensePlate = newVehicleLicensePlate;
    }

    public String getNewVehicleVin() {
        return newVehicleVin;
    }

    public void setNewVehicleVin(String newVehicleVin) {
        this.newVehicleVin = newVehicleVin;
    }

    public String getNewVehicleMake() {
        return newVehicleMake.toString();
    }

    public void setNewVehicleMake(VehicleMake newVehicleMake) {
        this.newVehicleMake = newVehicleMake;
    }

    public String getNewVehicleModel() {
        return newVehicleModel.toString();
    }

    public void setNewVehicleModel(VehicleModel newVehicleModel) {
        this.newVehicleModel = newVehicleModel;
    }


//    public Boolean getNewIsPurchase() {
//        return newIsPurchase;
//    }
//
//    public void setNewIsPurchase(Boolean newIsPurchase) {
//        this.newIsPurchase = newIsPurchase;
//    }
//
//    public LocalDate getNewPurchaseDate() {
//        return newPurchaseDate;
//    }
//
//    public void setNewPurchaseDate(LocalDate newPurchaseDate) {
//        this.newPurchaseDate = newPurchaseDate;
//    }
//
//    public Integer getNewVehiclePrice() {
//        return newVehiclePrice;
//    }
//
//    public void setNewVehiclePrice(Integer newVehiclePrice) {
//        this.newVehiclePrice = newVehiclePrice;
//    }
    //endregion
}

