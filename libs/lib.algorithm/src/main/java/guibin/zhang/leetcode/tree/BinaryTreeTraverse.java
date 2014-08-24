package guibin.zhang.leetcode.tree;

import java.util.Stack;

/**
 *
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class BinaryTreeTraverse {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    /**
     * Use one stack to save right, access the curr and left.
     * 
     * @param root 
     */
    public void preorder(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                //Access the curr firstly
                System.out.print(curr.val + ",");

                if (curr.right != null) {
                    stack.push(curr.right);//Save curr.right to be accessed later
                }
                curr = curr.left;//Then move to curr.left
            } else {
                curr = stack.pop();
            }
        }
        
        System.out.println();
    }
    
    /**
     * Use one stack to track all the curr and left, 
     * then pop to access, and move curr to right.
     * 
     * @param root 
     */
    public void inorder(TreeNode root) {
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            if (!stack.isEmpty()) {
                curr = stack.pop();
                //Access the curr
                System.out.print(curr.val + ",");
                curr = curr.right;
            }
        }
        
        System.out.println();
    }
    
    /**
     * Use two stacks, one for walk through the tree, 
     * the other is for saving the result in reversed order.
     * 
     * @param root 
     */
    public void postorder(TreeNode root) {
        
        if (root == null) return;
        
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> output = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            output.push(curr);//push the node into stack to be accessed in reversed order.
            
            if (curr.left != null) {
                stack.push(curr.left);
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
        }
        while (!output.isEmpty()) {
            System.out.print(output.pop().val + ",");
        }
        System.out.println();
    }
    
    
    /**
     * Design an algorithm to perform inorder traversal of a binary search tree 
     * using only a constant amount of extra space.
     * 
     * We can use Morris Traversal to perform inorder traversal of a binary search 
     * tree using only a constant amount of extra space.
     * 
     * http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
     * http://chesterli0130.wordpress.com/2012/10/16/inorder-tree-traversal-without-recursion-and-without-stack/
     * 
     * @param node 
     */
    public void inorderWOSpace(TreeNode node) {
        
        if (node == null) return;
        
        TreeNode pre;
        TreeNode curr = node;
        
        while (curr != null) {
            
            if (curr.left == null) {
                //2. Access curr and move to its successor
                System.out.print(curr.val + ",");
                curr = curr.right;
                
            } else {
                pre = curr.left;
                //!= null => Find the inorder predecessor of current.
                //!= pre.right => current is root of subtree, pre is predecessor.
                //Find the rightmost node of left subtree of current.
                while (pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }
                
                if (pre.right == null) {
                    //1. Link curr and its predessor
                    pre.right = curr;
                    curr = curr.left;
                } else {
                    //3. Disconnect the link between current and predessor
                    pre.right = null;
                    //Access curr
                    System.out.print(curr.val + ",");
                    curr = curr.right;
                } 
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
    /**
     * This is an invalid BST 
     *      10
     *     /  \
     *    5   15
     *   / \  /  \
     *  8   9 6    20
     * 
     * Inorder traverse: 8, 5, 9, 10, 6, 15, 20
     * 
     */
        BinaryTreeTraverse bst = new BinaryTreeTraverse();
        TreeNode a = bst.new TreeNode(10);
        TreeNode b = bst.new TreeNode(5);
        TreeNode c = bst.new TreeNode(15);
        a.left = b;
        a.right = c;
        
        TreeNode f = bst.new TreeNode(8);
        TreeNode g = bst.new TreeNode(9);
        b.left = f;
        b.right = g;
        
        TreeNode d = bst.new TreeNode(6);
        TreeNode e = bst.new TreeNode(20);
        c.left = d;
        c.right = e;
        
        System.out.println("inorder withou space traverse:");
        bst.inorderWOSpace(a);
        
        System.out.println("preorder traverse:");
        bst.preorder(a);
        
        System.out.println("inorder traverse:");
        bst.inorder(a);
        
        System.out.println("postorder traverse:");
        bst.postorder(a);
    }
}
