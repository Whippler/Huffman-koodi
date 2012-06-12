package tietorakenteet;

/**
 *
 * @author Kalle
 */
public class HuffmanNode implements Comparable {

    private HuffmanNode left;
    private HuffmanNode right;
    private int count;
    private byte code;

    /**
     * Konstruktori luo solmun
     *
     * @param count tavujen määrä
     * @param left vasen lapsi
     * @param right oikea lapsi
     */
    public HuffmanNode(int count, HuffmanNode left, HuffmanNode right) {
        this.count = count;
        this.left = left;
        this.right = right;
    }

    /**
     * konstruktori luo lehtisolmun
     *
     * @param value tavujen määrä
     * @param code tavu
     */
    public HuffmanNode(int count, byte code) {
        this.count = count;
        this.code = code;
        // left ja right ovat null
    }

    /**
     *
     * @return palauttaa vasemman lapsen
     */
    public HuffmanNode getLeft() {
        return left;
    }

    /**
     *
     * @return palauttaa oikean lapsen
     */
    public HuffmanNode getRight() {
        return right;
    }

    /**
     *
     * @return palauttaa arvon
     */
    public int getCount() {
        return count;
    }

    /**
     *
     * @return palauttaa tavun
     */
    public byte getCode() {
        return code;
    }

    public String toString() {
        String l, r;

        if (left == null) {
            l = "null";
        } else {
            l = left.toString();
        }

        if (right == null) {
            r = "null";
        } else {
            r = right.toString();
        }

        return "Node[" + count + "|" + code + ", " + l + ", " + r + "]";
    }

    @Override
    public int compareTo(Object t) {

        if (this.count < ((HuffmanNode) t).count) {
            return -1;
        } else if (this.count > ((HuffmanNode) t).count) {
            return 1;
        } else {
            return 0;
        }
    }
}