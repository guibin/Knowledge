package guibin.zhang.leetcode.math;

/**
 * 
 * Implement int sqrt(int x).
 * 
 * Compute and return the square root of x.
 * 
 * http://www.cnblogs.com/AnnieKim/archive/2013/04/18/3028607.html
 * http://fisherlei.blogspot.com/2013/01/leetcode-sqrtx.html
 * http://blog.csdn.net/binary_search/article/details/8757292
 * http://blog.unieagle.net/2012/10/27/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Asqrtx/
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SqrtX {
    
    /**
     * 对于一个非负数n，它的平方根不会大于（n/2+1）,
     * 在[0, n/2+1]这个范围内可以进行二分搜索，求出n的平方根。
     * 
     * @param x
     * @return 
     */
    int sqrt(int x) {
        long i = 0;
        long j = x / 2 + 1;
        while(i <= j) {
            long mid = (i + j) / 2;
            long sqr = mid * mid;
            if (sqr == x) {
                return (int)mid;
            }
            else if (sqr < x) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return (int)j;
    }
}
