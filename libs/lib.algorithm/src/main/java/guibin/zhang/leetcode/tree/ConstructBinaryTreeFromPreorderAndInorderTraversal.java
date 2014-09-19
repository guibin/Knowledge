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
    
    public TreeNode buildTree_v2(int[] preorder, int[] inorder) {
        
        return buildTree(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
    }
    
    public TreeNode buildTree(int[] iOrder, int iStart, int iEnd, int[] pOrder, int pStart, int pEnd) {
        
        if (pStart <= pEnd && pStart >=0 && iStart <= iEnd && iStart >=0) {
            TreeNode root = new TreeNode(pOrder[pStart]);
            int idx = indexOf(iOrder, pOrder[pStart]);
            int len = idx - iStart;
            root.left = buildTree(iOrder, iStart, idx - 1, pOrder, pStart + 1, pStart + len);
            root.right = buildTree(iOrder, idx + 1, iEnd, pOrder, pStart + len + 1, pEnd);
            return root;
        }
        return null;
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
        
        cb.buildTree_v2(a, b);
        
        for(int i : cb.subarray(a, 0, 1)) {
            System.out.print(i + ",");
        }
        System.out.println("\n--");
        
        for(int i : cb.subarray(a, 1, 2)) {
            System.out.print(i + ",");
        }
    }
}
