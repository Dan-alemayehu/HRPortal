package com.astontech.hr.domain.VO;

public class ElementVO {

    private String newElementType;
    private String newElements;
    private String[] newElementArray;

    public ElementVO() {}

    //region CUSTOM METHODS
    public void splitNewElementsIntoArray(){
        //regex for splitting on a new line or carriage return is "\\r?\\n"
        this.setNewElementArray(this.newElements.split("\\r?\\n"));
    }

    //endregion

    public String getNewElementType() {
        return newElementType;
    }

    public void setNewElementType(String newElementType) {
        this.newElementType = newElementType;
    }

    public String getNewElements() {
        return newElements;
    }

    public void setNewElements(String newElements) {
        this.newElements = newElements;
    }

    public String[] getNewElementArray() {
        return newElementArray;
    }

    public void setNewElementArray(String[] newElementArray) {
        this.newElementArray = newElementArray;
    }
}
