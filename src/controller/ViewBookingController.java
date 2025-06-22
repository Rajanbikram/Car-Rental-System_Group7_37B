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
import Carrental_GroupG_37B.ViewBooking;
import Dao.BookingDao;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Car;

public class ViewBookingController {
    private final ViewBooking view;
    private final BookingDao bDao = new BookingDao();

    public ViewBookingController(ViewBooking view) {
        this.view = view;
        initListeners();
        loadBookings(); // Load bookings on initialization
        this.view.setVisible(true);
    }

    public void open() {
        loadBookings(); // Reload bookings when opened
        if (!view.isVisible()) {
            view.setVisible(true); // Open only if not visible
        }
    }

    private void initListeners() {
        view.CloseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    private void loadBookings() {
        ArrayList<Car> cars;
        try {
            cars = bDao.getAllBookings();
            view.Listview.removeAll();
            for (Car car : cars) {
                System.out.println("dsd");
                BookingHistory carpanel = new BookingHistory();
                new BookingHistoryController(carpanel, car);
                view.Listview.add(carpanel);
                view.Listview.add(Box.createVerticalStrut(10));
            }
            view.Listview.revalidate();
            view.Listview.repaint();
        } catch (SQLException ex) {
            Logger.getLogger(ViewBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
