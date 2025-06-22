/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author mamta sah
 */


import Carrental_GroupG_37B.comparecar;
import Carrental_GroupG_37B.main_menu;
import Carrental_GroupG_37B.Addcar; // Assuming Addcar class package
import Dao.CarDao;
import java.awt.event.ActionEvent;
import model.Car;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CompareCarController {
    private CarDao carDao = new CarDao();
    private comparecar compareView;
    ArrayList<Car> cars = null;

    public CompareCarController(comparecar compareView) {
        this.compareView = compareView;
        this.compareView.compareListener(new CompareCars());
        populateComboBoxes();
    }

   

    public void open() {
        compareView.setVisible(true);
    }

    public void close() {
        compareView.setVisible(false);
    }

    private void populateComboBoxes() {
        try {
            cars = carDao.getAllCars();
           
           compareView.car1Selector.removeAll();
           compareView.car2Selector.removeAll();
            for (Car car : cars) {
                
                compareView.car1Selector.addItem(car.getBrand() + " - " + car.getModel());
                compareView.car2Selector.addItem(car.getBrand() + " - " + car.getModel());
            }
            
        } catch (Exception e) {
            Logger.getLogger(CompareCarController.class.getName()).log(Level.SEVERE, "Error populating combo boxes", e);
        }
    }



    private void compareCars() {
        String car1Name = (String) compareView.car1Selector.getSelectedItem();
        String car2Name = (String) compareView.car2Selector.getSelectedItem();

        if (car1Name.equals("Select Car") || car2Name.equals("Select Car")) {
            JOptionPane.showMessageDialog(compareView, "Please select both cars!");
            return;
        }

        try {
            cars = carDao.getAllCars();
            Car car1 = null, car2 = null;
            for (Car car : cars) {
                String carFullName = car.getBrand() + " - " + car.getModel();
                if (carFullName.equals(car1Name)) car1 = car;
                if (carFullName.equals(car2Name)) car2 = car;
            }

            if (car1 != null && car2 != null) {
                DefaultTableModel model = new DefaultTableModel(
                    new Object[][]{
                        {"Brand", car1.getBrand(), car2.getBrand()},
                        {"Model", car1.getModel(), car2.getModel()},
                        {"Type", car1.getType(), car2.getType()},
                        {"Price", car1.getPrice(), car2.getPrice()},
                        {"Seating Capacity", String.valueOf(car1.getSeatingCapacity()), String.valueOf(car2.getSeatingCapacity())},
                        {"AC Availability", car1.getAcAvailability(), car2.getAcAvailability()},
                        {"Fuel Type", car1.getFuelType(), car2.getFuelType()}
                    },
                    new String[]{"Attribute", "Car 1", "Car 2"}
                );
                compareView.compareTable.setModel(model);
            } else {
                JOptionPane.showMessageDialog(compareView, "Error fetching car details!");
            }
        } catch (Exception e) {
            Logger.getLogger(CompareCarController.class.getName()).log(Level.SEVERE, "Error comparing cars", e);
        }
    }

    private class CompareCars implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            compareCars();
        }

    }

  

}