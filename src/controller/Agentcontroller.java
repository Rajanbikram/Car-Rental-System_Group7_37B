/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/*
 * Click nfs://NFS/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nfs://NFS/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Carrental_GroupG_37B.Addcar;
import Carrental_GroupG_37B.Agent;
import Carrental_GroupG_37B.ViewBooking;
import Carrental_GroupG_37B.ViewMyCar;
import Carrental_GroupG_37B.AgentProfile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Agentcontroller {
    private Agent agentForm;
    private int agentId;

    public Agentcontroller(Agent agentForm, int agentId) {
        this.agentForm = agentForm;
        this.agentId = agentId;
        agentForm.addEditListener(new addCar());
        initListeners();
    }

    public void open() {
        agentForm.setVisible(true);
    }

    private void initListeners() {
        agentForm.addManageListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewMyCar viewMyCar = new ViewMyCar();
                ViewMyCarsController controller = new ViewMyCarsController(viewMyCar, agentId);
                controller.open();
            }
        });

        agentForm.addViewListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewBooking viewBooking = new ViewBooking();
                ViewBookingController controller = new ViewBookingController(viewBooking);
                controller.open();
            }
        });

        agentForm.addProfileListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgentProfile view = new AgentProfile();
                AgentProfileController controller = new AgentProfileController(view, agentId);
                controller.open();
            }
        });
    }

    public void openAddCarDialog() {
    }

    private class addCar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Addcar addView = new Addcar();
            AddCarController c = new AddCarController(addView, agentId);
            c.open();
        }
    }
}