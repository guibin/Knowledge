package guibin.zhang.leetcode.dp;

/**
 * Implement regular expression matching with support for '.' and '*'.
 * 
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * 
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class RegularExpressionMatching {
    
    /**
     * Overall, there are 2 different cases: 
     * 1) the second char of pattern is "*" , 
     * 2) the second char of pattern is not "*".
     * 
     * For the 1st case, if the first char of pattern is not ".", 
     * the first char of pattern and string should be the same. 
     * Then continue to match the left part.
     * 
     * For the 2nd case, if the first char of pattern is "." 
     * or first char of pattern == the first i char of string, continue to match the left.
     * 
     * Be careful about the offset.
     * 
     * @param s
     * @param p
     * @return 
     */
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }

        // length == 1 is the case that is easy to forget.
        // as p is subtracted 2 each time, so if original
        // p is odd, then finally it will face the length 1
        if (p.length() == 1) {
            return (s.length() == 1)
                    && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        }

        // next char is not '*': must match current character
        if (p.charAt(1) != '*') {
            if (s.length() < 1) {
                return false;
            } else {
                return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
                        && isMatch(s.substring(1), p.substring(1));
            }
        }
        
        // next char is *
        while (s.length() > 0
               && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')) {
            if (isMatch(s, p.substring(2))) {
                return true;
            }
            s = s.substring(1);
        }
        return isMatch(s, p.substring(2));
    }
    
    public boolean isMatch_v2(String s, String p) {
        int height = s.length(), width = p.length();
        boolean[][] dp = new boolean[height + 1][width + 1];
        dp[0][0] = true;
        for (int i = 1; i <= width; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 2];
            }
        }
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= width; j++) {
                char sChar = s.charAt(i - 1);
                char pChar = p.charAt(j - 1);
                if (sChar == pChar || pChar == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pChar == '*') {
                    if (sChar != p.charAt(j - 2) && p.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2] | dp[i - 1][j];
                    }
                }
            }
        }
        return dp[height][width];
    }
}
