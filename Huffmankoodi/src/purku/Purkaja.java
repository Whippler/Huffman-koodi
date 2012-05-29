package purku;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import pakkaus.Huffmankoodi;
import tietorakenteet.Node;

/**
 *
 * @author lammenoj
 */
public class Purkaja {


    private FileInputStream fileStream;
    private DataInputStream dataStream;
    private HashMap<Byte, Integer> byteCount = new HashMap<Byte, Integer>();
    private Node HuffmanTree;
    private ArrayList<Byte> purettu;

    public Purkaja(File tiedosto) throws FileNotFoundException, IOException {

        fileStream = new FileInputStream(tiedosto);
        dataStream = new DataInputStream(fileStream);
        loadTree();
        loadFile();
        save();
    }

    private void loadTree() throws IOException {
        int count = dataStream.readInt();
        for (int i = 0; i < count; i++) {
            byteCount.put((byte) dataStream.read(), dataStream.readInt());
        }
        Huffmankoodi treeBuilder = new Huffmankoodi();
        HuffmanTree = treeBuilder.Huffman(byteCount);
        System.out.println(HuffmanTree);
    }

    private void loadFile() throws IOException {
        String buffer = "";
        Node search = HuffmanTree;


        while (dataStream.available() > 0 || buffer.length() > 0) {
            if (buffer.length() < 1) {
                buffer = buffer + Integer.toBinaryString(dataStream.readByte());
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
