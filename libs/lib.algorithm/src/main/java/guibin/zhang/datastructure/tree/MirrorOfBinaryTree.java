package guibin.zhang.datastructure.tree;

/**
 * Given a binary tree, please output its mirror
 * 
 * @author guibin
 */
public class MirrorOfBinaryTree {
    
    public void mirror(BinaryNodeJ<String> node) {
        
        if(node == null) return;
        
        BinaryNodeJ<String> temp = node.left;
        node.left = node.right;
        node.right = temp;
        
        mirror(node.left);
        mirror(node.right);
    }
    
    public static void main(String[] args) {
        BinaryNodeJ<String> tree = BinaryNodeJ.getDemoTree();
        TraverseBinaryTree traverse = new TraverseBinaryTree();
        MirrorOfBinaryTree mbt = new MirrorOfBinaryTree();
        System.out.println("Original tree");
        traverse.printTree(tree);
        
        mbt.mirror(tree);
        System.out.println("Mirror of the original tree");
        traverse.printTree(tree);
    }
}
