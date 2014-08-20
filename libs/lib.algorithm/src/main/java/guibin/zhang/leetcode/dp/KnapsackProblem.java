package guibin.zhang.leetcode.dp;

/**
 * Given weights and values of n items, 
 * put these items in a knapsack of capacity W to get the maximum total value in the knapsack. 
 * 
 * In other words, given two integer arrays val[0..n-1] and wt[0..n-1] 
 * which represent values and weights associated with n items respectively. 
 * 
 * Also given an integer W which represents knapsack capacity, 
 * find out the maximum value subset of val[] such 
 * that sum of the weights of this subset is smaller than or equal to W. 
 * 
 * You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
 * 
 * Question link: http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class KnapsackProblem {
    
    /**
     * 
     * @param W The target weight capacity of the knapsack.
     * @param wt Weight of each candidate item.
     * @param val Value of each candidate item.
     * @param n The nth item of current solution.
     * @return Returns the maximum value that can be put in a knapsack of capacity W
     */
    public int knapsackNaive(int W, int[] wt, int[] val, int n) {
        
        //If target weight is 0 or no item to be seleced.
        if (n == 0 || W == 0) {
            return 0;
        }
        
        // If weight of the nth item is more than Knapsack capacity W, then
        // this item cannot be included in the optimal solution
        if (wt[n - 1] > W) {
            return knapsackNaive(W, wt, val, n - 1);
        } else {// Return the maximum of two cases: (1) nth item included (2) not included
            return Math.max(val[n - 1] + knapsackNaive(W - wt[n - 1], wt, val, n - 1), //(1)
                    knapsackNaive(W, wt, val, n - 1)); //(2)
        }
    }
    
    /**
     * 
     * @param W
     * @param wt
     * @param val
     * @return 
     */
    public int knapsack(int W, int[] wt, int[] val) {
        
        int[][] dp = new int[wt.length + 1][W + 1];
        
        for(int i = 0; i <= wt.length; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        return dp[wt.length][W];
    }
    
    public static void main(String[] args) {
        int val[] = {60, 100, 120};
        int wt[] = {10, 20, 30};
        int W = 50;
        KnapsackProblem kp = new KnapsackProblem();
        System.out.println(kp.knapsackNaive(W, wt, val, val.length) + ", " + kp.knapsack(W, wt, val));
    }
}
