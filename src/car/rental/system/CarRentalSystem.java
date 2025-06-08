/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package car.rental.system;

import Controller.BookCarController;
import View.BookACarPage;

/**
 *
 * @author mamta sah
 */
public class CarRentalSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BookACarPage view = new BookACarPage();
        BookCarController controller = new BookCarController(view);
        controller.open();
    }
    }
        
        
        // TODO code application logic here
    
    

