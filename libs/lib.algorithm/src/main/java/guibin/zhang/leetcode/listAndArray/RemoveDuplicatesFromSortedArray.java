package guibin.zhang.leetcode.listAndArray;

/**
 * 
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * 
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * 
 * For example,
 * Given input array A = [1,1,2],
 * 
 * Your function should return length = 2, and A is now [1,2].
 * 
 * Run Status: Accepted!
 * Program Runtime: 580 milli secs
 * Progress: 160/160 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class RemoveDuplicatesFromSortedArray {
    
    public int removeDuplicates(int[] A) {
        
        if (A.length <= 1) {
            return A.length;
        }
        
        int idx = 0;
        for (int i = 1; i < A.length; i++) {
            //If curr is not duplicated with A[idx], copy curr to the **next pos of idx**.
            if (A[idx] != A[i]) {
                A[++idx] = A[i];
            }
        }
        return idx + 1;
    }
}
