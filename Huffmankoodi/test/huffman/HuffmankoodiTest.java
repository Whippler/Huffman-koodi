/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.TreeMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.*;
import tietorakenteet.HuffmanNode;

/**
 *
 * @author lammenoj
 */
public class HuffmankoodiTest {

    private Huffmankoodi huff;
    private byte[] tavut = {(byte) 'l', (byte) 'o', (byte) 'l'};

    public HuffmankoodiTest() {
    }

    @Before
    public void setUp() {
        huff = new Huffmankoodi(tavut);
    }

    /**
     * Test of Huffman method, of class Huffmankoodi.
     */
    @Test
    public void testHuffman() {

        HuffmanNode node1 = new HuffmanNode(1, (byte) -128);
        HuffmanNode node2 = new HuffmanNode(1, (byte) 111);
        HuffmanNode node3 = new HuffmanNode(2, node1, node2);
        HuffmanNode node4 = new HuffmanNode(2, (byte) 108);
        HuffmanNode root = new HuffmanNode(4, node4, node3);

        HuffmanNode expResult = root;
        HuffmanNode result = huff.Huffman();
        assertEquals(expResult.getCount(), result.getCount());
        assertEquals(expResult.getLeft().getCount(), result.getLeft().getCount());
        assertEquals(expResult.getRight().getCount(), result.getRight().getCount());

    }

    /**
     * Test of getByteCount method, of class Huffmankoodi.
     */
    @Test
    public void testGetByteCount() {

        TreeMap expResult = new TreeMap<Byte, Integer>();
        expResult.put((byte) 'l', 2);
        expResult.put((byte) 'o', 1);
        expResult.put((byte) -128, 1);
        
        TreeMap result = huff.getByteCount();
        assertEquals(expResult, result);
    }
}
