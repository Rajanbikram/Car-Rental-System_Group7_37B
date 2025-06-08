package Controller;

import Model.BookingData;
import Dao.CarBookingDao;
import View.BookACarPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class BookCarController {
    private final BookACarPage view;

    public BookCarController(BookACarPage view) {
        this.view = view;
        this.view.addBookCarListener(new BookCarListener());
    }

    public void open() {
        view.setVisible(true);
    }

    private void bookCar() {
        try {
            String name = view.getCustomerName();
            String email = view.getCustomerEmail();

            if (name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Name cannot be empty.");
                return;
            }
            if (email.trim().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Email cannot be empty.");
                return;
            }

            BookingData booking = new BookingData(name, email, LocalDate.now());
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
