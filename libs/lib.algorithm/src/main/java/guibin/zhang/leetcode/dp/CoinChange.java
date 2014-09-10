package guibin.zhang.leetcode.dp;

/**
 * Given a value N, if we want to make change for N cents, 
 * and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins, 
 * how many ways can we make the change? The order of coins doesn’t matter.
 * 
 * For example, for N = 4 and S = {1,2,3}, 
 * there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. 
 * So output should be 4. 
 * 
 * For N = 10 and S = {2, 5, 3, 6}, 
 * there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}. 
 * So the output should be 5.
 * 
 * Question link: http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class CoinChange {
    
    /**
     * 
     * To count total number solutions, we can divide all set solutions in two sets.
     * 1) Solutions that do not contain mth coin (or Sm).
     * 2) Solutions that contain at least one Sm.
     * Let count(S[], m, n) be the function to count the number of solutions, 
     * then it can be written as sum of count(S[], m-1, n) and count(S[], m, n-Sm).
     * 
     * C() --> count()
     *                               C({1,2,3}, 5)                     
     *                           /                 \
     *                          /                   \              
     *              C({1,2,3}, 2)                 C({1,2}, 5)
     *             /      \                       /         \
     *            /        \                     /           \
     * C({1,2,3}, -1)  C({1,2}, 2)        C({1,2}, 3)    C({1}, 5)
     *               /      \            /    \            /     \
     *              /        \          /      \          /       \
     *     C({1,2},0)  C({1},2)   C({1,2},1) C({1},3)    C({1}, 4)  C({}, 5)
     *                    / \      / \       / \        /     \    
     *                   /   \    /   \     /   \      /       \ 
     *                 .      .  .     .   .     .   C({1}, 3) C({}, 4)
     *                                                /  \
     *                                               /    \  
     *                                              .      .
     * 
     * @param S The coin set.
     * @param m The mth coin that is included or excluded in the solution.
     * @param n The target value
     * @return 
     */
    public int countNaive(int[] S, int m, int n) {
        
        //If n equals 0, then there is one solution which is don't include any coin.
        if (n == 0) return 1;
        //If n less than 0, then no solution.
        if (n < 0) return 0;
        //If there are no coins but n > 0, no solution.
        if (m <= 0 && n > 0) return 0;
        
        //count is sum of solutions 
        //(i) excluding excluding S[m-1]
        //(ii) including the coin S[m-1] 
        return countNaive(S, m - 1, n) + countNaive(S, m, n - S[m - 1]);
    }
    
    /**
     * 
     * @param S The coin set.
     * @param n The target value.
     * @return 
     */
    public int count(int[] S, int n) {
        
        int m = S.length;
        
        // We need n+1 rows as the table is consturcted in bottom up manner using 
        // the base case 0 value case (n = 0)
        int[][] dp = new int[n + 1][m];
        
        // Fill the enteries for case n = 0, if n equals 0, then there is one solution which is don't include any coin.
        for (int i = 0; i < m; i++) {
            dp[0][i] = 1;
        }
        
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < m; j++) {
                // Count of solutions including S[j]
                int x = (i - S[j] >= 0) ? dp[i - S[j]][j] : 0;
                // Count of solutions excluding S[j]
                int y = (j >= 1) ? dp[i][j - 1] : 0;
                // Total count
                dp[i][j] = x + y;
            }
        }
        return dp[n][m - 1];
    }
    
    /**
     * 
     * @param S The coin set.
     * @param n The target value.
     * @return 
     */
    public int count_v2(int[] S, int n) {
        
        int[] dp = new int[n + 1];
        
        // Base case (If given value is 0)
        dp[0] = 1;
        
        // Pick all coins one by one and update the table[] values
        // after the index greater than or equal to the value of the
        // picked coin
        for (int i = 0; i < S.length; i++) {
            for (int j = S[i]; j <= n; j++) {
                dp[j] += dp[j - S[i]];
            }
        }
        return dp[n];
    }
    
    public static void main(String[] args) {
        CoinChange cc = new CoinChange();
        int[] S = {2, 5, 3, 6};
        int n = 10;
        int m = 4;
        System.out.println(cc.countNaive(S, m, n) + ", " + cc.count(S, n) + ", " + cc.count_v2(S, n));
    }
}
