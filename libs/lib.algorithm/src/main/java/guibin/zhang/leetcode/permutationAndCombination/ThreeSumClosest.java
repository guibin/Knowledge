package guibin.zhang.leetcode.permutationAndCombination;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
 * Return the sum of the three integers. 
 * You may assume that each input would have exactly one solution.
 * 
 *   For example, given array S = {-1 2 1 -4}, and target = 1.
 * 
 *   The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * 
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] num, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int len = num.length;
        if(len < 3) return 0;
        
        int closet = num[0] + num[1] + num[2];
        Arrays.sort(num);
        
        for (int i = 0; i < len - 2; i++) {
            int j = i + 1;
            int m = len - 1;
            while (j < m) {
                int sum = num[i] + num[j] + num[m];
                int delta = sum - target;
                if (delta == 0) {
                    closet = sum;
                    return closet;
                } else {
                    if (Math.abs(delta) < Math.abs(closet - target)) {
                        closet = sum;
                    }
                    if (delta > 0) {
                        m --;
                    } else {
                        j ++;
                    }
                }
            }
        }
        return closet;
    }
    
    public static void main(String[] args) {
        ThreeSumClosest ts = new ThreeSumClosest();
        int[] num = {0, 0, 0};
        System.out.println(ts.threeSumClosest(num, 1));
    }
}
