/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;
import Model.Car;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
/**
 *
 * @author rohin
 */
public class CarTableView extends JFrame{
   
    public CarTableView(List<Car> cars) {
        setTitle("All Cars");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"ID", "Model", "Brand", "Status"};
        String[][] data = new String[cars.size()][columns.length];

        for (int i = 0; i < cars.size(); i++) {
            Car c = cars.get(i);
            data[i][0] = String.valueOf(c.getId());
            data[i][1] = c.getModel();
            data[i][2] = c.getBrand();
            data[i][3] = cars.get(i).getStatus();
        }

        JTable table = new JTable(new DefaultTableModel(data, columns));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    
}
