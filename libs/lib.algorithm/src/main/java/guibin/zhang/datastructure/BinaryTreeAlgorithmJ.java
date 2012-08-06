package guibin.zhang.datastructure;

/**
 *
 * @author guibin
 */
public class BinaryTreeAlgorithmJ {

    /**
     * Suppose there is only one node in the tree which is root, the depth is 1;
     * If the root node has left sub tree, but doesn't have the right sub tree,
     * the depth should be the depth of the left sub tree + 1; If the root node
     * has right sub tree, but doesn't have the left sub tree, the depth should
     * be the depth of the right sub tree + 1; If the root has both the left sub
     * tree and the right sub tree, the depth should be the max depth of both
     * sub trees + 1;
     *
     * @param root The root node of the binary tree.
     * @return The depth of the binary tree.
     */
    public int depthOfBinaryTree(BinaryNodeJ root) {
        if (root == null) {
            return 0;
        } else {
            if (root.left == null && root.right != null) {
                return depthOfBinaryTree(root.right) + 1;
            } else if (root.left != null && root.right == null) {
                return depthOfBinaryTree(root.left) + 1;
            } else {
                int leftDepth = depthOfBinaryTree(root.left);
                int rightDepth = depthOfBinaryTree(root.right);
                if (leftDepth >= rightDepth) {
                    return leftDepth + 1;
                } else {
                    return rightDepth + 1;
                }
            }
        }

    }
    /**
     * A more simple version.
     */
    public int depthOfBinaryTree2(BinaryNodeJ root) {
        if (root == null) {
            return 0;
        } else {
            int leftDepth = depthOfBinaryTree(root.left);
            int rightDepth = depthOfBinaryTree(root.right);
            if (leftDepth >= rightDepth) {
                return leftDepth + 1;
            } else {
                return rightDepth + 1;
            }
        }
    }

    public static void main(String[] args) {
        //   A
        //  / \
        // B   C
        ///   / \
        //D  E   F
        BinaryNodeJ D = new BinaryNodeJ("D");
        BinaryNodeJ E = new BinaryNodeJ("E");
        BinaryNodeJ F = new BinaryNodeJ("F");
        BinaryNodeJ B = new BinaryNodeJ(D, "B", null);
        BinaryNodeJ C = new BinaryNodeJ(E, "C", F);
        BinaryNodeJ A = new BinaryNodeJ(B, "A", C);
        
        BinaryTreeAlgorithmJ bta = new BinaryTreeAlgorithmJ();
        System.out.println("depthOfBinaryTree A is " + bta.depthOfBinaryTree(A));
        System.out.println("depthOfBinaryTree2 A is " + bta.depthOfBinaryTree2(A));
        System.out.println("depthOfBinaryTree C is " + bta.depthOfBinaryTree(C));
        System.out.println("depthOfBinaryTree2 C is " + bta.depthOfBinaryTree2(C));
        System.out.println("depthOfBinaryTree B is " + bta.depthOfBinaryTree(B));
        System.out.println("depthOfBinaryTree2 B is " + bta.depthOfBinaryTree2(B));
    }
}
