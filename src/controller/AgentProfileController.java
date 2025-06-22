/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author mamta sah
 */


import Dao.Dao;
import java.io.File;
import javax.swing.ImageIcon;
import Carrental_GroupG_37B.AgentProfile;

public class AgentProfileController {
    private AgentProfile agentProfile;
    private Dao dao = new Dao();
    private final int id;

    public AgentProfileController(AgentProfile agentProfile, int id) {
        this.agentProfile = agentProfile;
        this.id = id;
        loadData();
    }

    public void open() {
        this.agentProfile.setVisible(true);
    }

    public void close() {
        this.agentProfile.setVisible(false);
    }

    private void loadData() {
        model.model user = dao.getUserById(id);
        if (user != null) {
            agentProfile.Name.setText(user.getFullname());
            agentProfile.Email.setText(user.getEmail());
            agentProfile.AgentID.setText("Agent ID: " + id);
            agentProfile.Status.setText("Role: " + user.getRole()); // Display role
            agentProfile.Imagepath.setIcon(new ImageIcon(getClass().getResource("/Carrental_GroupG_37B/Luke.jpeg")));
        }
    }

    public void handleImageUpload(File selectedFile) {
        if (selectedFile != null) {
            agentProfile.Imagepath.setIcon(new ImageIcon(selectedFile.getAbsolutePath()));
            agentProfile.Imagepath.setText(selectedFile.getAbsolutePath());
        }
    }
}