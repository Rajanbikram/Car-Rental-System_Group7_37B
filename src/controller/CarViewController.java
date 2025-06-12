/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 * @author mamta sah
 */


import Dao.CarDao;
import java.awt.event.ActionEvent;
import Carrental_GroupG_37B.carView;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Car;



public class CarViewController {
    private final carView view;
    private final CarDao carDao;
    Car car = null;
    private int id;

    public CarViewController(carView view, Car car,int id) {
        this.view = view;
        this.carDao = new CarDao();
        this.car = car;
        this.id = id;
        getSetValues();
        this.view.setVisible(true);

        // Load car list when controller is initialized
        
      view.delete.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }

            private void deleteActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }

    // Load car list from database and display in JTable
    public void getSetValues() {
        System.out.println(car.getBrand());
        ImageIcon icon = new ImageIcon(car.getImagePath());
        view.carImage.setIcon(icon);
        view.NCar.setText(car.getBrand());
        view.type.setText(car.getType());
        view.modelC.setText(car.getModel());
        view.price.setText(car.getPrice());
    }

}