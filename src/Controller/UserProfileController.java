/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Dao.BookingDao;
import javax.swing.table.DefaultTableModel;
import java.util.List;


import java.util.List;
/**
 *
 * @author rohin
 */
public class UserProfileController {
    public static void loadUserBookings(String userName, DefaultTableModel tableModel){
        try{
            tableModel.setRowCount(0);
            
            List<String[]>bookings=BookingDao.getBookingsForUser(userName);
            
            for(String[] booking : bookings){
                tableModel.addRow(booking);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
