import java.util.Arrays;
import java.util.ArrayList;

public class Board {
    private int[][] board;
    private int dim;
    private int ham;
    
    public Board(int[][] blocks) {
        dim=blocks.length;
        board=new int[dim][dim];
        for(int i=0; i<dim;i++){
            for(int j=0;j<dim;j++){
                board[i][j]=blocks[i][j];
            }
        }
               
    }// construct a board from an N-by-N array of blocks
                                           // (where blocks[i][j] = block in row i, column j)
    public int dimension() {
        return dim;
    }// board dimension N
    public int hamming() {
        int count=0;
        for(int i=0; i<dim;i++){
            for(int j=0;j<dim;j++){
                if((i*dim+j+1)!=board[i][j])
                    count++;
            }
        }
        ham=count-1;
        return ham;
    }// number of blocks out of place
    public int manhattan() {
        int count=0;
        for(int i=0; i<dim;i++){
            for(int j=0;j<dim;j++){
                int n=board[i][j];
                if(n==0)
                    continue;
                
                int x=(n-1)/dim;
                int y=(n-1)%dim;
                count+=Math.abs(x-i)+Math.abs(y-j);
            }
        }
        
        return count;
    }// sum of Manhattan distances between blocks and goal
    public boolean isGoal(){
        return this.hamming()==0;
    }// is this board the goal board?
    public Board twin() {
        Board twin=new Board(board);
        
        
        for(int i=0; i<dim;i++){
            
            if(twin.board[i][0]!=0 && twin.board[i][1]!=0){
                int temp=twin.board[i][0];
                twin.board[i][0]=twin.board[i][1];
                twin.board[i][1]=temp;
                break;
            }                    
            
        }       
        return twin;    
    }// a boadr that is obtained by exchanging two adjacent blocks in the same row
    public boolean equals(Object y) {
        if(y==null)
            return false;
        try{
            Board that=(Board) y;
            if(that.board.length!=dim || that.board[0].length!=dim)
                return false;
            for(int i=0; i<dim;i++){
                for(int j=0;j<dim;j++){
                    if(that.board[i][j]!=board[i][j])
                        return false;
                }
            }
            return true;
            
        }catch( ClassCastException e){
            return false;
        }
        
        
    }// does this board equal y?
    public Iterable<Board> neighbors(){
        ArrayList<Board> bs= new ArrayList<Board>();
        int x=0,y=0;
        for(int i=0; i<dim;i++){
            for(int j=0;j<dim;j++){
                if(board[i][j]==0){
                    x=i; y=j;
                }                    
            }
        }
        
        if(x>0)
            bs.add(swap(board,x,y,x-1,y));
        if(x+1<dim)
            bs.add(swap(board,x,y,x+1,y));   
        if(y>0)  
            bs.add(swap(board,x,y,x,y-1)); 
        if(y+1<dim)
            bs.add(swap(board,x,y,x,y+1)); 
        return bs;
        
    }// all neighboring boards
    private Board swap(int[][] bs,int s,int t,int a,int b){
        Board bb=new Board(bs);
        int temp=bb.board[s][t];
        bb.board[s][t]=bb.board[a][b];
        bb.board[a][b]=temp;  
        return bb;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dim + "\n");
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }// string representation of this board (in the output format specified below)

    public static void main(String[] args){
        
        In in = new In("/8puzzle/"+args[0]);
        int n = in.readInt();
        int [][] arr=new int[n][n];
        
        for(int i=0; i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j]=in.readInt();
            }
        }
        Board b = new Board(arr);
        in.close();
        StdOut.println(b);
        StdOut.println(b.manhattan());
        StdOut.println(b.hamming());
        StdOut.println(b.isGoal());
        StdOut.println(b.twin());
        StdOut.print(b.equals(b.twin()));
        
    }// unit tests (not graded)
}