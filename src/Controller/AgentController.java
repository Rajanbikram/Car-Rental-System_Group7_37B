/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.BookingDao;
import Dao.CarDao;
import Dao.LocationDao;
import Dao.LogDao;
import Model.Booking;
import Model.Car;
import Model.Location;
import Model.Log;
import View.BookingTableView;
import View.CarTableView;
import View.LocationTableView;
import View.LogTableView;
import java.util.List;
/**
 *
 * @author rohin
 */
//View All bookings
public class AgentController {
    
    private BookingDao bookingDao;
    private CarDao carDao;
    private LocationDao locationDao;
    private LogDao logDao;

    public AgentController() {
        bookingDao = new BookingDao();
        carDao = new CarDao();
        locationDao = new LocationDao();
        logDao = new LogDao();
    }
    public List<Booking> getAllBookings(){
        return bookingDao.getAllBookings();
    }

    // Show all bookings
    public void showAllBookings() {
        List<Booking> bookings = bookingDao.getAllBookings();
        new BookingTableView(bookings).setVisible(true);
    }

    // Show all cars
    public void showAllCars() {
        List<Car> cars = carDao.getAllCars();
        new CarTableView(cars).setVisible(true);
    }

    // Show all locations
    public void showAllLocations() {
        List<Location> locations = locationDao.getAllLocations();
        new LocationTableView(locations).setVisible(true);
    }

    // Show all logs
    public void showAllLogs() {
        List<Log> logs = logDao.getAllLogs();
        new LogTableView(logs).setVisible(true);
    }
}

    


