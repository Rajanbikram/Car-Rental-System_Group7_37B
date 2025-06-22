/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import Dao.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Carrental_GroupG_37B.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import model.Car;

/**
 *
 * @author mamta sah
 */
public class Dashboardcontroller {
     
    private final Dao Dao = new Dao();
    private final CarDao cDao = new CarDao();
    private final BookingDao bDao = new BookingDao();
    private final main_menu userView;
    private int id;

    public Dashboardcontroller(main_menu userView, int id) throws SQLException {
        this.userView = userView;
        this.id = id;
        this.userView.showHistory(new showHistory());
        this.userView.showProfile(new showProfile()); // Added profile action listener
        this.userView.addCompareCarListener(new CompareCarsPanel());
        allCars();
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.setVisible(false);
    }
    
    public void allCars() throws SQLException {
        
        ArrayList<Car> cars = cDao.getAllCars();
        userView.listViewer.removeAll();
        for(Car car : cars) {
            carView carpanel = new carView();
            new CarViewController(carpanel, car, id);
            userView.listViewer.add(carpanel);
            userView.listViewer.add(Box.createVerticalStrut(10));
        }
        userView.listViewer.revalidate();
        userView.listViewer.repaint(); 
    }
    
    public void history() throws SQLException {
        ArrayList<Car> cars = bDao.getMyBookings(id);
        userView.listViewer.removeAll();
        for(Car car : cars) {
            BookingHistory carpanel = new BookingHistory();
            new BookingHistoryController(carpanel, car);
            userView.listViewer.add(carpanel);
            userView.listViewer.add(Box.createVerticalStrut(10));
        }
        userView.listViewer.revalidate();
        userView.listViewer.repaint(); 
    }

 
    private  class CompareCarsPanel implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            comparecar cmpCar = new comparecar();
            CompareCarController c = new CompareCarController(cmpCar);
            c.open();
        }

       
    }

    private class showHistory implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                history();
            } catch (SQLException ex) {
                Logger.getLogger(Dashboardcontroller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class showProfile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           ProfileDashboard profileDashboard = new ProfileDashboard();
            ProfileDashboardController c = new ProfileDashboardController(profileDashboard,id);
            c.open();
            userView.setVisible(false); // Hide main menu
        }
    }
}