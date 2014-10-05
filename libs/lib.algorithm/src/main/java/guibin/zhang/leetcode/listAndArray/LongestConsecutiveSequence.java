package guibin.zhang.leetcode.listAndArray;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * 
 * Your algorithm should run in O(n) complexity.
 * 
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class LongestConsecutiveSequence {
    
    /**
     * To avoid the negative number issue, we can use HashMap instead that putting all
     * numbers into an int array.
     * 
     * @param num
     * @return 
     */
    public int longestConsecutive(int[] num) {
        
        int maxLength = 0;
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        //Put all the number into HashMap
        for(int n : num) {
            map.put(n, false);
        }
        
        for(int m : num) {
            if(!map.get(m)) {
                //As the length of single element is 1, if this element has not been visited, we count it in.
                int length = 1 + findConsequtive(map, m - 1, -1) + findConsequtive(map, m + 1, 1);
                maxLength = Math.max(maxLength, length);
            }
        }
        
        return maxLength;
    }
    
    private int findConsequtive(Map<Integer, Boolean> map, int num, int step) {
        
        int length = 0;
        while(map.containsKey(num)) {
            length ++;
            map.put(num, true);
            num += step;
        }
        return length;
    }
    
    public static void main(String[] args) {
        
        LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();
        
        int[] num = {0,0};
        System.out.println(lcs.longestConsecutive(num));
        
        int[] num2 = {0,0, 2};
        System.out.println(lcs.longestConsecutive(num2));
        
        
        int[] num3 = {-1, -4, -2, 0};
        System.out.println(lcs.longestConsecutive(num3));
        
        int[] num4 = {0, 1, 2};
        System.out.println(lcs.longestConsecutive(num4));
        
        int[] num5 = {-1, -4, -2, 0, 1, 2};
        System.out.println(lcs.longestConsecutive(num5));
    }
}
