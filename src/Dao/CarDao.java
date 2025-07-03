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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    private final MySqlConnection dbConnection;

    public CarDao() {
        this.dbConnection = new MySqlConnection();
    }

    // Add a new car to the database with agent ID and timestamp
    public boolean addCar(Car car, int addedByAgentId) throws SQLException {
        String query = "INSERT INTO cars (image_path, brand, model, type, price, available, seating_capacity, ac_availability, fuel_type, added_by_agent_id, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            stmt.setInt(7, car.getSeatingCapacity());
            stmt.setString(8, car.getAcAvailability());
            stmt.setString(9, car.getFuelType());
            stmt.setInt(10, addedByAgentId);
            stmt.setTimestamp(11, new Timestamp(System.currentTimeMillis()));
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new SQLException("Error adding car to database: " + e.getMessage(), e);
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) dbConnection.closeConnection(conn);
        }
    }
    
    // Fetch all cars (unfiltered)
    public ArrayList<Car> getAllCars() throws SQLException {
        ArrayList<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars ORDER BY created_at DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnection.openConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Car car = new Car(
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
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching all cars: " + e.getMessage(), e);
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) dbConnection.closeConnection(conn);
        }
        return cars;
    }

    // Fetch cars filtered by agent ID
    public ArrayList<Car> getAllCars(int agentId) throws SQLException {
        ArrayList<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE added_by_agent_id = ? ORDER BY created_at DESC";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnection.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, agentId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Car car = new Car(
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
                cars.add(car);
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching cars for agent ID " + agentId + ": " + e.getMessage(), e);
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) dbConnection.closeConnection(conn);
        }
        return cars;
    }

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
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) dbConnection.closeConnection(conn);
        }
        return car;
    }

    // Delete a car by ID
    public boolean deleteCar(int carId) throws SQLException {
        String sql = "DELETE FROM cars WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dbConnection.openConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, carId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new SQLException("Error deleting car with ID " + carId + ": " + e.getMessage(), e);
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) dbConnection.closeConnection(conn);
        }
    }
}