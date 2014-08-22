package guibin.zhang.leetcode.tree;

/**
 * 
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ConvertSortedArrayToBinarySearchTree {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    public TreeNode sortedArrayToBST(int[] num) {
        
        return sortedArrayToBST(num, 0, num.length - 1);
    }
    
    public TreeNode sortedArrayToBST(int[] num, int start, int end) {

        while (start <= end) {
            int mid = (start + end) / 2;
            
            TreeNode node = new TreeNode(num[mid]);
            node.left = sortedArrayToBST(num, start, mid - 1);;
            node.right = sortedArrayToBST(num, mid + 1, end);;
            
            return node;
        }
        
        return null;
    }
}
