package guibin.zhang.leecode.matrix;

/**
 *
 * Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * 
 * For example,
 * Given n = 3,
 * 
 * You should return the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 * 
 * Run Status: Accepted!
 * Program Runtime: 540 milli secs
 * Progress: 21/21 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SpiralMatrixII {

    public int[][] generateMatrix(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int beginX = 0;
        int endX = n - 1;
        int beginY = 0;
        int endY = n - 1;
        int[][] matrix = new int[n][n];
        int end = n * n;

        for (int i = 1; i <= end;) {

            for (int j = beginY; j <= endY && i <= end && beginX <= endX; j++) {
                matrix[beginX][j] = i;
                i++;
            }
            beginX++;

            for (int j = beginX; j <= endX && i <= end && endY >= beginY; j++) {
                matrix[j][endY] = i;
                i++;
            }
            endY--;

            for (int j = endY; j >= beginY && i <= end && endX >= beginX; j--) {
                matrix[endX][j] = i;
                i++;
            }
            endX--;

            for (int j = endX; j >= beginX && i <= end && beginY <= endY; j--) {
                matrix[j][beginY] = i;
                i++;
            }
            beginY++;
        }

        return matrix;

    }
}
