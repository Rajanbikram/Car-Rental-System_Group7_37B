/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Carrental_GroupG_37B;
import View.BookACarPage;
import Controller.BookCarController;

/**
 *
 * @author Menuka
 */
public class java {
    public static void main(String args[]) {
        BookACarPage view = new BookACarPage();
        BookCarController controller = new BookCarController(view);
        controller.open();
    }
    
    
}
