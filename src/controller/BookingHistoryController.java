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
import Dao.BookingDao;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Booking;
import model.Car;

public class BookingHistoryController {
    private final BookingHistory view;
    private final Booking booking;
    private final BookingDao bDao = new BookingDao();

    public BookingHistoryController(BookingHistory view, Booking booking) {
        this.view = view;
        this.booking = booking;
        loadBookings();
        this.view.setVisible(true);
    }

    public void loadBookings() {
        // Set car image and name
        if (booking.getCarDetails().getImagePath() != null) {
            try {
                ImageIcon icon = new ImageIcon(booking.getCarDetails().getImagePath());
                if (view.carImage != null) view.carImage.setIcon(icon);
            } catch (Exception e) {
                System.out.println("Error loading image: " + booking.getImagePath());
            }
        }
        if (booking.getCarDetails() != null && booking.getCarDetails().getBrand() != null) {
            view.NCar.setText(booking.getCarDetails().getBrand());
        } else {
            view.NCar.setText(booking.getBrand()); // fallback
        }

        // Combine startDate and endDate
        if (view.startDate != null && booking.getStartDate() != null && booking.getEndDate() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateRange = dateFormat.format(booking.getStartDate()) + " to " + dateFormat.format(booking.getEndDate());
            view.startDate.setText(dateRange);
        }

        // Combine pickupLocation and dropLocation
        if (view.pickdrop != null && booking.getPickupLocation() != null && booking.getDropLocation() != null) {
            String locationRange = booking.getPickupLocation() + " to " + booking.getDropLocation();
            view.pickdrop.setText(locationRange);
        }

        // Set status and booker's name (only first name)
        if (view.status != null && booking.getStatus() != null) {
            view.status.setText(booking.getStatus());
        }
        if (view.fullname != null && booking.getFullname() != null) {
            String[] nameParts = booking.getFullname().split(" ");
            String bookerName = nameParts.length > 0 ? nameParts[0] : booking.getFullname(); // Take first name
            view.fullname.setText(bookerName);
        }

        // Add Delete button action
        if (view.Delete != null) {
            view.Delete.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this booking?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        if (bDao.deleteBooking(booking.getId())) {
                            JOptionPane.showMessageDialog(view, "Booking deleted successfully.");
                            // Panel removal is now the responsibility of the calling controller
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(BookingHistoryController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }
}
