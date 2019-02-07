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
            root.count = 1;
            return;
        }
        avlInsert(root, w);
    }

    /** insert w into the tree, maintaining AVL balance
     *  precondition: the tree is AVL balanced and n is not null */
    private void avlInsert(Node n, String w) {
        bstInsert(n, w);
        rebalance(n);
        resetHeights(n); // rebalancing messes up heights
        n.count++;
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
        if (n != null) {
            rebalance(n.left);
            rebalance(n.right);

            if (bal(n) < -1) {
                if (bal(n.left) < 0) {
                    rightRotate(n);
                } else {
                    leftRotate(n.left);
                    rightRotate(n);
                }
            } else if (bal(n) > 1) {
                if (bal(n.right) < 0) {
                    rightRotate(n.right);
                    leftRotate(n);
                } else {
                    leftRotate(n);
                }
            }
        }
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

    /** print a sideways representation of the tree - root at left,
     * right is up, left is down. */
    private void printTree() {
        printSubtree(root, 0);
    }
    private void printSubtree(Node n, int level) {
        if (n == null) {
            return;
        }
        printSubtree(n.right, level + 1);
        for (int i = 0; i < level; i++) {
            System.out.print("        ");
        }
        System.out.println(n.word);
        printSubtree(n.left, level + 1);
    }

    ///////////////////////////////
    // ENHANCEMENTS
    ///////////////////////////////

    /** Removes a node based on a word in a bst */
    public String bstRemove(String s) {
        return bstRemove(findNode(s, root));
    }

    /** Removes a node based on a node in a bst */
    public String bstRemove(Node n) {
        if (isLeaf(n)) {
            if (n.parent.left == n) {
                n.parent.left = null;
            } else {
                n.parent.right = null;
            }
            n.parent = null;
        } else if (n.right == null) {
            n.left.parent = n.parent;
            n.parent.left = n.left;
            n.left = null;
            n.parent = null;
        } else if (n.left == null) {
            n.right.parent = n.parent;
            n.parent.right = n.right;
            n.right = null;
            n.parent = null;
        } else {
            Node k = findMin(n.right);
            n.word = k.word;
            bstRemove(k);
        }
        return n.word;
    }

    /** Removes a node based on a word in a avl */
    public String avlRemove(String s) {
        return avlRemove(findNode(s, root));
    }

    /** Removes a node based on a node in a bst */
    public String avlRemove(Node n) {
        if (n.count == 1) {
            String s = bstRemove(n);
            rebalance(n);
            resetHeights(n);
            return s;
        }
        n.count--;
        return n.word;
    }

    /** Finds a node based on a word */
    private Node findNode(String s, Node n) {
        if (s.equals(n.word)) {
            return n;
        } else if (s.compareTo(n.word) > 0) {
            findNode(s, n.right);
        } else if (s.compareTo(n.word) < 0) {
            findNode(s, n.left);
        } else {
            System.out.println(s + " not found.");
            return null;
        }
        return null;
    }

    /** Finds the minimum node */
    private Node findMin(Node n) {
        if (n == null) {
            return n;
        }
        return findMin(n.left);
    }

    /** inner class representing a node in the tree. */
    public class Node {
        public String word;
        public Node parent;
        public Node left;
        public Node right;
        public int height;
        public int count = 0;

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
