package guibin.zhang.leetcode.tree;

/**
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as 
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * 
 * https://github.com/mengli/leetcode/blob/master/Balanced%20Binary%20Tree.java
 * http://www.cnblogs.com/infinityu/archive/2013/05/11/3073411.html
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class BalancedBinaryTree {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    public boolean isBalenced_v1(TreeNode root) {
        
        return determine(root) >= 0;
    }
    
    public int determine(TreeNode root) {
        
        if(root == null) {
            return 0;
        } else {
            int leftDepth = determine(root.left);
            int rightDepth = determine(root.right);
            if(leftDepth < 0 || rightDepth < 0 || Math.abs(leftDepth - rightDepth) > 1) return -1;
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }
    
    public boolean isBalenced_v2(TreeNode root) {
        
        return balancedHeight(root) >= 0;
    }
    
    public int balancedHeight(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left = balancedHeight(root.left);
        if(left == -1) return -1;
        int right = balancedHeight(root.right);
        if(right == -1) return -1;
        if(Math.abs(left - right) <= 1) {
            return Math.max(left, right) + 1;
        } else {
            return -1;
        }
    }
    
    public int heightOf(TreeNode root) {
        
        if(root == null) return 0;
        
        int leftHeight = heightOf(root.left);
        int rightHeight = heightOf(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * Need to be debugged.
     * @param root
     * @return 
     */
    public boolean isBalanced_error(TreeNode root) {
        
        if(root == null || root.left == null && root.right == null) {
            return true;
        } else if(root.left != null && root.right != null) {
            return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1;
        } else if(root.left != null) {
            return maxDepth(root.left) <= 1;
        } else {
            return maxDepth(root.right) <= 1;
        }
    }
    
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return Integer.MIN_VALUE;
        } else if(root.left == null && root.right == null) {
            return 1;
        } else {
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }
    
    public static void main(String[] args) {
        /**
          *          1
          *         / \
          *        2   5
          *       / \   \
          *      3   4   6
         */

        BalancedBinaryTree fbt = new BalancedBinaryTree();
        TreeNode a = fbt.new TreeNode(1);
        TreeNode b = fbt.new TreeNode(2);
        TreeNode c = fbt.new TreeNode(3);
        TreeNode d = fbt.new TreeNode(4);
        TreeNode f = fbt.new TreeNode(5);
        TreeNode g = fbt.new TreeNode(6);
        a.left = b;
//        a.right = f;
//        b.left = c;
//        b.right = d;
//        d.left = c;
//        f.right = g;
//        System.out.println(fbt.minDepth(a));
    }
}
