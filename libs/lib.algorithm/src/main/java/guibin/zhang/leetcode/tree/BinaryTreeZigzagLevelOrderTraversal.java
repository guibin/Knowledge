package guibin.zhang.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. 
 * (ie, from left to right, then right to left for the next level and alternate between).
 * 
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 * 
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * 
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 * 
 * Run Status: Accepted!
 * Program Runtime: 712 milli secs
 * Progress: 33/33 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    public List<List<Integer>> zigzagLevelOrder_v2(TreeNode root) {
        
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        TreeNode dummy = new TreeNode(0);
        
        queue.add(root);
        queue.add(dummy);
        TreeNode curr;
        boolean flip = false;
        
        while (!queue.isEmpty()) {
            curr = queue.remove();
            if (curr == dummy) {
                result.add(new ArrayList(row));
                row.clear();
                
                flip = !flip;
                if (!queue.isEmpty()) {
                    queue.add(dummy);
                }
            } else {
                if (flip) {
                    row.add(0, curr.val);//Access it and save it in reverse order
                } else {
                    row.add(curr.val);//Access it
                }
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }
        return result;
    }
    
    public void printTree(TreeNode root) {
        
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curr;
        TreeNode dummy = new TreeNode(0);
        TreeNode dummy2 = new TreeNode(0);
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
                System.out.print((curr == dummy2 ? "#" : curr.val) + ", ");
                if (curr.left != null || curr.right != null) {
                    queue.add(curr.left == null ? dummy2 : curr.left);
                    queue.add(curr.right == null ? dummy2 : curr.right);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        BinaryTreeZigzagLevelOrderTraversal rb = new BinaryTreeZigzagLevelOrderTraversal();
        TreeNode a = rb.new TreeNode(1);
        TreeNode b = rb.new TreeNode(2);
        TreeNode c = rb.new TreeNode(3);
        a.left = b;
        a.right = c;
        TreeNode d = rb.new TreeNode(4);
        TreeNode e = rb.new TreeNode(5);
        TreeNode f = rb.new TreeNode(6);
        TreeNode g = rb.new TreeNode(7);
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        TreeNode h = rb.new TreeNode(8);
        TreeNode i = rb.new TreeNode(9);
        TreeNode j = rb.new TreeNode(10);
        TreeNode k = rb.new TreeNode(11);
        TreeNode l = rb.new TreeNode(12);
        TreeNode m = rb.new TreeNode(13);
        TreeNode n = rb.new TreeNode(14);
        TreeNode o = rb.new TreeNode(15);
        d.left = h;
        d.right = i;
        e.left = j;
        e.right = k;
        f.left = l;
        f.right = m;
        g.left = n;
//        g.right = o;
        System.out.println("Before zigzag traverse:");
        rb.printTree(a);
        
        System.out.println("After zigzag traverse:");
        List<List<Integer>> result = rb.zigzagLevelOrder_v2(a);
        result.forEach(r -> {r.forEach(item -> System.out.print(item + ",")); 
        System.out.println();});
    }
}
