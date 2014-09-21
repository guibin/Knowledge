package guibin.zhang.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * 
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 * 
 * http://n00tc0d3r.blogspot.com/2013/05/recover-binary-search-tree.html
 * http://yucoding.blogspot.com/2013/03/leetcode-question-75-recover-binary.html
 * http://discuss.leetcode.com/questions/272/recover-binary-search-tree
 * http://www.darrensunny.me/leetcode-binary-tree-inorder-traversal/
 * http://www.darrensunny.me/leetcode-recover-binary-search-tree/
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class RecoverBinarySearchTree {
    
    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    public void recoverTree_v3(TreeNode root) {
        
        TreeNode[] nodes = new TreeNode[2];
        
        inorder(root, nodes, null);
        //found the two misplaced nodes
        if(nodes[0] != null && nodes[1] != null) {
            int temp = nodes[0].val;
            nodes[0].val = nodes[1].val;
            nodes[1].val = temp;
        }
    }
    
    private TreeNode inorder(TreeNode root, TreeNode[] nodes, TreeNode pre) {
        
        if(root == null) return pre;
        
        //left subtree
        TreeNode last = inorder(root.left, nodes, pre);
        //visit
        if(last != null && root.val < last.val) {
            if(nodes[0] == null) {//Found the first node
                nodes[0] = last;//The first one should be the bigger one, 
                nodes[1] = root;//the second one should be the smaller one.
            }
            else {
                nodes[1] = root;//the second one should be the smaller one.
                return root;
            }
        }
        //right subtree
        return inorder(root.right, nodes, root);
    }
    
    
    public void recoverTree(TreeNode root) {
        
        List<TreeNode> list = inOrderTraverse(root);
        boolean first = false;
        boolean second = false;
        //The first one should be the bigger one, 
        //the second one should be the smaller one.
        int i = 0;
        for(; i < list.size() - 1; i++) {
            if(list.get(i).val > list.get(i + 1).val) {
                first = true;
                break;
            }
        }
        int j = i + 1;
        for(; j < list.size() - 1; j++) {
            if(list.get(j).val > list.get(j + 1).val) {
                second = true;
                break;
            }
        }
        //Note: here is the edge case
        //Case 1: 3 and 2 need to be reversed
        //                    i i+1
        //1, 2, 3, 4, 5 => 1, 3, 2, 4, 5
        if(first && !second) {
            j = i + 1;
            swap(list, i, j);
        } else if (first && second) {
            //Case 2: 4 and 2 need to be reversed
            //                    i i+1 
            //1, 2, 3, 4, 5 => 1, 4, 3, 2, 5
            //                       j  j+1
            j = j + 1;
            swap(list, i, j);
        }
    }
    
    private void swap(List<TreeNode> list, int i, int j) {
        int tmp = list.get(i).val;
        list.get(i).val = list.get(j).val;
        list.get(j).val = tmp;
    }
    
    public List<TreeNode> inOrderTraverse(TreeNode root) {
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        List<TreeNode> result = new ArrayList<>();
        
        while(!stack.isEmpty() || curr != null) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            
            if (!stack.isEmpty()) {
                curr = stack.pop();
                result.add(curr);
                curr = curr.right;
            }
        }
        return result;
    }
    
    public List<TreeNode> inOrderTraverse_v2(TreeNode root) {
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curr = root;
        List<TreeNode> result = new ArrayList<TreeNode>();
        
        while(curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
        
        while(!stack.isEmpty()) {
            curr = stack.pop();
            result.add(curr);
            
            curr = curr.right;
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        
        RecoverBinarySearchTree fbt = new RecoverBinarySearchTree();
        TreeNode a = fbt.new TreeNode(2);
        TreeNode b = fbt.new TreeNode(3);
        TreeNode c = fbt.new TreeNode(1);
        TreeNode d = fbt.new TreeNode(3);
        TreeNode f = fbt.new TreeNode(4);
        TreeNode g = fbt.new TreeNode(5);
        
        a.left = b;
        a.right = c;
        fbt.recoverTree(a);
    }
    
}
