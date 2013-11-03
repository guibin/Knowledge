package guibin.zhang.leetcode.permutationAndCombination;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * 
 * The same repeated number may be chosen from C unlimited number of times.
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, � , ak) must be in non-descending order. (ie, a1 ? a2 ? � ? ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 2,3,6,7 and target 7, 
 * A solution set is: 
 * [7] 
 * [2, 2, 3] 
 * 
 * http://www.cnblogs.com/feiling/p/3234904.html
 * http://blog.csdn.net/zyfo2/article/details/8592955
 * http://leetcode.com/2010/09/print-all-combinations-of-number-as-sum.html
 * 
 * DFS入门: http://rapheal.iteye.com/blog/1526863
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class CombinationSum {
    
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int len = candidates.length;
        ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
        if (len == 0) {
            return results;
        }

        Arrays.sort(candidates);
        
        combinate(candidates, target, new ArrayList<Integer>(), 0, results, 0);
        return results;
    }

    /**
     * This is the combination with duplication, Idea: one loop + one recursion + startId, but startId is i while recursing.
     * 
     * @param arr
     * @param target
     * @param branch
     * @param sum
     * @param result
     * @param startId 
     */
    public void combinate(int[] arr, int target, ArrayList<Integer> branch, int sum,  
            ArrayList<ArrayList<Integer>> result, int startId) {
        
        if (sum == target) {
            result.add(new ArrayList<Integer>(branch));
            return;
        } else if (sum > target) {
            return;
        } else {
            for (int i = startId; i < arr.length; i++) {
                sum += arr[i];
                branch.add(arr[i]);
                combinate(arr, target, branch, sum, result, i);
                sum -= arr[i];
                branch.remove(branch.size() - 1);
            }
        }
    }
}
