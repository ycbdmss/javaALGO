public class Percolation {
    private boolean [][] grid;
    private WeightedQuickUnionUF un;
    private WeightedQuickUnionUF un1;
    private int size;
    
    public Percolation(int N) throws IllegalArgumentException {
        // create N-by-N grid, with all sites blocked
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new boolean [N+2][N+2];
        un = new WeightedQuickUnionUF(N*N + 2);
        un1 = new WeightedQuickUnionUF(N*N + 1);
        
        size= N;
        
    }   
    public void open(int i, int j) throws IndexOutOfBoundsException {
        if (i > size || j > size || j<1 || i<1 ) {
            throw new IndexOutOfBoundsException();
        }
        if (!isOpen(i, j)) {
            
            grid[i][j]= true;
            if (grid[i][j-1] == true) {
                un.union(index(i, j), index(i, j-1));
                un1.union(index(i, j), index(i, j-1));
            }
            if (grid[i-1][j] == true){
                un.union(index(i, j), index(i-1, j));
                un1.union(index(i, j), index(i-1, j));
            }
            if (grid[i][j+1] == true) {
                un.union(index(i, j), index(i, j+1));
                un1.union(index(i, j), index(i, j+1));
            }
            if (grid[i+1][j] == true){
                un.union(index(i, j), index(i+1, j));
                un1.union(index(i, j), index(i+1, j));
            }
            if (i == 1){
                un.union(index(i, j), 0);
                un1.union(index(i, j), 0);               
            }
            if (i == size){
                un.union(index(i, j), size*size+1);
            }
        }
        
        
        
    }// open site (row i, column j) if it is not open already
    public boolean isOpen(int i, int j) throws IndexOutOfBoundsException{
        if (i > size || j > size || j<1 || i<1){
            throw new IndexOutOfBoundsException();
        }
        return grid[i][j];
    }
    // is site (row i, column j) open?
    
    public boolean isFull(int i, int j) throws IndexOutOfBoundsException {
        if (i > size || j > size || j<1 || i<1){
            throw new IndexOutOfBoundsException();
        }
        return un1.connected(index(i,j),0);
    }
// is site (row i, column j) full?
    
    public boolean percolates() {
        return un.connected(0, size*size+1);
    }             // does the system percolate?
    
    private int index(int i, int j) {
        return (i-1)*size+j;
        
    }
    
    public static void main(String[] args) {
        int N = StdIn.readInt();
        Percolation perc = new Percolation(N);
        while (StdIn.hasNextChar()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            
            perc.open(p, q);
            StdOut.println(p + " " + q);
        }
                
        StdOut.println(perc.percolates());
                
    }
    
    
    // test client (optional)
}
