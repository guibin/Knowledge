package guibin.zhang.leetcode.permutationAndCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(candidates);
        
        combinate(candidates, target, 0, new ArrayList<Integer>(), results, 0);
        return results;
    }

    /**
     * This is the combination with duplication, Idea: one loop + one recursion + startId, but startId is i while recursing.
     * 
     * @param arr
     * @param target
     * @param sum
     * @param branch
     * @param result
     * @param startId 
     */
    public void combinate(int[] arr, int target, int sum, List<Integer> branch,   
            List<List<Integer>> result, int startId) {
        
        if (sum == target) {
            result.add(new ArrayList<>(branch));
        } else if (sum < target) {
            for (int i = startId; i < arr.length; i++) {
                sum += arr[i];
                branch.add(arr[i]);
                //Note: Here is i instead of i + 1, since we need duplication.
                combinate(arr, target, sum, branch, result, i);
                sum -= arr[i];
                branch.remove(branch.size() - 1);
            }
        }
    }
}
