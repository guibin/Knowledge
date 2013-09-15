package guibin.zhang.leecode.string;

/**
 * 
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 * 
 * Note: The numbers can be arbitrarily large and are non-negative.
 * 
 * 
 * http://gongxuns.blogspot.com/2013/01/leetcode-multiply-strings.html
 * http://discuss.leetcode.com/questions/221/multiply-strings
 * http://yucoding.blogspot.com/2013/01/leetcode-question-58-multiply-strings.html
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (num1 == null || num2 == null) {
            return null;
        }
        int n = num1.length();
        int m = num2.length();
        int[] res = new int[n + m];//the length of m + n is big enough.
        
        for (int i = 0; i < m; i++) {
            //Note: define carry inside of mun2
            int carry = 0;
            for (int j = 0; j < n; j++) {
                //Multiply from the least significant position, say from right to left
                int s = (num2.charAt(m-1-i) - '0') * (num1.charAt(n-1-j) - '0') + carry;
                //Compute the carry: Previous one plus the new value / 10
                carry = (res[i + j] + s) / 10;
                //Compute the result: Previous one plus the new value % 10
                res[i + j] = (res[i + j] + s) % 10;
            }
            //Don't forget this carry.
            res[n + i] += carry;
        }
        
        //Finish computing, just output the result reversely 
        int k = m + n - 1;
        while(res[k] == 0 && k > 0) {
            k--;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = k; i >= 0; i--) {
            sb.append(res[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        MultiplyStrings ms = new MultiplyStrings();
        System.out.println(ms.multiply("999", "999"));
    }
}
