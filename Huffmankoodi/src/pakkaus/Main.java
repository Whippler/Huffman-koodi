package pakkaus;

import java.io.File;
import java.util.HashMap;
import tietorakenteet.Node;

public class Main {

    public static void main(String[] args) {

        File tiedosto = new File("testi.txt");
        Tiedostonluku lukija = new Tiedostonluku(tiedosto);
        byte[] tavut = lukija.getTavut();
        Huffmankoodi huffman = new Huffmankoodi(tavut);
        
        Node huffmanTree = huffman.Huffman();
        
        System.out.println(huffmanTree);
        
    }
    
    private HashMap sanakirja(){
        HashMap<Byte, Integer> sanakirja = new HashMap<Byte, Integer>();
        
        return sanakirja;
    }
}
