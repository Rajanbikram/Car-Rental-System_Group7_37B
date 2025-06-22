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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Car;


public class BookingDao {
    private final MySqlConnection mysql = new MySqlConnection();

   

    public boolean addBooking(int userId, int carId, String startDate, String endDate, String pickup, String drop) throws SQLException {
        String query = "INSERT INTO bookings (user_id, car_id, start_date, end_date, pickup_location, drop_location, status, created_at) VALUES (?, ?, ?, ?, ?, ?, 'book', NOW())";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            stmt.setInt(2, carId);
            stmt.setString(3, startDate);
            stmt.setString(4, endDate);
            stmt.setString(5, pickup);
            stmt.setString(6, drop);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) mysql.closeConnection(conn);
        }
    }

    public ArrayList<Car> getMyBookings(int userId) throws SQLException {
        String query = "SELECT c.brand, c.model, c.type, c.price, c.image_path "
                + "FROM cars c "
                + "INNER JOIN bookings b ON c.id = b.car_id "
                + "WHERE b.user_id = ? ";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Car> cars = new ArrayList<>();

        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Car car = new Car(
                        rs.getString("image_path"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("type"),
                        rs.getString("price"),
                        0, // seatingCapacity (default 0, adjust if needed)
                        "N/A", // acAvailability (default, adjust if needed)
                        "N/A" // fuelType (default, adjust if needed)
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                /* ignore */ }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                mysql.closeConnection(conn);
            }
        }

        return cars; // Returns empty list if no cars found
    }

        public ArrayList<Car> getAllBookings() throws SQLException {
        String query = "SELECT c.brand, c.model, c.type, c.price, c.image_path "
                + "FROM cars c "
                + "INNER JOIN bookings b ON c.id = b.car_id ";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Car> cars = new ArrayList<>();

        try {
            conn = mysql.openConnection();
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Car car = new Car(
                        rs.getString("image_path"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("type"),
                        rs.getString("price"),
                        0, // seatingCapacity (default 0, adjust if needed)
                        "N/A", // acAvailability (default, adjust if needed)
                        "N/A" // fuelType (default, adjust if needed)
                );
                cars.add(car);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                /* ignore */ }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                mysql.closeConnection(conn);
            }
        }

        return cars; // Returns empty list if no cars found
    }

}