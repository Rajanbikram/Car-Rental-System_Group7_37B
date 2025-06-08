/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Model.Location;
import java.sql.*;
import java.util.*;
import Database.MySqlConnection;
/**
 *
 * @author rohin
 */
public class LocationDao {
    public List<Location> getAllLocations() {
        List<Location> list = new ArrayList<>();
        MySqlConnection db=new MySqlConnection();
        
        try (Connection conn=db.openConnection()) {
            String sql = "SELECT * FROM locations";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new Location(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("city")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
