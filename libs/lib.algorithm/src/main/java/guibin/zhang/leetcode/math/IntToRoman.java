package guibin.zhang.leetcode.math;

/**
 *
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class IntToRoman {
    
    public String intToRoman(int n) {

        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] digits = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            int times = n / nums[i];
            while (times > 0) {
                sb.append(digits[i]);
                times --;
            }
            n = n % nums[i];
        }
        return sb.toString();
    }
}
