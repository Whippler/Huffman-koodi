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
        HuffmanNode node1 = new HuffmanNode(6, (byte) 'g');
        HuffmanNode node2 = new HuffmanNode(3, (byte) 'h');
        keko.add(node2);
        keko.add(node1);
    }

    /**
     * Test of poll method, of class MinimiKeko.
     */
    @Test
    public void testPoll() {
        HuffmanNode expResult = new HuffmanNode(3, (byte) 'h');
        HuffmanNode result = keko.poll();
        assertEquals(expResult.getCode(), result.getCode());
        assertEquals(expResult.getCount(), result.getCount());
    }

    /**
     * Test of add method, of class MinimiKeko.
     */
    @Test
    public void testAdd() {

        HuffmanNode expResult = new HuffmanNode(1, (byte) 'h');
        keko.add(expResult);
        HuffmanNode result = keko.poll();
        assertEquals(expResult.getCode(), result.getCode());
        assertEquals(expResult.getCount(), result.getCount());
    }

    /**
     * Test of size method, of class MinimiKeko.
     */
    @Test
    public void testSize() {
        keko.add(new HuffmanNode(1, (byte) '3'));
        int expResult = 3;
        int result = keko.size();
        assertEquals(expResult, result);
        keko.poll();
        assertEquals(2, keko.size());
    }
}
