package purku;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lammenoj
 */
public class Main {

    public static void main(String[] args) {
        File tiedosto = new File("pakattu.dat");
        Purkaja purkaja;
        
        try {
            purkaja = new Purkaja(tiedosto);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
