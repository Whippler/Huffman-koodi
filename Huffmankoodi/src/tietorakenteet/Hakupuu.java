package tietorakenteet;

import java.util.ArrayList;

public class Hakupuu {

    private static Node root = null;
    private static ArrayList<Byte> lista;
    private int koko = 0;

    public Hakupuu() {
    }

    public Node getNode(byte key) {
        
        if (root == null){
            return null;
        } else if (root.getKey() == key) {
            return root;
        } else {
            return haku(root, key);
        }
    }

    public static Node haku(Node root, int key) {
        Node apu = null;

        if (root.getKey() < key) {
            if (root.getRight() != null) {
                apu = haku(root.getRight(), key);
            }

        } else if (root.getKey() > key) {
            if (root.getLeft() != null) {
                apu = haku(root.getLeft(), key);
            }

        } else if (root.getKey() == key) {
            apu = root;
        }
        return apu;
    }

    private static Node RightRotate(Node k1) {
        Node k2 = k1.getLeft();
        k2.setParent(k1.getParent());
        k1.setParent(k2);
        k1.setLeft(k2.getRight());
        k2.setRight(k1);
        if (k1.getLeft() != null) {
            k1.getLeft().setParent(k1);
        }
        k1.setHeight(Math.max(k1.getLeft().getHeight(), k1.getRight().getHeight()) + 1);
        k2.setHeight(Math.max(k2.getLeft().getHeight(), k2.getRight().getHeight()) + 1);
        return k2;
    }

    private static Node LeftRotate(Node k1) {
        Node k2 = k1.getRight();
        k2.setParent(k1.getParent());
        k1.setParent(k2);
        k1.setRight(k2.getLeft());
        k2.setLeft(k1);
        if (k1.getRight() != null) {
            k1.getRight().setParent(k1);
        }
        k1.setHeight(Math.max(k1.getLeft().getHeight(), k1.getRight().getHeight()) + 1);
        k2.setHeight(Math.max(k2.getLeft().getHeight(), k2.getRight().getHeight()) + 1);
        return k2;
    }

    private static Node RightLeftRotate(Node k1) {
        Node k2 = k1.getRight();
        k1.setRight(RightRotate(k2));
        return LeftRotate(k1);
    }

    private static Node LeftRightRotate(Node k1) {
        Node k2 = k1.getLeft();
        k1.setLeft(LeftRotate(k2));
        return LeftRotate(k1);
    }

    public void put(byte key, Object value) {
        koko = koko+1;
        
        Node uusi = lisaa(key, value);
        Node p = uusi.getParent();
        Node alipuu;
        while (p != null){
            
            
            if (p.getLeft().getHeight() == p.getRight().getHeight()+2) {
                Node vanhempi = p.getParent();
                if(p.getLeft().getLeft().getHeight() > p.getLeft().getRight().getHeight()){
                    alipuu = RightRotate(p);
                } else {
                    alipuu = LeftRightRotate(p);
                }
                if(vanhempi == null){
                    root = alipuu;
                } else if(vanhempi.getLeft() == p){
                    vanhempi.setLeft(alipuu);
                } else {
                    vanhempi.setRight(alipuu);
                }
                if(vanhempi != null){
                    vanhempi.setHeight(Math.max(vanhempi.getLeft().getHeight(), vanhempi.getRight().getHeight())+1);
                }
                return;
            }
            
            if (p.getRight().getHeight() == p.getLeft().getHeight()+2) {
                Node vanhempi = p.getParent();
                if(p.getRight().getRight().getHeight() > p.getRight().getLeft().getHeight()){
                    alipuu = LeftRotate(p);
                } else {
                    alipuu = RightLeftRotate(p);
                }
                if(vanhempi == null){
                    root = alipuu;
                } else if(vanhempi.getLeft() == p){
                    vanhempi.setLeft(alipuu);
                } else {
                    vanhempi.setRight(alipuu);
                }
                if(vanhempi != null){
                    vanhempi.setHeight(Math.max(vanhempi.getLeft().getHeight(), vanhempi.getRight().getHeight())+1);
                }
                return;
            }
            p.setHeight(Math.max(p.getLeft().getHeight(), p.getRight().getHeight())+1);
            p = p.getParent();
            
            
        }
    }

    private static Node lisaa(byte key, Object value) {
        Node uusi = new Node(key, value);
        if (root == null) {
            root = uusi;
            return root;
        }
        Node x = root;
        while (x != null) {
            Node p = x;
            if (uusi.getKey() < x.getKey()) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
            uusi.setParent(p);
            if (uusi.getKey() < p.getKey()) {
                p.setLeft(uusi);
            } else {
                p.setRight(uusi);
            }
        }
        return uusi;
    }
    
    public ArrayList<Byte> keySet(){
        lista = new ArrayList<Byte>();
        alkiot(root);
        return lista;
    }
    
    private void alkiot(Node root){
        if (root != null){
            alkiot(root.getLeft());
            lista.add(root.getKey());
            alkiot(root.getRight());                    
        }
    }
    
    public int size(){
        return koko;
    }

//    public static Node hae(Node root, int key) {
//
//        Node apu = null;
//
//        if (root.getKey() < key) {
//            if (root.getRight().getKey() < key) {
//                return hae(root.getRight(), key);
//            } else {
//                return apu;
//            }
//        } else if (root.getKey() > key) {
//            if (root.getLeft().getKey() > key) {
//                return hae(root.getRight(), key);
//            } else {
//                return apu;
//            }
//        }
//        return apu;
//
//    }
}
