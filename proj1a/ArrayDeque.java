public class ArrayDeque<T> {
    public int head, tail, size;
    public T[] deque;

    public ArrayDeque() {
        int head = -1, tail = 0, size = 8;
        T[] deque = (T[]) new Object[size];
    };


    public void addFirst(T item) {
        if (head == -1) {
            head = size - 1;
            deque[0] = item;
        }
        else {
            deque[head] = item;
            head--;
        }
        // resize
        if (head == -1) {
            if (tail == size) {
                resize();
            }
        }
        else {
            if (head + 1 == tail) {
                resize();
            }
        }
    };

    public void addLast(T item) {
        if (tail == size) {
            tail = 0;
            deque[0] = item;
        }
        else {
            deque[tail-1] = item;
            tail++;
        }
        // resize
        if (tail == size) {
            if (head == -1) {
                resize();
            }
        }
        else {
            if (head + 1 == tail) {
                resize();
            }
        }
    };

    public boolean isEmpty() {
        return (size == 0);
    };

    public int size() {
        return size;
    }

    public void printDeque() {
        if (head < tail - 1) {
            for(int i = head + 1; i < tail; i++) {
                System.out.println(deque[i]);
            }
        }
        else {
            for(int j = head + 1; j < size; j++) {
                System.out.println(deque[j]);
            }
            for(int k = 0; k < tail - 1; k++) {
                System.out.println(deque[k]);
            }
        }

    };

    public T removeFirst() {

    };

    public T removeLast() {

    };

    public T get(int index) {
        int pointer = head + 1 < size ? head + 1 : 0;
        while (index > 0) {
            pointer = pointer + 1 < size ? pointer + 1 : 0;
            index--;
        }
        return deque[pointer];
    };

    public void resize() {

    };

}