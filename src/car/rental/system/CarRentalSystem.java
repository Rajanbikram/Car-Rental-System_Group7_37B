/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package car.rental.system;

import Controller.BookCarController;
import View.BookaCar;

/**
 *
 * @author menuka
 */
public class CarRentalSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BookaCar viewFrame = new BookaCar();
        BookCarController controller = new BookCarController(viewFrame);
        controller.open();
    }

}
        
        
        // TODO code application logic here
    
    

