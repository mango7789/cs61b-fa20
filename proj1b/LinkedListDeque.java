public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    public Node head;
    public Node tail;
    public int size;

    public LinkedListDeque() {
        head = new Node(null, null, null);
        tail = new Node(null, head, null);
        head.next = tail;
        size = 0;
    };

    public LinkedListDeque(T value) {
        head = new Node(null, null, null);
        tail = new Node(null, null, null);
        Node middle = new Node(value, head, tail);
        head.next = middle;
        tail.prev = middle;
        size = 1;
    }

    @Override
    public void addFirst(T item) {
        Node first = new Node(item, head, head.next);
        head.next.prev = first;
        head.next = first;
        size++;
    };

    @Override
    public void addLast(T item) {
        Node last = new Node(item, tail.prev, tail);
        tail.prev.next = last;
        tail.prev = last;
        size++;
    };

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node pointer = head.next;
        while (pointer != tail) {
            System.out.println(pointer.item);
            pointer = pointer.next;
        }
        return;
    };

    @Override
    public T removeFirst() {
        T item = head.next.item;
        head.next = head.next.next;
        head.next.prev = head;
        size--;
        return item;
    };

    @Override
    public T removeLast() {
        T item = tail.prev.item;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        size--;
        return item;
    };

    @Override
    public T get(int index) {
        assert index >= 0;
        Node pointer = head.next;
        while (index > 0 && pointer != null) {
            pointer = pointer.next;
            index--;
        }
        return pointer.item;
    };

    public T getRecursive(int index, Node head) {
        assert index >= 0;
        if (index == 0) {
            return head.next.item;
        } else {
            return getRecursive(index - 1, head.next);
        }
    }
}
