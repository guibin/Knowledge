package guibin.zhang.leetcode.listAndArray;

/**
 * 
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * 
 * For example,
 * Given sorted array A = [1,1,1,2,2,3],
 * 
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 * 
 * Run Status: Accepted!
 * Program Runtime: 604 milli secs
 * Progress: 164/164 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class RemoveDuplicatesFromSortedArrayII {
    
    public int removeDuplicates(int[] A) {
        
        if (A.length <= 2) {
            return A.length;
        }
        
        int idx = 0;
        int counter = 1;
        for(int i = 1; i < A.length; i++) {
            if(A[i] == A[i - 1]) {
                counter ++;
                if(counter == 2) {
                    A[++idx] = A[i];
                }
            } else {
                A[++idx] = A[i];
                counter = 1;
            }
        }
        return idx + 1;
    }
    
    public int removeDuplicates_v2(int[] A) {

        if (A.length <= 2) {
            return A.length;
        }

        int idx = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[idx] != A[i]) {
                A[++idx] = A[i];
            } else {
                if (idx > 0 && A[idx - 1] != A[i]) {
                    A[++idx] = A[i];
                } else if (idx == 0 && A[idx] == A[i]) {
                    A[++idx] = A[i];
                }
            }
        }
        return idx + 1;
    }
}
