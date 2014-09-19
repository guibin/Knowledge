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
    
    public boolean isBalanced(TreeNode root) {
        return maxHeightOf(root) -  minHeightOf(root) <= 1;
    }
    
    /**
     * The max height from root to leaf
     * @param root
     * @return height
     */
    public int maxHeightOf(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxHeightOf(root.left), maxHeightOf(root.right)) + 1;
    }
    
    /**
     * The min height from root to leaf
     * @param root
     * @return height
     */
    public int minHeightOf(TreeNode root) {
        if (root == null) return 0;
        return Math.min(minHeightOf(root.left), minHeightOf(root.right)) + 1;
    }
    
    public int heightOf(TreeNode root) {
        
        if(root == null) return 0;
        
        int leftHeight = heightOf(root.left);
        int rightHeight = heightOf(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    public static void main(String[] args) {
        /**
          *          1
          *         / \
          *        2   5
          *       / \   \
          *      3   4   
         */

        BalancedBinaryTree fbt = new BalancedBinaryTree();
        TreeNode a = fbt.new TreeNode(1);
        TreeNode b = fbt.new TreeNode(2);
        TreeNode c = fbt.new TreeNode(3);
        TreeNode d = fbt.new TreeNode(4);
        TreeNode f = fbt.new TreeNode(5);
//        TreeNode g = fbt.new TreeNode(6);
        a.left = b;
        a.right = f;
        b.left = c;
        b.right = d;
        d.left = c;
//        f.right = g;
//        System.out.println(fbt.minDepth(a));
        
        System.out.println(fbt.minHeightOf(a));
    }
}
