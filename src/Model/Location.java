/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author rohin
 */
public class Location {
    private int id;
    private String name;
    private String city;
    
    public Location(int id, String name,String city){
        this.id=id;
        this.name=name;
        this.city=city;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getCity(){
        return city;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setCity(String city){
        this.city=city;
    }
    
}
