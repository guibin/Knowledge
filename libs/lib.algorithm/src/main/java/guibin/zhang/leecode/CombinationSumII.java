package guibin.zhang.leecode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * Given a collection of candidate numbers (C) and a target number (T), 
 * find all unique combinations in C where the candidate numbers sums to T.
 * 
 * Each number in C may only be used **once** in the combination.
 * 
 * Note:
 * 
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, � , ak) must be in non-descending order. (ie, a1 ? a2 ? � ? ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
 * A solution set is: 
 * [1, 7] 
 * [1, 2, 5] 
 * [2, 6] 
 * [1, 1, 6] 
 * 
 * http://fisherlei.blogspot.com/2013/01/leetcode-combination-sum-ii-solution.html
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class CombinationSumII {
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> row = new ArrayList<Integer>();
        int level = 0;
        int sum = 0;
        Arrays.sort(num);
        dfs(num, level, sum, target, row, result);
        return result;
    }
    
    public void dfs(int[] num, int level, int sum, int target, ArrayList<Integer> row, ArrayList<ArrayList<Integer>> result) {
        
        if (sum > target) {
            return;
        } else if (sum == target) {
            result.add(new ArrayList<Integer>(row));
            return;
        } else {
            for (int i = level; i < num.length; i++) {
                sum += num[i];
                row.add(num[i]);
                //Note: here is i + 1 instead of level + 1.
                dfs(num, i + 1, sum, target, row, result);
                row.remove(row.size() - 1);
                sum -= num[i];
                //Ignore the duplicated candidates in num.
                while (i < num.length - 1 && num[i] == num[i + 1]) {
                    i++;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        CombinationSumII cs = new CombinationSumII();
        int[] num = {1, 2};
        int target = 4;
        ArrayList<ArrayList<Integer>> result = cs.combinationSum2(num, target);
        for(ArrayList<Integer> list : result) {
            for(Integer i : list) {
                System.out.print(i);
            }
            System.out.println("");
        }
    }
}
