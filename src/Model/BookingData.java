import java.time.LocalDate;

public class BookingData {
    private String customerName;
    private String customerEmail;
    private LocalDate bookingDate;
    private String selectedCar;

    // Constructor
    public BookingData(String customerName, String customerEmail, LocalDate bookingDate, String selectedCar) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.bookingDate = bookingDate;
        this.selectedCar = selectedCar;
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

    public String getSelectedCar() {
        return selectedCar;
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

    public void setSelectedCar(String selectedCar) {
        this.selectedCar = selectedCar;
    }
}
