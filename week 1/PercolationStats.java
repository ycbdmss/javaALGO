public class PercolationStats {
    private int times;
    private double []stat;
    
    public PercolationStats(int N, int T) throws IllegalArgumentException {
        stat = new double[T];
        times = T;
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        for (int i=0; i < T; i++){
            Percolation p=new Percolation(N);
            while (!p.percolates()) {
                int k = StdRandom.uniform(N)+1;
                int l = StdRandom.uniform(N)+1;    
                if (!p.isOpen(k,l)) {
                    p.open(k,l);
                    stat[i]++;
                }
            }
            stat[i] = stat[i] / (N*N);
        }
    }
// perform T independent experiments on an N-by-N grid
    public double mean() {
        return StdStats.mean(stat);
    }
// sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(stat);
    }
// sample standard deviation of percolation threshold
    public double confidenceLo() {
        return mean() - 1.96*stddev()/Math.sqrt(times);
    }
// low  endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96*stddev()/Math.sqrt(times);
    }
    // high endpoint of 95% confidence interval
    
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        PercolationStats ps= new PercolationStats(n,t);
        StdOut.println(ps.mean()+","+ps.stddev()+","+ps.confidenceLo()+","+ps.confidenceHi());
    }
// test client (described below)
}