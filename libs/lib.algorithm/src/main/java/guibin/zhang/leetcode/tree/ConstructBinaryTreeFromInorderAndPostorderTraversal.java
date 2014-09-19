package guibin.zhang.leetcode.tree;

/**
 * 
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * 
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * 
 * Run Status: Accepted!
 * Program Runtime: 660 milli secs
 * Progress: 202/202 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        
        return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
    
    public TreeNode buildTree(int[] iOrder, int iStart, int iEnd, int[] pOrder, int pStart, int pEnd) {
        
        if(pStart <= pEnd && pStart >= 0 && iStart <= iEnd && iStart >=0) {
            
            TreeNode node = new TreeNode(pOrder[pEnd]);
            int iIdx = indexOf(iOrder, iStart, iEnd, pOrder[pEnd]);
            int len = iIdx - iStart;//leftlength is the most important
            
            node.left = buildTree(iOrder, iStart, iIdx - 1, pOrder, pStart, pStart + len - 1);
            node.right = buildTree(iOrder, iIdx + 1, iEnd, pOrder, pStart + len , pEnd - 1);
            
            return node;
        }
        return null;
    }
    
    private int indexOf(int[] inorder, int inStart, int inEnd, int value) {
        for(int i = inStart; i <= inEnd; i++) {
            if(inorder[i] == value) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        ConstructBinaryTreeFromInorderAndPostorderTraversal cb = new ConstructBinaryTreeFromInorderAndPostorderTraversal();
        int[] inorder = {2, 1};
        int[] postorder = {2, 1};
        TreeNode node = cb.buildTree(inorder, postorder);
        System.out.println(node.val);
    }
}
