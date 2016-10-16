public class Solver {
    private class Node implements Comparable<Node> {
        public Board board;
        public int moves=0;
        public int priority;
        public Node parent;
        
        public Node(Board b,Node p){
            board=b;                        
            
            parent=p;
            if(p!=null)
                moves=p.moves+1;
            priority= moves+b.manhattan();
        }
        
        public int compareTo(Node that) {
            /* YOUR CODE HERE */
            if (this.priority < that.priority) return -1;
            if (this.priority > that.priority) return +1;
            
            return 0;
        }    
    }
    
    private int moves=-1;
    private Boolean flag=true;
    private Node last;
    
    public Solver(Board initial){
        Node start= new Node(initial,null);
        Node twin= new Node(initial.twin(),null);
        MinPQ first= new MinPQ(initial.manhattan());
        MinPQ second= new MinPQ(initial.twin().manhattan());
        
        first.insert(start);
        second.insert(twin);
        while(true){
           
           Node low=(Node)first.delMin();
           
           Node lowa=(Node)second.delMin();
           
           if(lowa.board.isGoal()){
               flag=false;
               last=null;
               moves=-1;
               break;
           }
           
           if(low.board.isGoal()){
               last=low;
               moves=last.moves;               
               break;
           }
           for(Board i: lowa.board.neighbors()){
                if(lowa.parent==null || !i.equals(lowa.parent.board))
                    second.insert(new Node(i,lowa));
            } 
           
            for(Board i: low.board.neighbors()){
                if(low.parent==null || !i.equals(low.parent.board))
                    first.insert(new Node(i,low));
            }                                     
        }      
        
    }// find a solution to the initial board (using the A* algorithm)
    public boolean isSolvable(){
        return flag;
    }// is the initial board solvable?
    public int moves(){
        return moves;
    }// min number of moves to solve initial board; -1 if unsolvable
    public Iterable<Board> solution(){
        Stack solution = new Stack<Board>();
        Node temp=last;
        if(isSolvable()){
            solution.push(temp.board);
            while(temp.parent!=null){
                solution.push(temp.parent.board);
                temp=temp.parent;
            }
            return solution;
        }else{
            return null;
        }
            
        
        
    }// sequence of boards in a shortest solution; null if unsolvable
    public static void main(String[] args){
        In in = new In(args[0]);
        int n = in.readInt();
        int [][] arr=new int[n][n];
        
        for(int i=0; i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j]=in.readInt();
            }
        }
        in.close();
        Board b = new Board(arr);
        Solver s = new Solver(b);
        if(s.isSolvable()){
            for(Board i: s.solution()){
                StdOut.println(i);
            }
            
        }else
            StdOut.println("No solution possible");
        
    }// solve a slider puzzle (given below)
}
