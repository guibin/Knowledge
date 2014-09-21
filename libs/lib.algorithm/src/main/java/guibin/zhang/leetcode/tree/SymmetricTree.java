package guibin.zhang.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * 
 * For example, this binary tree is symmetric:
 * 
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following is not:
 * 
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 * 
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SymmetricTree {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }
    
    public boolean isSymmetric(TreeNode l, TreeNode r) {
        
        if(l != null && r != null && l.val != r.val) return false;
        
        if (l != null && r == null) return false;
        if (l == null && r != null) return false;
        if (l == null && r == null) return true;
        
        return isSymmetric(l.left, r.right) && isSymmetric(l.right, r.left);
    }
    
    public static void main(String[] args) {
        SymmetricTree st = new SymmetricTree();
        
        TreeNode node = st.new TreeNode(1);
        System.out.println(st.isSymmetric(node));
    }
}
