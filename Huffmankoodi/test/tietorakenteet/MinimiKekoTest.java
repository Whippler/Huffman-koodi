package tietorakenteet;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kalle
 */
public class MinimiKekoTest {

    private MinimiKeko keko;

    public MinimiKekoTest() {
    }

    @Before
    public void setUp() {
        keko = new MinimiKeko(10);
        Node node1 = new Node(6, (byte) 'g');
        Node node2 = new Node(3, (byte) 'h');
        keko.add(node2);
        keko.add(node1);
    }

    /**
     * Test of poll method, of class MinimiKeko.
     */
    @Test
    public void testPoll() {
        Node expResult = new Node(3, (byte) 'h');
        Node result = keko.poll();
        assertEquals(expResult.getCode(), result.getCode());
        assertEquals(expResult.getCount(), result.getCount());
    }

    /**
     * Test of add method, of class MinimiKeko.
     */
    @Test
    public void testAdd() {

        Node expResult = new Node(1, (byte) 'h');
        keko.add(expResult);
        Node result = keko.poll();
        assertEquals(expResult.getCode(), result.getCode());
        assertEquals(expResult.getCount(), result.getCount());
    }

    /**
     * Test of size method, of class MinimiKeko.
     */
    @Test
    public void testSize() {
        keko.add(new Node(1, (byte) '3'));
        int expResult = 3;
        int result = keko.size();
        assertEquals(expResult, result);
        keko.poll();
        assertEquals(2, keko.size());
    }
}
