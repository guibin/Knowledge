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
     * 0-1 Knapsack problem, no duplicated item is allowed.
     * We only consider whether the ith item can be selected.
     * 
     * @param capacity The target weight capacity of the knapsack.
     * @param wt Weight of each candidate item.
     * @param val Value of each candidate item.
     * @param n The nth item of current solution.
     * @return Returns the maximum value that can be put in a knapsack of capacity W
     */
    public int knapsackNaive(int capacity, int[] wt, int[] val, int n) {
        
        //If target weight is 0 or no item to be seleced.
        if (n == 0 || capacity == 0) {
            return 0;
        }
        
        // If weight of the nth item is more than Knapsack capacity W, then
        // this item cannot be included in the optimal solution
        if (wt[n - 1] > capacity) {
            return knapsackNaive(capacity, wt, val, n - 1);
        } else {// Return the maximum of two cases: (1) nth item included (2) not included
            return Math.max(val[n - 1] + knapsackNaive(capacity - wt[n - 1], wt, val, n - 1), //(1)
                    knapsackNaive(capacity, wt, val, n - 1)); //(2)
        }
    }
    
    /**
     * 0-1 Knapsack problem, no duplicated item is allowed.
     * We only consider whether the ith item can be selected.
     * 
     * @param capacity
     * @param wt
     * @param val
     * @return 
     */
    public int knapsack(int capacity, int[] wt, int[] val) {
        
        int[][] dp = new int[wt.length + 1][capacity + 1];
        
        for (int c = 0; c <= capacity; c++) {
            for(int i = 0; i <= wt.length; i++) {
                //If capacity == 0 or put no items into knapsack, the value is 0.
                if (i == 0 || c == 0) {
                    dp[i][c] = 0;
                // If capacity >= ith item, select the maxium of two cases:
                // (1) ith item included, (2) not included
                } else if (wt[i - 1] <= c) {
                    dp[i][c] = Math.max(val[i - 1] + dp[i - 1][c - wt[i - 1]], dp[i - 1][c]);
                } else {
                    // If capacity < ith item, cannot include ith item. the value = previous one.
                    dp[i][c] = dp[i - 1][c];
                }
            }
        }
        
        return dp[wt.length][capacity];
    }
    
    /**
     * Duplicated items is allowed from wt[] and val[].
     * 
     * @param capacity
     * @param wt
     * @param val
     * @return 
     */
    public int integerKnapsack(int capacity, int[] wt, int[] val) {
        
        int[] dp = new int[capacity + 1];
        for (int c = 0; c <= capacity; c++) {
            
            //The value of capacity c can be at least the value of capacity c - 1.
            if (c > 0) dp[c] = dp[c - 1]; 
            else dp[c] = 0;
            //For each capacity, scan all possible wt[] and val[] to find the max val.
            for (int i = 0; i < wt.length; i++) {
                if (wt[i] <= c) {
                    dp[c] = Math.max(dp[c], dp[c - wt[i]] + val[i]);
                }
            }
        }
        
        return dp[capacity];
    }
    
    
    public static void main(String[] args) {
        int val[] = {60, 100, 120};
        int wt[] = {10, 20, 30};
        int W = 50;
        KnapsackProblem kp = new KnapsackProblem();
        System.out.println(kp.knapsackNaive(W, wt, val, val.length) + ", " + kp.knapsack(W, wt, val) + ", " 
                + kp.integerKnapsack(W, wt, val));
    }
}
