package tietorakenteet;

import java.util.ArrayList;

public class Hakupuu<T> {

    private Node<T> root = null;
    private ArrayList<Byte> lista;
    private int pVasen;
    private int pOikea;
    private int pVasenVasen;
    private int pOikeaVasen;
    private int pVasenOikea;
    private int pOikeaOikea;

    public Hakupuu() {
    }

    public Node getNode(byte key) {

        if (root == null) {
            return null;
        } else if (root.getKey() == key) {
            return root;
        } else {
            return haku(root, key);
        }
    }

    public Node haku(Node<T> root, byte key) {
        Node<T> apu = null;

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

    private Node<T> RightRotate(Node<T> k1) {
        Node<T> k2 = k1.getLeft();
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

    private Node<T> LeftRotate(Node<T> k1) {
        Node<T> k2 = k1.getRight();
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

    private Node<T> RightLeftRotate(Node<T> k1) {
        Node<T> k2 = k1.getRight();
        k1.setRight(RightRotate(k2));
        return LeftRotate(k1);
    }

    private Node<T> LeftRightRotate(Node<T> k1) {
        Node<T> k2 = k1.getLeft();
        k1.setLeft(LeftRotate(k2));
        return LeftRotate(k1);
    }

    public void put(byte key, T value) {

        Node<T> uusi = lisaa(key, value);
        Node<T> p = uusi.getParent();
        if (p == null) {
            return;
        }

        alustaKorkeudet(p);

        Node<T> alipuu;
        while (p != null) {

            if (pVasen == (pOikea + 2)) {

                Node<T> vanhempi = p.getParent();


                if (pVasenVasen > pVasenOikea) {
                    alipuu = RightRotate(p);
                } else {
                    alipuu = LeftRightRotate(p);
                }
                if (vanhempi == null) {
                    root = alipuu;
                } else if (vanhempi.getLeft() == p) {
                    vanhempi.setLeft(alipuu);
                } else {
                    vanhempi.setRight(alipuu);
                }
                if (vanhempi != null && vanhempi.getRight() != null && vanhempi.getLeft() != null) {
                    vanhempi.setHeight(Math.max(vanhempi.getLeft().getHeight(), vanhempi.getRight().getHeight()) + 1);
                }
                return;
            }

            if (pOikea == pVasen + 2) {
                Node<T> vanhempi = p.getParent();

                if (pOikeaOikea > pVasen) {
                    alipuu = LeftRotate(p);
                } else {
                    alipuu = RightLeftRotate(p);
                }
                if (vanhempi == null) {
                    root = alipuu;
                } else if (vanhempi.getLeft() == p) {
                    vanhempi.setLeft(alipuu);
                } else {
                    vanhempi.setRight(alipuu);
                }
                if (vanhempi != null) {
                    vanhempi.setHeight(Math.max(vanhempi.getLeft().getHeight(), vanhempi.getRight().getHeight()) + 1);
                }
                return;
            }

            p.setHeight(Math.max(pVasen, pOikea) + 1);
            p = p.getParent();
        }
    }

    private Node<T> lisaa(byte key, Object value) {
        Node<T> uusi = new Node(key, value);
        Node<T> p = null;

        if (root == null) {
            root = uusi;
            return root;
        }

        Node<T> x = root;

        while (x != null) {
            p = x;
            if ((int) uusi.getKey() < (int) x.getKey()) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }

        uusi.setParent(p);

        if ((int) uusi.getKey() < (int) p.getKey()) {
            p.setLeft(uusi);
        } else {
            p.setRight(uusi);
        }

        return uusi;
    }

    private void alustaKorkeudet(Node<T> p) {

        if (p.getLeft() == null) {
            pVasen = -1;
        } else {
            pVasen = p.getLeft().getHeight();
        }
        if (p.getLeft() != null) {
            if (p.getLeft().getLeft() == null) {
                pVasenVasen = -1;
            } else {
                pVasenVasen = p.getLeft().getHeight();
            }
        }

        if (p.getRight() == null) {
            pOikea = -1;
        } else {
            pOikea = p.getRight().getHeight();
        }

        if (p.getRight() != null) {
            if (p.getRight().getRight() == null) {
                pOikeaOikea = -1;
            } else {
                pOikeaOikea = p.getLeft().getHeight();
            }
        }

        if(p.getRight()!=null){
        if (p.getRight().getLeft() == null) {
            pOikeaVasen = -1;
        } else {
            pOikeaVasen = p.getLeft().getHeight();
        }
        }

        if (p.getLeft() != null) {
            if (p.getLeft().getRight() == null) {
                pVasenOikea = -1;
            } else {
                pVasenOikea = p.getLeft().getHeight();
            }
        }
    }

    public ArrayList<Byte> keySet() {
        lista = new ArrayList<Byte>();
        alkiot(root);
        return lista;
    }

    private void alkiot(Node<T> root) {
        if (root != null) {
            alkiot(root.getLeft());
            lista.add((byte) root.getKey());
            alkiot(root.getRight());
        }
    }
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
