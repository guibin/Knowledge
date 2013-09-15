package guibin.zhang.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree
 *
 *    struct TreeLinkNode {
 *      TreeLinkNode *left;
 *      TreeLinkNode *right;
 *      TreeLinkNode *next;
 *    }
 * Populate each next pointer to point to its next right node. 
 * If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 * 
 * Note:
 * 
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree 
 * (ie, all leaves are at the same level, and every parent has two children).
 * 
 * For example,
 * Given the following perfect binary tree,
 * 
 *          1
 *        /  \
 *       2    3
 *      / \  / \
 *     4  5  6  7
 * After calling your function, the tree should look like:
 * 
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \  / \
 *     4->5->6->7 -> NULL
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class PopulatingNextRightPointersInEachNode {

    public class TreeLinkNode {

        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    /**
     * Key point: Breadth first traverse. 
     * When changed the level, last.next = null, 
     * when in same level, last.next = curr;
     * 
     * This version uses O(n) space, not constant space
     * 
     * @param root 
     */
    public void connect(TreeLinkNode root) {
        
        if(root == null) return;
        
        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        Queue<Integer> level = new LinkedList<Integer>();
        
        queue.add(root);
        level.add(0);
        
        TreeLinkNode last = null;
        int connectingLevel = -1;
        while(!queue.isEmpty()) {
            TreeLinkNode curr = queue.poll();
            int m = level.poll();
            if(last != null && connectingLevel == m) {
                last.next = curr;
            } else if(last != null) {
                last.next = null;
            }
            
            last = curr;
            connectingLevel = m;
            
            if(curr.left != null) {
                queue.add(curr.left);
                level.add(m + 1);
            }
            if(curr.right != null) {
                queue.add(curr.right);
                level.add(m + 1);
            }
        }
    }
    
    /**
     * Use constant extra space.
     * 
     * https://github.com/rffffffff007/leetcode/blob/master/Populating%20Next%20Right%20Pointers%20in%20Each%20Node.java
     * http://blog.csdn.net/guixunlong/article/details/8851276
     * 
     * @param root 
     */
    public void connect_v2(TreeLinkNode root) {
        
        TreeLinkNode node;
        TreeLinkNode last = null, curr = null;
        
        while(root != null) {
            node = root;
            last = null;
            curr = null;
            
            while(node != null) {
                if(node.left != null && curr != node.left) {
                    curr = node.left;
                } else if(node.right != null) {
                    curr = node.right;
                }
                if(last != null) {
                    if(last != curr) {
                        last.next = curr;
                    }
                } else {
                    // root is the left-most node of next row.
                    root = curr;
                }
                last = curr;
                if(node.right == null || curr == node.right) {
                    node = node.next;
                }
            }
        }
    }
}
