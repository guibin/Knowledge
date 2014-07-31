package guibin.zhang.leetcode.matrix;

/**
 * Given a multidimensional array like below:
 * 
 * 0 1 0 0 3
 * 0 3 3 0 0
 * 0 0 0 0 2
 * 0 0 1 0 2
 * 0 0 0 0 0
 * "objects" are considered groups of numbers that touch along top, left, right, or bottom edges. 
 * 
 * Find the number of objects. 
 * 
 * For example given the above array, your code should detect 4 unique "Objects". 
 * {1,3,3}, {3}, {1}, and {2, 2}.
 * 
 * Question link: http://www.careercup.com/question?id=6203659591352320
 * 
 * Solution summary: Memorize "visited" nodes (ie non-zero numbers), 
 * these nodes have been added to a graph and need not be observed again. 
 * We will traverse the matrix, and upon discovering a non-zero entity, non-visited entity, 
 * perform a DFS on it. 
 * 
 * Because we visit each space exactly once, O(m*n) time, 
 * where m * n is size of the matrix. 
 * Print as we go along, enforcing only a single pass.
 * 
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class GroupNumbersInMatrix {
    
    //DFS search on non-visited and non-zero element
    public static void accessGroupNumbers(int[][] matrix, boolean[][] visited, int row, int col) {
        
        if (!visited[row][col] && matrix[row][col] != 0) {
            System.out.print(matrix[row][col] + ", ");
            visited[row][col] = true;
        }
        //look down
        if (row + 1 < matrix.length && !visited[row + 1][col] && matrix[row + 1][col] != 0) {
            accessGroupNumbers(matrix, visited, row + 1, col);
        }
        //look up
        if (row > 0 && !visited[row - 1][col] && matrix[row - 1][col] != 0) {
            accessGroupNumbers(matrix, visited, row - 1, col);
        }
        //look right
        if (col + 1 < matrix[0].length && !visited[row][col + 1] && matrix[row][col + 1] != 0) {
            accessGroupNumbers(matrix, visited, row, col + 1);
        }
        //look left
        if (col > 0 && !visited[row][col - 1] && matrix[row][col - 1] != 0) {
            accessGroupNumbers(matrix, visited, row, col - 1);
        }
    }
    
    public static void searchInMatrix(int[][] matrix) {
        
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] visited = new boolean[row][col];
        
        //Traverse the matrix
        for (int r = 0; r < row; r ++) {
            for (int c = 0; c < col; c++) {
                if (matrix[r][c] == 0 || visited[r][c]) {
                    //ignor 0s and visted cell
                } else {
                    System.out.print("{");
                    //Perform DFS search on non-visited and non-zero element
                    accessGroupNumbers(matrix, visited, r, c);
                    System.out.println("}, ");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int[][] matrix = 
            {{0, 1, 0, 0, 3},
             {0, 3, 3, 0, 0},
             {0, 0, 0, 0, 2},
             {0, 0, 1, 0, 2},
             {0, 0, 0, 0, 0}};
        searchInMatrix(matrix);
    }
}
