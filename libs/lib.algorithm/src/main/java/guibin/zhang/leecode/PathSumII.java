package guibin.zhang.leecode;

import java.util.ArrayList;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * 
 * For example:
 * Given the below binary tree and sum = 22,
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * return
 * 
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 * 
 * Run Status: Accepted!
 * Program Runtime: 852 milli secs
 * Progress: 114/114 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class PathSumII {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> path = new ArrayList<Integer>();
        sum(root, sum, path, result);
        
        return result;
    }
    
    public void sum(TreeNode root, int sum, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result) {
        
        if(root == null) {
            return;
        }
        
        if(root.left == null && root.right == null && root.val == sum) {
            ArrayList<Integer> nPath = new ArrayList<Integer>(path);
            nPath.add(root.val);
            result.add(nPath);
        }
        
        /**
         * The reason to create the new ArrayList as nPath is to avoid the interference with other invoke.
         */
        ArrayList<Integer> nPath = new ArrayList<Integer>(path);
        nPath.add(root.val);
        
        sum(root.left, sum - root.val, nPath, result);
        sum(root.right, sum - root.val, nPath, result);
    }
    
    public void sum_v2(TreeNode root, int sum, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> result) {
        
        if(root == null) {
            return;
        }
        
        if(root.left == null && root.right == null && root.val == sum) {
            ArrayList<Integer> nPath = new ArrayList<Integer>(path);
            nPath.add(root.val);
            result.add(nPath);
            
        }
        
        path.add(root.val);
        
        /**
         * Here we reuse the path, but note to remove the last one element.
         */
        sum(root.left, sum - root.val, path, result);
        sum(root.right, sum - root.val, path, result);
        path.remove(path.size() - 1);
    }
    
}
