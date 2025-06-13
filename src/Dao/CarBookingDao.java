package Dao;

import DB.MysqlConnection;
import Model.BookingData;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarBookingDao {
    private static final MysqlConnection mysql = new MysqlConnection();

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
public static boolean bookCar(BookingData booking) {
    Connection conn = mysql.openConnection();
    String insertSql = "INSERT INTO car_bookings (customer_name, customer_email, booking_date, status, pickup_location, drop_location, phone_number) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
        insertStmt.setString(1, booking.getCustomerName());
        insertStmt.setString(2, booking.getCustomerEmail());
        insertStmt.setDate(3, Date.valueOf(booking.getBookingDate()));
        insertStmt.setString(4, "Confirmed");
        insertStmt.setString(5, booking.getPickupLocation());
        insertStmt.setString(6, booking.getDropLocation());
        insertStmt.setString(7, booking.getPhoneNumber());
        return insertStmt.executeUpdate() > 0;
    } catch (SQLException e) {
        Logger.getLogger(CarBookingDao.class.getName()).log(Level.SEVERE, null, e);
    } finally {
        mysql.closeConnection(conn);
    }
    return false;
}




}
