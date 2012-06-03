package huffman;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import tietorakenteet.Node;

/**
 * Luokka purkaa pakatun tiedoston
 *
 * @author lammenoj
 */
public class Purkaja {

    private FileInputStream fileStream;
    private DataInputStream dataStream;
    private TreeMap<Byte, Integer> byteCount = new TreeMap<Byte, Integer>();
    private Node HuffmanTree;
    private ArrayList<Byte> purettu = new ArrayList<Byte>();

    public Purkaja(File tiedosto) throws FileNotFoundException, IOException {

        fileStream = new FileInputStream(tiedosto);
        dataStream = new DataInputStream(fileStream);
        loadTree();
        loadFile();
//        save();
    }

    /**
     * Metodi lataa pakatusta tiedostosta huffman-puun
     *
     * @throws IOException
     */
    private void loadTree() throws IOException {
        int count = dataStream.readInt();
        System.out.println(count);
        for (int i = 0; i < count; i++) {
            byte tavu = (byte) dataStream.read();
            int lkm = dataStream.readInt();

            byteCount.put(tavu, lkm);
        }
        Huffmankoodi treeBuilder = new Huffmankoodi();
        HuffmanTree = treeBuilder.Huffman(byteCount);

        System.out.println(HuffmanTree); // tulostaa reconstruoidun huffman-puun
    }

    /**
     * Metodi lataa pakatusta tiedostosta alkup. tiedoston sisällön
     *
     * @throws IOException
     */
    private void loadFile() throws IOException {
        String buffer = "";
        Node search = HuffmanTree;
        outerloop:
        while(true) {

            if (!buffer.isEmpty() && buffer.charAt(0) == '0' && search.getRight() != null) {
                search = search.getRight();
                buffer = buffer.substring(1);
                System.out.print("R ");
            }
            if (!buffer.isEmpty() && buffer.charAt(0) == '1' && search.getLeft() != null) {
                search = search.getLeft();
                buffer = buffer.substring(1);
                System.out.print("L ");
            }
            
            if (search.getLeft() == null && search.getRight() == null) {
                System.out.println("lehti: " + (char)search.getCode());
                if (search.getCode()==(byte)-128) break outerloop;
                purettu.add(search.getCode());
                search = HuffmanTree;
            }
            
            //Lukee dataSreamiä
            if (buffer.length()<2 && dataStream.available() > 0) {
                byte tavu;
                tavu = dataStream.readByte();
                System.out.println("tavu luettu: " + restoreZeros(Integer.toBinaryString(tavu)));
                buffer = buffer + restoreZeros(Integer.toBinaryString(tavu));
                System.out.println("bufferi: " + buffer);
            }
            
        } //while(!buffer.isEmpty());

    }

    /**
     * Metodi tallentaa puretun tiedoston
     *
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

        for (Byte i : purettu) {
            streamData.write(i);
            streamData.flush();
        }
        streamData.close();
    }

    private String restoreZeros(String tavu) {

        while (0 != 8 - tavu.length()) {
            tavu = "0" + tavu;
        }
        return tavu;

    }
}
