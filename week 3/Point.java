/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlope();   
      // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }
    private class BySlope implements Comparator<Point>{
        public int compare(Point p1,Point p2){
            
            double slopeP1 = slopeTo(p1);  
            double slopeP2 = slopeTo(p2);  
            if (slopeP1 == slopeP2) return 0;  
            if (slopeP1 < slopeP2) return -1;  
            else return +1;  
        }
    }
    private class ByPos implements Comparator<Point>{
        public int compare(Point p1,Point p2){
            
        if (p1.y < p2.y) return -1;
        if (p1.y > p2.y) return +1;
        if (p1.x < p2.x) return -1;
        if (p1.x > p2.x) return +1;
        return 0;  
        }
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if(that == null) throw new NullPointerException();
        if(this.compareTo(that)==0)
            return -1/0.0;
        if ((that.x-this.x) == 0 ) return 1/0.0;  
        if((that.y-this.y)==0) return +0.0;
         
        return (that.y-this.y)*1.0/(that.x*1.0-this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y < that.y) return -1;
        if (this.y > that.y) return +1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return +1;
        return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point p1=new Point(4,6);
        Point p2=new Point(5,6);
        Point p3=new Point(4,5);
        Point p4=new Point(4,7);
        
        StdOut.println(p1.compareTo(p2));
        StdOut.println(p2.compareTo(p1));
        StdOut.println(p1.compareTo(p3));
        StdOut.println(p1.compareTo(p4));
        
        StdOut.println(p1.slopeTo(p2));
        StdOut.println(p1.slopeTo(p3));
        StdOut.println(p1.slopeTo(p4));
        
    }
}
