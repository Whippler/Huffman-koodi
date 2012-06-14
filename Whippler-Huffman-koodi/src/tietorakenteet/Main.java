/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tietorakenteet;

/**
 *
 * @author lammenoj
 */
public class Main {

    public static void main(String[] args) {
        Hakupuu<Integer> puu = new Hakupuu();
        puu.put((byte) 10, 5);
        puu.put((byte) 15, 10);
        puu.put((byte) 20, 15);
        puu.put((byte) 25, 20);
        puu.put((byte) 10, 5);
        puu.put((byte) 0, -5);

        System.out.println(puu.keySet());
    }
}
