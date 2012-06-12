/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kalle
 */
public class NodeTest {

    private Node node1;
    private Node node2;
    private Node root;

    public NodeTest() {
    }

    @Before
    public void setUp() {
        node1 = new Node(4, (byte) 'f');
        node2 = new Node(2, (byte) 'u');
        root = new Node(6, node1, node2);
    }

    /**
     * Test of getLeft method, of class Node.
     */
    @Test
    public void testGetLeft() {
        Node expResult = node1;
        Node result = root.getLeft();
        assertEquals(expResult.getCode(), result.getCode());
        assertEquals(expResult.getCount(), result.getCount());
    }

    /**
     * Test of getRight method, of class Node.
     */
    @Test
    public void testGetRight() {
        Node expResult = node2;
        Node result = root.getRight();
        assertEquals(expResult.getCode(), result.getCode());
        assertEquals(expResult.getCount(), result.getCount());
    }

    /**
     * Test of getCount method, of class Node.
     */
    @Test
    public void testGetCount() {
        int expResult = 6;
        int result = root.getCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCode method, of class Node.
     */
    @Test
    public void testGetCode() {
        byte expResult = (byte) 'f';
        byte result = node1.getCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Node.
     */
    @Test
    public void testToString() {
        String expResult = "Node[6|0, Node[4|102, null, null], Node[2|117, null, null]]";
        String result = root.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Node.
     */
    @Test
    public void testCompareTo() {
        int expResult = 1;
        int result = node1.compareTo(node2);
        assertEquals(expResult, result);

        expResult = -1;
        result = node2.compareTo(node1);
        assertEquals(expResult, result);

        expResult = 0;
        result = node1.compareTo(node1);
        assertEquals(expResult, result);
    }
}
