/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pakkaus;

import huffman.Huffmankoodi;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import tietorakenteet.Node;

/**
 *
 * @author Kalle
 */
public class HuffmankoodiTest {
    
    public HuffmankoodiTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void testHuffman() {
        System.out.println("Huffman");
        Huffmankoodi instance = null;
        Node expResult = null;
        Node result = instance.Huffman();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
