/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;
import Model.Location;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
/**
 *
 * @author rohin
 */
public class LocationTableView extends JFrame {
    public LocationTableView(List<Location> locations) {
        setTitle("All Locations");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"ID", "City", "Name"};
        String[][] data = new String[locations.size()][columns.length];

        for (int i = 0; i < locations.size(); i++) {
            Location l = locations.get(i);
            data[i][0] = String.valueOf(l.getId());
            data[i][1] = l.getCity();
            data[i][2] = l.getName();
        }

        JTable table = new JTable(new DefaultTableModel(data, columns));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
}
