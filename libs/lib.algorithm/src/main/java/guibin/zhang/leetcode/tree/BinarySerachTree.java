package guibin.zhang.leetcode.tree;

/**
 * Check if a binary tree is a BST. 
 * Given a binary tree where each Node contains a key, 
 * determine whether it is a binary search tree. 
 * Use extra space proportional to the height of the tree.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class BinarySerachTree {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    /**
     * Instead of examining all nodes of both subtrees in each pass, 
     * we only need to examine two nodes in each pass.
     * 
     * http://leetcode.com/2010/09/determine-if-binary-tree-is-binary.html
     * 
     * @param node
     * @return 
     */
    private boolean isBST(TreeNode node, int low, int high) {
        
        if (node == null) return true;
        if (low < node.val && node.val < high) {
            return isBST(node.left, low, node.val) && isBST(node.right, node.val, high);
        } else {
            return false;
        }
    }
    
    public boolean isBST(TreeNode node) {
        return isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    /**
     * This is an invalid BST 
     *    10
     *   /  \
     *  5   15
     *     /  \
     *    6    20
     * 
     * This is a valid BST 
     *    10
     *   /  \
     *  5   15
     *     /  \
     *    11    20
     */   
    public static void main(String[] args) {
        BinarySerachTree bst = new BinarySerachTree();
        TreeNode a = bst.new TreeNode(10);
        TreeNode b = bst.new TreeNode(5);
        TreeNode c = bst.new TreeNode(15);
        a.left = b;
        a.right = c;
        TreeNode d = bst.new TreeNode(6);
        TreeNode e = bst.new TreeNode(20);
        c.left = d;
        c.right = e;
        
        System.out.println(bst.isBST(a));//False
        
        d.val = 11;
        System.out.println(bst.isBST(a));//True
        
        TreeNode A = bst.new TreeNode(2);
        TreeNode B = bst.new TreeNode(3);
        TreeNode C = bst.new TreeNode(4);
        A.left = B;
        A.right = C;
        System.out.println(bst.isBST(A));//False
        
        A.val = 3;
        B.val = 2;
        System.out.println(bst.isBST(A));//True
    }
}
