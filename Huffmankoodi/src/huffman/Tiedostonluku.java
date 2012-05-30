package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Luokka lukee tiedoston pakkaamista varten
 * @author lammenoj
 */
public class Tiedostonluku {

    private File tiedosto;
    private byte[] tavut2;

    public Tiedostonluku(File tiedosto) {
        this.tiedosto = tiedosto;
        fileSize();
    }

    private boolean fileSize() {
        long pituus = tiedosto.length();

        if (pituus > Integer.MAX_VALUE) {
            return false;
        } else {
            tavut2 = new byte[(int) pituus];
            load();
            return true;
        }
    }

    /**
     * Metodi lukee pakattavan tiedoston
     */
    private void load() {
        FileInputStream lukija = null;

        try {

            lukija = new FileInputStream(tiedosto);
            int offset = 0;
            int numRead = 0;

            try {
                while (offset < tavut2.length
                        && (numRead = lukija.read(tavut2, offset, tavut2.length - offset)) >= 0) {
                    offset += numRead;
                }
            } catch (IOException ex) {
                Logger.getLogger(Tiedostonluku.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tiedostonluku.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                lukija.close();
            } catch (IOException ex) {
                Logger.getLogger(Tiedostonluku.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     *
     * @return Palauttaa tiedoston sisällön tavuina taulukossa.
     */
    public byte[] getTavut() {
        return tavut2;
    }
}
