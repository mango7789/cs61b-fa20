import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private final int ResizeTimes = 2;
    private int Size = 16;
    private double LoadFactor = 0.75;
    private int Count = 0;
    private ArrayList<Node<K, V>> HashArr = new ArrayList<>(Size);

    private class Node<K, V> {
        private K key;
        private V val;
        private Node<K, V> next = null;

        public Node(K k, V v) {
            this.key = k;
            this.val = v;
        }

        public K getkey() {
            return key;
        }

        public V getval() {
            return val;
        }

        public Node<K, V> Next() {
            return next;
        }

        public void setVal(V v) {
            this.val = v;
        }
        public void setNext(Node<K, V> NewNode) {
            this.next = NewNode;
        }
    }
    private void InitArray() {
        for (int i = 0; i < Size; i++) {
            HashArr.add(null);
        }
    }
    public MyHashMap(){
        InitArray();
    }
    public MyHashMap(int initialSize){
        Size = initialSize;
        InitArray();
    }
    public MyHashMap(int initialSize, double loadFactor){
        Size = initialSize;
        LoadFactor = loadFactor;
        InitArray();
    }


    private void resize() {
        double CurrFactor =  Count / (double) Size;
        if (CurrFactor < 0.75) {
            return;
        }
        ArrayList<Node<K, V>> TempArr = new ArrayList<>(this.HashArr);
        Size *= ResizeTimes;
        HashArr = new ArrayList<>(Size);
        InitArray();
        for (Node<K, V> header : TempArr) {
            if (header == null) {
                continue;
            }
            while (header != null) {
                put(header.getkey(), header.getval());
                Count--;
                header = header.Next();
            }
        }

    }
    private int ComputeIndex(K k) {
        return Math.floorMod(Objects.hashCode(k), Size);
    }
    @Override
    public void clear(){
        HashArr = new ArrayList<>(Size);
        InitArray();
        Count = 0;
    }

    @Override
    public boolean containsKey(K k){
        return keySet().contains(k);
    }

    @Override
    public V get(K k){
        Node<K, V> IndexHeader = HashArr.get(ComputeIndex(k));
        if (IndexHeader == null) {
            return null;
        }
        while (IndexHeader != null) {
            if (IndexHeader.getkey().equals(k)) {
                return IndexHeader.getval();
            }
            IndexHeader = IndexHeader.Next();
        }
        return null;
    }

    @Override
    public int size() {
        return Count;
    }

    @Override
    public void put(K k, V v) {
        int KeyIndex = ComputeIndex(k);
        Node<K, V> header = HashArr.get(KeyIndex);
        Node<K, V> putNode = new Node<>(k, v);
        Count++;
        if (header == null) {
            HashArr.add(KeyIndex, putNode);
            return;
        }
        while (header.Next() != null) {
            if (header.getkey().equals(k)) {
                header.setVal(v);
                Count--;
                return;
            }
            header = header.Next();
        }
        if (header.getkey().equals(k)) {
            header.setVal(v);
            Count--;
            return;
        }
        header.setNext(putNode);
        resize();
    }

    @Override
    public Set<K> keySet() {
        Set<K> keyset = new HashSet<>();
        for (Node<K, V> header : HashArr) {
            while (header != null) {
                keyset.add(header.getkey());
                header = header.Next();
            }
        }
        return keyset;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    @Override
    public V remove(K k) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K k, V v) {
        throw new UnsupportedOperationException();
    }


}
