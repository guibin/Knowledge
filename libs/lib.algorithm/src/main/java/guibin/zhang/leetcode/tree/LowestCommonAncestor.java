package guibin.zhang.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * http://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class LowestCommonAncestor {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    /**
     * Method 1 (By Storing root to n1 and root to n2 paths):
     * Following is simple O(n) algorithm to find LCA of n1 and n2.
     * 1) Find path from root to n1 and store it in a vector or array.
     * 2) Find path from root to n2 and store it in another vector or array.
     * 3) Traverse both paths till the values in arrays are same. Return the common element just before the mismatch.
     * 
     * @param root
     * @param path
     * @param k
     * @return 
     */
    public boolean findPath(TreeNode root, List<TreeNode> path, int k) {
        
        if (root == null) return false;
        path.add(root);
        if (root.val == k) return true;
        
        if (findPath(root.left, path, k) || findPath(root.right, path, k)) {
            return true;
        } 
        path.remove(path.size() - 1);
        return false;
    }
    
    public int findLCA(TreeNode root, int n1, int n2) {
        
        List<TreeNode> path1 = new ArrayList<>(), path2 = new ArrayList<>();
        
        if (!findPath(root, path1, n1) || !findPath(root, path2, n2)) {
            return -1;
        }
        int i = 0;
        while(i < path1.size() && i < path2.size() && path1.get(i).val == path2.get(i).val) {
            i++;
        }
        return path1.get(i - 1).val;
    }
    
    /**
     * Method 2 (Using Single Traversal)
     * The method 1 finds LCA in O(n) time, 
     * but requires three tree traversals plus extra spaces for path arrays. 
     * If we assume that the keys n1 and n2 are present in Binary Tree, 
     * we can find LCA using single traversal of Binary Tree and without extra storage for path arrays.
     * 
     * The idea is to traverse the tree starting from root. 
     * 1. If any of the given keys (n1 and n2) matches with root, 
     *    then root is LCA (assuming that both keys are present). 
     * 2. If root doesn’t match with any of the keys, we recur for left and right subtree. 
     *    The node which has one key present in its left subtree 
     *    and the other key present in right subtree is the LCA. 
     * 3. If both keys lie in left subtree, then left subtree has LCA also, 
     *    otherwise LCA lies in right subtree.
     * 
     */
    
    public TreeNode findLCA_v2 (TreeNode root, int n1, int n2) {
        
        if (root == null) return null;
        
         // If either n1 or n2 matches with root's key, report the presence 
         // by returning root (Note that if a key is ancestor of other, 
         // then the ancestor key becomes LCA.
        if (root.val == n1 || root.val == n2) {
            return root;
        }
        
        // Look for keys in left and right subtrees
        TreeNode leftLCA = findLCA_v2(root.left, n1, n2);
        TreeNode rightLCA = findLCA_v2(root.right, n1, n2);
        
        // If both of the above calls return Non-NULL, then one key
        // is present in once subtree and other is present in other,
        // So this node is the LCA
        if (leftLCA != null && rightLCA != null) return root;
        
        // Otherwise check if left subtree or right subtree is LCA
        return leftLCA != null ? leftLCA : rightLCA;
    }
    
    
    public static void main(String[] args) {
        /**
          *    10
          *   /  \
          *  5   15
          *     /  \
          *    6    20
          *   / \   / \
          *  7   8 19 21
          * serialize: 10 5 # # 15 6 # # 20 # # 
          */
        LowestCommonAncestor lca = new LowestCommonAncestor();
        TreeNode a = lca.new TreeNode(10);
        TreeNode b = lca.new TreeNode(5);
        TreeNode c = lca.new TreeNode(15);
        a.left = b;
        a.right = c;
        TreeNode d = lca.new TreeNode(6);
        TreeNode e = lca.new TreeNode(20);
        c.left = d;
        c.right = e;
        TreeNode f = lca.new TreeNode(7);
        TreeNode g = lca.new TreeNode(8);
        d.left = f;
        d.right = g;
        TreeNode h = lca.new TreeNode(19);
        TreeNode k = lca.new TreeNode(21);
        e.left = h;
        e.right = k;
        
        System.out.println(lca.findLCA_v2(a, 7, 15).val);//expected 15
        System.out.println(lca.findLCA_v2(a, 7, 20).val);//expected 15
        System.out.println(lca.findLCA_v2(a, 7, 21).val);//expected 15
        System.out.println(lca.findLCA_v2(a, 5, 8).val);//expected 10
    }
}
