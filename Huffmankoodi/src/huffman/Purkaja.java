package huffman;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import tietorakenteet.Node;

/**
 * Luokka purkaa pakatun tiedoston
 * @author lammenoj
 */
public class Purkaja {


    private FileInputStream fileStream;
    private DataInputStream dataStream;
    private TreeMap<Byte, Integer> byteCount = new TreeMap<Byte, Integer>();
    private Node HuffmanTree;
    private ArrayList<Byte> purettu;

    public Purkaja(File tiedosto) throws FileNotFoundException, IOException {

        fileStream = new FileInputStream(tiedosto);
        dataStream = new DataInputStream(fileStream);
        loadTree();
//        loadFile();
//        save();
    }

    
    /**
     * Metodi lataa pakatusta tiedostosta huffman-puun
     * @throws IOException 
     */
    private void loadTree() throws IOException {
        int count = dataStream.readInt();
        for (int i = 0; i < count; i++) {
            byteCount.put((byte) dataStream.read(), dataStream.readInt());
        }
        Huffmankoodi treeBuilder = new Huffmankoodi();
        HuffmanTree = treeBuilder.Huffman(byteCount);
        System.out.println(HuffmanTree);
    }

    
    /**
     * Metodi lataa pakatusta tiedostosta alkup. tiedoston sisällön
     * @throws IOException 
     */
    private void loadFile() throws IOException {
        String buffer = "";
        Node search = HuffmanTree;


        while (dataStream.available() > 0 || buffer.length() > 0) {
            if (buffer.length() < 2) {
                buffer = buffer + Integer.toBinaryString(dataStream.readByte());
                System.out.println(buffer);
            }

            if (buffer.charAt(0) == 0 && search.getRight() != null) {
                search = search.getRight();
                buffer = buffer.substring(1);
            } else if (buffer.charAt(0) == 1 && search.getLeft() != null) {
                search = search.getLeft();
                buffer = buffer.substring(1);
            } else if (search.getLeft()==null && search.getRight()==null){
                purettu.add(search.getCode());
                search = HuffmanTree;
            }
        }

    }

    /**
     * Metodi tallentaa puretun tiedoston
     * @throws IOException 
     */
    
    private void save() throws IOException {

        File target = new File("purettu.txt");
        try {
            target.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Purkaja.class.getName()).log(Level.SEVERE, null, ex);
        }
        FileOutputStream streamFile = new FileOutputStream(target);
        DataOutputStream streamData = new DataOutputStream(streamFile);
        
        for (Byte i:purettu){
            streamData.write(i);
            streamData.flush();
        }
        streamData.close();
    }
}
