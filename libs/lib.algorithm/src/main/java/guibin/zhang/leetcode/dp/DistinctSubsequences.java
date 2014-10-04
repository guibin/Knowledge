package guibin.zhang.leetcode.dp;

/**
 * 
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 * 
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) 
 * of the characters without disturbing the relative positions of the remaining characters. 
 * (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * 
 * 给定两个字符串S和T，求S有多少个不同的子串**与T相同**。S的子串定义为在S中任意去掉0个或者多个字符形成的串。
 * 
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 * 
 * Return 3.
 * 
 * http://blog.csdn.net/abcbc/article/details/8978146
 * http://fisherlei.blogspot.com/2012/12/leetcode-distinct-subsequences_19.html
 * http://stackoverflow.com/questions/5151483/how-to-find-the-number-of-distinct-subsequences-of-a-string
 * http://tech-lightnight.blogspot.com/2012/11/distinct-subsequences.html
 * http://blog.csdn.net/niaokedaoren/article/details/8834077
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class DistinctSubsequences {
    
    /**
     * When you see string problem that is about subsequence or matching, 
     * dynamic programming method should come to your mind naturally. 
     * The key is to find the changing condition.
     * 
     * @param S 
     * @param T
     * @return The number of subsequence of S that matches T.
     */
    public int numDistincts(String S, String T) {
        
        int[][] dp = new int[S.length() + 1][T.length() + 1];
        
        //Let W(i, j) stand for the number of subsequences of S(0, i) matches T(0, j). 
        //If S.charAt(i - 1) == T.charAt(j - 1), W(i, j) = W(i-1, j-1) + W(i-1,j); 
        //Otherwise, W(i, j) = W(i-1,j).
        
        //We know that for any number i, dp[i][0] = 1
        //because we need to delete all characters from S(0, i) to a empty string.
        for (int i = 0; i < S.length(); i++){
            dp[i][0] = 1;
        }
        
        //If the character at position i in S is equal to the character at position j in T, there are two options.
        //1. Delete the character at position i in S. 
        //   Then the number of distinct subsequences should be the number of distinct subsequences of T(0, j) in S(0, i – 1).
        //2. Remains the character at position i in S. Then the number is the number of distinct subsequences of T(0, j – 1) in S(0, i – 1).
        //   So dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1].
        //If the character at position i in S is not equal to the character at position j in T, 
        //then we can only delete this character. So num[i][j] = num[i - 1][j].
        
        for (int i = 1; i <= S.length(); i ++) {
            for (int j = 1; j <= T.length(); j++) {
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[S.length()][T.length()];
    }
    
    /**
     * 首先找到在S中与T的第一个字符相同的字符，从这个字符开始，递归地求S和T剩下的串。
     * T为空串时，返回1。因为空串本身是另外一个串的一个子序列。
     * @param S
     * @param T
     * @return 
     */
    public int numDistincts_naive(String S, String T) {
        
        if (S.length() == 0) {
            return T.length() == 0 ? 1 : 0;
        }
        if (T.length() == 0) {//empty string is a subsequence of any string
            return 1;
        }
        int cnt = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == T.charAt(0)) {
                cnt += numDistincts_naive(S.substring(i + 1), T.substring(1));
            }
        }
        return cnt;
    }
}
