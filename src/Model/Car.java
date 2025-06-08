/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
/**
 *
 * @author rohin
 */
public class Car {
    private int id;
    private String brand;
    private String model;
    private String status;
    
    public Car(int id, String brand, String model, String status){
        this.id=id;
        this.brand=brand;
        this.model=model;
        this.status=status;
    }
    public int getId(){
        return id;
    }
    public String getBrand(){
        return brand;
    }
    public String getModel(){
        return model;
    }
    public String getStatus(){
        return status;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setBrand(String brand){
        this.brand=brand;
    }
    public void setModel(String model){
        this.model=model;
    }
    public void setstatus(String status){
        this.status=status;
    }
    
}
