/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Menuka
 */
public class Registrationdesign {
    public class RegistrationPageData {

    private String fullName;

    private String emailAddress;

    private String username;

    private String password;

    private String confirmPassword;
 
    // Constructor

    public RegistrationPageData(String fullName, String emailAddress, String username, String password, String confirmPassword) {

        this.fullName = fullName;

        this.emailAddress = emailAddress;

        this.username = username;

        this.password = password;

        this.confirmPassword = confirmPassword;

    }
 
    // Getters and Setters

    public String getFullName() {

        return fullName;

    }

    public void setFullName(String fullName) {

        this.fullName = fullName;

    }
 
    public String getEmailAddress() {

        return emailAddress;

    }

    public void setEmailAddress(String emailAddress) {

        this.emailAddress = emailAddress;

    }
 
    public String getUsername() {

        return username;

    }

    public void setUsername(String username) {

        this.username = username;

    }
 
    public String getPassword() {

        return password;

    }

    public void setPassword(String password) {

        this.password = password;

    }
 
    public String getConfirmPassword() {

        return confirmPassword;

    }

    public void setConfirmPassword(String confirmPassword) {

        this.confirmPassword = confirmPassword;

    }

}  
 
}
