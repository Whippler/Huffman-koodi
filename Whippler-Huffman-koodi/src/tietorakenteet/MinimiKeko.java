package tietorakenteet;

/**
 *
 * @author lammenoj
 */
public class MinimiKeko {

    private HuffmanNode[] keko;
    private int heapSize;
    
    /**
     * Luo koko, kokoisen minimikeko olion
     * @param koko 
     */
    public MinimiKeko(int koko) {
        keko = new HuffmanNode[koko];
    }
    
    /**
     * 
     * @param i
     * @return palauttaa keskimmäisen lapsen indeksin
     */
    private int center(int i) {
        return 3 * i + 2;
    }
    
    /**
     * 
     * @param i
     * @return palauttaa vasemman lapsen indeksin
     */
    private int left(int i) {
        return 3 * i + 1;
    }
    
    /**
     * 
     * @param i
     * @return palauttaa oikean lapsen indeksin
     */
    private int right(int i) {
        return 3*i + 3;
    }
    /**
     * 
     * @param i
     * @return palauttaa vanhemman indeksin
     */
    private int parent(int i) {
        return (int)Math.round((double)i/3);
    }

    /**
     * järjestää keon alkion i
     * @param i 
     */
    private void heapify(int i) {
        int vasen = left(i);
        int kesk = center(i);
        int oikea = right(i);
        int pienin;

        if (oikea <= heapSize) {
            int a = keko[vasen].compareTo(keko[kesk]);
            int b = keko[vasen].compareTo(keko[oikea]);

            if (a < 0 && b < 0) {
                pienin = vasen;
            } else if (b > 0 && keko[oikea].compareTo(keko[kesk]) < 0) {
                pienin = oikea;
            } else {
                pienin = kesk;
            }

            if (keko[i].compareTo(keko[pienin]) < 0) {
                HuffmanNode apu = keko[i];
                keko[i] = keko[pienin];
                keko[pienin] = apu;
                heapify(pienin);
            }

        } else if (kesk <= heapSize) {

            if (keko[kesk].compareTo(keko[vasen]) < 0) {
                pienin = kesk;
            } else {
                pienin = oikea;
            }

            HuffmanNode apu = keko[i];
            keko[i] = keko[pienin];
            keko[pienin] = apu;

        } else if (vasen <= heapSize){ 
            
            if (keko[i].compareTo(keko[left(i)]) > 0) {
            HuffmanNode apu = keko[i];
            keko[i] = keko[left(i)];
            keko[left(i)] = apu;
            }
        }
    }
    
    /**
     * 
     * @return palauttaa pienimmän alkion ja poistaa sen
     */
    public HuffmanNode poll() {
        HuffmanNode min = keko[1];
        keko[1] = keko[heapSize];
        heapSize = heapSize - 1;
        heapify(1);
        return min;
    }
    /**
     * lisää kekoon alkion k
     * @param k 
     */
    public void add(HuffmanNode k){
        heapSize = heapSize+1;
        int i = heapSize;
        while (i>1 && k.compareTo(keko[parent(i)]) < 0){
            keko[i] = keko[parent(i)];
            i = parent(i);
        }
        keko[i] = k;
    }
    
    /**
     * 
     * @return palauttaa alkioiden määrän
     */
    public int size(){
        return heapSize;
    }
}
