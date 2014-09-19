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
 * Definition of balanced tree:
 * 1. The left and right subtrees' heights differ by at most one, AND
 * 2. The left subtree is balanced, AND
 * 3. The right subtree is balanced
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
        return determine(root) >= 0;
    }
    
    /**
     * This method looks similar with computing the height of the tree.
     * Once one of the branch is differ more than 2, just terminate computing, return -1.
     * 
     * Or we can terminate the height computation for the other child once we find the discrepancy
     * is bigger than 2 to make the recursive more efficient. 
     * 
     * @param root
     * @return 
     */
    public int determine(TreeNode root) {
        if(root == null) {
            return 0;
        } else {
            int leftHeight = determine(root.left);
            int rightHeight = determine(root.right);
            //If height diff is bigger than 1, just use -1, to avoid the compute the other child.
            if(leftHeight < 0 || rightHeight < 0 || Math.abs(leftHeight - rightHeight) > 1) return -1;
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
    
    public boolean isBalanced_v2(TreeNode root) {
        if (root == null) {
            return true;
        }

        int lhight = heightOf(root.left);
        int rhight = heightOf(root.right);
        if (Math.abs(lhight - rhight) > 1) {
            return false;
        } else {
            return isBalanced_v2(root.left) && isBalanced_v2(root.right);
        }
    }

    public int heightOf(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = heightOf(root.left);
        int rightHeight = heightOf(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * Actually this method is wrong. 
     * We should traverse to each node to judge if each node is balanced,
     * not just the max height of root and min height of root.
     * 
     * For example:
     * {1,2,2,3,3,3,3,4,4,4,4,4,4,#,#,5,5}
     *                    1
     *               /        \
     *               2         2
     *            /   \      /   \
     *          3     3     3     3
     *        /  \  /  \   / \
     *       4   4 4   4  4   4
     *      / \
     *     5   5
     * 
     * Based on the definition, this tree is balanced.
     * But the output of isBalanced(root) is false, since the diff of max height and min height of root is 2
     * 
     * @param root
     * @return 
     */
    public boolean isBalanced_wrong(TreeNode root) {
        return maxHeightOf(root) - minHeightOf(root) <= 1;
    }

    /**
     * The max height from root to leaf
     *
     * @param root
     * @return height
     */
    public int maxHeightOf(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxHeightOf(root.left), maxHeightOf(root.right)) + 1;
    }

    /**
     * The min height from root to leaf
     *
     * @param root
     * @return height
     */
    public int minHeightOf(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.min(minHeightOf(root.left), minHeightOf(root.right)) + 1;
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
