package guibin.zhang.leecode;

import java.util.ArrayList;

/**
 * 
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * 
 * For example,
 * Given the following matrix:
 * 
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 * 
 * Run Status: Accepted!
 * Program Runtime: 508 milli secs
 * Progress: 21/21 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SpiralMatrix {
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<Integer> result = new ArrayList<Integer>();
        int rows = matrix.length;
        if (rows == 0) {
            return result;
        }
        
        int beginX = 0;
        int endX = rows - 1;
        int beginY = 0;
        int endY = matrix[0].length - 1;
        
        while (true) {
            for (int i = beginY; i <= endY; i++) {
                result.add(matrix[beginX][i]);
            }
            beginX ++;
            if (beginX > endX) break;
            
            for (int i = beginX; i <= endX; i++) {
                result.add(matrix[i][endY]);
            }
            endY --;
            if(endY < beginY) break;
            
            for (int i = endY; i >= beginY; i--) {
                result.add(matrix[endX][i]);
            }
            endX --;
            if(endX < beginX) break;
            
            for (int i = endX; i >= beginX; i--) {
                result.add(matrix[i][beginY]);
            }
            beginY ++;
            if (beginY > endY) break;
        }
        
        return result;
    }
}
