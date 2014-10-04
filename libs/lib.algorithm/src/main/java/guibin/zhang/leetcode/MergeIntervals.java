package guibin.zhang.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example,
 * Given [1,3],[2,6],[8,10],[15,18],
 * return [1,6],[8,10],[15,18].
 * 
 * https://github.com/rffffffff007/leetcode/blob/master/Merge%20Intervals.java
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class MergeIntervals {
    
    public class Interval {

        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
    
    public List<Interval> merge(List<Interval> intervals) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if (intervals== null || intervals.size() <= 1) return intervals;
        
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval v1, Interval v2) {
                if (v1.start > v2.start) return 1;
                if (v1.start < v2.start) return -1;
                if (v1.end > v2.end) return 1;
                if (v1.end < v2.end) return -1;
                return 0;
            }
        });
        
        Interval tmp = null;
        List<Interval> result = new ArrayList<>();
        for (Interval v : intervals) {
            if (tmp == null) {
                tmp = v;
            } else {
                if (v.end < tmp.start) {
                    result.add(v);
                } else if (tmp.end < v.start) {
                    result.add(tmp);
                    tmp = v;
                } else {
                    tmp = new Interval(Math.min(v.start, tmp.start), Math.max(v.end, tmp.end));
                }
            }
        }
        result.add(tmp);
        
        return result;
    }
    
    public static void main(String[] args) {
        MergeIntervals mi = new MergeIntervals();
        ArrayList<Interval> list = new ArrayList<Interval>();
        list.add(mi.new Interval(1, 4));
        list.add(mi.new Interval(0, 0));
        List<Interval> res = mi.merge(list);
        for(Interval v : res) {
            System.out.print("[" + v.start + "," + v.end + "], ");
        }
        System.out.println("--");
    }
}
