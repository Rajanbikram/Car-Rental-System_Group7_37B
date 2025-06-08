/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;
import Model.Booking;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
/**
 *
 * @author rohin
 */
public class BookingTableView extends JFrame{
    public BookingTableView(List<Booking> bookings){
        setTitle("All Bookings");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        String[] columns={"Bookings_ID","customerName","Car","Date","Location"};
        String[][] data=new String[bookings.size()][columns.length];
        
        for(int i=0; i< bookings.size(); i++){
            Booking b=bookings.get(i);
            data[i][0]=String.valueOf(b.getId());
            data[i][1]=b.getCustomerName();
            data[i][2]= b.getCarId();
            data[i][3]=b.getBookingDate();
            data[i][4]=b.getLocation();
        }
        JTable table=new JTable(new DefaultTableModel(data, columns));
        JScrollPane scrollPane=new JScrollPane(table);
        add(scrollPane);
    }
    
}
