/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;





import model.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import database.MySqlConnection;

public class Dao {
    MySqlConnection mysql = new MySqlConnection();

    public boolean register(model user) {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO users (full_name, username, email, role, password) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getFullname());
            pstmt.setString(2, user.getUsername());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getRole());
            pstmt.setString(5, user.getPassword());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return false;
    }

    public String checkUser(String username, String email) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users where email = ? or username = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, username);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                return result.getString("role");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return "null";
    }

    public int logIn(String username, String password) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users where username = ? AND password = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                return result.getInt("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return 0;
    }

    public model getUserById(int id) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                return new model(
                    result.getString("full_name"),
                    result.getString("username"),
                    result.getString("email"),
                    result.getString("role"),
                    result.getString("password")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }

    public List<model> getAllUsers() {
        List<model> users = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM users";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet result = pstmt.executeQuery()) {
            while (result.next()) {
                users.add(new model(
                    result.getString("full_name"),
                    result.getString("username"),
                    result.getString("email"),
                    result.getString("role"),
                    result.getString("password")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysql.closeConnection(conn);
        }
        return users;
    }
}