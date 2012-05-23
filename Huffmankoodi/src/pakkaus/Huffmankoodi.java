package pakkaus;

import java.util.HashMap;
import java.util.PriorityQueue;
import tietorakenteet.Node;

/**
 *
 * @author lammenoj
 */
public class Huffmankoodi {

    private byte[] tavut;
    private HashMap<Byte, Integer> byteCount = new HashMap<Byte, Integer>();
    private PriorityQueue aakkosto = new PriorityQueue<Node>();

    public Huffmankoodi(byte[] tavut) {
        this.tavut = tavut;
    }

    /**
     * metodi laskee tiedostolle huffman-puun
     *
     * @return palauttaa huffman-puun juuren
     */
    public Node Huffman() {
        Count();

        //Luo puut
        for (byte i : byteCount.keySet()) {
            System.out.println(i + ": " + byteCount.get(i));  // tulostaa merkkien määrät
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
     * Metodi laskee tiedoston tavut
     */
    private void Count() {
        for (Byte i : tavut) {
            if (byteCount.containsKey(i)) {
                byteCount.put(i, byteCount.get(i) + 1);
            } else {
                byteCount.put(i, 1);
            }
        }
    }
}
