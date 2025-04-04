import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {
    private TreeSet<Point2D> points;
    private KdTree tree;

    // construct an empty set of points
    public  PointSET(){
        this.points = new TreeSet<>();
    }

    // is the set empty?
    public  boolean isEmpty(){
        return points.size() == 0;
    }

    // number of points in the set
    public  int size(){
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public  void insert(Point2D p){
        if(p.x()<0 || p.x()>1 || p.y()<0 || p.y()>1){
            throw new IllegalArgumentException("Points must be in range [0,1]");
        }
        points.add(p);
    }

    // does the set contain point p?
    public  boolean contains(Point2D p){
        return points.contains(p);
    }

    // draw all points to standard draw
    public  void draw(){
        for(Point2D p : points){
            p.draw();
        }
    }

    // all points that are inside the rectangle (or on the boundary)
    public  Iterable<Point2D> range(RectHV rect){
        if(rect==null) throw new IllegalArgumentException("RectHV must not be null");
        List<Point2D> result =new ArrayList<>();
        for(Point2D p : points){
            if(rect.contains(p)) result.add(p);
        }
        return result;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException("Point must not be null");
        Point2D nearest = null;
        double minDist = Double.MAX_VALUE;
        for (Point2D point : points) {
            double dist = p.distanceSquaredTo(point);
            if (dist < minDist) {
                nearest = point;
                minDist = dist;
            }
        }
        return nearest;
    }

}