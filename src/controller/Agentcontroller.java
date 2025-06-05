/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Carrental_GroupG_37B.Addcar;
import Carrental_GroupG_37B.Agent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author mamta sah
 */
public class Agentcontroller {

    private Agent agentForm;
    private int agentId;

    public Agentcontroller(Agent agentForm, int agentId) {
        this.agentForm = agentForm;
        this.agentId = agentId;
        agentForm.addEditListener(new addCar());
        initListeners(); // Initialize listeners
    }

    public void open() {
        agentForm.setVisible(true);
    }

    private void initListeners() {
        agentForm.addEditListener(e -> openAddCarDialog()); // Listener for Edit button
    }

    public void openAddCarDialog() {
       // Addcar addCarDialog = new Addcar(agentForm, true); // Modal dialog
        //addCarDialog.setLocationRelativeTo(agentForm); // Center dialog
       // addCarDialog.setVisible(true);//
    }

    private class addCar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Addcar addView = new Addcar();
            AddCarController c = new AddCarController(addView,agentId);
            c.open();
        }

    }


}

