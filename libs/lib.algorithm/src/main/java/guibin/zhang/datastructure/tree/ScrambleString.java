package guibin.zhang.datastructure.tree;

/**
 * 
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 * 
 * Below is one possible representation of s1 = "great":
 * 
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
           / \
 *           a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * 
 * For example, if we choose the node "gr" and swap its two children, 
 * it produces a scrambled string "rgeat".
 * 
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 * We say that "rgeat" is a scrambled string of "great".
 * 
 * Similarly, if we continue to swap the children of nodes "eat" and "at", 
 * it produces a scrambled string "rgtae".
 * 
 *     rgtae
 *    /    \
 *   rg    tae
 *  / \    /  \
 * r   g  ta  e
 *        / \
 *       t   a
 * We say that "rgtae" is a scrambled string of "great".
 * 
 * Given two strings s1 and s2 of the same length, 
 * determine if s2 is a scrambled string of s1.
 * 
 * 3 dimension dynamic programming
 * 
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ScrambleString {
    
    /**
     * http://discuss.leetcode.com/questions/262/scramble-string
     * 
     * Given a string A and a string B, if B is a scramble string of A, 
     * then there must be a way for us to divide B into two parts B1, B2 and A into A1, A2, 
     * where B1 contains exactly the same characters (ignore order) as A1 or A2. 
     * Otherwise, B won't be a scramble string of A.
     * 
     * For example, if A = "abc", B = "cba", then we can divide like the following.
     * 
     * B1="c", B2="ba", A1="a", A2="bc"
     * 
     * B1="c", B2="ba", A1="ab", A2="c"
     * 
     * Divide #1 is not a match. 
     * But in #2, B1 has same set of characters as A2, 
     * so we only need to check if B1 is a scramble string of A2, and so does B2 and A1.
     * 
     * The following code try to divide B at each position (from 1 to B.length-1), 
     * and if we found the valid division,check whether the two parts are scramble string of A1, A2 recursively.
     * 
     * @param s1
     * @param s2
     * @return 
     */
    public boolean isScramble(String T, String C) {
        
        if(T.length() != C.length()) {
            return false;
        } else if(T.length() == 1) {
            return T.charAt(0) == C.charAt(0);
        } else {
            for(int i = 1; i < T.length(); i++) {
                String t1 = T.substring(0, i);
                String t2 = T.substring(i);
                
                String c1 = C.substring(0, t1.length());
                String c2 = C.substring(t1.length());
                
                if(isEqualSet(t1, c1)) {
                    if(isScramble(t1, c1) && isScramble(t2, c2)) {
                        return true;
                    }
                }
                
                c1 = C.substring(0, t2.length());
                c2 = C.substring(t2.length());
                if(isEqualSet(t1, c2)) {
                    if(isScramble(t1, c2) && isScramble(t2, c1)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
    
    private boolean isEqualSet(String A, String B) {
        int[] set = new int[256];
        for(char c : A.toCharArray()) {
            set[c] ++;
        }
        for(char c : B.toCharArray()) {
            set[c] --;
            if(set[c] < 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * http://blog.sina.com.cn/s/blog_b9285de20101gy6n.html
     * 
     * dp[i][j][k] 代表了s1从i开始，s2从j开始，长度为k的两个substring是否为scramble string.
     * 
     * 有三种情况需要考虑：
     * 
     * 1. 如果两个substring相等的话，则为true
     * 
     * 2. 如果两个substring中间某一个点，左边的substrings为scramble string， 
     *    同时右边的substrings也为scramble string，则为true
     * 
     * 3. 如果两个substring中间某一个点，s1左边的substring和s2右边的substring为scramble string, 
     *    同时s1右边substring和s2左边的substring也为scramble string，则为true
     * 
     * @param s1
     * @param s2
     * @return 
     */
    public boolean isScramble_v2(String s1, String s2) {

        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                for (int k = 1; k <= n - Math.max(i, j); k++) {
                    if (s1.substring(i, i + k).equals(s2.substring(j, j + k))) {
                        dp[i][j][k] = true;
                    } else {
                        for (int m = 1; m < k; m++) {
                            
                            if (dp[i][j][m] && dp[i + m][j + m][k - m] || 
                                    dp[i][j + k - m][m] && dp[i + m][j][k - m]) {
                                
                                dp[i][j][k] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return dp[0][0][n];
    }
}
