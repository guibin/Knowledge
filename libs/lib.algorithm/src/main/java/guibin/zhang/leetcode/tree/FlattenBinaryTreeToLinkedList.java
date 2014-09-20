package guibin.zhang.leetcode.tree;

import java.util.Stack;

/**
 * 
 * Given a binary tree, flatten it to a linked list in-place.
 * 
 * For example,
 * Given
 * 
 *          1
 *         / \
 *        2   5
 *       / \   \
 *      3   4   6
 * The flattened tree should look like:
 *    1
 *     \
 *      2
 *       \
 *        3
 *         \
 *          4
 *           \
 *            5
 *             \
 *              6
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 * 
 * https://github.com/rffffffff007/leetcode/blob/master/Flatten%20Binary%20Tree%20to%20Linked%20List.java
 * https://github.com/mengli/leetcode/blob/master/Flatten%20Binary%20Tree%20to%20Linked%20List.java
 * 
 */
public class FlattenBinaryTreeToLinkedList {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    /**
     * Preorder traverse + linked list.
     * @param root 
     */
    public void flatten_v2(TreeNode root) {
        
        if (root == null) return;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode prev = root;
                
        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();

            //Access curr preorder
            if(prev != curr) {//To avoid the cycle of the list.
                prev.right = curr;//Connect prev to curr
                prev.left = null;//Disconnect left of prev
                prev = curr;//Move prev to curr
            }
            
            if(curr.right != null) stack.push(curr.right);
            if(curr.left != null) stack.push(curr.left);
        }
    }
    
    /**
     * Use preorder traverse. Use prev to save the previous access, to link to curr as a linkedlist.
     * 
     * @param root 
     */
    public void flatten_v3(TreeNode root) {
        
        TreeNode curr = root;
        TreeNode prev = root;
        Stack<TreeNode> s = new Stack<>();
        
        while (curr != null || !s.isEmpty()) {
            if (curr != null) {
                
                //Access curr preorder
                if (prev != curr) {
                    prev.left = null;//Cut off left
                    prev.right = curr;//Connect to curr on right
                    prev = curr;//Move prev to curr
                }
                
                if (curr.right != null) s.push(curr.right);
                curr = curr.left;
            } else {
                curr = s.pop();
            }
        }
    }
    
    
    /**
     * Memory Limit Exceeded
     * @param root 
     */
    public void flatten(TreeNode root) {
        
        if(root == null) {
            return;
        }
        
        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root;
        
        do {
            while(curr.left != null || curr.right != null) {
                if(curr.right != null) {
                    stack.push(curr.right);
                }
                if(curr.left != null) {
                    curr.right = curr.left;
                    curr = curr.left;
                }
            }
            
            TreeNode last = curr;
            if(!stack.isEmpty()) {
                curr = stack.pop();
            }
            if(last != curr) {//To avoid the cycle of the list.
                last.right = curr;
                last.left = null;
            }
        }
        while(!stack.isEmpty());
    }
    
    public void printLinkedList(TreeNode root) {
        if(root != null) {
            System.out.print(root.val + ", ");
            printLinkedList(root.right);
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        
        FlattenBinaryTreeToLinkedList fbt = new FlattenBinaryTreeToLinkedList();
        TreeNode a = fbt.new TreeNode(0);
        TreeNode b = fbt.new TreeNode(1);
        TreeNode c = fbt.new TreeNode(2);
        TreeNode d = fbt.new TreeNode(3);
        TreeNode f = fbt.new TreeNode(4);
        TreeNode g = fbt.new TreeNode(5);
        a.left = b;
        a.right = f;
        b.left = c;
        b.right = d;
        f.right = g;
        
        fbt.flatten_v2(a);
//        fbt.flatten_v3(a);
        fbt.printLinkedList(a);
        
    }
}
