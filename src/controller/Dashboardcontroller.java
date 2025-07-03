/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;




import Carrental_GroupG_37B.BookingHistory;
import Carrental_GroupG_37B.ProfileDashboard;
import Carrental_GroupG_37B.carView;
import Carrental_GroupG_37B.comparecar;
import Carrental_GroupG_37B.main_menu;
import Dao.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import model.Booking;
import model.Car;

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
        this.userView.showProfile(new showProfile());
        this.userView.addCompareCarListener(new CompareCarsPanel());
        this.userView.addSearchListener(new SearchListener()); // Added search listener call
        allCars();
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.setVisible(false);
    }
    
    public void allCars() throws SQLException {
        ArrayList<Car> cars = cDao.getAllCars(); // Show all cars for search
        if (userView.listViewer != null) {
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
    }
    
    public void history() throws SQLException {
        ArrayList<Booking> bookings = bDao.getMyBookings(id);
        if (userView.listViewer != null) {
            userView.listViewer.removeAll();
            for(Booking booking : bookings) {
                BookingHistory carpanel = new BookingHistory();
                new BookingHistoryController(carpanel, booking); 
                userView.listViewer.add(carpanel);
                userView.listViewer.add(Box.createVerticalStrut(10));
            }
            userView.listViewer.revalidate();
            userView.listViewer.repaint(); 
        }
    }

    private class CompareCarsPanel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Create a comparecar instance to get its jPanel1
                comparecar cmpCar = new comparecar();
                // Clear the listViewer and add the compare panel
                userView.clearListViewer();
                userView.listViewer.add(cmpCar.jPanel1);
                userView.listViewer.revalidate();
                userView.listViewer.repaint();

                // Add listener for CompareButton
                cmpCar.CompareButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        CompareCarController.compareCars(cmpCar); // Static call to reuse logic
                    }
                });

                // Add listener for Exit button
                cmpCar.Exit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        userView.clearListViewer();
                        try {
                            allCars(); // Return to car list
                        } catch (SQLException ex) {
                            Logger.getLogger(Dashboardcontroller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

                // Populate combo boxes (reuse CompareCarController logic)
                CompareCarController.populateComboBoxes(cmpCar, cDao);
            } catch (Exception ex) {
                Logger.getLogger(Dashboardcontroller.class.getName()).log(Level.SEVERE, "Error loading compare view", ex);
            }
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
            ProfileDashboardController c = new ProfileDashboardController(profileDashboard, id);
            c.open();
            userView.dispose();
        }
    }

    // ActionListener for Searchcar button
    private class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                allCars(); // Display all cars when search button is clicked
            } catch (SQLException ex) {
                Logger.getLogger(Dashboardcontroller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}