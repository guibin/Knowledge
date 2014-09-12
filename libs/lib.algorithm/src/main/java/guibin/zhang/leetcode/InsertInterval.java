package guibin.zhang.leetcode;

import java.util.ArrayList;

/**
 * 
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * 
 * You may assume that the intervals were initially sorted according to their start times.
 * 
 * Example 1:
 * Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
 * 
 * Example 2:
 * Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
 * 
 * This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 * 
 * http://wmjjmw.blogspot.com/2013/03/leetcode-insert-interval.html
 * 
 * 遍历input list。
 * 如果当前interval < newInterval, add当前；
 * 如果当前interval > newInterval, add new Interval; 
 * 若重叠，则合并成新的new interval.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class InsertInterval {

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

    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        
        ArrayList<Interval> res = new ArrayList<Interval>();
        Interval temp = newInterval;
        for (Interval v : intervals) {
            //No overlap
            if(v.end < temp.start) {
                res.add(v);
            //No overlap
            } else if (v.start > temp.end) {
                res.add(temp);
                temp = v;
            //Overlap
            } else {
                temp = new Interval(Math.min(v.start, temp.start), Math.max(v.end, temp.end));
            }
        }
        res.add(temp);
        
        return res;
    }
}
