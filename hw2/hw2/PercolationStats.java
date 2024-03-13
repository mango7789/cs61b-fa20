package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] frac;
    private int t;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("");
        }
        frac = new double[T];
        t = T;
        int totalSites = N * N;
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                }
            }
            frac[i] = (double) p.numberOfOpenSites() / totalSites;
        }
    };

    public double mean() {
        return StdStats.mean(frac);
    };

    public double stddev() {
        return StdStats.stddev(frac);
    };

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(t);
    };

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(t);
    };

}
