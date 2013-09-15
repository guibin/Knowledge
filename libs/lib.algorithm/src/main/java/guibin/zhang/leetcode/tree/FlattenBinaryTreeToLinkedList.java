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
    
    public void flatten_v2(TreeNode root) {
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root != null) {
            stack.push(root);
            while(!stack.isEmpty()) {
                TreeNode n = stack.pop();
                if(n.right != null){
                    stack.push(n.right);
                }
                if(n.left != null) {
                    stack.push(n.left);
                }
                
                //Access this node
                if(root != n) {//To avoid the cycle of the list.
                    root.right = n;
                    root.left = null;
                    root = root.right;
                }
            }
        }
    }
    
    public void printLinkedList(TreeNode root) {
        if(root != null) {
            System.out.print(root.val + ", ");
            printLinkedList(root.right);
        }
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
        fbt.printLinkedList(a);
        
    }
}
