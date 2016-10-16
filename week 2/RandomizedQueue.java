import java.util.Iterator;
import java.util.NoSuchElementException;

 @SuppressWarnings("unchecked")
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int tail;
    
    public RandomizedQueue(){
         a = (Item[]) new Object[1];
         tail=0;
    }// construct an empty randomized queue
    public boolean isEmpty(){
        return tail==0;
    }// is the queue empty?
    public int size(){
        return tail;
    }// return the number of items on the queue
    
    private void resize(int capa){
        Item[] copy = (Item[]) new Object[capa];
        for (int i=0; i<tail; i++)
            copy[i]=a[i];
        a=copy;
        
    }
    public void enqueue(Item item){
        if(item==null) throw new NullPointerException();
        if(a.length== tail)
            resize(a.length*2);
        a[tail]=item;
        tail++;
    }// add the item
    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        int n=StdRandom.uniform(tail);
        Item item = a[n];    
        item=a[n];   
        a[n]=a[tail-1];
        a[tail-1]=null;
        tail--;
        if(tail<0.25*a.length)
            resize(a.length/2+1);
        return item;
    }// delete and return a random item
    public Item sample(){
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return a[StdRandom.uniform(tail)];
    }// return (but do not delete) a random item
    
    
    public Iterator<Item> iterator(){
        return new ArrayIterator();
    }// return an iterator over items in order from front to end
    
    private class ArrayIterator implements Iterator<Item>
    {
        private int i = 0;
        private int b[]=new int[tail];
        
        public ArrayIterator(){
            KnutchShuffle();
        }
            
        public boolean hasNext() { return i < tail; }
        
        public void remove() {
            /* not supported */
            throw new UnsupportedOperationException();
        }
        public Item next() { 
            if (!hasNext()) throw new NoSuchElementException();
            return a[b[i++]]; }
        
        
        private void KnutchShuffle(){
            
            for(int i=0; i< tail; i++){
                b[i]=i;
            }
            for(int i=1; i<tail; i++){
                int m=StdRandom.uniform(i+1);
                swap(m,i);
            }  
        }
        private void swap(int m,int i){
            int temp;
            temp=b[m];
            b[m]=b[i];
            b[i]=temp;
        }
        
    }
    
    public static void main(String[] args){
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        
        q.enqueue("A");   
        q.enqueue("B");                    
        q.enqueue("C");            
        Iterator iter= q.iterator();
        
        for(int i=0; i<3;i++){
            StdOut.println(iter.next());    
        }
        
    }// unit testing
}