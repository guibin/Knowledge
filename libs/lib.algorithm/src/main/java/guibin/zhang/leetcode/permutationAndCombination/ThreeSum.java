package guibin.zhang.leetcode.permutationAndCombination;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
 * Find all unique triplets in the array which gives the sum of zero.
 * 
 * Note:
 * 
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ? b ? c)
 * The solution set must not contain duplicate triplets.
 *     For example, given array S = {-1 0 1 2 -1 -4},
 * 
 *    A solution set is:
 *  (-1, 0, 1)
 *  (-1, -1, 2)
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ThreeSum {
    
    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int len = num.length;
        Arrays.sort(num);
        
        for (int i = 0; i < len - 2; i ++) {
            //num[i] num[i - 1]
            if (i > 0 && num[i] == num[i - 1]) continue;
            int j = i + 1;
            int k = len - 1;
            
            while (j < k) {
                //num[j] num[j - 1]
                if (j - 1 > i && num[j] == num[j - 1]) {
                    j ++;
                    continue;
                }
                //num[k] num[k + 1]
                if (k + 1< len && num[k] == num[k + 1]) {
                    k --;
                    continue;
                }
                
                int target = num[i] + num[j] + num[k];
                if (target == 0) {
                    ArrayList<Integer> row = new ArrayList<Integer>();
                    row.add(num[i]);
                    row.add(num[j]);
                    row.add(num[k]);
                    result.add(row);
                    j ++;
                    k --;
                } else if (target < 0) {
                    j ++;
                } else {
                    k --;
                }
            }
        }
        return result;
    }
}
