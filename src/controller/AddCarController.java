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
                int seatingCapacity = addView.getSeatingCap(); // New field
                String acAvailability = addView.getAcAvailability(); // New field
                String fuelType = addView.getFuelType(); // New field

                if (brand.isEmpty()) {
                    JOptionPane.showMessageDialog(addView, "Brand cannot be empty");
                    return;
                }
                if (type.isEmpty()) {
                    JOptionPane.showMessageDialog(addView, "Type cannot be empty");
                    return;
                }
                if (model.isEmpty()) {
                    JOptionPane.showMessageDialog(addView, "Model cannot be empty");
                    return;
                }
                if (price.isEmpty()) {
                    JOptionPane.showMessageDialog(addView, "Price cannot be empty");
                    return;
                }
                if (imagePath.isEmpty()) {
                    JOptionPane.showMessageDialog(addView, "Image cannot be empty");
                    return;
                }
                if (seatingCapacity <= 0) { // Validate seating capacity
                    JOptionPane.showMessageDialog(addView, "Seating Capacity must be greater than 0");
                    return;
                }
                if (acAvailability == null || acAvailability.isEmpty()) {
                    JOptionPane.showMessageDialog(addView, "AC Availability cannot be empty");
                    return;
                }
                if (fuelType == null || fuelType.isEmpty()) {
                    JOptionPane.showMessageDialog(addView, "Fuel Type cannot be empty");
                    return;
                }

                Car newCar = new Car(imagePath, brand, model, type, price, seatingCapacity, acAvailability, fuelType);

                if (carDao.addCar(newCar)) {
                    JOptionPane.showMessageDialog(addView, "Added successfully");
                    close();
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add car");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddCarController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

