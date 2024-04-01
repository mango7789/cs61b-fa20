package bearmaps.proj2ab;

import java.util.*;


public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    private List<PriorityNode> items;
    private Map<T, Integer> indices;

    private class PriorityNode implements Comparable<ArrayHeapMinPQ.PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(ArrayHeapMinPQ.PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((ArrayHeapMinPQ.PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

    public ArrayHeapMinPQ() {
        this.items = new ArrayList<>();
        this.indices = new HashMap<>();
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("The item aleardy exists in the Priority Queue!");
        }
        items.addLast(new PriorityNode(item, priority));
        indices.put(item, size() - 1);
        heapifyUp(size() - 1);
    }

    @Override
    public boolean contains(T item) {
        return getNodeIndex(item) != -1;
    }

    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("The Priority Queue is empty!");
        }
        return items.getFirst().getItem();
    }

    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("The Priority Queue is empty!");
        }
        T smallestItem = getSmallest();
        swap(0, size() - 1);
        items.removeLast();
        indices.remove(smallestItem);
        heapifyDown(0);
        return smallestItem;
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        int nodeIndex = getNodeIndex(item);
        if (nodeIndex == -1) {
            throw new NoSuchElementException("The item doesn't exist in the Priority Queue!");
        }
        PriorityNode currNode = items.get(nodeIndex);
        // if the priority decreases, heapify up
        if (currNode.getPriority() > priority) {
            currNode.setPriority(priority);
            heapifyUp(nodeIndex);
        }
        // if the priority increases, heapify down
        else if (currNode.getPriority() < priority){
            currNode.setPriority(priority);
            heapifyDown(nodeIndex);
        } else {
            return;
        }
    }

    private int leftChild(int k) {
        return 2 * k + 1;
    }

    private int rightChild(int k) {
        return 2 * k + 2;
    }

    private int parent(int k) {
        return (k - 1) / 2;
    }

    private void swap(int i, int j) {
        // swap in the arraylist
        PriorityNode temp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, temp);
        // swap in the map
        T item1 = items.get(i).getItem();
        T item2 = items.get(j).getItem();
        indices.put(item1, j);
        indices.put(item2, i);
    }

    private void heapifyDown(int i) {
        int smallestIndex = i;
        int leftChild = leftChild(i), rightChild = rightChild(i);
        // check if left node is smaller than root
        if (leftChild < size() && items.get(leftChild).compareTo(items.get(smallestIndex)) < 0) {
            smallestIndex = leftChild;
        }
        // check if right node is smaller than root
        if (rightChild < size() && items.get(rightChild).compareTo(items.get(smallestIndex)) < 0) {
            smallestIndex = rightChild;
        }
        // recursively heapify the PQ
        if (smallestIndex != i) {
            swap(i, smallestIndex);
            heapifyDown(smallestIndex);
        }
    }

    private void heapifyUp(int i) {
        int parent = parent(i);
        if (i == 0 || parent < 0 || items.get(parent).compareTo(items.get(i)) < 0) { return; }
        swap(parent, i);
        heapifyUp(parent);
    }

    private int getNodeIndex(T item) {
        Integer index = indices.get(item);
        return index != null ? index : -1;
    }

    public void print() {
        for (int i = 0; i < size(); i++) {
            System.out.print(items.get(i).getItem() + " ");
            System.out.print(items.get(i).getPriority() + " --> ");
        }
        System.out.println();
    }
}
