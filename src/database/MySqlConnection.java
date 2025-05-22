/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.sql.*;

/**
 *
 * @author thismac
 */
public class MySqlConnection implements database{

    @Override
    public Connection openConnection() {
       try{
            String username = "root";
            String password = "menuka";
            String database = "car_rental_db";
            Connection connection;

            connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/" + database,username,password
            );
            
            if(connection == null){
                System.out.print("Couldnot connect database");
            }
            else{
                System.out.println("Connection Successful");
            }
            return connection;
       }
       catch(Exception e){
           System.out.println(e);
           return null;   
       }
       
       
    }

    @Override
    public void closeConnection(Connection conn) {
        try{
            if(conn != null && !conn.isClosed()){
            conn.close();
            System.out.println("Connection Close");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }

    public ResultSet RunQuery(Connection conn, String query) {
        
        try{
            Statement stmp = conn.createStatement();
            ResultSet result = stmp.executeQuery(query);
            return result;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        
        }

    @Override
    public int executeUpdate(Connection conn, String query) {
        
       try{
            Statement stmp = conn.createStatement();
            int result = stmp.executeUpdate(query);
            return result;
        }catch(Exception e){
            System.out.println(e);
            return -1;
        }
        
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}