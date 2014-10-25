package guibin.zhang.leetcode.dp;

import java.util.Stack;

/**
 *
 * Given a 2D binary matrix filled with 0's and 1's, 
 * find the largest rectangle containing all ones and return its area.
 * 
 * http://www.cnblogs.com/lichen782/p/leetcode_maximal_rectangle.html
 * http://yyeclipse.blogspot.com/2012/11/solving-maximal-rectangle-problem-based.html
 * http://discuss.leetcode.com/questions/260/maximal-rectangle
 * http://tianrunhe.wordpress.com/2012/07/26/largest-rectangle-in-histogram/
 * http://tech-wonderland.net/blog/leetcode-maximal-rectangle.html
 * http://wehackcode.wordpress.com/2012/11/26/maximal-rectangle/
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class MaximalRectangle {
    
    public int maximalRectangle_v2(char[][] matrix) {
        
        int height = matrix.length;
        if (height == 0) return 0;
        int width = matrix[0].length, hist[] = new int[width], left[] = new int[width], result = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < height; i++) {
            stack.clear();
            for (int j = 0; j < width; j++) {
                hist[j] = matrix[i][j] == '0' ? 0 : (hist[j] + 1);
                while (!stack.empty() && hist[stack.peek()] >= hist[j]) stack.pop();
                left[j] = stack.empty() ? 0 : (stack.peek() + 1);
                stack.push(j);
            }
            stack.clear();
            int right = 0;
            for (int j = width - 1; j >= 0; j--) {
                while (!stack.empty() && hist[stack.peek()] >= hist[j]) stack.pop();
                right = stack.empty() ? width : stack.peek();
                stack.push(j);
                result = Math.max(result, hist[j] * (right - left[j]));
            }
        }
        return result;
    }
    
    
    public int maximalRectangle(char[][] matrix) {
        
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        if(matrix.length == 1 && matrix[0].length == 1) {
            if(matrix[0][0] == '0') {
                return 0;    
            } else {
                return 1;
            }
        }
        
        int[][] mt = new int[matrix.length][matrix[0].length];
        
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                mt[0][i] = matrix[0][i] - '0' + matrix[j][i] - '0';    
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                mt[i][0] = matrix[i][0] - '0' + matrix[i][j] - '0';
            }
        }
        int i = 0; 
        int j = mt[0].length - 1;
        for (; i < mt[0].length; i++) {
            if (mt[0][i] > 0) {
                break;
            }
        }
        for (; j >= i; j--) {
            if (mt[0][j] > 0) {
                break;
            }
        }
        
        int m = 0;
        int n = mt.length - 1;
        for (; m < mt.length; m++) {
            if (mt[m][0] > 0) {
                break;
            }
        }
        for (; n >= m; n--) {
            if (mt[m][0] > 0) {
                break;
            }
        }
        return (m - n + 1) * (j - i + 1);
    }
}
