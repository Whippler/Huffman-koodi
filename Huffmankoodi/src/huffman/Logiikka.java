package huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tietorakenteet.Hakupuu;
import tietorakenteet.HuffmanNode;

/**
 * Toteuttaa ohjelman pää luokan
 *
 * @author lammenoj
 */
public class Logiikka {

    private static Hakupuu<String> sanakirja = new Hakupuu();
    private static String syote;
    private static String kohde;

    public static void main(String[] args) {
        args = new String[2];              //Testausta varten
        args[0] = "Holmes.txt";
        args[1] = "TestiPakattu.dat";

        if (args.length == 2) {

            if (args[0].substring(args[0].length() - 4).equals(".txt")) {
                System.out.println("PAKKAAMINEN");
                syote = args[0];
                kohde = args[1];
                pakkaaja();
            } else if (args[0].substring(args[0].length() - 4).equals(".dat")) {
                System.out.println("\n" + "PURKAMINEN");
                syote = args[0];
                kohde = args[1];
                purkaja();
            } else {
                System.out.println("Virheellinen syöte!");
            }
        } else {
            System.out.println("Virheellinen syöte!");
        }
    }

    /**
     * Suorittaa tiedoston lukemisen pakkaamisen ja tallentamisen
     */
    private static void pakkaaja() {
        // Lukee pakattavan tiedoston
        //----------------------------------------------------------------------

        File tiedosto = new File(syote);
        Tiedostonluku lukija = new Tiedostonluku(tiedosto);
        byte[] tavut = lukija.getTavut();
        Huffmankoodi huffman = new Huffmankoodi(tavut);
        HuffmanNode huffmanTree = huffman.Huffman();
        System.out.println(huffmanTree);
        sanakirja(huffmanTree, "");


        // tallentaa pakatun tiedoston
        //----------------------------------------------------------------------
        long currentTimeMillis = System.currentTimeMillis();
        Tallennus tallennus = new Tallennus(tavut, sanakirja, huffman.getByteCount(), kohde);

        try {
            tallennus.compress();
        } catch (IOException ex) {
            Logger.getLogger(Logiikka.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print("Tallennus: ");
        System.out.println(System.currentTimeMillis() - currentTimeMillis + "ms");

    }

    /**
     * Metodi rakentaa sanakirjan hufman puusta
     *
     * @param root Huffman-puun juuri
     * @param jono rekursio rakentaa bittijonon tähän olioon
     */
    private static void sanakirja(HuffmanNode root, String jono) {

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

        File tiedosto = new File(syote);
        try {
            Purkaja purkaja = new Purkaja(tiedosto, kohde);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Logiikka.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Logiikka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
