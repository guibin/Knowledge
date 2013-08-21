package guibin.zhang.leecode;

/**
 *
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * 
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * 
 * Run Status: Accepted!
 * Program Runtime: 576 milli secs
 * Progress: 112/112 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class RemoveElement {

    public int removeElement(int[] A, int elem) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int idx = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != elem) {
                A[idx] = A[i];
                idx++;
            }
        }
        return idx;//Note, here idx already ++, so idx is the length, instead of idx + 1.
    }
}
