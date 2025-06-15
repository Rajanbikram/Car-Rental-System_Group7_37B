/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import Database.DBConnection;
/**
 *
 * @author rohin
 */
public class TableGenerator {
    public static JTable createTableFromQuery(String query){
        JTable table=new JTable();
        
        try{
            Connection conn=null;
            try{
                conn=DBConnection.getConnection();
        } catch(SQLException ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection failed","Error",JOptionPane.ERROR_MESSAGE);
            return table;
        }
        PreparedStatement ps=conn.prepareStatement(query);
        ResultSet rs=ps.executeQuery();
        ResultSetMetaData metaData=rs.getMetaData();
        
        int columnCount = metaData.getColumnCount();
        String[] columnNames=new String[columnCount];
        for(int i=0; i< columnCount; i++){
            columnNames[i]=metaData.getColumnName(i+1);
        }
        
        DefaultTableModel model=new DefaultTableModel(columnNames, 0);
        while(rs.next()){
            Object[] row=new Object[columnCount];
            for(int i=0; i< columnCount; i++){
                row[i]=rs.getObject(i+1);
            }
            model.addRow(row);
        }
        table.setModel(model);
        
        rs.close();
        ps.close();
        conn.close();
        
    }catch (Exception e){
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error fetching data!", "Error",JOptionPane.ERROR_MESSAGE);
    
    }
    return table;
  }
}

      
    
