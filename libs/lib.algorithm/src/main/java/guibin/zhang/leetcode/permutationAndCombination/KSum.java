package guibin.zhang.leetcode.permutationAndCombination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import scala.actors.threadpool.Arrays;

/**
 * 
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 * 
 * Note:
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
 * The solution set must not contain duplicate quadruplets.
 *     For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 * 
 *     A solution set is:
 *     (-1,  0, 0, 1)
 *     (-2, -1, 1, 2)
 *     (-2,  0, 0, 2)
 * 
 * Solution:
 * 
 * Given an int[] a, and a target number, please find out if there exists K number whose sum is target.
 * 
 * http://tech-wonderland.net/blog/summary-of-ksum-problems.html
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class KSum {
    
    public static List<List<Integer>> zeroSumInSortedArray(int[] a, int begin, int count, int target) {
        
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        //Base case, Two sum.
        if (count == 2) {
            int i = begin, j = a.length - 1;
            while (i < j) {
                int sum = a[i] + a[j];
                if (sum == target && !visited.contains(a[i])) {
                    visited.add(a[i]);
                    visited.add(a[j]);
                    List<Integer> branch = new ArrayList<>();
                    branch.add(a[i]);
                    branch.add(a[j]);
                    result.add(branch);
                    i ++; j --;
                } else if (sum < target) {
                    i ++;
                } else { //sum > target
                    j --;
                }
            }
        } else {//Normal case, recursivly invoke
            for (int i = begin; i < a.length; i++) {
                if (!visited.contains(a[i])) {
                    visited.add(a[i]);
                    List<List<Integer>> subResult = zeroSumInSortedArray(a, i + 1, count - 1, target - a[i]);
                    if (subResult.size() > 0) {
                        for (int j = 0; j < subResult.size(); j ++) {
                            subResult.get(j).add(0, a[i]);//Prepend is to make the result in asending order.
                        }
                        result.addAll(subResult);
                    }
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] arags) {
        
        int[] a = {1, 0, -1, 0, -2, 2};
        int target = 0;
        
        Arrays.sort(a);
        List<List<Integer>> result = zeroSumInSortedArray(a, 0, 4, target);
        result.stream().forEach(
                (lt) -> {lt.stream().forEach(
                        (i) -> {System.out.print(i + ",");});
                System.out.println();});
    }
    
}
