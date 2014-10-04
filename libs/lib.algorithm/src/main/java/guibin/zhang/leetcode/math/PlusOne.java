package guibin.zhang.leetcode.math;

/**
 *
 * Given a number represented as an array of digits, plus one to the number.
 *
 * Run Status: Accepted!
 * Program Runtime: 560 milli secs
 * Progress: 108/108 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {

        int[] result = new int[digits.length];
        int carry = 1;
        for (int i = digits.length - 1; i >=0; i--) {
            int sum = carry + digits[i];
            result[i] = sum % 10;
            carry = sum / 10;
        }
        
        if (carry != 0) {//When the carry-over is not zero, extend the reuslt array
            int[] tmp = new int[digits.length + 1];
            tmp[0] = carry;
            System.arraycopy(result, 0, tmp, 1, digits.length);
            return tmp;
        }
        return result;
    }
}
