package guibin.zhang.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Serialize and de-serialize a Binary Tree both recursively and iterately.
 *
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class SerializeBinaryTree {

    public class TreeNode {

        int val;
        TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public String serialize(TreeNode root) {
        
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    /**
     * preorder, recursive
     * @param x
     * @param sb 
     */
    private void serialize(TreeNode x, StringBuilder sb) {
        
        if (x == null) {
            sb.append("# ");
        } else {
            sb.append(x.val).append(" ");
            serialize(x.left, sb);
            serialize(x.right, sb);
        }
    }

    public TreeNode deserialize(String s) {

        if (s == null || s.length() == 0) return null;

        StringTokenizer st = new StringTokenizer(s, " ");
        return deserialize(st);
    }
 
    /**
     * preorder, recursive
     * @param st
     * @return 
     */
    private TreeNode deserialize(StringTokenizer st) {

        if (!st.hasMoreTokens()) return null;
        
        String val = st.nextToken();
        if (val.equals("#")) return null;
        
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserialize(st);
        root.right = deserialize(st);
        
        return root;
    }

    
    public String serialize_v2(TreeNode root) {
        
        StringBuilder sb = new StringBuilder();
        serialize_v2(root, sb);
        return sb.toString();
    }

    /**
     * level order, iteration
     * @param x
     * @param sb 
     */
    private void serialize_v2(TreeNode x, StringBuilder sb) {
        
        Queue<TreeNode> q = new LinkedList<>();
        q.add(x);
        while (!q.isEmpty()) {
            TreeNode curr = q.remove();
            if (curr != null) {
                sb.append(curr.val).append(" ");
                
                if (curr.left != null || curr.right != null) {
                    q.add(curr.left);
                    q.add(curr.right);
                } else {
                    q.add(curr.left);
                    q.add(curr.right);
                }
            }
            else {
                sb.append("#").append(" ");
            }
        }
    }
    
    public TreeNode deserialize_v2(String s) {

        if (s == null || s.length() == 0) return null;

        StringTokenizer st = new StringTokenizer(s, " ");
        return deserialize_v2(st);
    }
 
    /**
     * level order, iteration
     * @param st
     * @return 
     */
    private TreeNode deserialize_v2(StringTokenizer st) {

        if (!st.hasMoreTokens()) return null;
        
        Queue<TreeNode> q = new LinkedList<>();
        String val = st.nextToken();
        if (val.equals("#")) return null;
        
        TreeNode root = new TreeNode(Integer.parseInt(val));
        q.add(root);
        
        while (st.hasMoreTokens()) {
            TreeNode curr = q.remove();
            val = st.nextToken();
            TreeNode l = "#".equals(val) ? null : new TreeNode(Integer.parseInt(val));
            val = st.nextToken();
            TreeNode r = "#".equals(val) ? null : new TreeNode(Integer.parseInt(val));
            if (l != null) {
                curr.left = l;
                q.add(l);
            }
            if (r != null) {
                curr.right = r;
                q.add(r);
            }
        }
        return root;
    }
    
    public static void main(String[] args) {
        /**
          *    10
          *   /  \
          *  5   15
          *     /  \
          *    6    20
          * 
          * serialize: 10 5 # # 15 6 # # 20 # # 
          */
        SerializeBinaryTree bst = new SerializeBinaryTree();
        TreeNode a = bst.new TreeNode(10);
        TreeNode b = bst.new TreeNode(5);
        TreeNode c = bst.new TreeNode(15);
        a.left = b;
        a.right = c;
        TreeNode d = bst.new TreeNode(6);
        TreeNode e = bst.new TreeNode(20);
        c.left = d;
        c.right = e;
        
        System.out.println("serialize preorder");
        TreeNode t = bst.deserialize("10 5 # # 15 6 # # 20 # #");
        System.out.println(bst.serialize(t));
        
        System.out.println("serialize level order");
        TreeNode t2 = bst.deserialize_v2("10 5 15 # # 6 20");
        System.out.println(bst.serialize_v2(t2));
    }
}
