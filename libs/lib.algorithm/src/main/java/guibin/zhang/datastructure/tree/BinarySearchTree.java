package guibin.zhang.datastructure.tree;

/**
 *
 * Check whether given binary tree is a BST or not.
 * BST is binary search tree.
 * A binary search tree is a binary tree which has the following properties:
 * 1. The left subtree of a node contains only nodes with keys less than the node’s key
 * 2. The right subtree of a node contains only nodes with keys greater than the node’s key
 * 3. Both the left and right subtrees must also be binary search trees
 * 
 * The following three solutions can be used to check whether given binary tree is a binary search tree or not:
 * 1. For every node check whether its values is greater than maximum value of its left subtree 
 *    and is less than minimum value of its right subtree
 * 2. Traverse down the tree by keeping allowable minimum and maximum at every node
 * 3. Do InOrder Traversal and store the values in tempArray and check if they are sorted, it is a BST else not.
 * 
 * @author guibin
 */
public class BinarySearchTree {

    private BinaryNodeJ<Integer> root;
    
    public boolean isBinarySearchTreeSolution2(BinaryNodeJ<Integer> node) {
        return isBinarySearchTreeSolution2(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    /**
     * Solution 2 is more efficient.
     * @param node The root the of the tree to be checked.
     * @param minValue The allowable minimum
     * @param maxValue The allowable maximum
     * @return 
     */
    public boolean isBinarySearchTreeSolution2(BinaryNodeJ<Integer> node, int minValue, int maxValue) {
        
        //  True is its a empty tree
        if(node == null) {
            return true;
        }
        
        //  False if this node violates the BST property
        if(node.data < minValue || node.data > maxValue) {
            return false;
        }
        
        return (isBinarySearchTreeSolution2(node.left, minValue, node.data)) 
                && (isBinarySearchTreeSolution2(node.right, node.data, maxValue));
    }
    
    /**
     * Add the current data to the existing binary search tree
     * @param currentData 
     */
    public void add(int currentData) {
        if(root == null) {
            root = new BinaryNodeJ<Integer>(currentData);
            return;
        }
        add(currentData, root);
    }
    
    /**
     * Add current data to the binary search tree from the position.
     * @param currentData
     * @param position 
     */
    private void add(int currentData, BinaryNodeJ<Integer> position) {
        if(currentData < position.data) {
            if(position.left == null) {
                position.left = new BinaryNodeJ<Integer>(currentData);
                position.left.parent = position;
                return;
            }
            add(currentData, position.left);
        } else {
            if(position.right == null) {
                position.right = new BinaryNodeJ<Integer>(currentData);
                position.left.parent = position;
                return;
            }
            add(currentData, position.right);
        }
    }
    
    public static void main(String[] args) {
        
    }
    
}
