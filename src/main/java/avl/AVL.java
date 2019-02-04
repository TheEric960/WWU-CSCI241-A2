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
        n.height = height(n);
    }

    /** insert w into the tree, maintaining AVL balance
     *  precondition: the tree is AVL balanced */
    public void avlInsert(String w) {
        if (root == null) {
            root = new Node(w);
            size = 1;
            return;
        }
        avlInsert(root, w);
    }

    /** insert w into the tree, maintaining AVL balance
     *  precondition: the tree is AVL balanced and n is not null */
    private void avlInsert(Node n, String w) {
        bstInsert(n, w);
        rebalance(n);
    }

    /** do a left rotation: rotate on the edge from x to its right child.
     *  precondition: x has a non-null right child */
    public void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null)
            y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    /** do a right rotation: rotate on the edge from x to its left child.
     *  precondition: n has a non-null left child */
    public void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != null)
            x.right.parent = y;
        x.parent = y.parent;
        if (y.parent == null)
            root = x;
        else if (y == y.parent.right)
            y.parent.right = x;
        else
            y.parent.left = x;
        x.right = y;
        y.parent = x;
    }

    public void rebalance(Node n) {
        if (bal(n) < -1) {
            if (bal(n.left) < 0) {
                rightRotate(n);
            } else {
                leftRotate(n.left);
                rightRotate(n);
            }
        } else if (bal(n) > 1){
            if (bal(n.right) < 0) {
                rightRotate(n.right);
                leftRotate(n);
            } else {
                leftRotate(n);
            }
        }
        resetHeights(n);
    }

    /** returns the balance of the tree */
    private int bal(Node n) {
        return height(n.right) - height(n.left);
    }

    /** fixes heights after rebalancing */
    private void resetHeights(Node n) {
        n.height = height(n);
        if (n.right != null)
            resetHeights(n.right);
        if (n.left != null)
            resetHeights(n.left);
    }

    /** return the height of the tree rooted at n */
    private int height(Node n) {
        if (n == null) {
            return -1;
        } else if (isLeaf(n)) {
            return 0;
        }

        // want branch with greatest height
        if (height(n.left) > height(n.right)) {
            return 1 + height(n.left);
        } else {
            return 1 + height(n.right);
        }
    }

    /** return true if Node n is a leaf node. a null node is not considered
     * a leaf. */
    private boolean isLeaf(Node n) {
        return n != null && n.left == null && n.right == null;
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
