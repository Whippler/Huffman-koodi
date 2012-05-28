package pakkaus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author lammenoj
 */
public class Tallennus {

    private HashMap sanakirja;
    private byte[] tavut;
    private ArrayList<Byte> compressed = new ArrayList<Byte>();
    private File tiedosto;
    private String buffer = "";

    public Tallennus(byte[] tavut, HashMap sanakirja) {
        this.tavut = tavut;
        this.sanakirja = sanakirja;
        tiedosto = new File("");
    }

    public void compress() {
        
        for (byte i : tavut) {
            buffer = buffer + sanakirja.get(i);
            while (buffer.length() > 7) {
                compressed.add((byte) Integer.parseInt(buffer.substring(0, 7), 2));
                System.out.println(compressed.get(compressed.size()-1));
                buffer = buffer.substring(8);
            }
        }
        if(!buffer.isEmpty()){
            while (buffer.length()<8){
                buffer = buffer + "0";
            }
            compressed.add((byte) Integer.parseInt(buffer.substring(0, 7), 2));
            buffer = buffer.substring(8);
        }

    }
    
    public void save(){
        
    }
}
