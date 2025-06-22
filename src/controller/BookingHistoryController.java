/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author mamta sah
 */
import Carrental_GroupG_37B.BookingHistory;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import model.Car;

public class BookingHistoryController {
    private final BookingHistory view;
    private final Car car;
     

    public BookingHistoryController(BookingHistory view,Car car) {
        this.view = view;
        this.car = car;
        loadBookings();
        this.view.setVisible(true);
    }

    public void loadBookings() {
     System.out.println(car.getBrand());
        
        String path = car.getImagePath();
        if (path != null && !path.isEmpty()) {
            try {
                ImageIcon icon = new ImageIcon(path);
               view.carImage.setIcon(icon);
            } catch (Exception e) {
               
            }
        }
        view.NCar.setText(car.getBrand());
        view.type.setText(car.getType());
        view.modelC.setText(car.getModel());
        view.price.setText(car.getPrice());
        
    }
}