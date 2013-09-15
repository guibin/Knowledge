package guibin.zhang.leecode.listAndArray;

/**
 * 
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * 
 * Would this affect the run-time complexity? How and why?
 * 
 * Write a function to determine if a given target is in the array.
 * 
 * Run Status: Accepted!
 * Program Runtime: 548 milli secs
 * Progress: 271/271 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SearchInRotatedSortedArrayII {
    public boolean search(int[] A, int target) {
        
        int start = 0;
        int end = A.length - 1;
        
        if (A[start] == target || A[end] == target) {
            return true;
        } 
        
        //Elimit the deplicated head and tail.
        if (A[start] == A[end]) {
            for(int i = 1; i < A.length; i++) {
                if (A[i] == A[0]) {
                    start = i;
                } else {
                    start ++;
                    break;
                }
            }
            for(int j = A.length - 2; j >=0; j--) {
                if (A[j] == A[0]) {
                    end = j;
                } else {
                    end --;
                    break;
                }
            }
        }
        
        int mid = start + (end - start)/2;
        while (start <= end) {
            if (A[mid] == target) {
                return true;
            } else {
                if (A[mid] < A[end]) {
                    if (A[mid] < target && target <= A[end]) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                    mid = start + (end - start) / 2;
                } else if(A[mid] > A[end]) {
                    if (A[mid] > target && target >= A[start]) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                    mid = start + (end - start) / 2;
                    //Note this case
                } else {//A[mid] == A[end]
                    end = mid - 1;
                    mid = start + (end - start) / 2;
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        
        SearchInRotatedSortedArrayII si = new SearchInRotatedSortedArrayII();
        int[] A = {3,1,2,2,2};
        System.out.println(si.search(A, 1));
    }
}
