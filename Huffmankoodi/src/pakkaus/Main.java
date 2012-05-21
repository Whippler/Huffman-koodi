package pakkaus;

import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {

        PriorityQueue aakkosto = new PriorityQueue<Node>();

        Huffmankoodi test = new Huffmankoodi();
        Node HuffmanTree = test.Huffman();
        
        System.out.println(HuffmanTree);
 
    }
}
