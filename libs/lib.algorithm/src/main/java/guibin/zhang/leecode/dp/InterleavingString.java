package guibin.zhang.leecode.dp;

/**
 *
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * For example, Given: s1 = "aabcc", s2 = "dbbca",
 *
 * When s3 = "aadbbcbcac", return true. When s3 = "aadbbbaccc", return false.
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {

        return isInterleave(s1, 0, s2, 0, s3, 0);
    }

    /**
     * Run Status: Time Limit Exceeded when running big data set.
     *
     * @param s1
     * @param p1
     * @param s2
     * @param p2
     * @param s3
     * @param p3
     * @return
     */
    public boolean isInterleave(String s1, int p1, String s2, int p2, String s3, int p3) {

        //Note the edge case
        if (s1.length() == 0 && s2.length() == 0 && s3.length() == 0) {
            return true;
        } else if (s1.length() == 0 && s2.equals(s3) || s2.length() == 0 && s1.equals(s3)) {
            return true;
        }
        if (p1 == s1.length() && p2 == s2.length() && p3 == s3.length()) {
            return true;
        }

        if (p1 < s1.length() && p2 < s2.length() && p3 < s3.length()
                && s1.charAt(p1) == s2.charAt(p2) && s2.charAt(p2) == s3.charAt(p3)) {
            return isInterleave(s1, p1 + 1, s2, p2, s3, p3 + 1) || isInterleave(s1, p1, s2, p2 + 1, s3, p3 + 1);
        } else {
            if (p1 < s1.length() && p3 < s3.length() && s1.charAt(p1) == s3.charAt(p3)) {
                return isInterleave(s1, p1 + 1, s2, p2, s3, p3 + 1);
            } else if (p2 < s2.length() && p3 < s3.length() && s2.charAt(p2) == s3.charAt(p3)) {
                return isInterleave(s1, p1, s2, p2 + 1, s3, p3 + 1);
            } else {
                return false;
            }
        }
    }

    
    /**
     * The explanation of the algorithm, and corresponding proof:
     * http://lifestruthonwisdom.blogspot.com/2012/09/dynamic-programming-practice-problems_28.html .
     * 
     * http://blog.theliuy.com/interleaving-string/
     * http://yucoding.blogspot.com/2013/01/leetcode-question-27-interleaving-string.html
     * 
     * This a 2D DP(Dynamic Programming) problem. 
     * 
     * Use match[i][j] to save if s1[i] and s2[j] are matched s3[i+j]. 
     * 
     * match[i][j] =   (s3[i+j]==s1[i]  && match[i-1][j])
     *                     || (s3[i+j] ==s2[j] && match[i][j-1])
     * @param s1
     * @param p1
     * @param s2
     * @param p2
     * @param s3
     * @param p3
     * @return 
     */
    public boolean isInterleave_v2(String s1, int p1, String s2, int p2, String s3, int p3) {

        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        boolean[][] mat = new boolean[s1.length() + 1][s2.length() + 1];
        mat[0][0] = true;
        for (int i = 1; i <= s1.length(); ++i) {
            mat[i][0] = mat[i - 1][0] && (s3.charAt(i - 1) == s1.charAt(i - 1));
        }
        for (int i = 1; i <= s2.length(); ++i) {
            mat[0][i] = mat[0][i - 1] && (s3.charAt(i - 1) == s2.charAt(i - 1));
        }
        for (int i = 1; i <= s1.length(); ++i) {
            for (int j = 1; j <= s2.length(); ++j) {
                mat[i][j] = (mat[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) || (mat[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
            }
        }
        return mat[s1.length()][s2.length()];
    }
}
