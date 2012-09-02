package guibin.zhang.datastructure.tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author guibin
 */
public class LowestCommonAncestor {
    
    
    /**
     * If the tree to be processed is a BST, we only need to process from the root, 
     * if the current node is greater than both of a and b, the lca must be in the left subtree,
     * if the current node is less than both of a and b, the lca must be in the right subtree.
     * The first node that is between a and b should be the lca.
     * 
     * @param root
     * @param a
     * @param b
     * @return 
     */
    public BinaryNodeJ<Integer> lcaOfBinarySearchTree(BinaryNodeJ<Integer> root, int a, int b) {
        
        BinaryNodeJ<Integer> current = root;
        BinaryNodeJ<Integer> result = null;
        if(current != null && current.data >= a && current.data >= b) {
            result = lcaOfBinarySearchTree(current.left, a, b);
        } else if(current != null &&  current.data < a && current.data < b) {
            result = lcaOfBinarySearchTree(current.right, a, b);
        } else {
            result = root;
        }
        return result;
    }
    
    /**
     * If the tree is not binary, just a common tree, but with parent pointer.
     * Then this problem can be converted to the first common node of the singly list, 
     * which has been solved by the class FirstCommonNode.java.
     * 
     */
    
    /**
     * 
     * To attack this problem we need to follow the below steps.
     * 1. Find the path of the first node using in-order traversal - Cost: O(n)
     * 2. Find the path of the second node using in-order traversal - Cost: O(n)
     * 3. Put the nodes of the first path in a set - Cost: O(logn)
     * 4. For each node in the second path check if it exists in the first path. 
     * The matching one would be the Least Common Ancestor - Cost: O(logn)
     * 
     * The total cost for this program would be - O(n) + O(n) + O(logn) + O(logn) = O(n). 
     * 
     * Please refer to http://www.technicalypto.com/2010/02/least-common-ancestor-without-using.html
     * 
     * @param root
     * @param a
     * @param b
     * @return 
     */
    public BinaryNodeJ<Integer> lcaOfBinaryTree(BinaryNodeJ<Integer> root, Integer a, Integer b) {

        TreeTracePath<Integer> ttp = new TreeTracePath<Integer>();
        
        //If either of the nodes is root, then there is no common parent
        if(root.data == a || root.data == b) {
            return null;
        }
        
        //Using the path tracer, find the path of two nodes in 2*O(n) time.
        Stack<BinaryNodeJ<Integer>> path1 = new Stack<BinaryNodeJ<Integer>>();
        Stack<BinaryNodeJ<Integer>> path2 = new Stack<BinaryNodeJ<Integer>>();
        //Find the path of the first node
        ttp.trace(root, a, path1);
        //Find the path of the second node
        ttp.trace(root, b, path2);
        
        //All that is left to do is to find the common parent now.
        Set<BinaryNodeJ<Integer>> firstPath = new HashSet<BinaryNodeJ<Integer>>();
        for(BinaryNodeJ<Integer> n: path1) {
            firstPath.add(n);
        }
        while(!path2.empty()) {
            BinaryNodeJ<Integer> current = path2.pop();
            if(firstPath.contains(current)) {
                return current;
            }
        }
        
        return null;
    }
    
    
    public static void main(String[] args) {
        LowestCommonAncestor lca = new LowestCommonAncestor();
        BinaryNodeJ<Integer> bst = BinaryNodeJ.getBinarySearchTree();
        BinaryNodeJ<Integer> res1 = lca.lcaOfBinarySearchTree(bst, 7, 10);
        BinaryNodeJ<Integer> res2 = lca.lcaOfBinarySearchTree(bst, 14, 16);
        BinaryNodeJ<Integer> res3 = lca.lcaOfBinarySearchTree(bst, 7, 14);
        System.out.println(res1.data + ", " + res2.data + ", " + res3.data);
        
        res1 = lca.lcaOfBinaryTree(bst, 7, 10);
        res2 = lca.lcaOfBinaryTree(bst, 14, 16);
        res3 = lca.lcaOfBinaryTree(bst, 7, 14);
        System.out.println(res1.data + ", " + res2.data + ", " + res3.data);
    }
}
