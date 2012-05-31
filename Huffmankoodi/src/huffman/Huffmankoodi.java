package huffman;

import java.util.PriorityQueue;
import java.util.TreeMap;
import tietorakenteet.Node;

/**
 *
 * @author lammenoj
 */
public class Huffmankoodi {

    private byte[] tavut;
    private TreeMap<Byte, Integer> byteCount = new TreeMap<Byte, Integer>();
    private PriorityQueue aakkosto = new PriorityQueue<Node>();

    public Huffmankoodi() {
    }

    public Huffmankoodi(byte[] tavut) {
        this.tavut = tavut;
        Count();
    }

    /**
     * metodi laskee tiedostolle huffman-puun
     *
     * @return palauttaa huffman-puun juuren
     */
    public Node Huffman() {
        return Huffman(this.byteCount);
    }
    
    /**
     * Metodi rakentaa huffman-puun merkkien määristä
     * @param byteCount metodi saa parametrina merkkien määrät
     * @return palauttaa Huffman-puun juuren
     */
    public Node Huffman(TreeMap<Byte, Integer> byteCount) {
        
//        for(byte i:byteCount.keySet()){
//            System.out.println(i);
//        }

        //Luo puut
        for (byte i : byteCount.keySet()) {
//            System.out.println((char)i + ": " + byteCount.get(i));  // tulostaa merkkien määrät

            Node a = new Node(byteCount.get(i), i);
            aakkosto.add(a);
        }

        //Luo Huffman puun yhdistämällä edellä luodut puut
        while (aakkosto.size() > 1) {
            Node x = (Node) aakkosto.poll();
            Node y = (Node) aakkosto.poll();
            Node z = new Node(x.getCount() + y.getCount(), x, y);

            aakkosto.add(z);
        }
        //palauttaa puun juuren
        return (Node) aakkosto.poll();
    }

    /**
     * Metodi palauttaa merkkien määrät
     * @return 
     */
    public TreeMap<Byte, Integer> getByteCount() {
        return byteCount;
    }

    /**
     * Metodi laskee tiedoston tavut
     */
    private void Count() {
        for (Byte i : tavut) {
            System.out.println(i);
            if (byteCount.containsKey(i)) {
                byteCount.put(i, byteCount.get(i) + 1);
            } else {
                byteCount.put(i, 1);
            }
        }
        byteCount.put((byte)-128, 1);
    }
}
