package guibin.zhang.leetcode.tree;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * http://www.ardendertat.com/2011/10/10/programming-interview-questions-7-binary-search-tree-check/
 * 
 * 递归地判断当前节点是否大于**左**孩子，并且是否大于其左孩子的每一个**右**子孙；
 * 同理，判断当前节点是否小于**右**孩子，并且是否小于其右孩子的每一个**左**子孙
 * 
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ValidateBinarySearchTree {
    
    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    public boolean isValidBST(TreeNode root) {
        
//        return isValidBST(root, new TreeNode(Integer.MIN_VALUE));
        return isValidBST(root, Integer.MAX_VALUE , Integer.MIN_VALUE);
    }
    
    public boolean isValidBST(TreeNode root, int max, int min) {
        
        if(root == null) {
            return true;
        }
        
        if(!isValidBST(root.left, root.val, min)){
            return false;
        }
        if(root.val <= min || root.val >= max) {
            return false;
        }
        if(!isValidBST(root.right, max, root.val)) {
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        
        ValidateBinarySearchTree fbt = new ValidateBinarySearchTree();
        TreeNode a = fbt.new TreeNode(1);
        TreeNode b = fbt.new TreeNode(1);
        
        a.left = b;
        System.out.println(fbt.isValidBST(a));
    }
}
