/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author mamta sah
 */
import Carrental_GroupG_37B.Agent;
import Carrental_GroupG_37B.ProfileDashboard;
import Carrental_GroupG_37B.From_login1;
import Dao.Dao;
import java.io.File;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProfileDashboardController {
    private ProfileDashboard profileDashboard;
    private Dao dao = new Dao();
    private final int id;
    private From_login1 loginUI; // Reference to login UI

    public ProfileDashboardController(ProfileDashboard profileDashboard, int id) {
        this.profileDashboard = profileDashboard;
        this.id = id;
        this.loginUI = new From_login1(); // Initialize login UI
        loadData();
        initListeners(); // Add listeners for buttons
    }
    
    public void open() {
        this.profileDashboard.setVisible(true);
    }
    
    public void close() {
        this.profileDashboard.setVisible(false);
    }

    private void loadData() {
        model.model user = dao.getUserById(id);
        if (user != null) {
            profileDashboard.Name.setText(user.getFullname());
            profileDashboard.Email.setText(user.getEmail());
            profileDashboard.UserId.setText("User ID: " + id);
            profileDashboard.Status.setText("Role: " + user.getRole()); // Display role
            profileDashboard.imagepath.setIcon(new ImageIcon(getClass().getResource("/Carrental_GroupG_37B/Luke.jpeg")));
        } else {
            System.out.println("User not found for ID: " + id); // Debug output
        }
    }

    public void handleImageUpload(File selectedFile) {
        if (selectedFile != null) {
            profileDashboard.imagepath.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
            profileDashboard.imagepath.setText(""); // Clear text to avoid overlap
        }
    }

    private void initListeners() {
        // Assuming there's a logout button (e.g., profileDashboard.logoutButton)
        profileDashboard.addLogoutButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
    }

    private void logout() {
        // Dispose of the current profile view
        if (profileDashboard != null) {
            profileDashboard.dispose();
        }
        // Create a new login UI instance
        From_login1 newLoginUI = new From_login1();
        // Add ActionListener to the login button directly
        if (newLoginUI.lgnBtn != null) {
            System.out.println("Attaching listener to lgnBtn in ProfileDashboard");
            newLoginUI.lgnBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        performLogin(newLoginUI);
                        System.out.println("lgnBtn clicked, userId: " + dao.logIn(newLoginUI.uNameField.getText(), new String(newLoginUI.passwordField.getPassword()))); // Debug
                    } catch (SQLException ex) {
                        Logger.getLogger(ProfileDashboardController.class.getName()).log(Level.SEVERE, "Login failed", ex);
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
        String username = loginView.uNameField.getText();
        char[] passwordChars = loginView.passwordField.getPassword();
        String password = new String(passwordChars);

        int userId = dao.logIn(username, password);
        System.out.println("Login attempt - userId: " + userId + ", username: " + username); // Debug
        if (userId > 0) {
            model.model user = dao.getUserById(userId);
            if (user != null) {
                String role = user.getRole().toLowerCase();
                System.out.println("Role detected: " + role); // Debug
                // Redirect based on role
                if ("agent".equals(role)) {
                    Agentcontroller agentController = new Agentcontroller(new Agent(), userId);
                    agentController.open();
                } else if ("user".equals(role)) {
                    try {
                        Dashboardcontroller dashboardController = new Dashboardcontroller(new Carrental_GroupG_37B.main_menu(), userId);
                        dashboardController.open();
                    } catch (Exception ex) {
                        Logger.getLogger(ProfileDashboardController.class.getName()).log(Level.SEVERE, "Dashboard initialization failed", ex);
                        JOptionPane.showMessageDialog(loginView, "Failed to load user dashboard: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
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