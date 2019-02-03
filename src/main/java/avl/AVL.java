package avl;

public class AVL {

    public Node root;

    private int size;

    public int getSize() {
        return size;
    }

    /** find w in the tree. return the node containing w or
     * null if not found */
    public Node search(String w) {
        return search(root, w);
    }
    private Node search(Node n, String w) {
        if (n == null) {
            return null;
        }
        if (w.equals(n.word)) {
            return n;
        } else if (w.compareTo(n.word) < 0) {
            return search(n.left, w);
        } else {
            return search(n.right, w);
        }
    }

    /** insert w into the tree as a standard BST, ignoring balance */
    public void bstInsert(String w) {
        if (root == null) {
            root = new Node(w);
            size = 1;
            return;
        }
        bstInsert(root, w);
    }

    /* insert w into the tree rooted at n, ignoring balance
     * pre: n is not null */
    private void bstInsert(Node n, String w) {
        if (w.compareTo(n.word) > 0) {
            if (n.right == null) {
                n.right = new Node(w, n);
                size += 1;
            } else {
                bstInsert(n.right, w);
            }
        } else if (w.compareTo(n.word) < 0) {
            if (n.left == null) {
                n.left = new Node(w, n);
                size += 1;
            } else {
                bstInsert(n.left, w);
            }
        }
    }

    /** insert w into the tree, maintaining AVL balance
     *  precondition: the tree is AVL balanced */
    public void avlInsert(String w) {
        //TODO    
    }

    /** insert w into the tree, maintaining AVL balance
     *  precondition: the tree is AVL balanced and n is not null */
    private void avlInsert(Node n, String w) {
        //TODO    
    }

    /** do a left rotation: rotate on the edge from x to its right child.
     *  precondition: x has a non-null right child */
    public void leftRotate(Node x) {
        //TODO    
    }

    /** do a right rotation: rotate on the edge from x to its left child.
     *  precondition: n has a non-null left child */
    public void rightRotate(Node y) {
        //TODO    
    }

    public void rebalance(Node n) {
        //TODO    
    }

    /** inner class representing a node in the tree. */
    public class Node {
        public String word;
        public Node parent;
        public Node left;
        public Node right;
        public int height;

        public String toString() {
            return word + "(" + height + ")";
        }

        /** constructor: gives default values to all fields */
        public Node() { }

        /** constructor: sets only word */
        public Node(String w) {
            word = w;
        }

        /** constructor: sets word and parent fields */
        public Node(String w, Node p) {
            word = w;
            parent = p;
        }

        /** constructor: sets all fields */
        public Node(String w, Node p, Node l, Node r) {
            word = w;
            parent = p;
            left = l;
            right = r;
        }
    }

}
