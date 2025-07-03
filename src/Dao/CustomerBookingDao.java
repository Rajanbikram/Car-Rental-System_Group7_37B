/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Model.customerbooking;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author rohin
 */
public class CustomerBookingDao {
    private List<customerbooking> bookingList = new ArrayList<>();
    
    public void addBooking(customerbooking booking){
        bookingList.add(booking);
    }
    public List<customerbooking> getAllBoookings(){
        return bookingList;
    }

    public List<customerbooking> getAllBookings() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
