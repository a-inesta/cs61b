package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import java.util.Random;

public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    private int N;
    private int T;
    private PercolationFactory pf;
    private int[] count;
    private double mean;
    private double stddev;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        this.N = N;
        this.T = T;
        this.pf = pf;
        this.mean = 0;
        this.stddev = 0;
        this.count = new int[T];
        simulation();
    }

    private void simulation() {
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            count[i] = totalOpenSites(p);
        }
        this.mean = StdStats.mean(count);
        this.stddev = StdStats.stddev(count);
    }
    private int totalOpenSites(Percolation p) {
        while (p.percolates()) {
            p.open(StdRandom.uniform(N), StdRandom.uniform(N));
        }
        return p.numberOfOpenSites();
    }
    public double mean() {
        return this.mean;
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean - 1.96 * stddev / Math.sqrt((double) T);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean + 1.96 * stddev / Math.sqrt((double) T);
    }
}
