/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Dao.CustomerBookingDao;
import Model.customerbooking;
import java.util.List;
/**
 *
 * @author rohin
 */
public class CustomerbookingController {
     private CustomerBookingDao bookingDao = new CustomerBookingDao();

    public void loadDummyData() {
        bookingDao.addBooking(new customerbooking(1, "Sita Rai", "Toyota Fortuner", "2025-06-17"));
        bookingDao.addBooking(new customerbooking(2, "Hari Gurung", "Hyundai Creta", "2025-06-15"));
    }

    public List<customerbooking> getAllBookings() {
        return bookingDao.getAllBookings();
    }
}
