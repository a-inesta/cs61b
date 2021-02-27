package lab9;

import edu.princeton.cs.algs4.SET;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the value mapped to by KEY in the subtree rooted in P.
     * or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (key.compareTo(p.key) > 0) {
            return getHelper(key, p.right);
        } else if (key.compareTo(p.key) < 0) {
            return getHelper(key, p.left);
        }
        return p.value;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }
        return getHelper(key, root);
    }

    /**
     * Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
     * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key, value);
        }
        if (key.compareTo(p.key) > 0) {
            p.right = putHelper(key, value, p.right);
        } else if (key.compareTo(p.key) < 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.value = value;
        }

        return p;
    }

    /**
     * Inserts the key KEY
     * If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        keySetHelper(keys, root);
        return keys;
    }

    private void keySetHelper(Set<K> keys, Node p) {
        keys.add(p.key);
        if (p.left != null) {
            keySetHelper(keys, p.left);
        }
        if (p.right != null) {
            keySetHelper(keys, p.right);
        }
    }

    /* Returns the minimum key of p.*/
    private Node minimum(Node p) {
        if (p.left == null) {
            return p;
        }
        return minimum(p.left);
    }

    /**
     * Removes minimum Node in the tree whose root is p.
     * returns new root after deleting
     */
    private Node removeMin(Node p) {
        if (p.left == null) {
            Node rightChild = p.right;
            p.right = null;
            size--;
            return rightChild;
        }
        p.left = removeMin(p.left);
        return p;
    }

    /**
     * Removes KEY from the tree if present
     * returns VALUE removed,
     * null on failed removal.
     */
    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(key, root);
            return node.value;
        }
        return null;
    }

    private Node remove(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (key.compareTo(p.key) > 0) {
            p.right = remove(key, p.right);
            return p;
        } else if (key.compareTo(p.key) < 0) {
            p.left = remove(key, p.left);
            return p;
        } else {
            //待删除节点左子树为空
            if (p.left == null) {
                Node rightChild = p.right;
                p.right = null;
                size--;
                return rightChild;
            }
            //待删除节点右子树为空
            if (p.right == null) {
                Node leftChild = p.left;
                p.left = null;
                size--;
                return leftChild;
            }
            //待删除节点左右子树均非空
            //找到比删除节点大的最小节点，即删除节点右子树的最小节点
            //用这个节点顶替删除节点的位置
            Node successor = minimum(p.right);
            successor.right = removeMin(p.right);
            successor.left = p.left;
            p.left = p.right = null;
            return successor;
        }
    }

    private Node getNode(Node p, K key) {
        if (p == null) {
            return null;
        }
        if (key.compareTo(p.key) > 0) {
            return getNode(p.right, key);
        } else if (key.compareTo(p.key) < 0) {
            return getNode(p.left, key);
        }
        return p;
    }


    /**
     * Removes the key-value entry for the specified key only if it is
     * currently mapped to the specified value.  Returns the VALUE removed,
     * null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (get(key) == value) {
            return remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("hello", 5);
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);
        bstmap.remove("cat");
        Set<String> bstset = bstmap.keySet();
        System.out.println(bstset.toString());
    }
}
