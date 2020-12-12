package com.example.drx.models;

public class ListedItem {
    String ID;
    Double Price;
    Double Quantity;
    String Description;
    String Specs;
    int Image;

    public ListedItem(String ID, Double price, Double quantity) {
        this.ID = ID;
        Price = price;
        Quantity = quantity;
    }

    public ListedItem(String ID, Double price, Double quantity, String description, String specs, int image) {
        this.ID = ID;
        Price = price;
        Quantity = quantity;
        Description = description;
        Specs = specs;
        Image = image;
    }

    public ListedItem(String ID, Double price, Double quantity, String description, String specs) {
        this.ID = ID;
        Price = price;
        Quantity = quantity;
        Description = description;
        Specs = specs;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Double getQuantity() {
        return Quantity;
    }

    public void setQuantity(Double quantity) {
        Quantity = quantity;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSpecs() {
        return Specs;
    }

    public void setSpecs(String specs) {
        Specs = specs;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
