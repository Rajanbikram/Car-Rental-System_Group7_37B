/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
/**
 * @author mamta sah
 */

public class Car {
    private int id;          // Added for delete functionality
    private String imagePath;
    private String brand;
    private String model;
    private String type;
    private String price;
    private boolean available = true;
    private int seatingCapacity;    // New field
    private String acAvailability; // New field
    private String fuelType;       // New field

    // Updated Constructor with new fields
    public Car(String imagePath, String brand, String model, String type, String price, 
               int seatingCapacity, String acAvailability, String fuelType) {
        this.imagePath = imagePath;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.price = price;
        this.seatingCapacity = seatingCapacity;
        this.acAvailability = acAvailability;
        this.fuelType = fuelType;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // New Getter and Setter for seatingCapacity
    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    // New Getter and Setter for acAvailability
    public String getAcAvailability() {
        return acAvailability;
    }

    public void setAcAvailability(String acAvailability) {
        this.acAvailability = acAvailability;
    }

    // New Getter and Setter for fuelType
    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getModelc() {
        throw new UnsupportedOperationException("Not supported yet."); // Remove if not needed
    }
}

