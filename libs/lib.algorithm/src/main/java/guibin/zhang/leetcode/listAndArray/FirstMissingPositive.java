package guibin.zhang.leecode.listAndArray;

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
 * http://discuss.leetcode.com/questions/219/first-missing-positive
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int n = A.length;   
        int i = 0;
        while (i < n) {
            if (A[i] >= 0 && A[i] < n && A[i] != A[A[i]]) {
                int tmp = A[i];
                //Note use the tmp as the index instead of A[A[i]], 
                //otherwise it will be dead loop, since A[A[i]] was changed.
                A[i] = A[tmp];
                A[tmp] = tmp;
            } else {
                i ++;
            }
        }
        int k = 1;//Note: scan from 1 instead of 0;
        while (k < n && A[k] == k) {
            k++;
        }
        if (n == 0 || k < n) {
            return k;
        } else {
            return A[0] == k ? k + 1 : k;
        }
    }
    
    public int firstMissingPositive_error(int[] A) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (A.length == 0) {
            return 1;
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int counter = 0;
        int lastIndex = -1;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                min = Math.min(min, A[i]);
                max = Math.max(max, A[i]);
                sum += A[i];
                counter++;
                lastIndex = i;
            }
        }
        
        if (counter == 1) {
            if (A[lastIndex] == 1) {
                return 2;
            } else {
                return A[lastIndex] - 1;
            }
        } else if (counter == 0) {
            return 1;
        }
            
        int eSum = max * (max + 1) / 2 - min * (min + 1) / 2 + min;
        if (max == min) {
            if (max == 1) {
                return 2;
            } else {
                return 1;
            }
        } else if (eSum == sum) {
            if(min != 1) {
                return 1;
            } else {
                return max + 1;
            }
        } else {
            return eSum - sum;
        }
    }
    
    public static void main(String[] args) {
        FirstMissingPositive fm = new FirstMissingPositive();
        int[] A = {1, 0};
        System.out.println(fm.firstMissingPositive(A));
    }
}
