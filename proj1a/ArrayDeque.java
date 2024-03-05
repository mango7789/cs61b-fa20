public class ArrayDeque<T> {
    public int head, tail, size;
    public T[] deque;

    public ArrayDeque() {
        size = 8; head = size - 1; tail = 0;
        deque = (T[]) new Object[size];
    };


    public void addFirst(T item) {
        if (head == 0) {
            head = size - 1;
            deque[0] = item;
        }
        else {
            deque[head] = item;
            head--;
        }
        // resize
        resize();
    };

    public void addLast(T item) {
        if (tail == size - 1) {
            tail = 0;
            deque[size - 1] = item;
        }
        else {
            deque[tail] = item;
            tail++;
        }
        // resize
        resize();
    };

    public boolean isEmpty() {
        return (size() == 0);
    };

    public int size() {
        return head <= tail - 1 ? tail - head - 1 : size - (head + 1) + tail;
    }

    public void printDeque() {
        if (head <= tail - 1) {
            for(int i = head + 1; i < tail; i++) {
                System.out.println(deque[i]);
            }
        }
        else {
            for(int j = head + 1; j < size; j++) {
                System.out.println(deque[j]);
            }
            for(int k = 0; k < tail; k++) {
                System.out.println(deque[k]);
            }
        }

    };

    public T removeFirst() {
        if (head == size - 1) {
            head = 0;
        }
        else {
            head += 1;
        }
        T removed = deque[head];
        resize();
        return removed;
    };

    public T removeLast() {
        if (tail == 0) {
            tail = size - 1;
        }
        else {
            tail -= 1;
        }
        T removed = deque[tail];
        resize();
        return removed;
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
        if (size == 8) {
            return;
        }
        int total_len = size();
        int new_size;
        // check if we need resize
        if (total_len == size - 1) {
            new_size = size * 2;
        }
        else if (2 * total_len < size) {
            new_size = (int) size / 2;
        }
        else {
            return;
        }
        // create a new deque, use arraycopy to implement the resize
        T[] new_deque = (T[]) new Object[new_size];
        if (head < tail - 1) {
            System.arraycopy(deque, head + 1, new_deque, 0, total_len);
        }
        else if (head == tail - 1) {
            return;
        }
        else {
            int latter_len = size - (head + 1);
            System.arraycopy(deque, head + 1, new_deque, 0, latter_len);
            System.arraycopy(deque, 0, new_deque, latter_len, tail);
        }
        deque = new_deque; size = new_size; head = size - 1; tail = total_len;
    };

}