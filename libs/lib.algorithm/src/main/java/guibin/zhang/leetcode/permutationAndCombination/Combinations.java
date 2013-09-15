package guibin.zhang.leetcode.permutationAndCombination;

import java.util.ArrayList;

/**
 * 
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * 
 * For example,
 * If n = 4 and k = 2, a solution is:
 * 
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * 
 * https://code.google.com/p/leetcode/source/browse/combinations.java
 * http://shibaili.blogspot.com/2013/06/day-38-77-combinations.html
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class Combinations {
    
    /**
     * http://discuss.leetcode.com/questions/252/combinations
     * 
     * @param n
     * @param k
     * @return 
     */
    public ArrayList<ArrayList<Integer>> combine(int n, int k) {

        if (n < k) {
            return null;
        }
        ArrayList<ArrayList<Integer>> all = new ArrayList<ArrayList<Integer>>();
        if (k == 1) {
            for (int i = 1; i <= n; i++) {
                ArrayList<Integer> al = new ArrayList<Integer>();
                al.add(i);
                all.add(al);
            }
            return all;
        }
        
        for (int i = n; i >= k; i--) {
            for (ArrayList<Integer> al : combine(i - 1, k - 1)) {
                al.add(i);
                all.add(al);
            }
        }
        return all;
    }
}
