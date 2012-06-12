/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kalle
 */
public class NodeTest {
    
    public NodeTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getLeft method, of class Node.
     */
    @Test
    public void testGetLeft() {
        System.out.println("getLeft");
        Node instance = null;
        Node expResult = null;
        Node result = instance.getLeft();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRight method, of class Node.
     */
    @Test
    public void testGetRight() {
        System.out.println("getRight");
        Node instance = null;
        Node expResult = null;
        Node result = instance.getRight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCount method, of class Node.
     */
    @Test
    public void testGetCount() {
        System.out.println("getCount");
        Node instance = null;
        int expResult = 0;
        int result = instance.getCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCode method, of class Node.
     */
    @Test
    public void testGetCode() {
        System.out.println("getCode");
        Node instance = null;
        byte expResult = 0;
        byte result = instance.getCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Node.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Node instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Node.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Object t = null;
        Node instance = null;
        int expResult = 0;
        int result = instance.compareTo(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
