package guibin.zhang.leecode;

/**
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in
 * the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid (marked 'Finish' in
 * the diagram below).
 *
 * How many possible unique paths are there?
 *
 * Run Status: Accepted! Program Runtime: 508 milli secs Progress: 61/61 test
 * cases passed.
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (m == 0 || n == 0) {
            return 0;
        }

        int[][] p = new int[m][n];
        for (int i = 0; i < n; i++) {
            p[0][i] = 1;
        }
        for (int j = 0; j < m; j++) {
            p[j][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                p[j][i] = p[j - 1][i] + p[j][i - 1];
            }
        }

        return p[m - 1][n - 1];
    }
}
