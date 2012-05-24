package pakkaus;

import java.io.File;
import java.util.HashMap;

/**
 *
 * @author lammenoj
 */
public class Tallennus {

    private HashMap sanakirja;
    private byte[] tavut;
    private File tiedosto;

    public Tallennus(byte[] tavut, HashMap sanakirja) {
        this.tavut = tavut;
        this.sanakirja = sanakirja;
        tiedosto = new File("");
    }
    
    public void save(){
        for (byte i: tavut){
            sanakirja.get(i);
        }
    }
}
