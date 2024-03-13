package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    int N;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
    };

    private void check_index(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void open(int row, int col) {
        check_index(row, col);
    };

    public boolean isOpen(int row, int col) {
        check_index(row, col);

    };

    public boolean isFull(int row, int col) {
        check_index(row, col);

    };

    public int numberOfOpenSites() {

    };

    public boolean percolates() {

    };

    public static void main(String[] args) {

    };
}
