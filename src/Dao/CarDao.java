/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
/**
 * @author mamta sah
 */


/**
 * @author mamta sah
 */

import model.Car;
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    private final MySqlConnection dbConnection;

    public CarDao() {
        this.dbConnection = new MySqlConnection();
    }

    // Add a new car to the database
    public boolean addCar(Car car) throws SQLException {
        String query = "INSERT INTO cars (image_path, brand, model, type, price, available, seating_capacity, ac_availability, fuel_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dbConnection.openConnection();
            stmt = conn.prepareStatement(query);
            stmt.setString(1, car.getImagePath());
            stmt.setString(2, car.getBrand());
            stmt.setString(3, car.getModel());
            stmt.setString(4, car.getType());
            stmt.setString(5, car.getPrice());
            stmt.setBoolean(6, car.isAvailable());
            stmt.setInt(7, car.getSeatingCapacity());         // New field
            stmt.setString(8, car.getAcAvailability());      // New field
            stmt.setString(9, car.getFuelType());            // New field
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new SQLException("Error adding car to database: " + e.getMessage(), e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Error closing statement: " + e.getMessage());
                }
            }
            if (conn != null) dbConnection.closeConnection(conn);
        }
        return false;
    }
    
    public ArrayList<Car> getAllCars() {
        ArrayList<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dbConnection.openConnection();
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Car car = new Car(
                        rs.getString("image_path"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("type"),
                        rs.getString("price"),
                        rs.getInt("seating_capacity"),         // New field
                        rs.getString("ac_availability"),      // New field
                        rs.getString("fuel_type")             // New field
                );
                car.setId(rs.getInt("id"));
                car.setAvailable(rs.getBoolean("available"));
                cars.add(car);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    // New method to get car by ID for compare functionality
    public Car getCarById(int id) throws SQLException {
        String sql = "SELECT * FROM cars WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Car car = null;

        try {
            conn = dbConnection.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                car = new Car(
                    rs.getString("image_path"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getString("type"),
                    rs.getString("price"),
                    rs.getInt("seating_capacity"),
                    rs.getString("ac_availability"),
                    rs.getString("fuel_type")
                );
                car.setId(rs.getInt("id"));
                car.setAvailable(rs.getBoolean("available"));
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching car by ID: " + e.getMessage(), e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Error closing result set: " + e.getMessage());
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Error closing statement: " + e.getMessage());
                }
            }
            if (conn != null) dbConnection.closeConnection(conn);
        }
        return car;
    }
}