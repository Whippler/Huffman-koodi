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
        System.out.println(puu.keySet());
        puu.put((byte) 15, 10);
        System.out.println(puu.keySet());
        puu.put((byte) 8, 15);
        System.out.println(puu.keySet());

    }
}
