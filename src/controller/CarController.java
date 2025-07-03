/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.CarBookingDao;
import Model.Carr;

/**
 *
 * @author rohin
 */
public class CarController {
    private CarBookingDao carBookingDao=new CarBookingDao();
    
    public void initcars(){
        String imagePath="src/View/suzuki.png";
        Carr car = new Carr("Toyota", "Toyota Fortuner GR-S", "SUV", "1.5 Crore", imagePath);

        carBookingDao.addCar(car);
    }
    public void showCars(){
        for(Carr car:carBookingDao.getAllCars()){
            System.out.println("Brand:"+car.getBrand());
            System.out.println("Model:"+car.getModel());
            System.out.println("Type:"+car.getType());
            System.out.println("Price:"+car.getPrice());
            System.out.println("Image Path:"+car.getImagePath());
        }
    }
    public static void main(String[] args) {
        CarController controller=new CarController();
        controller.initcars();
        controller.showCars();
    }
    
}
