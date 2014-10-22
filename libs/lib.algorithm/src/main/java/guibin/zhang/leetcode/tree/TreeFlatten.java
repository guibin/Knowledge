package guibin.zhang.leetcode.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Flatten the tree into string, then inflatten it back to tree.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class TreeFlatten {
    
    public class TreeNode<T> {
        
        T val;
        List<TreeNode<T>> children;
        
        public TreeNode(T val) {
            this.val = val;
        }
        
        public TreeNode(T val, List<TreeNode<T>> children) {
            this.val = val;
            this.children = children;
        }
        
        @Override
        public boolean equals(Object obj) {
            return this.val.toString().equals(((TreeNode)obj).val.toString());
        }
        
        @Override
        public int hashCode() {
            return this.val.toString().hashCode();
        }
    }
    
    /**
     * 
     * @param root
     * @param sb 
     */
    public void flatten_v2(TreeNode root, StringBuilder sb) {
        
        if (root == null)  return;
        
        if (root.children != null) {
            for (int i = 0; i < root.children.size(); i++) {
                TreeNode n = (TreeNode)root.children.get(i);
                sb.append(root.val.toString()).append(" ").append(n.val.toString()).append("\n");
                flatten_v2(n, sb);
            }
        }
    }
    
    public TreeNode deflatten_v2(String str) throws IOException {
        
        BufferedReader br = new BufferedReader(new StringReader(str));
        Map<TreeNode, TreeNode> checker = new HashMap<>();
        String row = null;
        TreeNode root = null;
        
        while ((row = br.readLine()) != null) {
            String[] nodes = row.split(" ");
            TreeNode parent = new TreeNode(nodes[0]);
            TreeNode child = new TreeNode(nodes[1]);
            
            if (root == null) root = parent;
            
            if (!checker.containsKey(parent)) checker.put(parent, parent);
            if (!checker.containsKey(child)) checker.put(child, child);
            
            if (checker.get(parent).children == null) checker.get(parent).children = new ArrayList<>();
            checker.get(parent).children.add(checker.get(child));
        }
        
        return root;
    }
    
    /**
     * Flatten with position of children together
     * @param root
     * @param sb 
     */
    public void flatten_v3(TreeNode root, StringBuilder sb) {
        
        if (root == null)  return;
        
        if (root.children != null) {
            for (int i = 0; i < root.children.size(); i++) {
                TreeNode n = (TreeNode)root.children.get(i);
                sb.append(root.val.toString()).append(" ").append(n.val.toString()).append(":").append(i).append("\n");
                flatten_v3(n, sb);
            }
        }
    }
    
    public TreeNode deflatten_v3(String str) throws IOException {
        
        BufferedReader br = new BufferedReader(new StringReader(str));
        Map<TreeNode, TreeNode> checker = new HashMap<>();
        String row = null;
        TreeNode root = null;
        
        while ((row = br.readLine()) != null) {
            String[] nodes = row.split(" ");
            TreeNode parent = new TreeNode(nodes[0]);
            String[] childInfo = nodes[1].split(":");
            TreeNode child = new TreeNode(childInfo[0]);
            int order = Integer.parseInt(childInfo[1]);
            
            if (root == null) root = parent;
            
            if (!checker.containsKey(parent)) checker.put(parent, parent);
            if (!checker.containsKey(child)) checker.put(child, child);
            
            if (checker.get(parent).children == null) checker.get(parent).children = new ArrayList<>();
            if (order > checker.get(parent).children.size()) {
                checker.get(parent).children.add(checker.get(child));
            } else {
                checker.get(parent).children.add(order, checker.get(child));
            }
        }
        
        return root;
    }
    
    public String flatten(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        flatten(root, sb);
        return sb.toString();
    }
    
    /**
     * Flatten the tree into string representation, eg: A(B(EFG)C(H)D(I)).
     * @param root Root node
     * @param sb The result
     */
    public void flatten(TreeNode<String> root, StringBuilder sb) {
        
        if (root != null) {
            sb.append(root.val.toString());
            if (root.children != null) {
                sb.append("(");
                root.children.stream().forEach((n) -> {
                    flatten(n, sb);
                });
                sb.append(")");
            }
        }
    }
    
    /**
     * Suppose the val is only one character. A(B(EFG)C(H)D(I))
     * @param s
     * @return 
     */
    public TreeNode<Character> deflatten(String s) {
        
        if (s.length() < 1) return null;
        
        TreeNode<Character> root = new TreeNode<>(s.charAt(0));
        Stack<Character> stack = new Stack<>();
        int idx = 1;
        
        while (idx < s.length()) {
            if (s.charAt(idx) != ')') {
                stack.push(s.charAt(idx++));
            } else {
                idx++;
//                List<TreeNode<Character>> children = buildChilder(stack);
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
//                deflatten(s, idx, stack);
                
            }
        }
        return root;
    }
    
    public static void main(String[] args) throws IOException {
        TreeFlatten tr = new TreeFlatten();
        TreeNode<String> a = tr.new TreeNode<>("A");
        TreeNode<String> b = tr.new TreeNode<>("B");
        TreeNode<String> c = tr.new TreeNode<>("C");
        TreeNode<String> d = tr.new TreeNode<>("D");
        List<TreeNode<String>> childrenA = new ArrayList<>();
        childrenA.add(b);
        childrenA.add(c);
        childrenA.add(d);
        a.children = childrenA;
        
        TreeNode<String> e = tr.new TreeNode<>("E");
        TreeNode<String> f = tr.new TreeNode<>("F");
        TreeNode<String> g = tr.new TreeNode<>("G");
        List<TreeNode<String>> childrenB = new ArrayList<>();
        childrenB.add(e);
        childrenB.add(f);
        childrenB.add(g);
        b.children = childrenB;
        
        TreeNode<String> h = tr.new TreeNode<>("H");
        List<TreeNode<String>> childrenC = new ArrayList<>();
        childrenC.add(h);
        c.children = childrenC;
        
        TreeNode<String> i = tr.new TreeNode<>("I");
        List<TreeNode<String>> childrenD = new ArrayList<>();
        childrenD.add(i);
        d.children = childrenD;
        
        /**
         *         a
         *     /   |   \
         *    b     c  d
         *   /|\    |  |
         * e  f  g  h  i
         * 
         * expected output: A(B(EFG)C(H)D(I))
         */
        
        String str = tr.flatten(a);
        System.out.println(str);
        
        /**
         * child root
         * B A
         * E B
         * F B
         * G B
         * C A
         * H C
         * D A
         * I D
         */
        StringBuilder sb = new StringBuilder();
        tr.flatten_v2(a, sb);
        System.out.println("Serialize v2:");
        System.out.println(sb.toString());
        System.out.println("Deserialize");
        TreeNode n = tr.deflatten_v2(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        tr.flatten_v2(n, sb2);
        System.out.println(sb2.toString());
        
        System.out.println("Serialize v3:");
        StringBuilder sb3 = new StringBuilder();
        tr.flatten_v3(a, sb3);
        System.out.println(sb3.toString());
        
        System.out.println("Deserialize v3:");
        TreeNode d3 = tr.deflatten_v3(sb3.toString());
        StringBuilder sb4 = new StringBuilder();
        tr.flatten_v3(d3, sb4);
        System.out.println(sb4.toString());
        
        
//        tr.deflatten(str);
    }
}
