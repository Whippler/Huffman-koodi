/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pakkaus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lammenoj
 */
public class Tiedostonluku {

//    private static Scanner lukija;
    private static File tiedosto;
    private static ArrayList<Integer> tavut = new ArrayList<Integer>();
    private byte[] tavut2;

    public Tiedostonluku() {
        tiedosto = new File("testi.txt");
        long pituus = tiedosto.length();

        if (pituus > Integer.MAX_VALUE) {
            System.out.println("file too large");
        } else {
            tavut2 = new byte[(int) pituus];
            load();
        }
    }

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

    public byte[] getTavut() {
//        for (byte i : tavut2) {
//            System.out.println(i);
//        }
        return tavut2;
    }
}
