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
    private ArrayList<Byte> purettu = new ArrayList<Byte>();

    /**
     * 
     * @param tiedosto Purettava tiedosto
     * @param fileName Tiedostonimi mihin tiedosto puretaan
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public Purkaja(File tiedosto, String fileName) throws FileNotFoundException, IOException {

        fileStream = new FileInputStream(tiedosto);
        dataStream = new DataInputStream(fileStream);
        loadTree();
        long currentTimeMillis = System.currentTimeMillis();
        loadFile();
        System.out.print("tiedoston luku ja kääntäminen: ");
        System.out.println(System.currentTimeMillis() - currentTimeMillis + "ms");
        currentTimeMillis = System.currentTimeMillis();
        save(fileName);
        System.out.print("tallennus: ");
        System.out.println(System.currentTimeMillis() - currentTimeMillis + "ms");
    }

    /**
     * Metodi lukee pakatusta tiedostosta merkkien määrät ja laskee niistä huffmanpuun
     *
     * @throws IOException
     */
    private void loadTree() throws IOException {

        int count = dataStream.readInt();
        for (int i = 0; i < count; i++) {
            byte tavu = (byte) dataStream.read();
            int lkm = dataStream.readInt();

            byteCount.put(tavu, lkm);
        }
        Huffmankoodi treeBuilder = new Huffmankoodi();
        HuffmanTree = treeBuilder.Huffman(byteCount);
    }

    /**
     * Metodi lataa pakatusta tiedostosta alkuperäisen tiedoston sisällön
     *
     * @throws IOException
     */
    private void loadFile() throws IOException {
        String buffer = "";
        Node search = HuffmanTree;

        outerloop:
        while (true) {

            if (!buffer.isEmpty() && buffer.charAt(0) == '0' && search.getRight() != null) {
                search = search.getRight();
                buffer = buffer.substring(1);
//                System.out.print("R ");
            }
            if (!buffer.isEmpty() && buffer.charAt(0) == '1' && search.getLeft() != null) {
                search = search.getLeft();
                buffer = buffer.substring(1);
//                System.out.print("L ");
            }

            if (search.getLeft() == null && search.getRight() == null) {
//                System.out.println("lehti: " + (char)search.getCode());
                if (search.getCode() == (byte) -128) {
                    break outerloop;
                }
                purettu.add(search.getCode());
                search = HuffmanTree;
            }

            //Lukee dataSreamiä
            if (buffer.length() < 2 && dataStream.available() > 0) {
                byte tavu = dataStream.readByte();
                String binary = Integer.toBinaryString(tavu);

//                System.out.println("tavu luettu: " + binary);
                if (binary.length() > 8) {
                    binary = binary.substring(binary.length() - 8);
                }
                buffer = buffer + restoreZeros(binary);
//                System.out.println("bufferi: " + buffer);
            }

        } //while(!buffer.isEmpty());

    }

    /**
     * Metodi tallentaa puretun tiedoston
     *
     * @throws IOException
     */
    private void save(String fileName) throws IOException {

        File target = new File(fileName);
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
    
    
    /**
     * Metodi palauttaa tavun alkuun nollat kun ne on leikattu lukuvaiheessa pois.
     * @param tavu 
     * @return 
     */
    private String restoreZeros(String tavu) {

        while (0 != 8 - tavu.length()) {
            tavu = "0" + tavu;
        }
        return tavu;

    }
}
