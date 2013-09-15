package guibin.zhang.leetcode.listAndArray;

/**
 * 
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * For example:
 * A = [2,3,1,1,4], return true.
 * 
 * A = [3,2,1,0,4], return false.
 * 
 * Run Status: Accepted!
 * Program Runtime: 508 milli secs
 * Progress: 66/66 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class JumpGame {

    public boolean canJump(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (A.length <= 1) {
            return true;
        }
        
        int curr = 0;
        int max = 0;
        
        for (int i = 0; i < A.length - 1; i++) {
            if (i > max) {
                return false;
            }
            
            curr = i + A[i];
            if (curr > max) {
                max = curr;
            }
            if (max >= A.length - 1) {
                return true;
            }
        }
        return false;
    }
}
