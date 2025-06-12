/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Carrental_GroupG_37B;

import controller.Logincontroller;


/**
 *
 * @author mamta sah
 */
public class Carrental_GroupG_37B {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       From_login1 lgnForm = new From_login1();
        Logincontroller c = new Logincontroller(lgnForm);
        c.open();
        
        
    }
}