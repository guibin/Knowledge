package guibin.zhang.leecode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * Given a binary tree, return the level order traversal of its nodes' values. 
 * (ie, from left to right, level by level).
 * 
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 * 
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * 
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 * 
 * Run Status: Accepted!
 * Program Runtime: 720 milli secs
 * Progress: 33/33 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class BinaryTreeLevelOrderTraversal {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<Integer> level = new LinkedList<Integer>();
        
        if(root != null) {
            queue.offer(root);
            level.offer(0);
        }
        
        TreeNode curr = null;
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> row = null;
        while(!queue.isEmpty()) {
            curr = queue.poll();
            int m = level.poll();
            if(result.size() < m + 1) {
                row = new ArrayList<Integer>();
                result.add(row);
            }
            result.get(m).add(curr.val);
            
            if(curr.left != null) {
                queue.offer(curr.left);
                level.offer(m + 1);
            }
            if(curr.right != null) {
                queue.offer(curr.right);
                level.offer(m + 1);
            }
        }
        return result;
    }
    
    public ArrayList<ArrayList<Integer>> levelOrder_v2(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        dfs(root, 0, result);
        return result;
    }
    
    public void dfs(TreeNode root, int depth, ArrayList<ArrayList<Integer>> result) {
        if(root == null) {
            return;
        }
        
        ArrayList<Integer> row = null;
        if(result.size() < depth + 1) {
            result.add(new ArrayList<Integer>());
        }
        row = result.get(depth);
        row.add(root.val);
        
        dfs(root.left, depth + 1, result);
        dfs(root.right, depth + 1, result);
    }
}
