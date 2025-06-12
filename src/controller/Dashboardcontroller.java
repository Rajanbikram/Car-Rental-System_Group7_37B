/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.CarDao;
import Dao.Dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Carrental_GroupG_37B.*;
import javax.swing.Box;
import model.Car;

/**
 *
 * @author mamta sah
 */
public class Dashboardcontroller {
     
    private final Dao Dao = new Dao();
    private final CarDao cDao = new CarDao();
    private final main_menu userView;
    private int id;

    public Dashboardcontroller (main_menu userView,int id) throws SQLException {
        this.userView = userView;
        this.id = id;
        setValues();
       
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }
    
    public void setValues() throws SQLException{
        userView.getNote().setText("Hello " + Integer.toString(id));
        ArrayList<Car> cars = cDao.getAllCars();
        userView.listViewer.removeAll();
        for(Car car : cars){
            carView carpanel = new carView();
            new CarViewController(carpanel,car,id);
            userView.listViewer.add(carpanel);
            userView.listViewer.add(Box.createVerticalStrut(10));
        }
        userView.listViewer.revalidate();
        userView.listViewer.repaint();
         
         
         
    }

}
