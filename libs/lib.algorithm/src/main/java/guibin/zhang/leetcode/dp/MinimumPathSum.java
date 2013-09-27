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
}
