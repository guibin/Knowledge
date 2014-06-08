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
    
    public void combineV2(int n, ArrayList<Integer> branch, ArrayList<ArrayList<Integer>> result, int startId, int k) {
        
        if (branch.size() == k) {
            result.add(new ArrayList(branch));
        } else if (branch.size() > k) {
        } else {
            for (int i = startId; i <= n; i++) {
                branch.add(i);
                combineV2(n, branch, result, ++startId, k);
                branch.remove(branch.size() - 1);
            }
        }
    }
    
    /**
     * 
     * Write a program Combinations.java that takes one command-line argument n and 
     * prints out all 2^n combinations of any size. 
     * 
     * A combination is a subset of the n elements, independent of order. 
     * As an example, when n = 3 you should get the following output.
     * a ab abc ac b bc c
     * 
     * http://introcs.cs.princeton.edu/java/23recursion/
     * 
     * combine("", "ABC") => ""             i = 0;
     *   combine("A", "BC") => "A"          i = 0;
     *     combine("AB", "C") => "AB"       i = 0;
     *       combine("ABC", "") => "ABC"    i = 0;
     *       combine("ABC", "") => X        
     *     combine("AB", "C") => X          i = 1;
     *   combine("AC", "B") => "AC"         i = 1;
     * combine("B", "AC") => "B"            i = 1;
     *   combine("BC", "A") => "BC"         i = 1;
     * combine("C", "AB") => "C"            i = 2;
     * 
     * @param prefix
     * @param s 
     */
    public void combine(String prefix, String s) {
        System.out.println(prefix);
        for (int i = 0; i < s.length(); i++) {
            combine(prefix + s.charAt(i), s.substring(i + 1));
        }
    }
    
    public void combineK(String prefix, String s, int K) {
        if (prefix.length() == K) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < s.length(); i++) {
                combineK(prefix + s.charAt(i), s.substring(i + 1), K);
            }
        }
    }
    
    public static void main(String[] args) {
        
        Combinations comb = new Combinations();
        ArrayList<ArrayList<Integer>> resultA = comb.combine(4, 2);
        
        ArrayList<ArrayList<Integer>> resultB = new ArrayList<ArrayList<Integer>>();
        comb.combineV2(4, new ArrayList<Integer>(), resultB, 1, 2);
        
        System.out.println("-------------ResultA-------------");
        for (ArrayList<Integer> list : resultA) {
            for (int i : list) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
            
        System.out.println("-------------ResultB-------------");
        for (ArrayList<Integer> list : resultB) {
            for (int i : list) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
        
        System.out.println("Combination of ADCDE");
        comb.combine("", "ABC");
        
        System.out.println("Combination K of ABC");
        comb.combineK("", "ABC", 2);
    }
}
