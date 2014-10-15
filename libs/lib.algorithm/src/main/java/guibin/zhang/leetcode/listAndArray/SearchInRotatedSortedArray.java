package guibin.zhang.leetcode.listAndArray;

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

    public int search_v2 (int[] A, int target) {
        int start = 0;
        int end = A.length - 1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            }
            
            //mid is in the left part of rotated array
            if (A[mid] >= A[start]) {
                if (target >= A[start] && target < A[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {//mid is in the right part of rotated array
                if (target > A[mid] && target <= A[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } 
        }
        return -1;
    }
    
    /**
     * Search the separate point of the rotated array.
     * EG. {4,5,6,7,2,3,4} => return 4
     * 
     * @param A
     * @return 
     */
    public int searchSeparatePointOfRotateArray(int[] A) {
        
        //Use "left" to judge which part mid is pointing to
        int start = 0, left = 0;
        int end = A.length - 1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            //mid is in right part
            if (A[mid] < A[left]) {
                if (A[mid] < A[mid - 1]) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            } else {//mid is in the left part
                start = mid + 1;
            }
        }
        return 0;
    }
    
    public int search(int[] A, int target) {
        int start = 0;
        int end = A.length - 1;
        int mid = start + (end - start) / 2;

        while (start <= end) {
            if (A[mid] == target) {
                return mid;
            }
            
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
        return -1;
    }
    
    public static void main(String[] args) {
        SearchInRotatedSortedArray si = new SearchInRotatedSortedArray();
        int[] A = {4,5,6,7,2,3,4};//4
        int[] B = {4,5,6,7,2};//4
        int[] C = {4,5,6,7};//0
        int[] E = {9,4,5,6,7};//1
        
        System.out.println(si.searchSeparatePointOfRotateArray(C));
    }
}
