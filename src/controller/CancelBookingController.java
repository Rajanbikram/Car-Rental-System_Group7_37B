/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.BookingDao;
import Carrental_GroupG_37B.cancelbooking; // Swing JPanel for cancel booking
import javax.swing.JOptionPane;

public class CancelBookingController {
    private final cancelbooking view;
    private final BookingDao bookingDao;
    private final long bookingId;
    private final long userId;

    public CancelBookingController(cancelbooking view, long bookingId, long userId) {
        this.view = view;
        this.bookingDao = new BookingDao();
        this.bookingId = bookingId;
        this.userId = userId;

        this.view.setVisible(true);

        // Set up button click listener
        this.view.cancelbutton.addActionListener(e -> cancelBooking());
    }

    private void cancelBooking() {
        try {
            boolean success = bookingDao.cancelBooking(bookingId, userId);
            if (success) {
                JOptionPane.showMessageDialog(view, "Booking cancelled successfully.");
            } else {
                JOptionPane.showMessageDialog(view, "Booking not found or already cancelled.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error cancelling booking: " + e.getMessage());
        }
    }
}
