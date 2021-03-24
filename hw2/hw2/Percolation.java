package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Assert;

public class Percolation {
    private int N;
    private int top;
    private int bot;
    private boolean[][] grid;
    private int openSitesNum;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF noBotUF;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.top = N * N;
        this.bot = N * N + 1;
        grid = new boolean[N][N];
        openSitesNum = 0;
        uf = new WeightedQuickUnionUF(N * N + 2);
        noBotUF = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {
            noBotUF.union(i, virtualTop());
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }
        try {
            validArguments(row, col);
        } catch (IndexOutOfBoundsException e) {
            return;
        }

        grid[row][col] = true;
        this.openSitesNum += 1;
        if (isOpen(row - 1, col)) {
            uf.union(row * N + col, (row - 1) * N + col);
            noBotUF.union(row * N + col, (row - 1) * N + col);
        }
        if (isOpen(row + 1, col)) {
            uf.union(row * N + col, (row + 1) * N + col);
            noBotUF.union(row * N + col, (row + 1) * N + col);
        }
        if (isOpen(row, col - 1)) {
            uf.union(row * N + col, row * N + col - 1);
            noBotUF.union(row * N + col, row * N + col - 1);
        }
        if (isOpen(row, col + 1)) {
            uf.union(row * N + col, row * N + col + 1);
            noBotUF.union(row * N + col, row * N + col + 1);
        }
        if (row == 0) {
            uf.union(col, virtualTop());
        }
        if (row == N - 1) {
            uf.union(row * N + col, virtualBottom());
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        try {
            validArguments(row, col);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        try {
            validArguments(row, col);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        if (!isOpen(row, col)) {
            return false;
        }
        return noBotUF.connected(row * N + col, virtualTop());
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSitesNum;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(virtualTop(), virtualBottom());
    }

    private void validArguments(int row, int col) {
        if (row >= grid.length || col >= grid.length || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int virtualTop() {
        return this.top;
    }

    private int virtualBottom() {
        return this.bot;
    }

    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        Assert.assertFalse(p.percolates());
        p.open(0, 1);
        Assert.assertTrue(p.isOpen(0, 1));
        Assert.assertEquals(1, p.openSitesNum);
        p.open(1, 1);
        p.open(2, 1);
        p.open(3, 1);
        Assert.assertTrue(p.isFull(3, 1));
        p.open(3, 2);
        Assert.assertFalse(p.percolates());
        p.open(4, 2);
        Assert.assertTrue(p.percolates());
    }
}
