package huffman;

import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import tietorakenteet.Node;

public class Main {

    private static HashMap<Byte, String> sanakirja = new HashMap<Byte, String>();
    private static String filename = "Holmes.txt";
    private static String filename2 = "pakattu";
    private static String filename3 = "purettu";

    public static void main(String[] args) {
        try {
            createFile();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        Tallennus tallennus = new Tallennus(tavut, sanakirja, huffman.getByteCount(), filename2);

        try {
            tallennus.compress();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

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

    private static void purkaja() {

        File tiedosto = new File(filename2 + ".dat");
        try {
            Purkaja purkaja = new Purkaja(tiedosto, filename3);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void createFile() throws FileNotFoundException, IOException {
        File tied = new File("singlechar.txt");
        tied.createNewFile();
        FileOutputStream output = new FileOutputStream(tied);
        DataOutputStream dataStream = new DataOutputStream(output);
        for (int i = 0; i<200;i++){
            dataStream.writeChars("moi");
        }
    }
}
