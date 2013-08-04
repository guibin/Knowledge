package guibin.zhang.leecode;

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
        
        if (A == null) {
            return 0;
        } else if (A.length == 0) {
            return 0;
        } else if (A.length == 1) {
            return 1;
        }
        int end = 1;
        int prev = A[0];
        int curr = A[1];
        for(int i = 1; i < A.length; i++) {
            curr = A[i];
            if(curr != prev) {
                if(i > end) {
                    A[end] = curr;
                }
                end++;
            } 
            prev = curr;
        }
        return end;
    }
    
    public int removeDuplicates_v2(int[] A) {
        int lastI = 0;
        int realI = 0;
        for(int i = 0; i <= A.length; i++){
            if(i > lastI && (i == A.length || A[i] != A[lastI])){
                A[realI++] = A[lastI];
                lastI = i;
            }
        }
        return realI;
    }
}
