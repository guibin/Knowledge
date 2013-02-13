package guibin.zhang.algorithm.array;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image
 * is 4 bytes, write a method to rotate the image by 90 degree clockwise.
 * 
 * @author guibin
 */
public class ArrayRotation {
    
    /**
     * .
     * A(0,0)   B(0,1)  C(0,3)  D(0,3)
     * E(0,1)   F(1,1)  G(2,1)  H(3,1)
     * 
     * 
     * @param matrix
     * @param n The n dimension, nxn matrix
     */
    public int[][] rotate(int[][] matrix, int n) {
        
        int[][] result = new int[n][n];
        
        for(int j = 0; j < n; j++) {
            for(int i = 0; i < n; i++) {
                //swap matrix[j][i] with matrix[i][n - 1 - j]
                result[i][n - 1 - j] = matrix[j][i];
            }
        }
        return result;
    }
    
    /**
     * 
     * 1(0,0)	2(0,1)	3(0,2)	4(0,3)	5(0,4)	
     * 6(1,0)	7(1,1)	8(1,2)	9(1,3)	10(1,4)	
     * 11(2,0)	12(2,1)	13(2,2)	14(2,3)	15(2,4)	
     * 16(3,0)	17(3,1)	18(3,2)	19(3,3)	20(3,4)	
     * 21(4,0)	22(4,1)	23(4,2)	24(4,3)	25(4,4)
     * 
     * @param matrix
     * @param n 
     */
    public void rotataInplace(int[][] matrix, int n) {
        
        int layers = n / 2;
        for(int lyr = 0; lyr < layers; lyr++) {
            int[] tmp = new int[n - lyr * 2];
            
            //top to right
            for(int  i = lyr; i < n - lyr; i++) {
                //save right
                tmp[i - lyr] = matrix[i][n - lyr - 1];//NOTE: the index of tmp.
                matrix[i][n - lyr - 1] = matrix[lyr][i];
            }
            
            //left to top
            for(int i = lyr; i < n - lyr; i++) {
                matrix[lyr][n - i - 1] = matrix[i][lyr];
            }
            
            //down to left
            for(int i = lyr; i < n - lyr; i++) {
                matrix[i][lyr] = matrix[n - lyr - 1][i];
            }
            
            //right to down
            for(int i = lyr; i < n -lyr; i++) {
                matrix[n - lyr - 1][n - i - 1] = tmp[i - lyr];//NOTE: the index of tmp.
            }
        }
        
    }
    
    public static void main(String[] args) {
        ArrayRotation ar = new ArrayRotation();
        int[][] matrix = {{1, 2, 3, 4, 5},
                          {6, 7, 8, 9, 10},
                          {11, 12, 13, 14, 15},
                          {16, 17, 18, 19, 20},
                          {21, 22, 23, 24, 25}};
        
        System.out.println("Before rotation:");
        for(int j=0; j<5; j++) {
            for(int i=0; i<5; i++) {
                System.out.print(matrix[j][i] + "(" + j + "," + i +")\t");
            }
            System.out.println();
        }
        
        int[][] res = ar.rotate(matrix, 5);
        
        System.out.println("After rotation:");
        for(int j=0; j<5; j++) {
            for(int i=0; i<5; i++) {
                System.out.print(res[j][i] + "(" + j + "," + i +")\t");
            }
            System.out.println();
        }
        
        System.out.println("After inplace rotation:");
        ar.rotataInplace(matrix, 5);
        for(int j=0; j<5; j++) {
            for(int i=0; i<5; i++) {
                System.out.print(matrix[j][i] + "(" + j + "," + i +")\t");
            }
            System.out.println();
        }
    }
    
}
