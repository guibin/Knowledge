package guibin.zhang.leecode;

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
            
            TreeNode left = sortedArrayToBST(num, start, mid - 1);
            TreeNode right = sortedArrayToBST(num, mid + 1, end);
 
            TreeNode node = new TreeNode(num[mid]);
            node.left = left;
            node.right = right;
            
            return node;
        }
        
        return null;
    }
}
