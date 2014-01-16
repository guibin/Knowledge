package guibin.zhang.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * 
 * @author zhangguibin
 */
public class MaxPointsOnALine {
    class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }
    
    public int maxPoints(Point[] points) {
        
        int result = 1;
        if (points.length == 0) {
            return 0;
        } else if (points.length == 1) {
            return result;
        }
        
        //Sort the points to identify the duplicates
        Arrays.sort(points, new Comparator() {
            public int compare (Object o1, Object o2) {
                return ((Point)o1).x - ((Point)o2).x;
            }
        });
        
        int duplicates = 1;
        int max = 1;
        for (int i = 0; i < points.length - 1; i++) {
            float slope = Float.MAX_VALUE;
            duplicates = 1;
            Map<Float, Integer> map = new HashMap<Float, Integer>();
            for (int j = i + 1; j < points.length; j++) {
                //Find the duplicates
                if (points[j].x == points[i].x && points[j].y == points[i].y) {
                    ++ duplicates;
                    continue;
                }
                //Compute the slope
                if ((points[j].x - points[i].x) != 0) {
                    slope = (float)(points[j].y - points[i].y) / (float)(points[j].x - points[i].x);
                } else {
                    slope = 0;
                }
                //Put the slops into map in case that the points are not adjacent.
                int preMax = map.get(slope) == null ? duplicates : map.get(slope);
                map.put(slope, preMax + 1);
            }
            for (Integer ii : map.values()) {
                max = Math.max(ii, max);
            }
            result = Math.max(result, max);
            result = Math.max(duplicates, result);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        MaxPointsOnALine mp = new MaxPointsOnALine();
        Point[] points = {mp.new Point(), mp.new Point(-1, -1), mp.new Point(2, 2)};
        System.out.println(mp.maxPoints(points));
    }
}
