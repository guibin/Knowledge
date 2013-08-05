package guibin.zhang.leecode;

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
        
        if(A == null) {
            return 0;
        } else if(A.length == 0) {
            return 0;
        }else if( A.length == 1) {
            return 1;
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
}
