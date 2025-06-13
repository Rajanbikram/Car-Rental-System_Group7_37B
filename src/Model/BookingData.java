
package Model;
import java.time.LocalDate;

public class BookingData {
    private String customerName;
    private String customerEmail;
    private LocalDate bookingDate;
    private String pickupLocation;
    private String dropLocation;
    private String phoneNumber;
    
    public BookingData(String customerName, String customerEmail, LocalDate bookingDate, String pickupLocation,
            String dropLocation, String phoneNumber) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.bookingDate = bookingDate;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(String dropLocation) {
        this.dropLocation = dropLocation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
