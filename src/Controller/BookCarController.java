package Controller;

import Model.BookingData;
import Dao.CarBookingDao;
import View.BookACarPage;
import View.BookaCar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class BookCarController {
    private final BookaCar view;

    public BookCarController(BookaCar view) {
        this.view = view;
        this.view.addBookCarListener(new BookCarListener());
    }

    public BookCarController(BookACarPage view) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void open() {
        view.setVisible(true);
    }

    private void bookCar() {
        try {
            String name = view.getCustomerName();
            String email = view.getCustomerEmail();
            String phoneNumber = view.getPhoneNumber();
            String pickupLocation = view.getPickupLocation();
            String dropLocation = view.getDropLocation();

            // Validate input fields
            if (name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Name cannot be empty.");
                return;
            }
            if (email.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Email cannot be empty.");
                return;
            }
            if (phoneNumber.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Phone number cannot be empty.");
                return;
            }
            if (pickupLocation.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Pickup location cannot be empty.");
                return;
            }
            if (dropLocation.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Drop location cannot be empty.");
                return;
            }

            // Create BookingData object
            BookingData booking = new BookingData(
                    name,
                    email,
                    LocalDate.now(),
                    pickupLocation,
                    dropLocation,
                    phoneNumber
            );

            // Submit booking
            boolean booked = CarBookingDao.bookCar(booking);

            if (booked) {
                JOptionPane.showMessageDialog(view, "Car booked successfully!");
            } else {
                JOptionPane.showMessageDialog(view, "Car booking failed. Please try again.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error during booking: " + ex.getMessage());
        }
    }

    private class BookCarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            bookCar();
        }
    }
}
