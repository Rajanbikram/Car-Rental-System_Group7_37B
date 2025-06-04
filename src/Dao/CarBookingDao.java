package dao;

import database.MySqlConnection;
import model.BookingData;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarBookingDao {
    private static final MySqlConnection mysql = new MySqlConnection();

    public static boolean userBookedCar(int userId, String selectedCar) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM car_bookings WHERE user_id = ? AND selected_car = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, selectedCar);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // return true if booking exists
        } catch (SQLException e) {
            Logger.getLogger(CarBookingDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }

    public static boolean bookCar(int userId, BookingData booking) {
    Connection conn = mysql.openConnection();
    String insertSql = "INSERT INTO car_bookings (user_id, customer_name, customer_email, selected_car, booking_date, status) VALUES (?, ?, ?, ?, ?, ?)";

    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
        insertStmt.setInt(1, userId);
        insertStmt.setString(2, booking.getCustomerName());
        insertStmt.setString(3, booking.getCustomerEmail());
        insertStmt.setString(4, booking.getSelectedCar());
        insertStmt.setDate(5, Date.valueOf(booking.getBookingDate()));
        insertStmt.setString(6, "Confirmed");
        return insertStmt.executeUpdate() > 0;
    } catch (SQLException e) {
        Logger.getLogger(CarBookingDao.class.getName()).log(Level.SEVERE, null, e);
    } finally {
        mysql.closeConnection(conn);
    }

    return false;
}

}
