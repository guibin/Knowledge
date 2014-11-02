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
     * @param Capacity The target weight capacity of the knapsack.
     * @param wt Weight of each candidate item.
     * @param val Value of each candidate item.
     * @param n The nth item of current solution.
     * @return Returns the maximum value that can be put in a knapsack of capacity W
     */
    public int knapsackNaive(int Capacity, int[] wt, int[] val, int n) {
        
        //If target weight is 0 or no item to be seleced.
        if (n == 0 || Capacity == 0) {
            return 0;
        }
        
        // If weight of the nth item is more than Knapsack capacity W, then
        // this item cannot be included in the optimal solution
        if (wt[n - 1] > Capacity) {
            return knapsackNaive(Capacity, wt, val, n - 1);
        } else {// Return the maximum of two cases: (1) nth item included (2) not included
            return Math.max(val[n - 1] + knapsackNaive(Capacity - wt[n - 1], wt, val, n - 1), //(1)
                    knapsackNaive(Capacity, wt, val, n - 1)); //(2)
        }
    }
    
    /**
     * 
     * @param Capacity
     * @param wt
     * @param val
     * @return 
     */
    public int knapsack(int Capacity, int[] wt, int[] val) {
        
        int[][] dp = new int[wt.length + 1][Capacity + 1];
        
        for(int i = 0; i <= wt.length; i++) {
            for (int c = 0; c <= Capacity; c++) {
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
        
        return dp[wt.length][Capacity];
    }
    
    public static void main(String[] args) {
        int val[] = {60, 100, 120};
        int wt[] = {10, 20, 30};
        int W = 50;
        KnapsackProblem kp = new KnapsackProblem();
        System.out.println(kp.knapsackNaive(W, wt, val, val.length) + ", " + kp.knapsack(W, wt, val));
    }
}
