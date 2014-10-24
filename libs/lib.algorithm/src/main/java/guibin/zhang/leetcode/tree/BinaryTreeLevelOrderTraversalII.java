package guibin.zhang.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
 * (ie, from left to right, level by level from leaf to root).
 * 
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 * 
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * 
 * [
 *   [15,7]
 *   [9,20],
 *   [3],
 * ]
 * 
 * Run Status: Accepted!
 * Program Runtime: 744 milli secs
 * Progress: 34/34 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class BinaryTreeLevelOrderTraversalII {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        List<Integer> branch = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode dummy = new TreeNode(0);
        q.add(root);
        q.add(dummy);
        
        while (!q.isEmpty()) {
            TreeNode curr = q.remove();
            if (curr == dummy) {
                result.add(0, new ArrayList<>(branch));
                branch.clear();
                if (!q.isEmpty()) {
                    q.add(dummy);
                }
            } else {
                branch.add(curr.val);
                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
        }
        return result;
    }
    
}
