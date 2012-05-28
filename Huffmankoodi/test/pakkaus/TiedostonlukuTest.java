/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pakkaus;

import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Kalle
 */
public class TiedostonlukuTest {

    private File tiedosto;
    Tiedostonluku lukija;

    public TiedostonlukuTest() {

    }

    @Before
    public void setUp() {
        tiedosto = new File("testi.txt");
        lukija = new Tiedostonluku(tiedosto);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getTavut method, of class Tiedostonluku.
     */
    @Test
    public void testGetTavut() {
        byte[] expResult = {108, 111, 108};
        byte[] result = lukija.getTavut();
        for (int i = 0; i<expResult.length;i++)
        assertEquals(expResult[i], result[i]);
    }
}
