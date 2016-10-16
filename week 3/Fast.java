import java.util.Arrays;

public class Fast {
    public static void main(String[] args){
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] ps =  new Point[n];
        Point[] slope =  new Point[n-1];
        for(int i=0;i<n;i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point point = new Point(x, y);
            ps[i]= point;
        }
        in.close();
        
        
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        for(int i=0;i<n;i++) ps[i].draw();
        
        for(int i=0;i<n;i++){
            for(int j=0; j<n;j++){
            if(j<i) slope[j]=ps[j];
            if(j>i) slope[j-1]=ps[j];
            }
            
            Arrays.sort(slope,ps[i].SLOPE_ORDER);
            
            int c=0;
            while(c<n-3){
                double s_ij=ps[i].slopeTo(slope[c]);
                double s_jk=slope[c].slopeTo(slope[c+1]);
                double s_kl=slope[c+1].slopeTo(slope[c+2]);
                
                if(s_ij==s_jk && s_jk==s_kl ){
                    
                    Point same[]= new Point[n];
                    same[0]=ps[i];
                    for(int k=0; k<3;k++){
                        same[k+1]=slope[c+k];
                    }
                    
                    int temp=c+3;
                    
                    while(temp<n-1){
                        if(ps[i].slopeTo(slope[temp])!=s_ij)
                            break;
                        
                        same[temp-c+1]=slope[temp];   
                        ++temp;
                        
                    }
                    
                    Arrays.sort(same,0,temp-c+1);
                    
                    if(same[0].compareTo(ps[i])==0){
                        for(int k=0; k<temp-c;k++){
                            StdOut.print(same[k]+" -> ");
                        }
                        
                        StdOut.print(same[temp-c]+"\n");
                        
                        
                        ps[i].drawTo(same[temp-c]);
                        
                    }
                    c=temp-1;
                    
                }
                c++;   
                
            }            
        }
    }
}