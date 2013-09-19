package guibin.zhang.leetcode.string;

/**
 *
 * Given a string S, find the longest palindromic substring in S. 
 * You may assume that the maximum length of S is 1000, 
 * and there exists one unique longest palindromic substring.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class LongestPalindromicSubstring {
    
    public String longestPalindrome(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        int maxLeft = 0;
        int maxLength = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            //offset is used to control whether the length of string being test even or odd.
            for (int offset = 0; offset <= 1; offset ++) {
                int left = i;
                int right = i + offset;
                while (left >= 0 && right < length && s.charAt(left) == s.charAt(right)) {
                    left --;
                    right ++;
                }
                left ++;
                right --;
                int pLen = right - left + 1;
                if (pLen > maxLength) {
                    maxLeft = left;
                    maxLength = pLen;
                }
            }
        }
        return s.substring(maxLeft, maxLeft + maxLength);
        
    }
}
