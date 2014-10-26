package guibin.zhang.leetcode;

/**
 *
 * Implement wildcard pattern matching with support for '?' and '*'.
 * 
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * 
 * Some examples:
 * isMatch("aa","a") ? false
 * isMatch("aa","aa") ? true
 * isMatch("aaa","aa") ? false
 * isMatch("aa", "*") ? true
 * isMatch("aa", "a*") ? true
 * isMatch("ab", "?*") ? true
 * isMatch("aab", "c*a*b") ? false
 * 
 * http://discuss.leetcode.com/questions/222/wildcard-matching
 * http://yucoding.blogspot.com/2013/02/leetcode-question-123-wildcard-matching.html
 * http://blog.csdn.net/hopeztm/article/details/8039777
 * http://www.kaixinwenda.com/article-lbyxiafei-9416765.html
 * http://fisherlei.blogspot.com/2013/01/leetcode-wildcard-matching.html
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class WildcardMatching {

    public boolean isMatch(String str, String ptn) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int lenS = str.length();
        int lenP = ptn.length();

        int i = 0;
        int j = 0;
        char t, p;
        while (i < lenP) {
            p = ptn.charAt(i);
            while (j < lenS) {
                t = str.charAt(j);
                if (p == '*') {
                    j++;
                } else if (p == '?') {
                    j++;
                    i++;
                    break;
                } else {
                    if (p == t) {
                        j++;
                        i++;
                        break;
                    } else {
                        return false;
                    }
                }
            }

            if (j >= lenS) {
                if (ptn.charAt(i) == '*') {
                    i++;
                    continue;
                }
                break;
            }
        }
        if (j == lenS && i == lenP) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        WildcardMatching wm = new WildcardMatching();
        System.out.println(wm.isMatch("a", "a*"));
    }
}
