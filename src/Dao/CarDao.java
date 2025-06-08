/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Model.Car;
import java.sql.*;
import java.util.*;
import Database.MySqlConnection;
/**
 *
 * @author rohin
 */
public class CarDao {
    public List<Car> getAllCars() {
        List<Car> list = new ArrayList<>();
        MySqlConnection db=new MySqlConnection();
        
        try(Connection conn=db.openConnection()){
            String sql = "SELECT * FROM cars";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new Car(
                    rs.getInt("id"),
                    rs.getString("model"),
                    rs.getString("brand"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
