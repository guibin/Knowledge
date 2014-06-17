package guibin.zhang.codecareer;

import scala.actors.threadpool.Arrays;

/**
 *
 * http://codercareer.blogspot.com/2011/10/no-09-numbers-with-given-sum.html
 * 
 * @author Guibin Zhang
 */
public class NumbersWithAGivenSum {

    /**
     * Question: Given an **increasingly sorted** array and a number s, please
     * find two numbers whose sum is s. If there are multiple pairs with sum s,
     * just output any one of them.
     *
     * 
     * @param nums The given int array to be searched in.
     * @param target The target sum.
     * @return
     */
    public int[] sumToTarget(int[] nums, int target) {

        int[] ret = new int[2];
        int start = 0;
        int end = nums.length - 1;
        int sum = 0;
        
        while (start < end) {
            sum = nums[start] + nums[end];
            if (sum == target) {
                ret[0] = nums[start];
                ret[1] = nums[end];
                return ret;
            } else {
                if (sum < target) {
                    start ++;
                } else {
                    end --;
                }
            }
        }
        return null;
    }
    
    
    /**
     * Given an array, please determine whether it contains three numbers whose
     * sum equals to 0.
     *
     * This is very similar with above sumToTarget, it can be changed to:
     * Find two numbers whose sum is -s, s is also in the array.
     * 
     * 
     * @param nums
     * @param sum
     * @return
     */
    public boolean sumToZero(int[] nums) {
        
        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {
            if (sumToTarget(nums, -nums[i], i) != null) {
                return true;
            }
        }
        return false;
    }

    private int[] sumToTarget(int[] nums, int target, int excludeIndex) {

        int[] ret = new int[2];
        int start = 0;
        int end = nums.length - 1;
        int sum = 0;
        
        while (start < end) {
            
            if (start == excludeIndex) start++;
            if (end == excludeIndex) end--;
            
            sum = nums[start] + nums[end];
            if (sum == target) {
                ret[0] = nums[start];
                ret[1] = nums[end];
                return ret;
            } else {
                if (sum < target) {
                    start ++;
                } else {
                    end --;
                }
            }
        }
        return null;
    }
    
}
