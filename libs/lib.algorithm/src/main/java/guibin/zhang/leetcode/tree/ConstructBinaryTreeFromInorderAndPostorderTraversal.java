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
    
    public TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int poStart, int poEnd) {
        
        if(poStart <= poEnd && poStart >= 0 && inStart >=0 && inStart <= inEnd) {
            
            TreeNode node = new TreeNode(postorder[poEnd]);
            int indexInorder = indexOf(inorder, inStart, inEnd, postorder[poEnd]);
            int leftLength = indexInorder - inStart;//leftlength is the most important
            
            node.left = buildTree(inorder, inStart, indexInorder - 1, postorder, poStart, poStart + leftLength - 1);
            node.right = buildTree(inorder, indexInorder + 1, inEnd, postorder, poStart + leftLength , poEnd - 1);
            
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
