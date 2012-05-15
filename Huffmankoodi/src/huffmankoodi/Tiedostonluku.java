/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffmankoodi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lammenoj
 */
public class Tiedostonluku {

    private static Scanner lukija;
    private static File tiedosto;
    private static ArrayList<Byte> tavut = new ArrayList<Byte>();

    public Tiedostonluku() {
        tiedosto = new File("testi.txt");
    }

    public void load() {
        
        try {
            lukija = new Scanner(tiedosto);

            while (lukija.hasNextByte()) {
                tavut.add(lukija.nextByte());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tiedostonluku.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Byte> getTavut() {
        return tavut;
    }
    
     
}
