/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pakkaus;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Kalle
 */
public class TiedostonlukuTest {
    
    public TiedostonlukuTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTavut method, of class Tiedostonluku.
     */
    @Test
    public void testGetTavut() {
        System.out.println("getTavut");
        Tiedostonluku instance = null;
        byte[] expResult = null;
        byte[] result = instance.getTavut();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
