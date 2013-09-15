package guibin.zhang.leetcode.matrix;

import java.util.ArrayList;

/**
 * 
 * Given numRows, generate the first numRows of Pascal's triangle.
 * 
 * For example, given numRows = 5,
 * Return
 * 
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
* 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class PascalsTriangle {
    public ArrayList<ArrayList<Integer>> generate(int numRows) {
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(numRows <= 0) {
            return result;
        } else if(numRows == 1) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            row.add(numRows);
            result.add(row);
            return result;
        } else {
            for(int i = 0; i < numRows; i++) {
                ArrayList<Integer> row = new ArrayList<Integer>();
                for(int j = 0; j <= i; j++) {
                    if(j == 0) {
                        row.add(1);
                    } else if(j == i) {
                        row.add(1);
                    } else {
                        //current_node = previous_level(j-1) + previous_level(j)
                        row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                    }
                }
                result.add(row);
            }
        }
        return result;
    }
}
