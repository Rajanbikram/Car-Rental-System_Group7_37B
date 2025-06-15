/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
/**
 *
 * @author rohin
 */
public class AgentBooking {
    private int id;
    private String customerName;
    private String CarId;
    private String location;
    private String bookingDate;
   

    public AgentBooking(int id, String customerName,String carId,String location, String bookingDate) {
        this.id = id;
        this.customerName = customerName;
        this.CarId = carId;
        this.location = location;
        this.bookingDate = bookingDate;
        
    } 
    public int getId(){
        return id;
    }
    public String getCustomerName(){
        return customerName;
    }
    public String getCarId(){
        return CarId;
    }
    public String getBookingDate(){
        return bookingDate;
    }
    public String getLocation(){
        return location;
    }   
}
