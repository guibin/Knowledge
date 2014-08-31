package guibin.zhang.leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an index k, return the kth row of the Pascal's triangle.
 * 
 * For example, given k = 3,
 * Return [1,3,3,1].
 * 
 * Note:
 * 
 * Could you optimize your algorithm to use only O(k) extra space?
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 * 
 * http://blog.csdn.net/guixunlong/article/details/8851137
 * 
 */
public class PascalsTriangleII {
    
    public ArrayList<Integer> getRow(int rowIndex) {

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i <= rowIndex; i++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    row.add(1);
                } else if (j == i) {
                    row.add(1);
                } else {
                    //current_node = previous_level(j-1) + previous_level(j)
                    row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(row);
        }
        return result.get(rowIndex);
    }
    
    /**
     * NO extra space is used.
     * 
     * @param rowIndex
     * @return 
     */
    public List<Integer> getRow_v2(int rowIndex) {
        
        List<Integer> result = new ArrayList<>(); 
        
        for(int i = 0; i <= rowIndex; i++) {
            result.add(1);
            for(int j = result.size() - 2; j >= 0; j--) {
                int value = result.get(j);
                if(j > 0) {
                    value += result.get(j - 1);
                }
                result.set(j, value);
            }
        }
        return result;
    }
    
    /**
     * Build Pascal's Triangle from the first line, just save the result in the same line.
     * 
     * http://www.darrensunny.me/leetcode-pascals-triangle-ii/
     * 
     * @param rowIndex
     * @return 
     */
    public List<Integer> getRow_v3(int rowIndex) {
        
        ArrayList<Integer> result = new ArrayList<>(); 
        result.add(1);//The row 0 case, just one element.
        
        // Build each row one at a time
        for (int i = 1; i <= rowIndex; i++) {
            int tmp1 = 1; // Leading 1
            for (int j = 1; j < i; j++) {
                int tmp2 = result.get(j);// Cache the value before it is overwritten
                result.set(j, tmp1 + tmp2);
                tmp1 = tmp2;
            }
            result.add(1);// Tailing 1
        }
        
        return result;
    }
    
    
    public void print(List<Integer> row) {
        for(int i : row) {
            System.out.print(i + ",");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        
        PascalsTriangleII pt = new PascalsTriangleII();
//        pt.print(pt.getRow(0));
//        pt.print(pt.getRow(1));
//        pt.print(pt.getRow_v2(2));
//        pt.print(pt.getRow_v2(3));
//        pt.print(pt.getRow_v2(4));
        pt.print(pt.getRow_v3(5));
//        pt.print(pt.getRow_v3(6));
//        pt.print(pt.getRow_v3(7));
        
    }
}
