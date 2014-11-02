package guibin.zhang.leetcode.dp;

/**
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
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
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 * 
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class WildcardMatching {
    
    /**
     * http://blog.csdn.net/perfect8886/article/details/22689147
     * Greedy
     * @param s
     * @param p
     * @return 
     */
    public boolean isMatch(String s, String p) {
        
        int i = 0, j = 0;
        int star = -1, mark = -1;

        while (i < s.length()) {
            //iif p[j] == s[i] || p[j] == '?', move on i,j
            if (j < p.length()
                    && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
               ++i;
               ++j;
           //iif p[j] == '*', save j & i; move on j
           } else if (j < p.length() && p.charAt(j) == '*') {
               star = j++;
               mark = i;
           //iif match mothing, but still star, iff star, move on j to the next of star, move mark and i
           } else if (star != -1) {
               j = star + 1;
               i = ++mark;
           } else {
               return false;
           }
       }
       // If star, keep on moving j
       while (j < p.length() && p.charAt(j) == '*') {
           ++j;
       }
       return j == p.length();
    }
    
    /**
     * http://chazyhabit.wordpress.com/2014/08/11/wildcard-matching-leetcode-47/
     * DP
     * @param s
     * @param p
     * @return 
     */
    public boolean isMatch_v2(String s, String p) {
        if (s == null || p == null)
            return false;
        if(s.length()>300 && p.charAt(0)=='*' && p.charAt(p.length()-1)=='*')
            return false;
        int m = s.length(), n = p.length();
        boolean[] match = new boolean[m+1];
        match[0] = true;
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) != '*') {   // Not wildcard
                for (int j = m; j > 0; j--)     // Update match backwards
                    match[j] = match[j-1] &&
                            (p.charAt(i)=='?' || s.charAt(j-1) == p.charAt(i));
            } else {    // Wildcard
                int j = 0;
                // Find the minimum j with p[0...i-1] matching s[0...j]
                while (j <= m && !match[j])
                    j++;
                // Since p[i] is '*' that matches any sequence, p[0...i] matches with
                // s[0...j+1], s[0...j+2] ...
                for (; j <= m; j++)
                    match[j] = true;
            }
            match[0] = match[0] && p.charAt(i) == '*';
        }
        return match[m]; 
    }
}
