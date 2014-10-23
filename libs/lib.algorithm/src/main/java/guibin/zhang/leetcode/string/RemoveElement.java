package guibin.zhang.leetcode.string;

/**
 *
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * 
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
* 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class RemoveElement {
    
    public int removeElement(int[] A, int elem) {
        
        if (A.length == 0) return 0;
        
        int curr = 0, idx = 0;
        while (curr < A.length) {
            if (A[curr] != elem) {
                A[idx] = A[curr];
                curr++;
                idx++;
            } else {
                curr++;
            }
        }
        return idx;
    }
}
