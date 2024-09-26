package com.astontech.hr.domain.VO;

public class VehicleMakeVO {

    //region ATTRIBUTES
    private String newVehicleMakeName;
    private String newVehicleModelName;
    private String[] newVehicleModelArray;

    private String[] newVehicleMakes;
    //endregion

    //region CONSTRUCTORS
    public VehicleMakeVO() {
    }

    ;

    public VehicleMakeVO(String newMakeName) {

    }
    //endregion

    //region GETTERS / SETTERS
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

    public String[] getNewVehicleModelArray() {
        return newVehicleModelArray;
    }

    public void setNewVehicleModelArray(String[] newVehicleModelArray) {
        this.newVehicleModelArray = newVehicleModelArray;
    }
    //endregion

    //region CUSTOM METHODS
    public void splitNewVehicleModelIntoArray() {
        //regex for splitting on a new line or carriage return is "\\r?\\n"
        this.setNewVehicleModelArray(this.newVehicleModelName.split("\\r?\\n"));
    }

    public String[] getNewVehicleMakes() {
        return newVehicleMakes;
    }

    public void setNewVehicleMakes(String[] newVehicleMakes) {
        this.newVehicleMakes = newVehicleMakes;
    }

    //endregion
}