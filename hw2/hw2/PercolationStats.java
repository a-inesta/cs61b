package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    private int N;
    private int T;
    private PercolationFactory pf;
    private int[] count;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.T = T;
        this.pf = pf;
        this.count = new int[T];
        simulation();
    }

    private void simulation() {
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            count[i] = totalOpenSites(p);
        }
    }

    //if random site isOpen ,the program will continue instead of calling open().
    private int totalOpenSites(Percolation p) {
        while (!p.percolates()) {
            int row = StdRandom.uniform(N);
            int col = StdRandom.uniform(N);
            while (p.isOpen(row, col)) {
                row = StdRandom.uniform(N);
                col = StdRandom.uniform(N);
            }
            p.open(row, col);
        }
        return p.numberOfOpenSites();
    }

    public double mean() {
        return StdStats.mean(count);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(count);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt((double) T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt((double) T);
    }

}
