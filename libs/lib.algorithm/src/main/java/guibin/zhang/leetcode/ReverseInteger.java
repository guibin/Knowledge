package guibin.zhang.leetcode;

import java.util.Stack;

/**
 * 
 * Reverse digits of an integer.
 * 
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 * 
 * click to show spoilers.
 * 
 * Have you thought about this?
 * Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
 * 
 * If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
 * 
 * Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, 
 * then the reverse of 1000000003 overflows. How should you handle such cases?
 * 
 * Throw an exception? Good, but what if throwing an exception is not an option? 
 * You would then have to re-design the function (ie, add an extra parameter).
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ReverseInteger {
    public int reverse(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
        Stack<Integer> stack = new Stack<Integer>();
        boolean positive = x > 0;
        x = Math.abs(x);
        
        while (x != 0) {
            stack.push(x % 10);
            x /= 10;
        }
        long rev = 0L;//Use long to avoid the int overflow.
        int size = stack.size();
        for (int n = 0; !stack.isEmpty() && n < size; n ++) {
            rev = rev + (long)Math.pow(10, n) * stack.pop();
        }
        if (positive && rev < Integer.MAX_VALUE || !positive && (-rev) > Integer.MIN_VALUE) {
            return positive ? (int)rev : (int)(-rev);
        } else {
            return 0;
        }
    }
    
    public int reverse_v2(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int rev = 0;
        while (x != 0) {
            rev *= 10;
            rev += x % 10;
            x /= 10;
        }
        return rev;
    }
}
