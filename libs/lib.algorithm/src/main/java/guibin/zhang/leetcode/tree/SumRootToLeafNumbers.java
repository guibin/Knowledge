package guibin.zhang.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * For example,
 *     1
 *    / \
 *   2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25.
 * 
 * http://blog.sina.com.cn/s/blog_b9285de20101iv6l.html
 * http://discuss.leetcode.com/questions/1189/sum-root-to-leaf-numbers
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SumRootToLeafNumbers {
    
    public static class TreeNode {
        public String s;
        public int val;
        public TreeNode left;
        public TreeNode right;
        
        public TreeNode(int x) {
            this.val = x;
            this.s = String.valueOf(x);
        }
    }
    
    public int sumNumbers_v1(TreeNode root) {

        return dfsSumNumbers(root, 0);
    }
    
    /**
     * 就是DFS，当是leaf的时候返回sum，sum通过dfs的输入往下传。思路很直观
     * @param root
     * @param sum
     * @return 
     */
    public int dfsSumNumbers(TreeNode root, int sum) {
        
        if(root == null) {
            return 0;
        }
        
        int s = sum * 10 + root.val;
        if(root.left == null && root.right == null) {
            return s;
        } else {
            return dfsSumNumbers(root.left, s) + dfsSumNumbers(root.right, s);
        }
    }
    
    /**
     * If we want to compute the path of each, for example 1,2,4; 1,2,5; 1,3,6.
     * We can re-use the same logic before, to accumulate the value of parent to child. 
     * When we meet the leaf, just print it.
     * 
     * @param root
     * @return 
     */
    public int sumNumbersNoRecursive(TreeNode root) {
        
        int sum = 0;
        if(root != null) {
            Stack<TreeNode> stack = new Stack<TreeNode>();
            stack.push(root);
            while(!stack.isEmpty()) {
                TreeNode node = stack.pop();
                
                //When it is leaf, add to sum.
                if(node.left == null && node.right == null) {
                    sum += node.val;
                }
                
                //When it is not leaf, child = parent * 10 + child
                if(node.left != null) {
                    node.left.val += node.val * 10;
                    stack.push(node.left);
                }
                if(node.right != null) {
                    node.right.val += node.val * 10;
                    stack.push(node.right);
                }
            }
        }
        
        return sum;
    }
    
    public int sumNumbersBreadthFirstTraversal(TreeNode root) {
        int sum = 0;
        
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        if(root != null) {
            que.offer(root);
            
            while(!que.isEmpty()) {
                TreeNode node = que.poll();
                //When it is leaf, add to sum.
                if(node.left == null && node.right == null) {
                    sum += node.val;
                }
                
                //When it is not leaf, child = parent * 10 + child
                if(node.left != null) {
                    node.left.val += node.val * 10;
                    que.offer(node.left);
                }
                if(node.right != null) {
                    node.right.val += node.val * 10;
                    que.offer(node.right);
                }
            }
        }
        
        return sum;
    }
    
    public String listEachPath(TreeNode root) {
        StringBuilder path = new StringBuilder();
        Queue<TreeNode> que = new LinkedList<TreeNode>();
        
        if(root != null) {
            que.offer(root);
            while(!que.isEmpty()) {
                TreeNode node = que.poll();
                
                if(node.left == null && node.right == null) {
                    path.append(node.s).append(";\n");
                }
                
                if(node.left != null) {
                    node.left.s = new StringBuilder(node.s).append(",").append(node.left.s).toString();
                    que.offer(node.left);
                }
                
                if(node.right != null) {
                    node.right.s = new StringBuilder(node.s).append(",").append(node.right.s).toString();
                    que.offer(node.right);
                }
            }
        }
        return path.toString();
    }
    
    public static void main(String[] args) {
        /**
         *          1
         *         / \
         *        2   3
         *       / \   \
         *      4   5   6
         */
        SumRootToLeafNumbers.TreeNode a = new SumRootToLeafNumbers.TreeNode(1);
        SumRootToLeafNumbers.TreeNode b = new SumRootToLeafNumbers.TreeNode(2);
        SumRootToLeafNumbers.TreeNode c = new SumRootToLeafNumbers.TreeNode(3);
        SumRootToLeafNumbers.TreeNode d = new SumRootToLeafNumbers.TreeNode(4);
        SumRootToLeafNumbers.TreeNode e = new SumRootToLeafNumbers.TreeNode(5);
        SumRootToLeafNumbers.TreeNode f = new SumRootToLeafNumbers.TreeNode(6);
        
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.right = f;
        
        SumRootToLeafNumbers stn = new SumRootToLeafNumbers();
        System.out.println(stn.sumNumbers_v1(a));
//        System.out.println(stn.sumNumbersNoRecursive(a));
//        System.out.println(stn.sumNumbersBreadthFirstTraversal(a));
        System.out.println(stn.listEachPath(a));
    }
}
