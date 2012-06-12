package tietorakenteet;

public class Node {

    private Node left; 
    private Node right;
    private Node parent;
    private int height;
    private byte key;   
    private Object value; 

    public Node(byte key, Object value, Node left, Node right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node(byte key, Object value) {
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
    
    public void setParent(Node parent){
        this.parent = parent;
    }
    
    public Node getParent(){
        return parent;
    }

    public Object getValue() {
        return value;
    }
    
    public int getHeight(){
        return height;
    }
    
    public void setHeight(int korkeus){
        this.height = korkeus;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public byte getKey() {
        return key;
    }

    public String toString() {
        String l, r;
        
        if (left == null)
            l = "null";
        else
            l = left.toString();

        if (right == null)
            r = "null";
        else
            r = right.toString();

        return "Node["+key+","+value+", "+l+", "+r+"]";
    }

}
