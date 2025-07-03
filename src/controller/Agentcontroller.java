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
import Carrental_GroupG_37B.From_login1;
import Dao.Dao;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Agentcontroller {
    private Agent agentForm;
    private int agentId;
    private Dao dao = new Dao(); // DAO instance for login operations

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
        // Edit (Add Car) listener
        agentForm.addEditListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Addcar addView = new Addcar();
                AddCarController c = new AddCarController(addView, agentId);
                c.open();
            }
        });

        // Manage cars listener
        agentForm.addManageListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewMyCar viewMyCar = new ViewMyCar();
                ViewMyCarsController controller = null;
                try {
                    controller = new ViewMyCarsController(viewMyCar, agentId);
                } catch (SQLException ex) {
                    Logger.getLogger(Agentcontroller.class.getName()).log(Level.SEVERE, "Failed to load ViewMyCars", ex);
                }
                if (controller != null) controller.open();
            }
        });

        // View bookings listener
        agentForm.addViewListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewBooking viewBooking = new ViewBooking();
                ViewBookingController controller = new ViewBookingController(viewBooking);
                controller.open();
            }
        });

        // Profile listener
        agentForm.addProfileListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgentProfile view = new AgentProfile();
                AgentProfileController controller = new AgentProfileController(view, agentId);
                controller.open();
            }
        });

        // Logout listener
        agentForm.addLogoutButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
    }

    public void openAddCarDialog() {
        // Placeholder for add car dialog logic
    }

    private class addCar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Addcar addView = new Addcar();
            AddCarController c = new AddCarController(addView, agentId);
            c.open();
        }
    }

    private void logout() {
        // Dispose of the current agent view
        if (agentForm != null) {
            agentForm.dispose();
        }
        // Create a new login UI instance
        From_login1 newLoginUI = new From_login1();
        // Add ActionListener to the login button directly, matching ProfileDashboardController
        if (newLoginUI.lgnBtn != null) { // Debug check
            System.out.println("Attaching listener to lgnBtn");
            newLoginUI.lgnBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        performLogin(newLoginUI);
                        System.out.println("lgnBtn clicked"); // Debug confirmation
                    } catch (SQLException ex) {
                        Logger.getLogger(Agentcontroller.class.getName()).log(Level.SEVERE, "Login failed", ex);
                        JOptionPane.showMessageDialog(newLoginUI, "Error during login: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        } else {
            System.out.println("lgnBtn is null in From_login1");
        }
        // Make the login UI visible
        newLoginUI.setVisible(true);
    }

    private void performLogin(From_login1 loginView) throws SQLException {
        String username = loginView.uNameField.getText(); // Match field name from ProfileDashboardController
        char[] passwordChars = loginView.passwordField.getPassword();
        String password = new String(passwordChars);

        int userId = dao.logIn(username, password);
        if (userId > 0) {
            model.model user = dao.getUserById(userId);
            if (user != null) {
                String role = user.getRole().toLowerCase();
                // Redirect based on role
                if ("agent".equals(role)) {
                    Agentcontroller agentController = new Agentcontroller(new Agent(), userId);
                    agentController.open();
                } else if ("user".equals(role)) {
                    Dashboardcontroller dashboardController = new Dashboardcontroller(new Carrental_GroupG_37B.main_menu(), userId);
                    dashboardController.open();
                }
                // Dispose of login UI after successful login
                loginView.dispose();
            } else {
                JOptionPane.showMessageDialog(loginView, "User details not found!", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(loginView, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}