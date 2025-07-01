/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import Dao.CarDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Car;
import javax.swing.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Carrental_GroupG_37B.Addcar;

public class AddCarController {
    private CarDao carDao = new CarDao();
    private int id;
    private Addcar addView;

    public AddCarController(Addcar addView, int id) {
        this.addView = addView;
        this.id = id;
        System.out.println("controller called...");
        this.addView.addAddBtnListener(new addNewCar());
    }
    
    public void open(){
        this.addView.setVisible(true);
    }
    
    public void close(){
        this.addView.setVisible(false);
    }

    private class addNewCar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("adding...");
            try {
                String brand = addView.getBrand();
                String type = addView.getCarType();
                String model = addView.getModel();
                String price = addView.getPrice();
                String imagePath = addView.getImagePath();
                int seatingCapacity = addView.getSeatingCap();
                String acAvailability = addView.getAcAvailability();
                String fuelType = addView.getFuelType();

                if (brand.isEmpty() || type.isEmpty() || model.isEmpty() || price.isEmpty() || imagePath.isEmpty() || acAvailability.isEmpty() || fuelType.isEmpty()) {
                    JOptionPane.showMessageDialog(addView, "All fields are required");
                    return;
                }
                if (seatingCapacity <= 0) {
                    JOptionPane.showMessageDialog(addView, "Seating Capacity must be greater than 0");
                    return;
                }

                Car newCar = new Car(imagePath, brand, model, type, price, seatingCapacity, acAvailability, fuelType);
                if (carDao.addCar(newCar, id)) {
                    JOptionPane.showMessageDialog(addView, "Added successfully");
                    close();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add car");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddCarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
