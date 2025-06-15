/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author rohin
 */
public class DBConnection {
       public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/java_connection";
        String user = "root";
        String password = "Iamrohini@123"; 

        return DriverManager.getConnection(url, user, password);
    }
}
