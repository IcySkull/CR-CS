import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] ary;
    private static final double CONFIDENCE_95 = 1.96;

    public PercolationStats(int n, int trials) // perform trials independent experiments on an n-by-n grid
    {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("n and trials must be greater than 0");

        ary = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            int count = 0;
            while (!p.percolates()) {
                int r = StdRandom.uniform(n) + 1;
                int c = StdRandom.uniform(n) + 1;
                if (!p.isOpen(r, c)) {
                    p.open(r, c);
                    count++;
                }
            }
            ary[i] = (double) count / (n*n);
        }
    }

    public double mean() // sample mean of percolation threshold
    {
        return StdStats.mean(ary);
    }

    public double stddev() // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(ary);
    }

    public double confidenceLo() // low endpoint of 95% confidence interval
    {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(ary.length);
    }

    public double confidenceHi() // high endpoint of 95% confidence interval
    {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(ary.length);
    }

    public static void main(String[] args) // test client (described below)
    {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());
        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}