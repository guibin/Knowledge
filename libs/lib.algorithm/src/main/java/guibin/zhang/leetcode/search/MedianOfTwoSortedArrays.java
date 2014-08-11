package guibin.zhang.leetcode.search;

/**
 *
 * Median of Two Sorted Arrays
 * 
 * There are two sorted arrays A and B of size m and n respectively. 
 * Find the median of the two sorted arrays. 
 * The overall run time complexity should be O(log (m+n)).
 * 
 * http://blog.csdn.net/lemon_wonder/article/details/9002762
 * http://blog.csdn.net/zxzxy1988/article/details/8587244
 * http://fisherlei.blogspot.com/2012/12/leetcode-median-of-two-sorted-arrays.html
 * https://code.google.com/p/leetcode/source/browse/medianTwoSortedArray.java?r=4c766c4299fa8d0004884440e3bd150ae5ef0211
 * https://code.google.com/p/leetcode/source/browse/medianTwoSortedArray.java?r=4c766c4299fa8d0004884440e3bd150ae5ef0211
 * http://gongxuns.blogspot.com/2012/12/leetcodemedian-of-two-sorted-arrays.html
 * http://wcf1987.iteye.com/blog/1678149
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class MedianOfTwoSortedArrays {
    
    public double findMedianSortedArrays(int A[], int B[]) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int total = A.length + B.length;
        if (total % 2 == 0) {
            return (findKth(A, 0, A.length - 1, B, 0, B.length - 1, total / 2) + 
                    findKth(A, 0, A.length - 1, B, 0, B.length - 1, total / 2 + 1)) / 2.0;
        } else {
            return findKth(A, 0, A.length - 1, B, 0, B.length - 1, total / 2 + 1);
        }
    }
    
    public double findKth (int[] a, int startA, int endA, int[] b, int startB, int endB, int k) {
        
        int m = endA - startA + 1;
        int n = endB - startB + 1;
        //Always suppose length of a <= length of b
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
        
        //split k into two parts
        int pa = Math.min(m, k/2);
        int pb = k - pa;
        
        //Discard part a from a
        if (a[startA + pa - 1] < b[startB + pb - 1]) {
            return findKth(a, startA + pa, endA, b, startB, endB, k - pa);
        }
        //Discard part b from b
        else if (a[startA + pa - 1] > b[startB + pb - 1]) {
            return findKth(a, startA, endA, b, startB + pb, endB, k - pb);
        //Find it
        } else {
            return a[startA + pa - 1];
        }
    }
    
    public static void main(String[] args) {
        MedianOfTwoSortedArrays mt = new MedianOfTwoSortedArrays();
        int[] a = {1, 5, 6, 8, 10, 11, 16, 30};
        int[] b = {2, 9, 13, 15, 17, 18, 20, 22, 25};
        System.out.println(mt.findMedianSortedArrays(a, b));
    }
}
