package guibin.zhang.leetcode.tree;

/**
 *
 * Given a binary tree, find its minimum depth.
 * 
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *  
 * Run Status: Accepted!
 * Program Runtime: 668 milli secs
 * Progress: 41/41 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class MinimumDepthOfBinaryTree {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    public int minDepth(TreeNode root) {
        
        if(root == null) {
            return 0;
        }
        
        int leftDepth = 1 + minDepth(root.left);
        int rightDepth = 1 + minDepth(root.right);
        
        //the tree only has right branch
        if(root.left == null && leftDepth ==  1) {
            return rightDepth;
        }
        //the tree only has left branch
        else if(root.right == null && rightDepth == 1) {
            return leftDepth;
        }
        else {
            return Math.min(leftDepth, rightDepth);
        }
    }
    
    public int minDepth_v2(TreeNode root) {
        if(root == null) {
            return 0;
        } else {
            return depth(root);//No plus one
        }
    }
    
    /**
     * This is the standard function to get the min depth of the tree.
     * @param root
     * @return 
     */
    private int depth(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;//Note this MAX_VALUE due to the Math.min() at the end of the function.
        } else if (root.left == null && root.right == null) {
            return 1;
        } else{
            return Math.min(depth(root.left), depth(root.right)) + 1;
        }
    }
}
