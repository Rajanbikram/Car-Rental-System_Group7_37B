/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author mamta sah
 */


import Carrental_GroupG_37B.ProfileDashboard;
import Dao.Dao;
import java.io.File;
import javax.swing.ImageIcon;

import Carrental_GroupG_37B.ProfileDashboard;
import Dao.Dao;
import java.io.File;
import javax.swing.ImageIcon;

public class ProfileDashboardController {
    private ProfileDashboard profileDashboard;
    private Dao dao = new Dao();
    private final int id;
   
    public ProfileDashboardController(ProfileDashboard profileDashboard,int id) {
        this.profileDashboard = profileDashboard;
        this.id = id;
        loadData();
    }
    
    public void open(){
        this.profileDashboard.setVisible(true);
    }
    
        public void close(){
        this.profileDashboard.setVisible(false);
    }

    private void loadData() {
        // Real login logic (replace with actual authentication)
        
        
            model.model user = dao.getUserById(id);
            if (user != null) {
                profileDashboard.Name.setText(user.getFullname());
                profileDashboard.Email.setText(user.getEmail());
                profileDashboard.UserId.setText("User ID: " + id);
                profileDashboard.Status.setText("Role: " + user.getRole()); // Display role
                profileDashboard.imagepath.setIcon(new ImageIcon(getClass().getResource("/Carrental_GroupG_37B/Luke.jpeg")));
            }
        }
    

    public void handleImageUpload(File selectedFile) {
        if (selectedFile != null) {
            profileDashboard.imagepath.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
            profileDashboard.imagepath.setText(selectedFile.getAbsolutePath());
        }
    }
}