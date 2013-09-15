package guibin.zhang.leecode.matrix;

/**
 *
 * Follow up for "Unique Paths":
 * 
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * 
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * 
 * For example,
 * 
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * 
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * The total number of unique paths is 2.
 * 
 * Note: m and n will be at most 100.
 * 
 * Run Status: Accepted!
 * Program Runtime: 532 milli secs
 * Progress: 42/42 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */

public class UniquePathsII {
    
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int rows = obstacleGrid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = obstacleGrid[0].length;
        int[][] p = new int[rows][cols];

        for (int i = 0; i < cols; i++) {
            if (obstacleGrid[0][i] != 1) {
                p[0][i] = 1;
            } else {
                break;
            }
        }
        for (int j = 0; j < rows; j++) {
            if (obstacleGrid[j][0] != 1) {
                p[j][0] = 1;
            } else {
                break;
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (obstacleGrid[i][j] != 1) {
                    p[i][j] = p[i - 1][j] + p[i][j - 1];
                }
            }
        }
        return p[rows - 1][cols - 1];
    }
}
