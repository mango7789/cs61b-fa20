package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private int size;
    private int top;
    private int down;
    private int numOpenSites = 0;
    private int[][] neighbors = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufExcludeDown;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        size = N;
        grid = new boolean[N][N];
        top = 0; down = N * N + 1;
        uf = new WeightedQuickUnionUF(down + 1);
        ufExcludeDown = new WeightedQuickUnionUF(down);
    };

    private void check_index(int row, int col) {
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int xyTo1D(int row, int col) {
        return row * size + col + 1;
    }

    public void open(int row, int col) {
        check_index(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOpenSites++;
        }

        int Curr1D = xyTo1D(row, col);

        if (row == 0) {
            uf.union(Curr1D, top);
            ufExcludeDown.union(xyTo1D(row, col), top);
        }
        if (row == size - 1) {
            uf.union(Curr1D, down);
        }
        // union with the neighbors
        for (int[] neighbor : neighbors) {
            int AdjRow = row + neighbor[0];
            int AdjCol = col + neighbor[1];
            if (AdjRow >= 0 && AdjRow < size && AdjCol >= 0 && AdjCol < size) {
                if (isOpen(AdjRow, AdjCol)) {
                    int AdjCurr1D = xyTo1D(AdjRow, AdjCol);
                    uf.union(Curr1D, AdjCurr1D);
                    ufExcludeDown.union(Curr1D, AdjCurr1D);
                }
            }
        }
    };

    public boolean isOpen(int row, int col) {
        check_index(row, col);
        return grid[row][col];
    };

    public boolean isFull(int row, int col) {
        check_index(row, col);
        return ufExcludeDown.connected(xyTo1D(row, col), top);
    };

    public int numberOfOpenSites() {
        return numOpenSites;
    };

    public boolean percolates() {
        return uf.connected(top, down);
    };

    public static void main(String[] args) {

    };
}
