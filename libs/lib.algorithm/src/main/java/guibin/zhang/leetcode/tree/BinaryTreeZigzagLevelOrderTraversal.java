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
    
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> row = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<Integer> level = new LinkedList<Integer>();
        
        if(root != null) {
            queue.offer(root);
            level.offer(0);
        } else {
            return result;
        }
        int lastLevel = 0;
        boolean reverse = false;
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            int m = level.poll();
            if(m == lastLevel) {
                row.add(curr.val);
            } else {
                if(!reverse) {
                    result.add(row);
                } else {
                    //reverse
                    int start = 0;
                    int end = row.size() - 1;
                    while(start <= end) {
                        int tmp = row.get(start);
                        row.set(start, row.get(end));
                        row.set(end, tmp);
                        start ++;
                        end --;
                    }
                    result.add(row);
                }
                reverse = !reverse;
                row = new ArrayList<Integer>();
                row.add(curr.val);//Don't forget to add the curr to the new row.
            }
            
            if(curr.left != null) {
                queue.offer(curr.left);
                level.offer(m + 1);
            }
            if(curr.right != null) {
                queue.offer(curr.right);
                level.offer(m + 1);
            }
            lastLevel = m;
        }
        //Note: at the end of the traverse, need to judge the flag of reverse.
        if (reverse) {
            int start = 0;
            int end = row.size() - 1;
            while (start <= end) {
                int tmp = row.get(start);
                row.set(start, row.get(end));
                row.set(end, tmp);
                start++;
                end--;
            }
            result.add(row);
        } else {
            result.add(row);
        }
        
        return result;
    }
    
    public List<List<Integer>> zigzagLevelOrder_v2(TreeNode root) {
        
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        
        queue.add(root);
        queue.add(null);
        TreeNode curr = null;
        boolean flip = false;
        
        while (!queue.isEmpty()) {
            curr = queue.remove();
            if (curr == null) {
                result.add(new ArrayList(row));
                row.clear();
                
                flip = !flip;
                if (queue.isEmpty()) {
                    break;
                }
                queue.add(null);
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
        TreeNode curr = null;
        queue.add(root);
        queue.add(null);
        
        while (!queue.isEmpty()) {
            curr = queue.remove();
            if (curr == null) {
                System.out.println();
                if (queue.isEmpty()) {
                    break;
                }
                queue.add(null);
            } else {
                System.out.print((curr.val == -99 ? "#" : curr.val) + ", ");
                if (curr.left != null || curr.right != null) {
                    queue.add(curr.left == null ? new TreeNode(-99) : curr.left);
                    queue.add(curr.right == null ? new TreeNode(-99) : curr.right);
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
        
        rb.printTree(a);
        
        List<List<Integer>> result = rb.zigzagLevelOrder_v2(a);
        result.forEach(r -> {r.forEach(item -> System.out.print(item + ",")); 
        System.out.println();});
    }
}
