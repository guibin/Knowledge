package guibin.zhang.leetcode.matrix;

import java.util.ArrayList;

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
        
        if(rowIndex < 0) {
            return new ArrayList<Integer>();
        } else if(rowIndex == 0) {//Be careful: when index == 0, the output should be 1.
            ArrayList<Integer> row = new ArrayList<Integer>();
            row.add(1);
            return row;
        }
        else {
            for(int i = 0; i <= rowIndex; i++) {
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
        return result.get(rowIndex);
    }
    
    /**
     * NO extra space is used.
     * 
     * @param rowIndex
     * @return 
     */
    public ArrayList<Integer> getRow_v2(int rowIndex) {
        
        ArrayList<Integer> result = new ArrayList<Integer>(); 
        if(rowIndex < 0) {
            return result;
        } 
        
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
    
    public void print(ArrayList<Integer> row) {
        for(int i : row) {
            System.out.print(i + ",");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        
        PascalsTriangleII pt = new PascalsTriangleII();
        pt.print(pt.getRow(1));
        pt.print(pt.getRow(2));
        pt.print(pt.getRow(3));
        pt.print(pt.getRow(4));
        pt.print(pt.getRow(5));
        pt.print(pt.getRow(6));
        pt.print(pt.getRow(7));
        
    }
}
