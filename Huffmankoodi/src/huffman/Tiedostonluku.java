package huffman;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Luokka lukee tiedoston pakkaamista varten
 *
 * @author lammenoj
 */
public class Tiedostonluku {

    private File tiedosto;
    private byte[] tavut2;

    /**
     * 
     * @param tiedosto Luettava tiedosto olio
     */
    public Tiedostonluku(File tiedosto) {
        this.tiedosto = tiedosto;
        fileSize();
    }

    /**
     * Metodi tarkistaa ettei tiedosto ole liian iso
     * @return 
     */
    private boolean fileSize() {
        long pituus = tiedosto.length() + 1;

        if (pituus > Integer.MAX_VALUE) {
            return false;
        } else {
            tavut2 = new byte[(int) pituus];
            
            try {
                load();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Tiedostonluku.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Tiedostonluku.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
    }

    /**
     * Metodi lukee pakattavan tiedoston
     */
    private void load() throws FileNotFoundException, IOException {
        FileInputStream lukija = new FileInputStream(tiedosto);
        int offset = 0;
        int numRead = 0;

        while (offset < tavut2.length && (numRead = lukija.read(tavut2, offset, tavut2.length - offset)) >= 0) {
            offset += numRead;
        }
        lukija.close();
    }

    /**
     *
     * @return Palauttaa tiedoston sisällön tavuina taulukossa.
     */
    public byte[] getTavut() {
        return tavut2;
    }
}
