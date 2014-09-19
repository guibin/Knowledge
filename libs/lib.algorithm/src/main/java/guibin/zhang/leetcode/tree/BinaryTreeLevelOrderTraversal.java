package guibin.zhang.leetcode.tree;

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
    
    public void levelOrder_v4(TreeNode root) {
        
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
            
            //Judge the result based on depth and result.size
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
        
        //Judge the result based on depth and result.size
        if(result.size() < depth + 1) {
            result.add(new ArrayList<Integer>());
        }
        result.get(depth).add(root.val);
        
        dfs(root.left, depth + 1, result);
        dfs(root.right, depth + 1, result);
    }
    
    public void levelOrder_v3(TreeNode root) {
        
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> levels = new LinkedList<>();
        TreeNode curr = null;
        if (root != null) {
            q.offer(root);
            levels.offer(0);
        }
        int prevLevel = 0;
        while (!q.isEmpty()) {
            if (levels.peek() > prevLevel) {
                System.out.println();
            }
            curr = q.poll();
            prevLevel = levels.poll();
            System.out.print(curr.val + ",");
            
            if (curr.left != null) {
                q.offer(curr.left);
                levels.offer(prevLevel + 1);
            }
            if (curr.left != null) {
                q.offer(curr.right);
                levels.offer(prevLevel + 1);
            }
        }
        System.out.println();
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
        bst.levelOrder_v3(a);
        
        System.out.println("level order travel v2");
        ArrayList<ArrayList<Integer>> ret = bst.levelOrder_v2(a);
        ret.forEach(row -> 
        {row.forEach(i -> System.out.print(i + ","));
        System.out.println();});
        
        System.out.println("level order travel v4");
        bst.levelOrder_v4(a);
    }
}
