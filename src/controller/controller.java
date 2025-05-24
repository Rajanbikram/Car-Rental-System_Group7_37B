
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Dao.Dao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Carrental_GroupG_37B.Addcar;
import Carrental_GroupG_37B.From_login1;
import Carrental_GroupG_37B.Register;
import javax.swing.JOptionPane;
import model.model;

/**
 *
 * @author mamta sah
 */
public class controller {

    public static void addCar(Addcar aThis, String text, String text0, String text1, String text2, String text3) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private final Dao Dao = new Dao();
private final Register userView;

public controller(Register userView) {
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
            
            String fullname= userView.getFullname();
            String Email=userView.getEmail();
            String UserName=userView.getUserName();
            String role = userView.getRole();
            String Password= userView.getPassword();
            String ConfrimPassword=userView.getConfirmpassword();
            
            if(! Password.equals(ConfrimPassword)){
                JOptionPane.showMessageDialog(null,"Password Mismatch!");
                return;
            }
            
            model user = new model(fullname,UserName, Email,role, Password);
            String r = Dao.checkUser(UserName,Email);
            if(r.equals("null")){
                if(Dao.register(user)){
                    JOptionPane.showMessageDialog(null, "Registration successful");
                    From_login1 lgnForm = new From_login1();
                    Logincontroller c = new Logincontroller(lgnForm);
                    c.open();
                    close();
                }else{
                 JOptionPane.showMessageDialog(null, "Registration Unsuccessful");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Username or email already exist");
            }
            
        } catch (Exception ex) {
            System.out.println("Error adding user: " + ex.getMessage());
        }
    }
}
}
