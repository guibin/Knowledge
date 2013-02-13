package guibin.zhang.algorithm.array;

/**
 * 
 * @author guibin
 */
public class ArrayTransform {
    
    /**
     * Write an algorithm such that if an element in an MxN matrix is 0, 
     * its entire row and column is set to 0.
     * 
     * @param matrix 
     */
    public void setZeros(int[][] matrix) {
        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];
        
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }
        
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[0].length; j++) {
                if(row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 3, 4, 5},
                          {6, 7, 0, 9, 10},
                          {11, 12, 0, 14, 15},
                          {16, 17, 18, 19, 20},
                          {21, 22, 23, 24, 25}, 
                          {26, 27, 28, 29, 0}};
        
        System.out.println("Before set zeros:");
        for(int j=0; j<6; j++) {
            for(int i=0; i<5; i++) {
                System.out.print(matrix[j][i] + "(" + j + "," + i +")\t");
            }
            System.out.println();
        }
        
        ArrayTransform at = new ArrayTransform();
        at.setZeros(matrix);
        
        System.out.println("After set zeros:");
        for(int j=0; j<6; j++) {
            for(int i=0; i<5; i++) {
                System.out.print(matrix[j][i] + "(" + j + "," + i +")\t");
            }
            System.out.println();
        }
    }
}
