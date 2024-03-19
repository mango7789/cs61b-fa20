import org.w3c.dom.Node;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;
    private int size = 0;

    private class Node {
        public final K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    @Override
    public Set<K> keySet() {
        HashSet<K> HSet = new HashSet<>();
        AddKey(root, HSet);
        return HSet;
    }

    public void AddKey(Node node, Set<K> set) {
        if (node == null) {
            return;
        }
        set.add(node.key);
        AddKey(node.left, set);
        AddKey(node.right, set);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public void PrintInOrder() {
        PrintInOrder(root);
    }

    public void PrintInOrder(Node node) {
        if (node == null) {
            return;
        }
        PrintInOrder(node.left);
        System.out.println(node.key.toString() + "-->" + node.value.toString());
        PrintInOrder(node.right);
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    public boolean containsKey(Node node, K key) {
        if (node == null) {
            return false;
        }
        int Compare = key.compareTo(node.key);
        if (Compare < 0) {
            return containsKey(node.left, key);
        } else if (Compare > 0) {
            return containsKey(node.right, key);
        }
        return true;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    public V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        }
        return node.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        Node node = root;
        Node prev = null;
        int cmp = 0;
        while (node != null) {
            cmp = key.compareTo(node.key);
            if (cmp == 0) {
                node.value = value;
                return;
            } else if (cmp > 0) {
                prev = node;
                node = node.right;
            } else {
                prev = node;
                node = node.left;
            }
        }
        Node NewNode = new Node(key, value);
        // direction (left/right)
        if (cmp < 0) {
            prev.left = NewNode;
        } else if (cmp > 0) {
            prev.right = NewNode;
        } else if (node == null) {
            root = NewNode;
        }
        // increment the size by 1
        size++;
    }

    @Override
    public V remove(K key) {
        Node node = root;
        Node prev = null;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                prev = node;
                node = node.left;
            } else if (cmp > 0) {
                prev = node;
                node = node.right;
            } else {
                break;
            }
        }
        // don't find the key
        if (node == null) {
            return null;
        }
        // leaf node
        if (node.left == null && node.right == null) {
            V val = node.value;
            if (prev.right == node) {
                prev.right = null;
            }
            else {
                prev.left = null;
            }
            size--;
            return val;
        }
        // others
        if (prev == null) {
            Node pointer = root.right;
            V val = root.value;
            if (pointer == null) {
                root = root.left;
                size--;
                return val;
            }
            while (pointer != null) {
                pointer = pointer.left;
            }
            pointer.left = root.left;
            root = root.right;
            return val;
        } else {
            return root.value;
        }
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
