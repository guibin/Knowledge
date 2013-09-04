package guibin.zhang.leecode.tree;

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
    
    public TreeNode buildTree(int[] inorder, int ins, int ine, int[] postorder, int pos, int poe) {
        
        if(pos <= poe && pos >= 0 && ins >=0 && ins <= ine) {
            int root = postorder[poe];
            TreeNode node = new TreeNode(root);
            
            int indexInorder = indexOf(inorder, ins, ine, root);
            if(indexInorder < 0) {
                return null;
            }
            int leftLength = indexInorder - ins;//leftlength is the most important
            TreeNode left = buildTree(inorder, ins, indexInorder - 1, postorder, pos, pos + leftLength - 1);
            TreeNode right = buildTree(inorder, indexInorder + 1, ine, postorder, pos + leftLength , poe - 1);
            node.left = left;
            node.right = right;
            
            return node;
        }
        return null;
    }
    
    private int indexOf(int[] inorder, int ins, int ine, int value) {
        for(int i = ins; i <= ine; i++) {
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
