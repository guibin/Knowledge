package guibin.zhang.leetcode.permutationAndCombination;

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

        Arrays.sort(num);
        combinate(num, target, new ArrayList<Integer>(), 0, result, 0);
        
        return result;
    }
    
    public void combinate(int[] arr, int target, ArrayList<Integer> branch, int sum,  
            ArrayList<ArrayList<Integer>> result, int startId) {
        
        if (sum == target) {
            result.add(new ArrayList<Integer>(branch));
        } else if (sum < target){
            for (int i = startId; i < arr.length; i++) {
                sum += arr[i];
                branch.add(arr[i]);
                //i + 1 to avoid duplicately pick the elements
                combinate(arr, target, branch, sum, result, i + 1);
                sum -= arr[i];
                branch.remove(branch.size() - 1);
                //while loop is to avoid duplicated elements exists in candidates
                while(i < arr.length - 1 && arr[i] == arr[i + 1]) {
                    i ++;
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
