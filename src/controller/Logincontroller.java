/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;



import Dao.Dao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Carrental_GroupG_37B.Agent;
import Carrental_GroupG_37B.From_login1;
import Carrental_GroupG_37B.main_menu;
import javax.swing.JOptionPane;

/**
 *
 * @author mamta sah
 */
public class Logincontroller {
  
    private final Dao Dao = new Dao();
    private final From_login1 userView;

    public Logincontroller(From_login1 userView) {
        this.userView = userView;
        userView.addAddUserListener(new AddUserListener());
    }

    public void open() {
        this.userView.setVisible(true);
    }

    public void close() {
        this.userView.dispose();
    }

    class AddUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String UserName = userView.getUserName();
                String Password = userView.getPassword();
         
                if (UserName.isEmpty()) {
                    JOptionPane.showMessageDialog(userView, "Enter UserName");
                    return;
                }
                if (Password.isEmpty()) {
                    JOptionPane.showMessageDialog(userView, "Enter Password");
                    return;
                }
                String role = Dao.checkUser(UserName, "a"); // Assuming "a" is a placeholder for email
                if (role == null || role.equals("null")) {
                    JOptionPane.showMessageDialog(null, "No Account exists");
                    return;
                }
            
                int id = Dao.logIn(UserName, Password);
                System.out.println("Login ID returned for " + (role.equals("Agent") ? "Agent" : "User") + ": " + id); // Enhanced debug log
                if (id == 0) {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials");
                    return;
                }
            
                if (role.equals("User")) {
                    main_menu lgnForm = new main_menu();
                    Dashboardcontroller c = new Dashboardcontroller(lgnForm, id);
                    c.open();
                    close();
                } else if (role.equals("Agent")) {
                    Agent lgnForm = new Agent();
                    Agentcontroller c = new Agentcontroller(lgnForm, id);
                    c.open();
                    close();
                }

            } catch (Exception ex) {
                System.out.println("Error during login: " + ex.getMessage());
            }
        }
    }
}



