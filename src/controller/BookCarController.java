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
import Carrental_GroupG_37B.main_menu;
import Dao.BookingDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Booking;
import model.Car;

public class BookCarController {
    private final BookCar view;
    private final BookingDao bookingDao;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final int userId;
    private final Car car;

    public BookCarController(BookCar view, Car car, int userId) {
        this.view = view;
        this.bookingDao = new BookingDao();
        this.car = car;
        this.userId = userId;
        initListeners();
        view.setVisible(true);
    }

     public void open() {
        this.view.setVisible(true);
    }

    // Added close method
    public void close() {
        this.view.setVisible(false);
    }

    private void initListeners() {
        view.ConfirmBooking.addActionListener(e -> {
            try {
                bookCar();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Booking error!");
            }
        });
        view.Cancel.addActionListener(e -> view.dispose());
    }

    private void bookCar() throws SQLException {

        Date startDate = view.DateFirst.getDate();
        Date endDate = view.DateSecond.getDate();
        String pickup = view.Pickup.getText().trim();
        String drop = view.Drop.getText().trim();

        if ( startDate == null || endDate == null || pickup.isEmpty() || drop.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All fields required!");
            return;
        }

        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
        String currentDateStr = dateFormat.format(new Date());

        if (endDate.before(startDate) || sqlStartDate.toString().compareTo(currentDateStr) < 0) {
            JOptionPane.showMessageDialog(view, "Invalid dates!");
            return;
        }

        if (!bookingDao.isCarAvailable(car.getId(), sqlStartDate, sqlEndDate)) {
            JOptionPane.showMessageDialog(view, "Car already booked!");
            return;
        }

        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setCarId(car.getId());
        booking.setStartDate(sqlStartDate);
        booking.setEndDate(sqlEndDate);
        booking.setPickupLocation(pickup);
        booking.setDropLocation(drop);
        booking.setStatus("booked");

        if (bookingDao.addBooking(booking)) {
            JOptionPane.showMessageDialog(view, "Booking confirmed!");
            view.dispose();

            BookingHistory history = new BookingHistory();
            if (history.NCar == null) history.NCar = new JLabel();
            if (history.startDate == null) history.startDate = new JLabel();
            if (history.pickdrop == null) history.pickdrop = new JLabel();
            if (history.status == null) history.status = new JLabel();
            if (history.carImage == null) history.carImage = new JLabel();
            history.NCar.setText(car.getBrand());
            history.startDate.setText(car.getType() + " (Booked: " + dateFormat.format(startDate) + " to " + dateFormat.format(endDate) + ")");
            history.pickdrop.setText(car.getModel() + " - " + pickup + " to " + drop);
            history.status.setText(car.getPrice());
            if (car.getImagePath() != null) {
                history.carImage.setIcon(new javax.swing.ImageIcon(car.getImagePath()));
            }
            JPanel bookingItem = new JPanel(new java.awt.GridLayout(1, 0));
            bookingItem.setPreferredSize(new java.awt.Dimension(535, 140));
            bookingItem.add(history);
            history.clearBookings();
            history.addBookingPanel(bookingItem);

           
           
        } else {
            JOptionPane.showMessageDialog(view, "Booking failed!");
        }
    }
}
