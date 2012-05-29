package pakkaus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lammenoj
 */
public class Tallennus {

    private HashMap sanakirja;
    private HashMap<Byte, Integer> byteCount;
    private byte[] tavut;
    private ArrayList<Byte> compressed = new ArrayList<Byte>(); //pakattu tiedosto
    private File tiedosto;
    private String buffer = "";

    public Tallennus(byte[] tavut, HashMap sanakirja, HashMap bytecount) {
        this.tavut = tavut;
        this.sanakirja = sanakirja;
        this.byteCount = bytecount;
        tiedosto = new File("pakattu.dat");
        try {
            tiedosto.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Tallennus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void compress() {

        for (byte i : tavut) {
            buffer = buffer + sanakirja.get(i);
            while (buffer.length() > 7) {
                compressed.add((byte) Integer.parseInt(buffer.substring(0, 7), 2));
                System.out.println(compressed.get(compressed.size() - 1));
                buffer = buffer.substring(8);
            }
        }
        if (!buffer.isEmpty()) {
            while (buffer.length() < 8) {
                buffer = buffer + "0";
            }
            compressed.add((byte) Integer.parseInt(buffer.substring(0, 7), 2));
            System.out.println(compressed.get(compressed.size() - 1));
            buffer = buffer.substring(8);
        }
        System.out.println("pakattu pituus: " + compressed.size());

    }

    private void save() {
        try {
            FileOutputStream output = new FileOutputStream(tiedosto);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tallennus.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        byte[] maarat = new byte[byteCount.size()*2];
        int j = 0;
        for (byte i : byteCount.keySet()) {
            Integer.toString(byteCount.get(i));
        }
        }
    }
