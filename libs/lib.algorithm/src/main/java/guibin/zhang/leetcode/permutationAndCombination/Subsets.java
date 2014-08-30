package guibin.zhang.leetcode.permutationAndCombination;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a set of distinct integers, S, return all possible subsets.
 * 
 * Note:
 * 
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If S = [1,2,3], a solution is:
 * 
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class Subsets {
    
    public ArrayList<ArrayList<Integer>> subsets(int[] a) {
        
        Arrays.sort(a);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        //Select each item from S
        for(int i = 0; i < a.length; i ++) {
            int size = result.size();
            //Pick up the existing result, add the item to it.
            for(int j = 0; j < size; j++) {
                ArrayList<Integer> subset = new ArrayList<>(result.get(j));
                subset.add(a[i]);
                result.add(subset);
            }
        }
        return result;
    }
    
    /**
     * http://blog.csdn.net/u011095253/article/details/9158397
     * http://discuss.leetcode.com/questions/253/subsets
     * @param S
     * @return 
     */
    public ArrayList<ArrayList<Integer>> subsets_v2(int[] S) {
        
        Arrays.sort(S);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> branch = new ArrayList<>();
        result.add(branch);
        fullCombination(result, branch, S, 0);
        return result;
    }
    
    public void fullCombination(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> branch, int[] a, int startId) {
        for(int i = startId; i < a.length; i ++) {
            branch.add(a[i]);
            res.add(new ArrayList<>(branch));
            fullCombination(res, branch, a, i + 1);//i + 1 or ++startId
            branch.remove(branch.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        Subsets s = new Subsets();
        int[] set = {1, 2, 3};
        ArrayList<ArrayList<Integer>> result = s.subsets(set);
        System.out.println("size=" + result.size());
        for(ArrayList<Integer> row : result) {
            for(int i : row) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
    }
}
