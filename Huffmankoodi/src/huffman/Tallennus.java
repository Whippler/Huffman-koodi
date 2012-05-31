package huffman;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Luokka tallentaa pakatun tiedoston
 * @author lammenoj
 */
public class Tallennus {

    private HashMap sanakirja;
    private TreeMap<Byte, Integer> byteCount;
    private byte[] tavut;
    private String buffer = "";
    private File tiedosto;
    private FileOutputStream output;
    private DataOutputStream dataStream;

    public Tallennus(byte[] tavut, HashMap sanakirja, TreeMap bytecount) {
        this.tavut = tavut;
        this.sanakirja = sanakirja;
        this.byteCount = bytecount;

        tiedosto = new File("pakattu.dat");
        try {
            tiedosto.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Tallennus.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            output = new FileOutputStream(tiedosto);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tallennus.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataStream = new DataOutputStream(output);
    }
    
    /**
     * Metodi tallentaa merkkien määrät pakattuun tiedostoon
     * @throws IOException 
     */
    private void saveCount() throws IOException {

        dataStream.writeInt(byteCount.size());

        for (byte i : byteCount.keySet()) {
            dataStream.write(i);
            dataStream.writeInt(byteCount.get(i));

        }
    }
    /**
     * Metodi tallentaa tiedoston sisällön pakattuun tiedostoon
     * @throws IOException 
     */
    public void compress() throws IOException {
//        System.out.println("aloitetetiin kirjoitus");
        saveCount();
//        System.out.println("tavuja yhteensä: " + tavut.length);
        for (byte i : tavut) {
            buffer = buffer + sanakirja.get(i);
//            System.out.println("bufferin sisältö: " + buffer);
            while (buffer.length() > 7) {
                System.out.println(buffer.substring(0, 8));
                byte kirjoita = (byte) Integer.parseInt(buffer.substring(0, 8), 2);
                dataStream.write(kirjoita);
//                System.out.println("kirjoitettiin: " + kirjoita);
                dataStream.flush();
                buffer = buffer.substring(8);
            }
        }
        if (!buffer.isEmpty()) {
            while (buffer.length() < 8) {
                buffer = buffer + "0";

            }

            String kirjoitetaan = buffer.substring(0,8);
            byte kirj = (byte) Integer.parseInt(kirjoitetaan, 2);
            dataStream.write(kirj);
//            System.out.println(Integer.toBinaryString(Integer.parseInt(buffer.substring(0,8),2)));
            dataStream.flush();
            buffer = buffer.substring(8);
//            System.out.println("bufferin sisältö: " +buffer);
        }
        dataStream.close();

    }
}