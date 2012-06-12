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
        Hakupuu puu = new Hakupuu();
        puu.put((byte)'h', 7);
        System.out.println(puu.containsKey((byte)'h'));
        System.out.println(puu.containsKey((byte)'j'));
    }
}
