package guibin.zhang.leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

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
    public List<List<Integer>> generate(int numRows) {
        
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j <= i; j++) {
                if(j == 0) {
                    row.add(1);//The first col is always 1
                } else if(j == i) {
                    row.add(1);//The diagonal line, the last element is always 1
                } else {
                    //current_node = previous_level(j-1) + previous_level(j)
                    row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(row);
        }
        return result;
    }
    
    public static void main(String[] args) {
        PascalsTriangle pt = new PascalsTriangle();
        List<List<Integer>> result = pt.generate(5);
        result.stream().forEach(list -> {
            list.stream().forEach((i) -> {
                System.out.print(i + ",");
            });
            System.out.println();
        });

    }
}
