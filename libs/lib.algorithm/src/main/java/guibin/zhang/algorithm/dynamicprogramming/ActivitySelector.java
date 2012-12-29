package guibin.zhang.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Suppose we have a set S = {a1, a2, ..., an} of n proposed activities 
 * that wish to use a resource, such as a lecture hall, which can serve only one activity at a time. 
 * Each activity ai has a start time si and a finish time fi , where 0 <= si < fi < œ.
 * If selected, activity ai takes place during the half-open time interval [si ; fi). 
 * Activities ai and aj are compatible if the intervals [si;fi) and [sj;fj0 do not overlap.
 * In the activity-selection problem, we wish to select a maximum-size 
 * subset of mutually compatible activities. 
 * @author guibin
 */
public class ActivitySelector {
    
    /**
     * Suppose activities have been sorted by end time. Use the "greedy algorithm".
     * @param activities the activities to be selected, suppose they are sorted by endTime.
     * @param k the indexer which is being processed now.
     * @param n the number of original activities.
     * @return 
     */
    public List<Activity> recursiveGreedySelector(List<Activity> activities, int k, int n) {
        int m = k+1;
        while(m <= n && activities.get(m).getStartTime() < activities.get(k).getEndTime()) {
            m = m+1;
        }
        if(m <= n) {
            List<Activity> list = recursiveGreedySelector(activities, k, n);
            list.add(0, activities.get(m));
            return list;
        }
        else {
            return new ArrayList<Activity>();
        }
    }
    
    public class Activity {

        long startTime = -1;
        long endTime = -1;
        
        public Activity(long startTime, long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
        
        public long getEndTime() {
            return endTime;
        }

        public long getStartTime() {
            return startTime;
        }
    }
}
