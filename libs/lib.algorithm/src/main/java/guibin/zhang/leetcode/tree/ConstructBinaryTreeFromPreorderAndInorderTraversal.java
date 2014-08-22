package guibin.zhang.leetcode.tree;

/**
 * 
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * 
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * 
 * Run Status: Accepted!
 * Program Runtime: 992 milli secs
 * Progress: 202/202 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        
        if(preorder.length <= 0) {
            return null;
        }
        int rootIndex = indexOf(preorder[0], inorder);
        if(rootIndex < 0) {
            return null;
        }
        
        TreeNode node = new TreeNode(preorder[0]);

        int[] left = subarray(inorder, 0, rootIndex);
        int[] right = subarray(inorder, rootIndex + 1, inorder.length);
        
        int[] lPreorder = subarray(preorder, 1, left.length + 1);
        TreeNode leftTree = buildTree(lPreorder, left);

        int[] rPreorder = subarray(preorder, 1 + left.length, preorder.length);
        TreeNode rightTree = buildTree(rPreorder, right);
        
        node.left = leftTree;
        node.right = rightTree;

        return node;
    }
    
    public int indexOf(int num, int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == num) {
                return i;
            }
        }
        
        return -1;
    }
    
    public int[] subarray(int[] arr, int start, int end) {
        
        int length = end - start  < 0 ? 0 : end - start;
        int[] result = new int[length];
        for(int i = start, j = 0; i < end; i++, j++) {
            result[j] = arr[i];
        }
        
        return result;
    }
    
    
    public TreeNode buildTree_v2(int[] preorder, int[] inorder) {
        
        return buildTree(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }
    
    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        
        if (preStart <= preEnd && preStart >=0 && inStart <= inEnd && inStart >=0) {
            TreeNode root = new TreeNode(preorder[preStart]);
            int idx = indexOf(inorder, preorder[preStart]);
            int len = idx - inStart;
            root.left = buildTree(preorder, preStart + 1, preStart + len, inorder, inStart, idx - 1);
            root.right = buildTree(preorder, preStart + len + 1, preEnd, inorder, idx + 1, inEnd);
            return root;
        }
        return null;
    }
    
    public int indexOf(int[] inorder, int v) {
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == v) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        ConstructBinaryTreeFromPreorderAndInorderTraversal cb = new ConstructBinaryTreeFromPreorderAndInorderTraversal();
        
        int[] a = {1, 2, 3};
        int[] b = {2, 1, 3};
        
        cb.buildTree(a, b);
        
        for(int i : cb.subarray(a, 0, 1)) {
            System.out.print(i + ",");
        }
        System.out.println("\n--");
        
        for(int i : cb.subarray(a, 1, 2)) {
            System.out.print(i + ",");
        }
    }
}
