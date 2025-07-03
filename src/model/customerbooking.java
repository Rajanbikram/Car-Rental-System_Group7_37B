/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author rohin
 */
public class customerbooking {
    private int bookingId;
    private String customerName;
    private String carModel;
    private String bookingDate;
    
    public customerbooking(int bookingId, String customerName, String carModel, String bookingDate){
         this.bookingId = bookingId;
        this.customerName = customerName;
        this.carModel = carModel;
        this.bookingDate = bookingDate;
    }

    // Getters
    public int getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getBookingDate() {
        return bookingDate;
    
    }
}
