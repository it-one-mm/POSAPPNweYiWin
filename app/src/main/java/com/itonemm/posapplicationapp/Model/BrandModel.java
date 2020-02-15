package com.itonemm.posapplicationapp.Model;

public class BrandModel {

    public String Name;
    public int Id;
    public int ColorId;
    public int Disable;

    public BrandModel(String name, int id, int colorId, int disable) {
        Name = name;
        Id = id;
        ColorId = colorId;
        Disable = disable;
    }

    public BrandModel(String name, int id, int colorId) {
        Name = name;
        Id = id;
        ColorId = colorId;
    }

    public BrandModel() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getColorId() {
        return ColorId;
    }

    public void setColorId(int colorId) {
        ColorId = colorId;
    }

    public int getDisable() {
        return Disable;
    }

    public void setDisable(int disable) {
        Disable = disable;
    }
}
