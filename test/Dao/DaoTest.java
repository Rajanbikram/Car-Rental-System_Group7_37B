/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Dao;

import model.model;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mamta sah
 */
public class DaoTest {
    
    public DaoTest() {
    }
    
  
    /**
     * Test of register method, of class Dao.
     */
    @Test
    public void testRegister() {
        System.out.println("register");
        model user = null;
        Dao instance = new Dao();
        boolean expResult = false;
        boolean result = instance.register(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkUser method, of class Dao.
     */
    @Test
    public void testCheckUser() {
        System.out.println("checkUser");
        String username = "";
        String email = "";
        Dao instance = new Dao();
        String expResult = "";
        String result = instance.checkUser(username, email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logIn method, of class Dao.
     */
    @Test
    public void testLogIn() {
        System.out.println("logIn");
        String username = "";
        String password = "";
        Dao instance = new Dao();
        int expResult = 0;
        int result = instance.logIn(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
