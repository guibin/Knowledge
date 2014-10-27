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
        
        List<Interval> result = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) return result;
        
        Collections.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval x, Interval y){
                if (x.start != y.start) return x.start - y.start;
                return x.end - y.end;
            }
        });
        
        Interval tmp = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
            if (curr.start > tmp.end) {//No overlap, add it to result
                result.add(tmp);
                tmp = curr;
            } else {//Merge the overlap
                tmp = new Interval(Math.min(tmp.start, curr.start), Math.max(tmp.end, curr.end));
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
