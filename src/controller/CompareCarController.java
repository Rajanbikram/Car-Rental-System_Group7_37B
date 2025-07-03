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
import Carrental_GroupG_37B.Addcar;
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
    private int userId;
    ArrayList<Car> cars = null;

    public CompareCarController(comparecar compareView, int userId) {
        this.compareView = compareView;
        this.userId = userId;
        this.compareView.compareListener(new CompareCars());
        populateComboBoxes(); // Call the instance method to populate
    }

    public void open() {
        compareView.setVisible(true);
    }

    public void close() {
        compareView.setVisible(false);
    }

    // Static method to populate combo boxes
    public static void populateComboBoxes(comparecar view, CarDao cDao) {
        try {
            ArrayList<Car> cars = cDao.getAllCars();
            view.car1Selector.setModel(new DefaultComboBoxModel<>(new String[]{"Select Car"}));
            view.car2Selector.setModel(new DefaultComboBoxModel<>(new String[]{"Select Car"}));
            for (Car car : cars) {
                view.car1Selector.addItem(car.getBrand() + " - " + car.getModel());
                view.car2Selector.addItem(car.getBrand() + " - " + car.getModel());
            }
        } catch (Exception e) {
            Logger.getLogger(CompareCarController.class.getName()).log(Level.SEVERE, "Error populating combo boxes", e);
        }
    }

    // Static method to compare cars
    public static void compareCars(comparecar view) {
        String car1Name = (String) view.car1Selector.getSelectedItem();
        String car2Name = (String) view.car2Selector.getSelectedItem();

        if (car1Name.equals("Select Car") || car2Name.equals("Select Car")) {
            JOptionPane.showMessageDialog(view, "Please select both cars!");
            return;
        }

        try {
            CarDao carDao = new CarDao();
            ArrayList<Car> cars = carDao.getAllCars();
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
                view.compareTable.setModel(model);
            } else {
                JOptionPane.showMessageDialog(view, "Error fetching car details!");
            }
        } catch (Exception e) {
            Logger.getLogger(CompareCarController.class.getName()).log(Level.SEVERE, "Error comparing cars", e);
        }
    }

    private void populateComboBoxes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private class CompareCars implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            compareCars(compareView); // Call the static compareCars method with the current view
        }
    }
}