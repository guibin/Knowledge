package guibin.zhang.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
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
}
