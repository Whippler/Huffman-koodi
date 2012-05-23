package tietorakenteet;

/**
 *
 * @author Kalle
 */
public class Node implements Comparable {

    private Node left;
    private Node right;
    private int count;
    private byte code;

    /**
     * Konstruktori luo solmun
     *
     * @param count tavujen määrä
     * @param left vasen lapsi
     * @param right oikea lapsi
     */
    public Node(int count, Node left, Node right) {
        this.count = count;
        this.left = left;
        this.right = right;
    }

    public Node(int count) {
        this.count = count;
    }

    /**
     * konstruktori luo lehtisolmun
     *
     * @param value tavujen määrä
     * @param code tavu
     */
    public Node(int count, byte code) {
        this.count = count;
        this.code = code;
        // left ja right ovat null
    }

    /**
     *
     * @return palauttaa vasemman lapsen
     */
    public Node getLeft() {
        return left;
    }

    /**
     *
     * @return palauttaa oikean lapsen
     */
    public Node getRight() {
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
    public int getCode() {
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

        if (this.count < ((Node) t).count) {
            return -1;
        } else if (this.count > ((Node) t).count) {
            return 1;
        } else {
            return 0;
        }
    }
}