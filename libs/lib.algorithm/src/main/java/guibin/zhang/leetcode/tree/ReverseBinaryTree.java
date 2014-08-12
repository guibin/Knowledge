package guibin.zhang.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class ReverseBinaryTree {
    
   public class TreeNode {

        String val;
        TreeNode left;
        TreeNode right;
        int level;
        
        TreeNode(String x) {
            val = x;
        }
    }
   
   /**
    * Reverse the alternate level nodes of the binary tree. 
    * 
    * Add null as the delimiter of each line.
    * 
    * Use two queue and one stack to reverse the tree alternately.
    * One queue is used as the normal working queue.
    * The other queue is used to save as the non-flip node.
    * Stack is used to save the flip values.
    * 
    * Question link: http://www.careercup.com/question?id=5068704572243968
    * 
    *        a 
    *     /     \ 
    *    b       c 
    *   / \     / \ 
    *  d   e   f   g 
    * / \ / \ / \ / \ 
    * h i j k l m n o 
    * 
    * Modified tree: 
    *        a 
    *     /     \ 
    *    c       b 
    *   / \     / \ 
    *  d   e   f   g 
    * / \ / \ / \ / \ 
    * o n m l k j i h
    * 
    * @param root 
    */
    public void reverseByAlternatelyLevel(TreeNode root) {

        if (root == null) return;

        Queue<TreeNode> workingQ = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        Queue<TreeNode> queue = new LinkedList<>();
        
        boolean flip = false;
        TreeNode curr = root;
        workingQ.add(curr);
        workingQ.add(null);
        
        while (!workingQ.isEmpty()) {
            curr = workingQ.remove();
            if (curr == null) {
                if (flip) {
                    //reverse all the values in queue
                    while (!queue.isEmpty()) {
                        curr = queue.remove();
                        String r = stack.pop();
                        String l = stack.pop();
                        curr.left.val = r;
                        curr.right.val = l;
                    }
                }
                flip = !flip;
                if (workingQ.isEmpty()) {
                    break;
                }
                workingQ.add(null);
            } else {
                if (curr.left != null) {
                    workingQ.add(curr.left);
                }
                if (curr.right != null) {
                    workingQ.add(curr.right);
                }
                //Parent is in queue, children are in stack. One parent => two kids
                if (flip) {
                    stack.push(curr.val);
                } else {
                    queue.add(curr);
                }
            }
        }
    }
   
   /**
    * Original tree:
    *        a 
    *     /     \ 
    *    b       c 
    *   / \     / \ 
    *  d   e   f   g 
    * / \ / \ / \ / \ 
    * h i j k l m n o 
    * 
    * Modified tree: 
    *         a 
    *      /     \ 
    *     c       b 
    *    / \     / \ 
    *   g   f   e   d 
    *  / \ / \ / \ / \ 
    * o  n m l k j i  h
    * @param root 
    */
   public void reverseTotally(TreeNode root) {
       
       if (root != null) {
           reverseNode(root);
       }
       if (root.left != null) {
           reverseTotally(root.left);
       }
       if (root.right != null) {
           reverseTotally(root.right);
       }
   }
   
   private void reverseNode(TreeNode node) {
       TreeNode tmp = node.left;
       node.left = node.right;
       node.right = tmp;
   }
   
   /**
    * Print the tree as level order, use # as placeholder of null child.
    * 
    * Idea: Add null as the delimiter of each line.
    * 
    * @param node 
    */
   public void printTree(TreeNode node) {

        if (node == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        //Use null as the delimiter of each level
        queue.add(null);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.remove();
            if (curr == null) {
                System.out.println();
                //To avoid dead loop here
                if (queue.isEmpty()) break;
                //Use null as the delimiter of each level
                queue.add(null);
            } else {
                System.out.print(curr.val + ", ");
                if (curr.left != null || curr.right != null) {
                    //Use # as the placeholder
                    queue.add(curr.left == null ? new TreeNode("#") : curr.left);
                    queue.add(curr.right == null ? new TreeNode("#") : curr.right);
                }
            }
        }
    }
   
   /**
    * Print the tree as level order, use # as placeholder of null child.
    * 
    * Idea: use the attribute level to judge each level.
    * 
    * @param node 
    */
    public void printTree_v2(TreeNode node) {
        
        if (node == null) return;
        
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curr = node;
        curr.level = 1;
        queue.offer(curr);
        int currLevel = curr.level;
        
        while (!queue.isEmpty()) {
            curr = queue.poll();
            if (curr.level > currLevel) {
                currLevel = curr.level;
                System.out.println();
            }

            System.out.print(curr.val + ", ");
            if (curr.left != null || curr.right != null) {
                //Use # as the placeholder
                TreeNode l = curr.left == null ? new TreeNode("#") : curr.left;
                TreeNode r = curr.right == null ? new TreeNode("#") : curr.right;
                l.level = curr.level + 1;
                r.level = curr.level + 1;
                queue.add(l);
                queue.add(r);
            }
        }
        System.out.println();
    }
   
   public static void main(String[] args) {
       
       ReverseBinaryTree rb = new ReverseBinaryTree();
       TreeNode a = rb.new TreeNode("a");
       TreeNode b = rb.new TreeNode("b");
       TreeNode c = rb.new TreeNode("c");
       a.left = b;
       a.right = c;
       TreeNode d = rb.new TreeNode("d");
       TreeNode e = rb.new TreeNode("e");
       TreeNode f = rb.new TreeNode("f");
       TreeNode g = rb.new TreeNode("g");
       b.left = d;
       b.right = e;
       c.left = f;
       c.right = g;
       TreeNode h = rb.new TreeNode("h");
       TreeNode i = rb.new TreeNode("i");
       TreeNode j = rb.new TreeNode("j");
       TreeNode k = rb.new TreeNode("k");
       TreeNode l = rb.new TreeNode("l");
       TreeNode m = rb.new TreeNode("m");
       TreeNode n = rb.new TreeNode("n");
       TreeNode o = rb.new TreeNode("o");
       d.left = h;
       d.right = i;
       e.left = j;
       e.right = k;
       f.left = l;
       f.right = m;
       g.left = n;
       g.right = o;
       
       
       System.out.println("Before reverse totally:");
       rb.printTree(a);
       rb.reverseTotally(a);
       System.out.println("After reverse totally:");
       rb.printTree(a);
       System.out.println("After reverse by alternative level:");
       rb.reverseByAlternatelyLevel(a);
       rb.printTree(a);
       System.out.println("print tree v2:");
       rb.printTree_v2(a);
   }
}
