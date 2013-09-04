package guibin.zhang.leecode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
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
    
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<Integer> level = new LinkedList<Integer>();
        
        if(root != null) {
            queue.offer(root);
            level.offer(0);
        }
        int m = 0;
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            m = level.poll();
            
            if(result.size() < m + 1) {
                result.add(0, new ArrayList<Integer>());
            }
            result.get(0).add(curr.val);
            
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
    
    public static void main(String[] args) {
        ArrayList<Integer> l = new ArrayList<Integer>();
        
        l.add(0);
        l.add(0, 1);
        System.out.println(l.size() + ": " + l.get(l.size() - 1));
    }
}
