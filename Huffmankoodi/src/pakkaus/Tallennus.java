package pakkaus;

import java.io.*;
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
    private String buffer = "";
    private File tiedosto;
    private FileOutputStream output;
    private DataOutputStream dataStream;

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
        try {
            output = new FileOutputStream(tiedosto);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tallennus.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataStream = new DataOutputStream(output);
    }

    public void compress() throws IOException {

        saveCount();
        for (byte i : tavut) {
            buffer = buffer + sanakirja.get(i);
            while (buffer.length() > 7) {
                dataStream.write(Integer.parseInt(buffer.substring(0, 7), 2));
                dataStream.flush();
                buffer = buffer.substring(8);
            }
        }
        if (!buffer.isEmpty()) {
            while (buffer.length() < 8) {
                buffer = buffer + "0";
            }
            dataStream.write(Integer.parseInt(buffer.substring(0, 7), 2));
            dataStream.flush();
            buffer = buffer.substring(8);
        }
        dataStream.close();

    }

    private void saveCount() throws IOException {

        dataStream.writeInt(byteCount.size());

        for (byte i : byteCount.keySet()) {
            dataStream.write(i);
            dataStream.writeInt(byteCount.get(i));

        }
    }
}