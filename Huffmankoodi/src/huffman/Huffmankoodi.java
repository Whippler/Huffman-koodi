package huffman;

import java.util.TreeMap;
import tietorakenteet.Hakupuu;
import tietorakenteet.MinimiKeko;
import tietorakenteet.HuffmanNode;

/**
 * Luokka toteuttaa Huffman puun muodostamisen
 * @author lammenoj
 */
public class Huffmankoodi {

    private byte[] tavut;
    private Hakupuu byteCount = new Hakupuu();
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
    public HuffmanNode Huffman(Hakupuu byteCount) {
        long currentTimeMillis = System.currentTimeMillis();
        //Luo puut
        for (byte i : byteCount.keySet()) {
            int count = Integer.parseInt((String)byteCount.getNode(i).getValue());
            HuffmanNode a = new HuffmanNode(count, i);
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
    public Hakupuu getByteCount() {
        return byteCount;
    }

    /**
     * Metodi laskee tiedoston tavut
     */
    private void Count() {
        long currentTimeMillis = System.currentTimeMillis();
        
        for (Byte i : tavut) {
            if (byteCount.getNode(i) != null) {
                byteCount.getNode(i).setValue(Integer.parseInt((String)byteCount.getNode(i).getValue())+1);
            } else {
                byteCount.put(i, 1);
            }
        }
        byteCount.put((byte)-128, 1);
        
        System.out.print("merkkien määrän laskenta: ");
        System.out.println(System.currentTimeMillis() - currentTimeMillis + "ms");
    }
}
