/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author mamta sah
 */

import Carrental_GroupG_37B.BookCar;
import Carrental_GroupG_37B.BookingHistory;
import Carrental_GroupG_37B.carView;
import Carrental_GroupG_37B.main_menu;
import Dao.BookingDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import model.Car;

public class BookCarController {

    private final BookCar view;
    private final BookingDao bookingDao;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final int userId; // Declare userId
    private final Car car;

    public BookCarController(BookCar view, Car car, int userId) {
        this.view = view;
        this.bookingDao = new BookingDao();
        this.car = car;
        this.userId = userId;
        initListeners();
        this.view.setVisible(true);

    }
    
    public void open(){
        view.setVisible(true);
    }
    
    public void close(){
        view.setVisible(false);
    }

 
    private void initListeners() {
        view.ConfirmBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bookCar();
                } catch (SQLException ex) {
                    Logger.getLogger(BookCarController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        view.Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    private void bookCar() throws SQLException {
        String name = view.Name.getText().trim();
        String email = view.Email.getText().trim();
        String phoneNum = view.PhoneNum.getText().trim();
        Date startDate = view.DateFirst.getDate();
        Date endDate = view.DateSecond.getDate();
        String pickup = view.Pickup.getText().trim();
        String drop = view.Drop.getText().trim();

        if (name.isEmpty() || email.isEmpty() || phoneNum.isEmpty() || startDate == null || endDate == null || pickup.isEmpty() || drop.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields are required!");
            return;
        }

        String startDateStr = dateFormat.format(startDate);
        String endDateStr = dateFormat.format(endDate);
        String currentDateStr = dateFormat.format(new Date());

        if (endDate.before(startDate)) {
            JOptionPane.showMessageDialog(view, "End date must be after start date!");
            return;
        }

        if (startDateStr.compareTo(currentDateStr) < 0 || endDateStr.compareTo(currentDateStr) < 0) {
            JOptionPane.showMessageDialog(view, "Dates must be in the future!");
            return;
        }

        // Assuming user_id and car_id need to be fetched or hardcoded for now
        if (bookingDao.addBooking(userId, car.getId(), startDateStr, endDateStr, pickup, drop)) {
            JOptionPane.showMessageDialog(view, "Booking confirmed successfully!");
            view.dispose();

            // Get carView details
            carView carViewInstance = new carView(); // Replace with actual carView instance if needed
            String carBrand = carViewInstance.NCar.getText();
            String carType = carViewInstance.type.getText();
            String carModel = carViewInstance.modelC.getText();
            String carPrice = carViewInstance.price.getText();
            ImageIcon carImage = (ImageIcon) carViewInstance.carImage.getIcon();

            // Use BookingHistory with carView design
            BookingHistory historyPanel = new BookingHistory();
            historyPanel.clearBookings();
            JPanel bookingItem = new JPanel();
            bookingItem.setLayout(new java.awt.GridLayout(1, 0));
            bookingItem.setPreferredSize(new java.awt.Dimension(535, 140));
            BookingHistory bookedPanel = new BookingHistory();
            bookedPanel.NCar.setText(carBrand);
            bookedPanel.type.setText(carType + " (Booked: " + startDateStr + " to " + endDateStr + ")");
            bookedPanel.modelC.setText(carModel + " - " + pickup + " to " + drop);
            bookedPanel.price.setText(carPrice);
            if (carImage != null) {
                bookedPanel.carImage.setIcon(carImage);
            }
            bookingItem.add(bookedPanel);
            historyPanel.addBookingPanel(bookingItem);

            // Update main_menu listViewer
            main_menu mainMenu = main_menu.getInstance(); // Fixed with getInstance
            mainMenu.listViewer.removeAll();
            mainMenu.listViewer.add(historyPanel);
            mainMenu.listViewer.revalidate();
            mainMenu.listViewer.repaint();

            
        } else {
            JOptionPane.showMessageDialog(view, "Failed to confirm booking!");
        }
    }
}