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
    
    /**
     * Iteration version, O(log N + log M).
     * @param a 
     * @param b
     * @param k search the kth item.
     * @return 
     */
    public int findKth_v2(int[] a, int[] b, int k) {
        
        int startA = 0, endA = a.length - 1, startB = 0, endB = b.length - 1, midA, midB;
        
        while (startA <= endA && startB <= endB) {
            midA = startA + (endA - startA)/2;
            midB = startB + (endB - startB)/2;
            int pa = midA - startA + 1;
            int pb = midB - startB + 1;
            
            //When pa + pb > k discard bigger parts
            if (pa + pb > k) {
                if (a[midA] > b[midB]) endA = midA - 1;
                else endB = midB - 1;

            //When pa + pb <= k discard smaler part
            } else if (a[midA] < b[midB]) {
                startA = midA + 1;
                k -= pa;
                
            } else {
                startB = midB + 1;
                k -= pb;
            }
        }
        
        //Pick up the non-consumed one.
        if (startA <= endA) return a[startA + k - 1];
        else return b[startB + k - 1];
    }
    
    
    /**
     * 
     * @param a array a
     * @param startA start position of array a, inclusive
     * @param endA end position of array a, inclusive
     * @param b array b
     * @param startB start position of array b, inclusive
     * @param endB end position of array b, inclusive
     * @param k The target position to be searched, 1 based.
     * @return 
     */
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
        
        /**
         * 我们要找第k大的数，可以先将k分为两部分，比较A[k/2]和B[k/2]。
         * 如果k为奇数的话，我们可以将剩下的一个随便放入A或者B。
         * 比较A[k/2]和B[k/2],如果A[k/2] == B[k/2]，说明正好相等。返回A[k/2]即可。
         * 如果A[k/2] > B[k/2]，说明B[k/2]之前的都可以丢掉。利用反证法可以证明。
         * 证明如下，假设B[k/2]中存在第k大的数，下标为index(index < k/2)，以为A[k/2] > B[k/2]，
         * 所以A中最多有k/2 - 1个比第k个数小的数。所以总共有k/2 - 1 + index <=k/2 - 1 + k/2 ，
         * 即小于等于k-1，与事实不符。所以可以丢掉B中k/2下标之前的数。不断进行可以得到。
         * 
         */
        
        //Discard part a from a, discard smaller part.
        if (a[startA + pa - 1] < b[startB + pb - 1]) {
            return findKth(a, startA + pa, endA, b, startB, endB, k - pa);
        }
        //Discard part b from b, discard smaller part
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
        int[] b = {2, 6, 13, 15, 17, 18, 20, 22, 25};
        System.out.println(mt.findMedianSortedArrays(a, b));
        
        System.out.println(mt.findKth(a, 0, a.length - 1, b, 0, b.length - 1, 5));
    }
}
