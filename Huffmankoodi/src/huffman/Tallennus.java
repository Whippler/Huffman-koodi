package huffman;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tietorakenteet.Hakupuu;

/**
 * Luokka tallentaa pakatun tiedoston
 *
 * @author lammenoj
 */
public class Tallennus {

    private Hakupuu<String> sanakirja;
    private Hakupuu<Integer> byteCount;
    private byte[] tavut;
    private String buffer = "";
    private File tiedosto;
    private FileOutputStream output;
    private DataOutputStream dataStream;

    /**
     *
     * @param tavut Tiedoston sisältö
     * @param sanakirja eri tavujen bittijono vastaavuudet
     * @param bytecount merkkien määrät
     * @param fileName tiedostonimi johon pakataan
     */
    public Tallennus(byte[] tavut, Hakupuu sanakirja, Hakupuu bytecount, String fileName) {
        this.tavut = tavut;
        this.sanakirja = sanakirja;
        this.byteCount = bytecount;

        tiedosto = new File(fileName);
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
     *
     * @throws IOException
     */
    private void saveCount() throws IOException {

        System.out.println(byteCount.size());
        dataStream.writeInt(byteCount.size()); // kirjoittaa merkkien määrän

        ArrayList<Byte> list = byteCount.keySet();
        for (byte i : list) {
            System.out.println(i);
            dataStream.write(i);
            int k = (Integer) byteCount.getNode(i).getValue();
            System.out.println(k);
            dataStream.writeInt(k);  // kirjoittaa merkin määrän
        }
    }

    /**
     * Metodi tallentaa tiedoston sisällön pakattuun tiedostoon
     *
     * @throws IOException
     */
    public void compress() throws IOException {
        saveCount();

        tavut[tavut.length - 1] = (byte) -128;
        for (byte i : tavut) {
            buffer = buffer + sanakirja.getNode(i).getValue();
            while (buffer.length() > 7) {
                byte kirjoita = (byte) Integer.parseInt(buffer.substring(0, 8), 2);
                dataStream.write(kirjoita);
                dataStream.flush();
                System.out.println(buffer);
                buffer = buffer.substring(8);
            }
        }
        // kun tiedoston on käytyläpi lisätään täyte bittejä
        if (!buffer.isEmpty()) {
            while (buffer.length() < 8) {
                buffer = buffer + "0";
            }
            String kirjoitetaan = buffer.substring(0, 8);
            byte kirj = (byte) Integer.parseInt(kirjoitetaan, 2);
            dataStream.write(kirj);
            dataStream.flush();
            System.out.println(buffer);
            buffer = buffer.substring(8);
        }
        dataStream.close();

    }
}