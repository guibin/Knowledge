package guibin.zhang.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

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
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> path = new ArrayList<>();
        sum(root, sum, path, result);
        
        return result;
    }
    
    
    public void sum(TreeNode root, int target, List<Integer> branch, List<List<Integer>> result) {
        
        if(root == null) return;
        
        //Firstly create nPath, then add root.val to nPath.
        //Instead of adding root.val to branch
        if(root.left == null && root.right == null && root.val == target) {
            List<Integer> nPath = new ArrayList<>(branch);
            nPath.add(root.val);
            result.add(nPath);
            return;
        }
        
        branch.add(root.val);
        sum(root.left, target - root.val, branch, result);
        sum(root.right, target - root.val, branch, result);
        branch.remove(branch.size() - 1);
    }
    
}
