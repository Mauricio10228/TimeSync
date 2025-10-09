/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package modelo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mauri
 */
public class InscripcionTest {
    
    public InscripcionTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getClienteId method, of class Inscripcion.
     */
    @Test
    public void testGetClienteId() {
        System.out.println("getClienteId");
        Inscripcion instance = null;
        int expResult = 0;
        int result = instance.getClienteId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventoId method, of class Inscripcion.
     */
    @Test
    public void testGetEventoId() {
        System.out.println("getEventoId");
        Inscripcion instance = null;
        int expResult = 0;
        int result = instance.getEventoId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFechaInscripcion method, of class Inscripcion.
     */
    @Test
    public void testGetFechaInscripcion() {
        System.out.println("getFechaInscripcion");
        Inscripcion instance = null;
        String expResult = "";
        String result = instance.getFechaInscripcion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
