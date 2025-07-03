/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import java.util.ArrayList;
import java.util.List;
import Model.Carr;
/**
 *
 * @author rohin
 */
public class CarBookingDao {
    private List<Carr> carList=new ArrayList<>();
    
    public void addCar(Carr car){
        carList.add(car);
    }
    public List<Carr> getAllCars(){
        return carList;
    }
}
