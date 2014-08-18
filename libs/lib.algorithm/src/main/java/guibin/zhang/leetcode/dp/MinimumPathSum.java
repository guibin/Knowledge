package guibin.zhang.leetcode.dp;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Run Status: Accepted! Program Runtime: 548 milli secs Progress: 61/61 test
 * cases passed.
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = grid[0].length;

        int[][] p = new int[rows][cols];
        p[0][0] = grid[0][0];//Note the corner case: only one item.
        for (int i = 1; i < cols; i++) {
            p[0][i] = p[0][i - 1] + grid[0][i];
        }
        for (int j = 1; j < rows; j++) {
            p[j][0] = p[j - 1][0] + grid[j][0];
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                p[i][j] = Math.min(p[i - 1][j] + grid[i][j], p[i][j - 1] + grid[i][j]);
            }
        }
        return p[rows - 1][cols - 1];
    }
    
    /**
     * Question link: http://www.geeksforgeeks.org/dynamic-programming-set-6-min-cost-path/
     * 
     * Given a cost matrix cost[][] and a position (m, n) in cost[][], 
     * write a function that returns cost of minimum cost path to reach (m, n) from (0, 0). 
     * Each cell of the matrix represents a cost to traverse through that cell. 
     * Total cost of a path to reach (m, n) is sum of all the costs on that path 
     * (including both source and destination). 
     * 
     * You can only traverse down, right and **diagonally** lower cells from a given cell, 
     * i.e., from a given cell (i, j), cells (i+1, j), (i, j+1) and (i+1, j+1) can be traversed. 
     * You may assume that all costs are positive integers.
     * 
     * This question is different from above minPathSum, since this question allow move diagonally.
     * 
     * Idea:
     * The path to reach (m, n) must be through one of the 3 cells: 
     * (m-1, n-1) or (m-1, n) or (m, n-1). 
     * 
     * So minimum cost to reach (m, n) can be written as “minimum of the 3 cells plus cost[m][n]“.
     * 
     * minCost(m, n) = min (minCost(m-1, n-1), minCost(m-1, n), minCost(m, n-1)) + cost[m][n]
     * 
     * @param cost The cost matrix
     * @param m end x
     * @param n end y
     * @return min cost from (0, 0) to (m, n)
     */
    public int minCostPathNaive(int[][] cost, int m, int n) {

        if (m < 0 || n < 0) {
            return Integer.MAX_VALUE;
        } else if (m == 0 && n == 0) {
            return cost[0][0];
        } else {
            return cost[m][n] + min(minCostPathNaive(cost, m - 1, n - 1),
                    minCostPathNaive(cost, m, n - 1),
                    minCostPathNaive(cost, m - 1, n));
        }
    }
    
    private int min(int x, int y, int z) {
        return Math.min(Math.min(x, y), z);
    }
    
    public int minCostPath(int[][] cost, int m, int n) {
        
        int[][] minCost = new int[cost.length][cost[0].length];
        
        //Base case
        minCost[0][0] = cost[0][0];
        //The first colomn case, each cell can only be computed from previous one.
        for (int i = 1; i < cost.length; i++) {
            minCost[i][0] = minCost[i - 1][0] + cost[i][0];
        }
        //The first row case, each cell can only be computed from previous one.
        for (int j = 1; j < cost[0].length; j++) {
            minCost[0][j] = minCost[0][j - 1] + cost[0][j];
        }
        for (int i = 1; i < cost.length; i++) {
            for (int j = 1; j < cost[0].length; j++) {
                //minCost(m, n) = min (minCost(m-1, n-1), minCost(m-1, n), minCost(m, n-1)) + cost[m][n]
                minCost[i][j] = min(minCost[i - 1][j - 1], minCost[i - 1][j], minCost[i][j - 1]) + cost[i][j];
            }
        }
        
        return minCost[cost.length - 1][cost[0].length - 1];
    }
    
    public static void main(String[] args) {
        int cost[][] = {{1, 2, 3},
                        {4, 8, 2},
                        {1, 5, 3}};
        MinimumPathSum mp = new MinimumPathSum();
        System.out.println(mp.minCostPathNaive(cost, 2, 2) + ", " + mp.minCostPath(cost, 2, 2));
        
    }
}
