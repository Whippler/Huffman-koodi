package pakkaus;

public class Node implements Comparable{

    private Node left;
    private Node right;
    private int count;
    private byte code;

    public Node(int count, Node left, Node right) {
        this.count = count;
        this.left = left;
        this.right = right;
    }

    public Node(int value, byte code) {
        this.count = value;
        this.code = code;
        // left ja right ovat null
    }
    
//    public Node(int value) {
//        this.value = value;
//    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public int getCount() {
        return count;
    } 
    
    public int getCode(){
        return code;
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

        return "Node["+count+", "+l+", "+r+"]";
    }

    @Override
    public int compareTo(Object t) {
        
        if(this.count < ((Node)t).count){
            return -1;
        } else if (this.count > ((Node)t).count){
            return 1;
        } else return 0;
    }

}