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

        ArrayList<Integer> row = new ArrayList<Integer>();
        int level = 0;
        int sum = 0;
        dfs(candidates, level, sum, target, row, results);
        return results;
    }

    public void dfs(int[] candidates, int level, int sum, int target, ArrayList<Integer> row, ArrayList<ArrayList<Integer>> results) {

        if (sum > target) {
            return;
        } else if (sum == target) {
            results.add(new ArrayList<Integer>(row));
            return;
        } else {
            for (int i = level; i < candidates.length; i++) {
                sum += candidates[i];
                row.add(candidates[i]);
                //Note: level if from i, instead of i + 1.
                dfs(candidates, i, sum, target, row, results);
                row.remove(row.size() - 1);
                sum -= candidates[i];
            }
        }
    }
}
