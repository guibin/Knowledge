package guibin.zhang.leecode.tree;

import java.util.ArrayList;

/**
 * 
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 * 
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 * 
 *   1         3     3      2      1
 *    \       /     /      / \      \
 *     3     2     1      1   3      2
 *    /     /       \                 \
 *   2     1         2                 3
 * 
 * http://discuss.leetcode.com/questions/269/unique-binary-search-trees-ii
 * http://yucoding.blogspot.com/2013/05/leetcode-question115-unique-binary.html
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class UniqueBinarySearchTreesII {
   
    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
   
    public ArrayList<TreeNode> generateTrees(int n) {
        
        return generateTrees(1, n);
    }
    
    /**
     * Pick a number i between 1..n as the root, 
     * and take all numbers less than i as the left sub tree, 
     * and all numbers greater than i as the right sub tree. 
     * Recursively construct the BST.
     * 
     * @param start
     * @param end
     * @return 
     */
    public ArrayList<TreeNode> generateTrees(int start, int end) {
        
        ArrayList<TreeNode> unique = new ArrayList<TreeNode>();
        if(start > end) {
            unique.add(null);
            return unique;
        }
        
        for(int i = start; i <= end; i ++) {
            for(TreeNode left : generateTrees(start, i - 1)) {
                for(TreeNode right : generateTrees(i + 1, end)) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    unique.add(root);
                }
            }
        }
        
        return unique;
    }
}
