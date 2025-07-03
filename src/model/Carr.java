/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author rohin
 */
public class Carr {
    private String brand;
    private String model;
    private String type;
    private String price;
    private String imagePath;
    
    public Carr(String brand, String model, String type, String price){
        this.brand=brand;
        this.model=model;
        this.type=type;
        this.price=price;
        this.imagePath=imagePath;
    }

    public Carr(String toyota, String toyota_Fortuner_GRS, String suv, String _Crore, String imagePath) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public String getBrand(){
        return brand;
    }
    public String getModel(){
        return model;
    }
    public String getType(){
        return type;
    }
    public String getPrice(){
       return price; 
    }
    public String getImagePath(){
        return imagePath;
    }
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setType(String type) { this.type = type; }
    public void setPrice(String price) { this.price = price; }
    public void setImagePath(String imagePath){this.imagePath=imagePath;}
}
