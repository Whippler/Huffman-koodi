package pakkaus;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import tietorakenteet.Node;

public class Main {

    private static HashMap<Byte, String> sanakirja = new HashMap<Byte, String>();
    private static String filename = "Holmes.txt";

    public static void main(String[] args) {

        File tiedosto = new File(filename);
        Tiedostonluku lukija = new Tiedostonluku(tiedosto);
        byte[] tavut = lukija.getTavut();
        Huffmankoodi huffman = new Huffmankoodi(tavut);

        Node huffmanTree = huffman.Huffman();

        sanakirja(huffmanTree, "");

//        System.out.println(huffmanTree);
//        for (byte i : sanakirja.keySet()) {
//            System.out.println((char) i + " - " + sanakirja.get(i));
//        }
        
        Tallennus tallennus = new Tallennus(tavut, sanakirja, huffman.getByteCount());
        
        try {
            tallennus.compress();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static void sanakirja(Node root, String jono) {

        if (root.getLeft() != null) {
            sanakirja(root.getLeft(), jono = jono + "1");
            jono = jono.substring(0, jono.length() - 1);
        }

        if (root.getRight() != null) {
            sanakirja(root.getRight(), jono = jono + "0");
            jono = jono.substring(0, jono.length() - 1);
        }

        if (root.getLeft() == null && root.getRight() == null) {
            sanakirja.put(root.getCode(), jono);
        }
    }
}
