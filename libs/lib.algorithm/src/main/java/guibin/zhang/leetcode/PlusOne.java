package guibin.zhang.leetcode;

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

        int promote = 0;
        int n = 1;
        int len = digits.length;
        int[] result = new int[len];

        for (int i = len - 1; i >= 0; i--) {
            if (i == len - 1) {
                if (digits[i] + n > 9) {
                    result[i] = 0;
                    promote = 1;
                } else {
                    result[i] = digits[i] + n;
                    promote = 0;
                }
            } else {
                if (digits[i] + promote > 9) {
                    result[i] = 0;
                    promote = 1;
                } else {
                    result[i] = digits[i] + promote;
                    promote = 0;
                }
            }
        }
        
        //Note when the result gets overflow, we need to create a bigger int[].
        if(promote > 0) {
             int[] tmp = new int[len + 1];
             tmp[0] = 1;
             System.arraycopy(result, 0, tmp, 1, len);
             result = tmp;
        }
        return result;
    }
}
