/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author mamta sah
 */
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Booking;
import model.Car;

public class BookingDao {

    private final MySqlConnection mysql = new MySqlConnection();

    // Check if car is available for given dates
    public boolean isCarAvailable(int carId, java.sql.Date startDate, java.sql.Date endDate) throws SQLException {
        String query = "SELECT COUNT(*) FROM bookings WHERE car_id = ? AND status != 'cancelled' "
                + "AND (start_date <= ? AND end_date >= ?)";
        try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, carId);
            stmt.setDate(2, endDate);
            stmt.setDate(3, startDate);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) == 0;
        }
    }

    // Add a new booking with double booking check
    public boolean addBooking(Booking booking) throws SQLException {
        String query = "INSERT INTO bookings (user_id, car_id, start_date, end_date, pickup_location, drop_location, status, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
        try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, booking.getUserId());
            stmt.setInt(2, booking.getCarId());
            stmt.setDate(3, (Date) booking.getStartDate());
            stmt.setDate(4, (Date) booking.getEndDate());
            stmt.setString(5, booking.getPickupLocation());
            stmt.setString(6, booking.getDropLocation());
            stmt.setString(7, booking.getStatus());
            return stmt.executeUpdate() > 0;
        }
    }

    // Get user-specific bookings with car details
    public ArrayList<Booking> getMyBookings(int userId) throws SQLException {
        String query = "SELECT b.*, c.brand, c.model, c.type, c.price, c.image_path, u.full_name "
                + "FROM bookings b "
                + "INNER JOIN cars c ON b.car_id = c.id "
                + "INNER JOIN users u ON b.user_id = u.id "
                + "WHERE b.user_id = ?";

        ArrayList<Booking> bookings = new ArrayList<>();

        try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setCarId(rs.getInt("car_id"));
                booking.setStartDate(rs.getDate("start_date"));
                booking.setEndDate(rs.getDate("end_date"));
                booking.setPickupLocation(rs.getString("pickup_location"));
                booking.setDropLocation(rs.getString("drop_location"));
                booking.setStatus(rs.getString("status"));
                booking.setFullname(rs.getString("full_name")); // <-- Full name from users table
                booking.setCarDetails(new Car(
                        rs.getString("image_path"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("type"),
                        rs.getString("price"),
                        0, "N/A", "N/A"
                ));

                bookings.add(booking);
            }
        }

        return bookings;
    }

    // Get all bookings (unchanged)
    public ArrayList<Car> getAllBookings() throws SQLException {
        String query = "SELECT c.brand, c.model, c.type, c.price, c.image_path "
                + "FROM cars c INNER JOIN bookings b ON c.id = b.car_id";
        ArrayList<Car> cars = new ArrayList<>();
        try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cars.add(new Car(
                        rs.getString("image_path"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("type"),
                        rs.getString("price"),
                        0, "N/A", "N/A"
                ));
            }
        }
        return cars;
    }

    // Delete a booking by ID
    public boolean deleteBooking(int bookingId) throws SQLException {
        String query = "DELETE FROM bookings WHERE id = ?";
        try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            return stmt.executeUpdate() > 0;
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author mamta sah
 */
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Booking;
import model.Car;

public class BookingDao {

    private final MySqlConnection mysql = new MySqlConnection();

    // Check if car is available for given dates
    public boolean isCarAvailable(int carId, java.sql.Date startDate, java.sql.Date endDate) throws SQLException {
        String query = "SELECT COUNT(*) FROM bookings WHERE car_id = ? AND status != 'cancelled' "
                + "AND (start_date <= ? AND end_date >= ?)";
        try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, carId);
            stmt.setDate(2, endDate);
            stmt.setDate(3, startDate);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) == 0;
        }
    }

    // Add a new booking with double booking check
    public boolean addBooking(Booking booking) throws SQLException {
        String query = "INSERT INTO bookings (user_id, car_id, start_date, end_date, pickup_location, drop_location, status, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
        try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, booking.getUserId());
            stmt.setInt(2, booking.getCarId());
            stmt.setDate(3, (Date) booking.getStartDate());
            stmt.setDate(4, (Date) booking.getEndDate());
            stmt.setString(5, booking.getPickupLocation());
            stmt.setString(6, booking.getDropLocation());
            stmt.setString(7, booking.getStatus());
            return stmt.executeUpdate() > 0;
        }
    }

    // Get user-specific bookings with car details
    public ArrayList<Booking> getMyBookings(int userId) throws SQLException {
        String query = "SELECT b.*, c.brand, c.model, c.type, c.price, c.image_path, u.full_name "
                + "FROM bookings b "
                + "INNER JOIN cars c ON b.car_id = c.id "
                + "INNER JOIN users u ON b.user_id = u.id "
                + "WHERE b.user_id = ?";

        ArrayList<Booking> bookings = new ArrayList<>();

        try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getInt("id"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setCarId(rs.getInt("car_id"));
                booking.setStartDate(rs.getDate("start_date"));
                booking.setEndDate(rs.getDate("end_date"));
                booking.setPickupLocation(rs.getString("pickup_location"));
                booking.setDropLocation(rs.getString("drop_location"));
                booking.setStatus(rs.getString("status"));
                booking.setFullname(rs.getString("full_name")); // <-- Full name from users table
                booking.setCarDetails(new Car(
                        rs.getString("image_path"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("type"),
                        rs.getString("price"),
                        0, "N/A", "N/A"
                ));

                bookings.add(booking);
            }
        }

        return bookings;
    }

    // Get all bookings (unchanged)
    public ArrayList<Car> getAllBookings() throws SQLException {
        String query = "SELECT c.brand, c.model, c.type, c.price, c.image_path "
                + "FROM cars c INNER JOIN bookings b ON c.id = b.car_id";
        ArrayList<Car> cars = new ArrayList<>();
        try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cars.add(new Car(
                        rs.getString("image_path"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("type"),
                        rs.getString("price"),
                        0, "N/A", "N/A"
                ));
            }
        }
        return cars;
    }

    // Delete a booking by ID
    public boolean deleteBooking(int bookingId) throws SQLException {
        String query = "DELETE FROM bookings WHERE id = ?";
        try (Connection conn = mysql.openConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            return stmt.executeUpdate() > 0;
        }
    }
}
