package guibin.zhang.leetcode.listAndArray;

/**
 * 
 * Given an unsorted integer array, find the first missing positive integer.
 * 
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * 
 * Your algorithm should run in O(n) time and uses constant space.
 * 
 * http://www.cnblogs.com/AnnieKim/archive/2013/04/21/3034631.html
 * http://blog.csdn.net/kenden23/article/details/17099987
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int len = A.length;   
        int i = 0;
        //A[i] == A[A[i]] means the item is where it should be.
        while (i < len) {
            if (A[i] >= 0 && A[i] < len && A[i] != A[A[i]]) {
                swap(A, i, A[i]);
            } else {
                i ++;
            }
        }
        //Note: scan from 1 instead of 0, since we are asked to search the first missing POSITIVE number
        int k = 1;
        while (k < len && A[k] == k) {
            k++;
        }
        if (len == 0 || k < len) {
            return k;
        } else {
            return A[0] == k ? k + 1 : k;
        }
    }
    
    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void main(String[] args) {
        FirstMissingPositive fm = new FirstMissingPositive();
        int[] A = {1, 0};
        System.out.println(fm.firstMissingPositive(A));
    }
}
