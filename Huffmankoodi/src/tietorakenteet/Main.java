
package tietorakenteet;

/**
 *
 * @author lammenoj
 */
public class Main {
    public static void main(String[] args) {
        
        
        MinimiKeko keko = new MinimiKeko(20);
        Node node = new Node(6, (byte)50);
        Node node1 = new Node(3, (byte)50);
        keko.add(node);
        keko.add(node1);
        
        System.out.println(keko.size());
        System.out.println(keko.poll());
    }
}
