package guibin.zhang.leecode;

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
    
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if (intervals == null || intervals.size() <= 1) {
            return intervals;
        }
        
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval t1, Interval t2) {
                if (t1.start != t2.start) {
                    return t1.start - t2.start;
                } else {
                    return t1.end - t2.end;
                }
            }
        });
        
        Interval last = null;
        ArrayList<Interval> res = new ArrayList<Interval>();
        for (Interval v : intervals) {
            if (last == null) {
                last = v;
            } else if (v.start > last.end) {
                res.add(last);
                last = v;
                //Since the intervals has been sorted, last should be ahead of v,
                //So this judge condition is useless.
            } else if (v.end < last.start) {
                res.add(v);
            } else {
                last.end = Math.max(last.end, v.end);
            }
        }
        if (last != null) {
            res.add(last);
        }
        return res;
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
