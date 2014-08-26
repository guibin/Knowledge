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
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     *
     * Assume a BST is defined as follows:
     *
     * The left subtree of a node contains only nodes with keys less than the
     * node's key. The right subtree of a node contains only nodes with keys
     * greater than the node's key. Both the left and right subtrees must also
     * be binary search trees.
     *
     * http://www.ardendertat.com/2011/10/10/programming-interview-questions-7-binary-search-tree-check/
     *
     * 递归地判断当前节点是否大于**左**孩子，并且是否大于其左孩子的每一个**右**子孙；
     * 同理，判断当前节点是否小于**右**孩子，并且是否小于其右孩子的每一个**左**子孙
     *
     * @param root
     * @return
     */
    public boolean isBST_v2(TreeNode root) {

//        return isValidBST(root, new TreeNode(Integer.MIN_VALUE));
        return isBST_v2(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    public boolean isBST_v2(TreeNode root, int max, int min) {

        if (root == null) {
            return true;
        }

        if (!isBST_v2(root.left, root.val, min)) {
            return false;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        if (!isBST_v2(root.right, max, root.val)) {
            return false;
        }
        return true;
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
