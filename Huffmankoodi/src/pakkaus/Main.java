package pakkaus;


import java.io.File;

public class Main {

    public static void main(String[] args) {

        File tiedosto = new File("Holmes.txt");
        Tiedostonluku lukija = new Tiedostonluku(tiedosto);
        Huffmankoodi huffman = new Huffmankoodi(lukija.getTavut());
        
        Node huffmanTree = huffman.Huffman();
        
        System.out.println(huffmanTree);
        
    }
}
