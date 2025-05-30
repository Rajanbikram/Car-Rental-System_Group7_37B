/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Model.Log;
import java.sql.*;
import java.util.*;
import Database.MySqlConnection;
/**
 *
 * @author rohin
 */
public class LogDao {
    public List<Log> getAllLogs() {
        List<Log> list = new ArrayList<>();
        MySqlConnection db=new MySqlConnection();
        
        try (Connection conn=db.openConnection()) {
            String sql = "SELECT * FROM logs";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new Log(
                    rs.getInt("id"),
                    rs.getString("action"),
                    rs.getString("actor"),
                    rs.getTimestamp("timestamp").toLocalDateTime()
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
