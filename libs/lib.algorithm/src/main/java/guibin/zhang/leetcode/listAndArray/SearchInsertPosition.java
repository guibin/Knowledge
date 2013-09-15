package guibin.zhang.leecode.listAndArray;

/**
 *
 * Given a sorted array and a target value, return the index if the target is found. 
 * If not, return the index where it would be if it were inserted in order.
 * 
 * You may assume no duplicates in the array.
 * 
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 * 
 * 
 * Run Status: Accepted!
 * Program Runtime: 552 milli secs
 * Progress: 62/62 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SearchInsertPosition {
    
    public int searchInsert(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int start = 0;
        int end = A.length - 1;
        int mid = 0;
        
        while (start < end) {
            mid = start + (end - start)/2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        //Note the ">="
        if (A[start] >= target) {
            return start;
        } else {
            return start + 1;
        }
    }
}
