package guibin.zhang.leecode.listAndArray;

/**
 *
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * Run Status: Accepted!
 * Program Runtime: 536 milli secs
 * Progress: 194/194 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SearchInRotatedSortedArray {

    public int search(int[] A, int target) {
        int start = 0;
        int end = A.length - 1;
        int mid = start + (end - start) / 2;

        while (start <= end) {
            if (A[mid] == target) {
                return mid;
            } else {
                if (A[mid] < A[end]) {
                    if (A[mid] < target && target <= A[end]) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                    mid = start + (end - start) / 2;
                } else {
                    if (A[mid] > target && target >= A[start]) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                    mid = start + (end - start) / 2;
                }
            }
        }
        return -1;
    }
}
