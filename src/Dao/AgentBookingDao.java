/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Model.AgentBooking;
import java.sql.*;
import java.util.*;
import Database.MySqlConnection;
/**
 *
 * @author rohin
 */
public class AgentBookingDao {

    public static List<String[]> getBookingsForUser(String userName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<AgentBooking> getAllBookings() {
        List<AgentBooking> list = new ArrayList<>();
        MySqlConnection db=new MySqlConnection();
        
        try (Connection conn=db.openConnection()) {
            String sql = "SELECT * FROM bookings";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new AgentBooking(
                    rs.getInt("id"),
                    rs.getString("customer_name"),
                    rs.getString("car_id"),
                    rs.getString("location"),
                    rs.getString("booking_date")
                   
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
