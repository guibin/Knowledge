package guibin.zhang.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
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
 * http://www.geeksforgeeks.org/level-order-tree-traversal/
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
    
    public void levelOrder(TreeNode root) {
        
        if (root == null) return;
        
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode dummy = new TreeNode(0);//For return line
        TreeNode dummy2 = new TreeNode(0);//For append #
        TreeNode curr;
        queue.add(root);
        queue.add(dummy);
        
        while (!queue.isEmpty()) {
            curr = queue.remove();
            if (curr == dummy) {
                //At the end of the each level
                System.out.println();
                
                if (!queue.isEmpty()) {
                    queue.add(dummy);
                }
            } else {
                //Access the elements here.
                System.out.print((curr == dummy2 ? "#" : curr.val) + ", ");
                
                if (curr.left != null || curr.right != null) {
                    queue.add(curr.left == null ? dummy2 : curr.left);
                    queue.add(curr.right == null ? dummy2 : curr.right);
                }
            }
        }
    }
    
    
    public List<List<Integer>> levelOrder_v2(TreeNode root) {
        
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }
    
    public void dfs(TreeNode root, int depth, List<List<Integer>> result) {
        
        if(root == null) return;
        
        //Judge the result based on depth and result.size
        if(result.size() < depth + 1) {
            result.add(new ArrayList<>());
        }
        result.get(depth).add(root.val);
        
        dfs(root.left, depth + 1, result);
        dfs(root.right, depth + 1, result);
    }
    
    public static void main(String[] args) {
         /**
          *    10
          *   /  \
          *  5   15
          *     /  \
          *    6    20
          */
        BinaryTreeLevelOrderTraversal bst = new BinaryTreeLevelOrderTraversal();
        TreeNode a = bst.new TreeNode(10);
        TreeNode b = bst.new TreeNode(5);
        TreeNode c = bst.new TreeNode(15);
        a.left = b;
        a.right = c;
        TreeNode d = bst.new TreeNode(6);
        TreeNode e = bst.new TreeNode(20);
        c.left = d;
        c.right = e;
        
        System.out.println("level order travel v2");
        List<List<Integer>> ret = bst.levelOrder_v2(a);
        ret.forEach(row -> 
        {row.forEach(i -> System.out.print(i + ","));
        System.out.println();});
        
        System.out.println("level order travel v4");
        bst.levelOrder(a);
    }
}
