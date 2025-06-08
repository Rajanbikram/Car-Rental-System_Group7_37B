/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;
import Model.Log;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
/**
 *
 * @author rohin
 */
public class LogTableView extends JFrame {
    public LogTableView(List<Log> logs) {
        setTitle("System Logs");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"ID", "Action","Actor", "Timestamp"};
        String[][] data = new String[logs.size()][columns.length];

        for (int i = 0; i < logs.size(); i++) {
            Log log = logs.get(i);
            data[i][0] = String.valueOf(log.getId());
            data[i][1] = log.getAction();
            data[i][2] = log.getActor();  
            data[i][3] = String.valueOf(log.getTimestamp());
        }

        JTable table = new JTable(new DefaultTableModel(data, columns));
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }
    
}
