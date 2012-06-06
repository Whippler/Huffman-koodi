package huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import tietorakenteet.Node;

/**
 * Toteuttaa ohjelman pää luokan
 *
 * @author lammenoj
 */
public class Logiikka {

    private static HashMap<Byte, String> sanakirja = new HashMap<Byte, String>();
    private static String filename;
    private static String filename2;
    private static String filename3;

    public static void main(String[] args) {
//        args = new String[3];
//        args[0] = "0";
//        args[1] = "6char.txt";
//        args[2] = "6charPakattu.dat";
//        String toiminto = args[0];
        if (args[0].equals("0")) {
            System.out.println("PAKKAAMINEN");
            filename = args[1];
            filename2 = args[2];
            pakkaaja();
        } else {
            System.out.println("\n" + "PURKAMINEN");
            filename2 = args[1];
            filename3 = args[2];
            purkaja();
        }
    }

    /**
     * Suorittaa tiedoston lukemisen pakkaamisen ja tallentamisen
     */
    private static void pakkaaja() {
        // Lukee pakattavan tiedoston
        //----------------------------------------------------------------------

        File tiedosto = new File(filename);
        Tiedostonluku lukija = new Tiedostonluku(tiedosto);
        byte[] tavut = lukija.getTavut();
        Huffmankoodi huffman = new Huffmankoodi(tavut);
        Node huffmanTree = huffman.Huffman();
        sanakirja(huffmanTree, "");


        // tallentaa pakatun tiedoston
        //----------------------------------------------------------------------
        long currentTimeMillis = System.currentTimeMillis();
        Tallennus tallennus = new Tallennus(tavut, sanakirja, huffman.getByteCount(), filename2);

        try {
            tallennus.compress();
        } catch (IOException ex) {
            Logger.getLogger(Logiikka.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print("Tallennus: ");
        System.out.println(System.currentTimeMillis()-currentTimeMillis + "ms");

    }

    /**
     * Metodi rakentaa sanakirjan hufman puusta
     *
     * @param root Huffman-puun juuri
     * @param jono rekursio rakentaa bittijonon tähän olioon
     */
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

    /**
     * Metodi suorittaa pakatun tiedoston
     */
    private static void purkaja() {

        File tiedosto = new File(filename2);
        try {
            Purkaja purkaja = new Purkaja(tiedosto, filename3);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Logiikka.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Logiikka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
