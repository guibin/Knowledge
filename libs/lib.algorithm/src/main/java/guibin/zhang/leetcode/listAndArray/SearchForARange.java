package guibin.zhang.leecode.listAndArray;

/**
 *
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * 
 * Run Status: Accepted!
 * Program Runtime: 536 milli secs
 * Progress: 81/81 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SearchForARange {
    public int[] searchRange(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int start = 0;
        int end = A.length - 1;
        int mid = 0;
        int[] res = {-1, -1};
        
        while (start < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                int s = mid;
                while (s >= start && A[s] == target) {
                    s --;
                }
                int e = mid;
                //Note: here "<= end" instead of "<"
                while (e <= end && A[e] == target) {
                    e ++;
                }
                res[0] = s + 1;
                res[1] = e - 1;
                return res;
            } else if (A[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (A[start] == target) {
            res[0] = start;
            res[1] = end;
        } 
        
        return res;
    }
    
    /**
     * Run Status: Accepted!
     * Program Runtime: 600 milli secs
     * Progress: 81/81 test cases passed.
     * 
     * @param A
     * @param target
     * @return 
     */
    public int[] searchRange_v2(int[] A, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int[] res = new int[2];
        res[0] = findLow(A, 0, A.length - 1, target);
        res[1] = findHigh(A, 0, A.length - 1, target);
        return res;
    }
    
    public int findLow(int[] A, int start, int end, int target) {
        
        int res = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                res = mid;
                int next = findLow(A, start, mid - 1, target);
                if (next != -1) {
                    res = next;
                }
                //Note this break, otherwise it will be dead loop.
                break;
            } else if (A[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return res;
    }
    
    public int findHigh(int[] A, int start, int end, int target) {
        
        int res = -1;
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                res = mid;
                int next = findHigh(A, mid + 1, end, target);
                if (next != -1) {
                    res = next;
                }
                //Note this break, otherwise it will be dead loop.
                break;
            } else if (A[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        SearchForARange sf = new SearchForARange();
        int[] A = {1};
        sf.searchRange_v2(A, 1);
    }
}
