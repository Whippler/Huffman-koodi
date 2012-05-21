
package huffmankoodi;

import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author lammenoj
 */
public class Huffmankoodi {
    
    private ArrayList<Byte> tavut;
    private HashMap<Byte, Integer> byteCount = new HashMap<Byte, Integer>();

    public Huffmankoodi() {
        Tiedostonluku tiedosto = new Tiedostonluku();
        tiedosto.load();
        tavut = tiedosto.getTavut();
    }
    
    private void Count(){
        for(Byte i: tavut){
            if (byteCount.containsKey(i)){
                byteCount.put(i, byteCount.get(i)+1);
            } else {
                byteCount.put(i, 1);
            }
        }
    }
}
