package guibin.zhang.leetcode.tree;

/**
 * 
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path 
 * such that adding up all the values along the path equals the given sum.
 * 
 * For example:
 * Given the below binary tree and sum = 22,
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class PathSum {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        //Reach the leaf node, and if the sum equals the root.value, it do have the sum.
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        /**
         * Otherwise, it is in the middle of the tree, keep on recursively judge
         */
        
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
        
    
}
