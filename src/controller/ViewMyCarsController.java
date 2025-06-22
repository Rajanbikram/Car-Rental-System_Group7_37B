package controller;






import Carrental_GroupG_37B.ViewMyCar;
import Dao.CarDao;
import model.Car;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

public class ViewMyCarsController {
    private final CarDao carDao = new CarDao();
    private final ViewMyCar view;

    public ViewMyCarsController(ViewMyCar view) {
        this.view = view;
        initController();
    }

    private void initController() {
        loadCarData();
        view.CloseButton.addActionListener(e -> view.dispose());
    }

    private void loadCarData() {
        ArrayList<Car> cars = carDao.getAllCars();
        view.listview.removeAll(); // Clear existing cards
        view.listview.setLayout(new GridLayout(0, 1, 10, 10)); // Vertical layout with gaps
        for (Car car : cars) {
            JPanel card = createCarCard(car);
            view.listview.add(card);
        }
        view.listview.revalidate();
        view.listview.repaint();
    }

    private JPanel createCarCard(Car car) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(135, 206, 235)));

        // Image
        JLabel imageLabel = new JLabel();
        if (car.getImagePath() != null && !car.getImagePath().isEmpty()) {
            ImageIcon imageIcon = new ImageIcon(car.getImagePath());
            imageLabel.setIcon(imageIcon);
        }
        card.add(imageLabel, BorderLayout.WEST);

        // Details Panel
        JPanel detailsPanel = new JPanel(new GridLayout(5, 1)); // Increased to 5 rows for booked status
        detailsPanel.add(new JLabel("Brand: " + car.getBrand()));
        detailsPanel.add(new JLabel("Model: " + car.getModel()));
        detailsPanel.add(new JLabel("Type: " + car.getType()));
        detailsPanel.add(new JLabel("Price: " + car.getPrice()));
        if (!car.isAvailable()) {
            detailsPanel.add(new JLabel("Status: Booked")); // Show booked status if not available
        }
        card.add(detailsPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonsPanel = new JPanel();
        JButton bookButton = new JButton("Book car");
        JButton deleteButton = new JButton("DELETE");

        // Set visibility of "Book car" button to off
        bookButton.setVisible(false); // Button visibility turned off

        buttonsPanel.add(bookButton);
        buttonsPanel.add(deleteButton);
        card.add(buttonsPanel, BorderLayout.EAST);

        return card;
    }

    public void open() {
        view.setVisible(true);
    }

    // New method to add booked car from Booking History
    public void addBookedCar(String imagePath, String brand, String model, String type, String price, boolean isAvailable) {
        Car car = new Car(imagePath, brand, model, type, price, 0, null, null); // Adjust constructor as per Car class
        car.setAvailable(isAvailable);
        JPanel card = createCarCard(car);
        view.listview.add(card);
        view.listview.revalidate();
        view.listview.repaint();
    }
}