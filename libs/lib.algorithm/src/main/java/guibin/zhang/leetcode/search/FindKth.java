package guibin.zhang.leetcode.search;

/**
 * Find the k th elements from two sorted arrays.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class FindKth {
    
    /**
     * Find the k-th largest items from TWO SORTED arrays
     * @param a The first sorted array
     * @param startA start position inclusive
     * @param endA end position inclusive
     * @param b The second sorted array
     * @param startB start position inclusive
     * @param endB end position inclusive
     * @param k The expected position, 1 based
     * @return 
     */
    public double findKthFromTwoSortedArray (int[] a, int startA, int endA, int[] b, int startB, int endB, int k) {
        
        int m = endA - startA + 1;
        int n = endB - startB + 1;
        
        //Always assume a.length <= b.length
        if (m > n) {
            return findKthFromTwoSortedArray(b, startB, endB, a, startA, endA, k);
        }
        
        //Two edge cases
        if (m == 0) {
            return b[startB + k - 1];
        }
        if (k == 1) {
            return Math.min(a[startA], b[startB]);
        }
        
        //Devide k into two parts
        int pa = Math.min(k/2, m);
        int pb = k - pa;
        
        if (a[startA + pa - 1] < b[startB + pb - 1]) {
            //Discard the partA from a
            return findKthFromTwoSortedArray(a, startA + pa, endA, b, startB, endB, k - pa);
        } else if (a[startA + pa - 1] > b[startB + pb - 1]) {
            //Discard the partB from b
            return findKthFromTwoSortedArray(a, startA, endA, b, startB + pb, endB, k - pb);
        } else {
            return a[startA + pa - 1];
        }
    }
    
    public static void main(String[] args) {
        int a[] = {};
        int b[] = {2, 3};
        FindKth fk = new FindKth();
        System.out.println(fk.findKthFromTwoSortedArray(a, 0, a.length - 1, b, 0, b.length - 1, 2));
    }
}
