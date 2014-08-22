package guibin.zhang.leetcode.tree;

/**
 * Given a binary tree, find the maximum path sum.
 * The path may start and end at any node in the tree.
 * 
 * For example:
 * Given the below binary tree,
 * 
 *     1
 *    / \
 *   2   3
 * 
 * Return 6.
 * 
 * http://fisherlei.blogspot.com/2013/01/leetcode-binary-tree-maximum-path-sum.html
 * http://www.cnblogs.com/shawnhue/archive/2013/06/08/leetcode_124.html
 * http://discuss.leetcode.com/questions/288/binary-tree-maximum-path-sum
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class BinaryTreeMaximumPathSum {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
//    private int maxSum;
    
    private int maxPathSum(TreeNode root) {
        
        if(root == null) return 0;
        
        int[] maxSum = {Integer.MIN_VALUE};
        maxSinglePathSum(root, maxSum);
        return maxSum[0];
        
    }
    /**
     * Compute the max sum up to root: Max(left, right) + root.val
     * @param root
     * @param maxSum int[] to store the maxSum
     * @return 
     */
    public int maxSinglePathSum(TreeNode root, int[] maxSum) {
        
        if(root == null) return 0;
        
        int leftSum = maxSinglePathSum(root.left, maxSum);
        int rightSum = maxSinglePathSum(root.right, maxSum);
        /**
         * For each node like following, there should be four ways existing for max path:
         * 1. Node only
         * 2. left-sub + node
         * 3. right-sub + node
         * 4. left-sub + node + right-sub
         * 
         *         a
         *        /  \ 
         *      node  ...
         *      /  \
         *    l-sub r-sub
         *   /   \
         *  ...
         */   
        int pathSum = root.val + Math.max(leftSum, 0) + Math.max(rightSum, 0);
        maxSum[0] = Math.max(maxSum[0], pathSum);//Compute the max sum
        
        //Here is to compute the max sum up to root. We only need one child, refer to http://www.cnblogs.com/shawnhue/archive/2013/06/08/leetcode_124.html
        return Math.max(Math.max(leftSum, rightSum), 0) + root.val;
    }
    
    public static void main(String[] args) {
        BinaryTreeMaximumPathSum bps = new BinaryTreeMaximumPathSum();
        
        BinaryTreeMaximumPathSum.TreeNode a = bps.new TreeNode(1);
        BinaryTreeMaximumPathSum.TreeNode b = bps.new TreeNode(-2);
        BinaryTreeMaximumPathSum.TreeNode c = bps.new TreeNode(3);
        BinaryTreeMaximumPathSum.TreeNode d = bps.new TreeNode(-4);
        BinaryTreeMaximumPathSum.TreeNode e = bps.new TreeNode(5);
        BinaryTreeMaximumPathSum.TreeNode f = bps.new TreeNode(-5);
        /**
         *            1a
         *           /   \
         *        -2b    3c
         *         / \   /
         *      -4d  5e -5f
         */
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        System.out.println(bps.maxPathSum(a));
    }
}
