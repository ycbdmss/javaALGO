public class Subset {
    public static void main(String[] args){
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
           
            q.enqueue(item); 
            
        }
        while(q.size()>k){
            q.dequeue();
        }
        for(int i=0;i<k;i++){
            StdOut.println(q.dequeue());
        }
        
    }
}