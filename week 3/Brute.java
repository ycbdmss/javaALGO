import java.util.Scanner;
import java.io.File;
import java.util.Arrays;

public class Brute{
    public static void main(String[] args){
        
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] ps =  new Point[n];
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
        Arrays.sort(ps); 
        for(int i=0;i<n-3;i++){
            for(int j=i+1;j<n-2;j++){
                for(int k=j+1;k<n-1;k++){
                    for(int l=k+1;l<n;l++){
                        double s_ij=ps[i].slopeTo(ps[j]);
                        double s_jk=ps[j].slopeTo(ps[k]);
                        double s_kl=ps[k].slopeTo(ps[l]);
                        if(s_ij==s_jk && s_jk==s_kl){
                            StdOut.print(ps[i]+" -> ");
                            StdOut.print(ps[j]+" -> ");
                            StdOut.print(ps[k]+" -> ");
                            StdOut.print(ps[l]+"\n");
                            ps[i].drawTo(ps[l]);
                        }                           
                    }
                }
            }
        }
    }
}
