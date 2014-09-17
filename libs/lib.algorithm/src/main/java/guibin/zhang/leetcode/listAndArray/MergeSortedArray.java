package guibin.zhang.leetcode.listAndArray;

/**
 *
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * 
 * Note:
 * You may assume that A has enough space to hold additional elements from B. 
 * The number of elements initialized in A and B are m and n respectively.
 * 
 * Run Status: Accepted!
 * Program Runtime: 564 milli secs
 * Progress: 59/59 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class MergeSortedArray {
    
    /**
     * 
     * @param A
     * @param m Actual number of elements in A
     * @param B
     * @param n 
     */
    public void merge_v2(int A[], int m, int B[], int n) {
        
        if (A.length < B.length) {
            merge(B, n, A, m);
        }
        
        int end = m + n - 1;
        int a = m - 1;
        int b = n - 1;
        
        while (a >= 0 && b >= 0) {
            A[end --] = A[a] > B[b] ? A[a --] : B[b --];
        }
        
        while (b >= 0) {
            A[end--] = B[b--];
        }
    }
    
    
    public void merge(int A[], int m, int B[], int n) {
        if (n == 0) {
            return;
        }
        //The idea is copy from end to start to avoid array shift.
        int endA = m - 1;
        int endB = n - 1;
        int end = A.length - 1;
        while (end >= 0) {
            //When B has been finished being copied, just leave A as it is.
            if (endB < 0) {
                return;
            }
            //When A has been finished being copied, copy the remaining B to A.
            if (endA < 0) {
                for (int i = endB; i >= 0; i--) {
                    A[end] = B[endB];
                    end--;
                    endB--;
                }
                return;
            }
            //In the normal case, compare A[i] and B[j] to copy the bigger one.
            if (B[endB] > A[endA]) {
                A[end] = B[endB];
                endB--;
            } else {
                A[end] = A[endA];
                endA--;
            }
            end--;
        }
    }
}
