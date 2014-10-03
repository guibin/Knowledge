package guibin.zhang.leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

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
    
    
    public int minimumTotal(List<List<Integer>> triangle) {
        
        //Copy the bottom row
        List<Integer> path = new ArrayList<>(triangle.get(triangle.size() - 1));
        
        //Start from the last row but one, refer it as currRow
        for (int i = triangle.size() - 2; i >= 0 ; i--) {
            List<Integer> currRow = triangle.get(i);
            for (int j = 0; j < currRow.size(); j++) {
                int min = Math.min(path.get(j), path.get(j + 1));
                path.set(j, currRow.get(j) + min);
            }
        }
        return path.get(0);
    }
    
    
    
    /**
     * replace every element in the current row at level i 
     * with the minimum sum possible after adding elements from the row below 
     * i.e. at level i+1. The code traverse the algorithm in bottom up fashion
     * 
     * Modify the triangle in-place.
     * 
     * @param triangle
     * @return 
     */
    public int minumTotal_v3(ArrayList<ArrayList<Integer>> triangle) {
        
        for (int i = triangle.size() - 2; i >= 0; i--) {
            
            ArrayList<Integer> currRow = triangle.get(i);
            ArrayList<Integer> nextLevel = triangle.get(i + 1);
            
            for (int j = 0; j < currRow.size(); j++) {
                int sum2 = currRow.get(j) + nextLevel.get(j);
                int sum3 = currRow.get(j) + nextLevel.get(j + 1);
                currRow.set(j, Math.min(sum2, sum3));
            }
        }
        return triangle.get(0).get(0);
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
    
}
