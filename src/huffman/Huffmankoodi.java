package huffman;

import java.util.TreeMap;
import tietorakenteet.MinimiKeko;
import tietorakenteet.HuffmanNode;

/**
 * Luokka toteuttaa Huffman puun muodostamisen
 * @author lammenoj
 */
public class Huffmankoodi {

    private byte[] tavut;
    private TreeMap<Byte, Integer> byteCount = new TreeMap<Byte, Integer>();
    private MinimiKeko aakkosto = new MinimiKeko(256);

    /**
     * parametritön construktori, käytetään tiedoston purku vaiheessa, kun merkkien määriä ei tarvitse laskea.
     */
    public Huffmankoodi() {
    }

    /**
     * 
     * @param tavut Taulukko tiedoston kaikista tavuista
     */
    public Huffmankoodi(byte[] tavut) {
        this.tavut = tavut;
        Count();
    }

    /**
     * metodi laskee tiedostolle huffman-puun
     *
     * @return palauttaa huffman-puun juuren
     */
    public HuffmanNode Huffman() {
        return Huffman(this.byteCount);
    }
    
    /**
     * Metodi rakentaa huffman-puun merkkien määristä
     * @param byteCount metodi saa parametrina merkkien määrät
     * @return palauttaa Huffman-puun juuren
     */
    public HuffmanNode Huffman(TreeMap<Byte, Integer> byteCount) {
        long currentTimeMillis = System.currentTimeMillis();
        //Luo puut
        for (byte i : byteCount.keySet()) {
            HuffmanNode a = new HuffmanNode(byteCount.get(i), i);
            aakkosto.add(a);
        }

        //Luo Huffman puun yhdistämällä edellä luodut puut
        while (aakkosto.size() > 1) {
            HuffmanNode x = (HuffmanNode) aakkosto.poll();
            HuffmanNode y = (HuffmanNode) aakkosto.poll();
            HuffmanNode z = new HuffmanNode(x.getCount() + y.getCount(), x, y);
            aakkosto.add(z);
        }
        //palauttaa puun juuren
        System.out.print("huffman-puun luonti: ");
        System.out.println(System.currentTimeMillis() - currentTimeMillis + "ms");
        return (HuffmanNode) aakkosto.poll();
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
        long currentTimeMillis = System.currentTimeMillis();
        
        for (Byte i : tavut) {
            if (byteCount.containsKey(i)) {
                byteCount.put(i, byteCount.get(i) + 1);
            } else {
                byteCount.put(i, 1);
            }
        }
        byteCount.put((byte)-128, 1);
        
        System.out.print("merkkien määrän laskenta: ");
        System.out.println(System.currentTimeMillis() - currentTimeMillis + "ms");
    }
}