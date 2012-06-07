/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.TreeMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.*;
import tietorakenteet.Node;

/**
 *
 * @author lammenoj
 */
public class HuffmankoodiTest {
    
    public HuffmankoodiTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of Huffman method, of class Huffmankoodi.
     */
    @Test
    public void testHuffman_0args() {
        System.out.println("Huffman");
        Huffmankoodi instance = new Huffmankoodi();
        Node expResult = null;
        Node result = instance.Huffman();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Huffman method, of class Huffmankoodi.
     */
    @Test
    public void testHuffman_TreeMap() {
        System.out.println("Huffman");
        TreeMap<Byte, Integer> byteCount = null;
        Huffmankoodi instance = new Huffmankoodi();
        Node expResult = null;
        Node result = instance.Huffman(byteCount);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByteCount method, of class Huffmankoodi.
     */
    @Test
    public void testGetByteCount() {
        System.out.println("getByteCount");
        Huffmankoodi instance = new Huffmankoodi();
        TreeMap expResult = null;
        TreeMap result = instance.getByteCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
