package guibin.zhang.leecode.search;

/**
 * Find the k th elements from two sorted arrays.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class FindKth {
    
    public double findKth (int[] a, int startA, int endA, int[] b, int startB, int endB, int k) {
        
        int m = endA - startA;
        int n = endB - startB;
        
        //Always assume a.length <= b.length
        if (m > n) {
            return findKth(b, startB, endB, a, startA, endA, k);
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
            return findKth(a, startA + pa, endA, b, startB, endB, k - pa);
        } else if (a[startA + pa - 1] > b[startB + pb - 1]) {
            //Discard the partB from b
            return findKth(a, startA, endA, b, startB + pb, endB, k - pb);
        } else {
            return a[startA + pa - 1];
        }
    }
    
    public static void main(String[] args) {
        int a[] = {};
        int b[] = {2, 3};
        FindKth fk = new FindKth();
        System.out.println(fk.findKth(a, 0, a.length, b, 0, b.length, 2));
    }
}
