package guibin.zhang.leetcode.matrix;

import java.util.ArrayList;

/**
 *
 * Given a triangle, find the minimum path sum from top to bottom. 
 * Each step you may move to adjacent numbers on the row below.
 * 
 * For example, given the following triangle
 * [
 *     [2],
 *    [3,4],
 *   [6,5,7],
 *  [4,1,8,3]
 * ]
 * 
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * 
 * http://discuss.leetcode.com/questions/285/triangle
 * https://github.com/mengli/leetcode/blob/master/Triangle.java
 * https://github.com/zhongjianluxian/LeetCode/blob/master/src/LeetCode/Triangle.java
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class Triangle {
    
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        
        int lastRow = triangle.size() - 1;
        int[] path = new int[triangle.size()];
        //Initialize the path
        for(int m = 0; m < triangle.get(lastRow).size(); m++) {
            path[m] = triangle.get(lastRow).get(m);
        }
        
        //From the last row but one, upto to the first row
        for(int row = triangle.size() - 2; row >= 0; row--) {
            for(int j = 0, col = 0; j < triangle.get(row).size(); j++, col++) {
                path[col] = triangle.get(row).get(col) + Math.min(path[j], path[j + 1]);
            }
        }
        
        return path[0];
    }
    
    /**
     * If the triangle can be modified, we can do it in place.
     * @param triangle
     * @return 
     */
    public int minumTotal_v2(ArrayList<ArrayList<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
            }
        }
        return triangle.get(0).get(0);
    }
    
    /**
     * replace every element in the current row at level i 
     * with the minimum sum possible after adding elements from the row below 
     * i.e. at level i+1. The code traverse the algorithm in bottom up fashion
     * 
     * @param triangle
     * @return 
     */
    public int minumTotal_v3(ArrayList<ArrayList<Integer>> triangle) {
        for (int i = triangle.size() - 2; i >= 0; i--) {
            ArrayList<Integer> al = triangle.get(i);
            ArrayList<Integer> nextLevel = triangle.get(i + 1);
            for (int j = 0; j < al.size(); j++) {
                int sum2 = 0, sum3 = 0;
                sum2 = al.get(j) + nextLevel.get(j);
                sum3 = al.get(j) + nextLevel.get(j + 1);
                al.set(j, Math.min(sum2, sum3));
            }
        }
        return triangle.get(0).get(0);
    }
}
