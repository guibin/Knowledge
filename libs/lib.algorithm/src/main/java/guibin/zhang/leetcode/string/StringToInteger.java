package guibin.zhang.leetcode.string;

/**
 *
 * Implement atoi to convert a string to an integer.
 * 
 * Hint: Carefully consider all possible input cases. 
 * If you want a challenge, please do not see below and ask yourself what are the possible input cases.
 * 
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
 * You are responsible to gather all the input requirements up front.
 * 
 * spoilers alert... click to show requirements for atoi.
 * 
 * **Requirements for atoi:
 * The function first discards as many whitespace characters as necessary 
 * until the first non-whitespace character is found. 
 * Then, starting from this character, takes an optional initial plus or minus sign 
 * followed by as many numerical digits as possible, and interprets them as a numerical value.
 * 
 * The string can contain additional characters after those that form the integral number, 
 * which are ignored and have no effect on the behavior of this function.
 * 
 * If the first sequence of non-whitespace characters in str is not a valid integral number, 
 * or if no such sequence exists because either str is empty or it contains only whitespace characters, 
 * no conversion is performed.
 * 
 * If no valid conversion could be performed, a zero value is returned. 
 * If the correct value is out of the range of representable values, 
 * INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
 * 
 * 
 * Thoughts:
 * 1. null or empty string
 * 2. white spaces
 * 3. +/- sign
 * 4. calculate real value
 * 5. handle negative value
 * 6. handle min & max
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class StringToInteger {
    public int atoi(String str) {
        
        //1. null or empty string
        if(str == null || str.length() == 0) return 0;
        //2. trim space
        str = str.trim();
        
        //3. check positive or negative
        int i = 0;
        char flag = '+';
        if (str.charAt(i) == '-') {
            flag = '-';
            i++;
        } else if (str.charAt(i) == '+') {
            flag = '+';
            i++;
        }
        
        //Use double to save result
        double result = 0;
        //4. Calculate real value
        while(str.length() > i && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            result = result * 10 + str.charAt(i) - '0';
            i++;
        }
        //5. Handle negative
        if (flag == '-') result = -result;
        
        //6. Handle MAX_VALUE and MIN_VALUE
        if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        
        return (int)result;
    }
        
        
}
