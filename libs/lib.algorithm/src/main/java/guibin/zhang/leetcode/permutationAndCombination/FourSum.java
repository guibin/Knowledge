package guibin.zhang.leetcode.permutationAndCombination;

import java.util.ArrayList;
import java.util.Arrays;
import scala.Array;

/**
 *
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
 * Find all unique quadruplets in the array which gives the sum of target.
 * 
 * Note:
 * 
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ? b ? c ? d)
 * The solution set must not contain duplicate quadruplets.
 *     For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 * 
 *     A solution set is:
 *     (-1,  0, 0, 1)
 *     (-2, -1, 1, 2)
 *     (-2,  0, 0, 2)
 * 
 * http://www.cnblogs.com/remlostime/archive/2012/10/27/2742676.html
 * 先排序，这后两重for循环，然后设两个指针遍历。
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class FourSum {
    
    public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int len = num.length;
        
        for (int i = 0; i < len - 3; i++) {
            //To avoid the duplicated result
            if (i > 0 && num[i] == num[i - 1]) continue;
            for (int j = i + 1; j < len - 2; j++) {
                //To avoid the duplicated result
                if (j > i + 1 && num[j] == num[j - 1]) continue;
                int m = j + 1;
                int n = len - 1;
                while (m < n) {
                    //To avoid the duplicated result
                    if (m > j + 1 && num[m] == num[m - 1]) {
                        m ++;
                        continue;
                    }
                    //To avoid the duplicated result
                    if (n < len - 1 && num[n] == num[n + 1]) {
                        n --;
                        continue;
                    }
                    
                    int delta = num[i] + num[j] + num[m] + num[n] - target;
                    if (delta == 0) {
                        ArrayList<Integer> row = new ArrayList<Integer>();
                        row.add(num[i]);
                        row.add(num[j]);
                        row.add(num[m]);
                        row.add(num[n]);
                        result.add(row);
                        m ++;
                        n --;
                    } else if (delta < 0) {
                        m ++;
                    } else {
                        n --;
                    }
                }
            }
        }
        return result;
    }
    
    public ArrayList<ArrayList<Integer>> fourSum_v2(int[] num, int target) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        Arrays.sort(num);
        //TODO: Filter out the duplicated elements, then invoke combinate
        Arrays.stream(num).forEach( k -> System.out.print(k + ","));
        System.out.println();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        combinate(num, target, new ArrayList<Integer>(), 0, 0, 0, result);
        return result;
    }

    public void combinate(int[] arr, int target, ArrayList<Integer> branch, int startId, int k, int sum, ArrayList<ArrayList<Integer>> result) {
        
        if (k == 4 && sum == target) {
            //If result doesn't contain branch, then add it.
            result.add(new ArrayList<Integer>(branch));
            
        } else if (k < 4 && sum <= target) {//Here is less than and equal
            for (int i = startId; i < arr.length; i++) {
                branch.add(arr[i]);
                sum += arr[i];
                combinate(arr, target, branch, ++startId, ++ k, sum, result);
                branch.remove(branch.size() - 1);
                sum -= arr[i];
                k--;
            }
        }
    }
    
    public static void main(String[] args) {
        FourSum fs = new FourSum();
        int[] num = {1, 1, 0, -1, -1, 2, -2};
        ArrayList<ArrayList<Integer>> result = fs.fourSum_v2(num, 0);
        for (ArrayList<Integer> a : result) {
            for (int i : a) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }
}
