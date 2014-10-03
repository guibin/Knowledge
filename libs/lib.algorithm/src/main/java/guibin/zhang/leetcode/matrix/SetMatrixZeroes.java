package guibin.zhang.leetcode.matrix;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SetMatrixZeroes {
    
    /**
     * Use the first row and the first col to mark the status of the whole matrix.
     * 1. check the zero status of the first row and the first col.
     * 2. mark zero on the first row and the first col when matrix[i][j] is zero.
     * 3. set the matrix as zero based on the first row and the fist col.
     * 4. Process the first row and the first col.
     * @param matrix 
     */
    public void setZero_v3(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        boolean rowZero = false;
        boolean colZero = false;
        //The first row
        for (int j = 0; j < cols; j++) {
            if (matrix[0][j] == 0) rowZero = true;
        }
        
        //The first col
        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) colZero = true;
        }
        
        //Mark zero on first row and first col, start from 1
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        //Set zero based on the first col, start from 1
        for (int i = 1; i < rows; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < cols; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        //Set zero based on the first row
        for (int j = 1; j < cols; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < rows; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        //Process the first col
        if (colZero) {
            for (int i = 0; i < rows; i++) {
                matrix[i][0] = 0;
            }
        }
        
        //Process the first row
        if (rowZero) {
            for (int j = 0; j < cols; j++) {
                matrix[0][j] = 0;
            }
        }
    }
    
    /**
     * Run Status: Accepted!
     * Program Runtime: 692 milli secs
     * Progress: 154/154 test cases passed.
     * 
     * Use O(m + n) space
     * 
     * @param matrix 
     */
    public void setZeroes(int[][] matrix) {
        
        int rows = matrix.length;
        if(rows == 0) return;
        int cols = matrix[0].length;
        Set<Integer> r = new HashSet<>();
        Set<Integer> c = new HashSet<>();
        
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] == 0) {
                    r.add(i);
                    c.add(j);
                }
            }
        }
        
        for(int i : r) {
            for(int jj = 0; jj < cols; jj++) {
                matrix[i][jj] = 0;
            }
        }
        for(int j : c) {
            for(int ii = 0; ii < rows; ii++) {
                matrix[ii][j] = 0;
            }
        }
    }
    
    /**
     * Since it is required to do in place, so use the element itself to store the status.
     * It is buggy here, the -1 could be confused by the existing value of the matrix.
     * If the matrix is Comparable[][], we can use the dummy object to distinguish it.
     * 
     * @param matrix 
     */
    public void setZeroes_v2(int[][] matrix) {
        int rows = matrix.length;
        if(rows == 0) return;
        int cols = matrix[0].length;
        
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] == 0) {
                    for(int jj = 0; jj < cols; jj++) {
                        if(matrix[i][jj] != 0) {
                            matrix[i][jj] = -1;
                        }
                    }
                    for(int ii = 0; ii < rows; ii++) {
                        if(matrix[ii][j] != 0) {
                            matrix[ii][j] = -1;
                        }
                    }
                }
            }
        }
        
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] == -1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    
}
