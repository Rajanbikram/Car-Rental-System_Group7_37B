
package Model;
import java.time.LocalDate;

public class BookingData {
    private String customerName;
    private String customerEmail;
    private LocalDate bookingDate;
    

    // Constructor
    public BookingData(String customerName, String customerEmail, LocalDate bookingDate) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.bookingDate = bookingDate;
       
    }

    // Getters
    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

   

    // Setters
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

}
