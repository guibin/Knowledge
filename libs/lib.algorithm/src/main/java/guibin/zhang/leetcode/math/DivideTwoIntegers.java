package guibin.zhang.leetcode.math;

/**
 * Divide two integers without using multiplication, division and mod operator.
 * 
 * 不让用*, /, %号做整数除法。基本只能bit了。但是bit操作都是跟2有关的，所以到最后还得不断缩小范围，好能贴近结果。
 * 
 * 算法：a / b
 * 
 * a本来是想一直-b，然后减到<0了，算counter。但是这样慢，所以类似c++ vector的思路，每次发现还没减没，那减数就翻倍（b <<= 1）
 * 然后到了一定程度，b实在太大了，大于a剩余的部分了，那么就停止。然后剩下的a再一点点开始减，b回归成最开始的值，重做这两步。
 * 
 * http://tech-wonderland.net/blog/leetcode-divide-two-integers.html
 * 
 * Even though we are not allowed to use *, / and %, but we can use minus and bit operator. 
 * My solution is based on such a fact that any number could be represented as a polynomial form, 
 * for example, 5 = 2^2 + 2^0, 78 = 2^5 + 2^3 + 2^2 + 2^1 and so on, 
 * any number could be represented as in this form. 
 * Now let’s consider the result quotient represented in this form, 
 * the good thing for thinking in this way is that, 
 * we now can make use of bit operator, a >> 1 = a / 2  a<<1 = a * 2. 
 * We keep multiply the divisor by 2 (divisor <<= 1) until it reaches or larger than half the dividend 
 * (because if we multiply it by 2 again, it will be larger than the dividend), 
 * we then subtract this value from the dividend, and here, 
 * suppose this value is divisor * 2^k, then 2^k needs to be added into the final quotient result 
 * (remember we represent the quotient in a polynomial form), 
 * we keep doing this until the dividend becomes zero. 
 * Another good point of this method is that we could find the quotient in log time 
 * since we always multiply the divisor by 2 by2, 
 * in the log time the divisor will reaches the dividend.
 * 
 * 100 / 3 = x =>
 * 100 = 3 * x => 100 = 3 * (2^0 + 2^1 + 2^2 + 2^3 + 2^4 + 2^0 + 2^0)
 *                                             ||
 *                                             33
 * => 100 = 3*2^0 + 3*2^1 + 3*2^2 + 3*2^3 + 3*2^4 + 3*2^0 + 3*2^0
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int ret = 0;

        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        while (a >= b) {
            long c = b;
            for (int i = 0; a >= c; ++i, c <<= 1) {
                a -= c;
                ret += (1 << i);
            }
        }

        return ((dividend ^ divisor) >> 31) == 0 ? ret : (-ret);
    }
    
    public static void main(String[] args) {
        DivideTwoIntegers dt = new DivideTwoIntegers();
        System.out.println(dt.divide(100, 3));
        System.out.println(2^1);
    }
}
