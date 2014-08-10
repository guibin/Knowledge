package guibin.zhang.leetcode.permutationAndCombination;

/**
 * write a function which takes in an array of integers and returns the highest positive product 
 * possible by multiplying 3 distinct numbers. 
 * NO SORTING is ALLOWED 
 * 
 * example: 
 * [1, 3, 5, 2, 8, 0, -1, 4]  => 8 * 5 * 4 = 160 
 * 
 * [0, -1, 3, 100, -70, -50]  => -70*-50*100=350000
 * 
 * 
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class ThreeProduct {
    
    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    
    public int partition(int[] a, int lo, int hi) {
        
        if (lo == hi) return lo;
        
        int i = lo, j = hi + 1;
        while (true) {
            while (a[++i] <= a[lo]) {
                if (i == hi) break;
            }
            while (a[--j] >= a[lo]) {
                if (j == lo) break;
            }
            if (i >= j) break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        
        return j;
    }
    
    public int selectK(int[] a, int k) {
        
        int result = a[0];
        int lo = 0; int hi = a.length - 1;
        while (lo <= hi) {
            int j = partition(a, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                result = a[j];
                break;
            }
        }
        return result;
    }
    
    /**
     * O(n) solution. Find out the 1st, 2nd and 3rd largest items, and the minimum two items. 
     * Then compute the max product.
     * 
     * @param A
     * @return 
     */
    public int maxThreeProduct(int[] A) {
        int a = selectK(A, A.length - 1);
        int b = selectK(A, A.length - 2);
        int c = selectK(A, A.length - 3);
        int d = selectK(A, 0);
        int e = selectK(A, 1);
        
        int[] B = {a * b * c, a * b * d, a * b * e, b * c * d, b * c * e, b * d * e, c * d * e};
        int max = B[0];
        for (int i = 1; i < B.length; i ++) {
            if (B[i] > max) max = B[i];
        }
        
        System.out.println("a = " + a + ", b = " + b + ", c = " + c + ", d = " + d + ", e = " + e);
        return max;
    }
    
    public static void main(String[] args) {
        ThreeProduct tp = new ThreeProduct();
        int[] A = {1, 3, 5, 2, 8, 0, -1, 4};
        int[] B = {0, -1, 3, 100, -70, -50};
        System.out.println("Max product A is " + tp.maxThreeProduct(A));
        System.out.println("Max product B is " + tp.maxThreeProduct(B));
    }
}
