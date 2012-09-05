package guibin.zhang.datastructure.tree;

/**
 *
 * http://crackinterviewtoday.wordpress.com/2010/03/11/diameter-of-a-binary-tree/
 * http://www.cs.duke.edu/courses/spring00/cps100/assign/trees/diameter.html
 * 
 * Diameter of a Tree is defined as the number of nodes on the longest path between two leaves in the tree.
 * 
 * Hence the diameter of a binary tree T will be the largest of the following quantities:
 * 1. the diameter of T’s left subtree
 * 2. the diameter of T’s right subtree
 * 3. the longest path between leaves that goes through the root of T (this can be computed from the heights of the subtrees of T)
 * 
 * Diameter = left height+ right height+1
 * 
 * @author guibin
 */
public class BinaryTreeDiameter {
    
    BinaryTreeDepth btd = new BinaryTreeDepth();
    
    public int diameterOf(BinaryNodeJ node) {
        if(node == null) {
            return 0;
        }
        
        int leftHeight = btd.depthOfBinaryTree2(node.left);
        int rightHeight = btd.depthOfBinaryTree2(node.right);
        
        int leftDiameter = diameterOf(node.left);
        int rightDiameter = diameterOf(node.right);
        
        return Math.max(leftHeight + rightHeight + 1, Math.max(leftDiameter, rightDiameter));
    }
    
}
