package guibin.zhang.leetcode.tree;

/**
 * Given two binary trees, write a function to check if they are equal or not.
 * 
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SameTree {
    
    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    /**
     * Run Status: Accepted!
     * Program Runtime: 588 milli secs
     * Progress: 52/52 test cases passed.
     * 
     * @param p
     * @param q
     * @return 
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        
        if(p == null && q == null) {
            return true;
        } else if(p != null && q == null) {
            return false;
        } else if(p == null && q != null) {
            return false;
        } else {
            if(p.val != q.val) {
                return false;
            } else {
                return isSameTree(p.left,q.left) && isSameTree(p.right, q.right);
            }
        }
    }
}
