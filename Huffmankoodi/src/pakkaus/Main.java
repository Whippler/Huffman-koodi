package pakkaus;


import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {

        PriorityQueue aakkosto = new PriorityQueue<Node>();

        Huffmankoodi lukija = new Huffmankoodi();
        Node huffmanTree = lukija.Huffman();
        
        System.out.println(huffmanTree);
 
    }
}
