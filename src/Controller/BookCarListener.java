import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import Model.BookingData;
import Dao.CarBookingDao;

public class BookCarListener implements ActionListener {
    private int userId;
    private String customerName;
    private String customerEmail;
    private String selectedCar;

    public BookCarListener(int userId, String customerName, String customerEmail, String selectedCar) {
        this.userId = userId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.selectedCar = selectedCar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Use today's date
            LocalDate bookingDate = LocalDate.now();
            BookingData booking = new BookingData(customerName, customerEmail, bookingDate, selectedCar);

            // Ask for confirmation if already booked
            if (CarBookingDao.userBookedCar(userId, selectedCar)) {
                int choice = JOptionPane.showConfirmDialog(null,
                        "You have already booked this car. Do you want to proceed with a new booking?",
                        "Already Booked", JOptionPane.YES_NO_OPTION);

                if (choice != JOptionPane.YES_OPTION) {
                    return; // cancel booking
                }
            }

            // Try to book
            boolean booked = CarBookingDao.bookCar(userId, booking);
            if (booked) {
                JOptionPane.showMessageDialog(null, "Car booked successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Car booking failed. Please try again.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error during car booking: " + ex.getMessage());
        }
    }
}
