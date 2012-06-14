package tietorakenteet;

public class Node<T> implements Comparable {

    private Node left = null;
    private Node right = null;
    private Node parent = null;
    private int height = 0;
    private byte key;
    private T value;

    public Node(byte key, T value, Node left, Node right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node(byte key, T value) {
        this.key = key;
        this.value = value;
        // left ja right ovat null
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getRight() {
        return right;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public T getValue() {
        return value;
    }

    public int getHeight() {
        if (this.parent == null) return -1;
        return height;
    }

    public void setHeight(int korkeus) {
        this.height = korkeus;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public byte getKey() {
        return key;
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

        return "Node[" + key + "," + value + ", " + l + ", " + r + "]";
    }

    @Override
    public int compareTo(Object t) {
        if ((int)this.key < (int)((Node) t).key) {
            return -1;
        } else if ((int)this.key > (int)((Node) t).key) {
            return 1;
        } else {
            return 0;
        }
    }
}
