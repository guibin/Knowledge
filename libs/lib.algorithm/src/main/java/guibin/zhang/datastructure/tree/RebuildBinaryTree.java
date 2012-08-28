package guibin.zhang.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * No6 of the book A. Given a result of pre-order and in-order traverse of a
 * binary tree, Please rebuild the original binary tree.
 *
 * For example: pre-order {1, 2, 4, 7, 3, 5, 6, 8} in-order {4, 7, 2, 1, 5, 3,
 * 8, 6}
 *
 * http://crackinterviewtoday.wordpress.com/2010/03/15/rebuild-a-binary-tree-from-inorder-and-preorder-traversals/
 *
 * @author guibin
 */
public class RebuildBinaryTree<T> {

    public BinaryNodeJ rebuildBinaryTree(List<T> preOrder, List<T> inOrder) {

        BinaryNodeJ node = null;
        
        if(preOrder != null && !preOrder.isEmpty() && inOrder != null && !inOrder.isEmpty()) {
            node = new BinaryNodeJ(preOrder.get(0));
        
            List<T> inOrderLeft = null;
            List<T> inOrderRight = null;
            int inOrderPos = inOrder.indexOf(preOrder.get(0));
            
            List<T> preOrderLeft = null;
            List<T> preOrderRight = null;
            
            inOrderLeft = inOrder.subList(0, inOrderPos);
            inOrderRight = inOrder.subList(inOrderPos + 1, inOrder.size());

            int preOrderPos = inOrderLeft.size();
            preOrderLeft = preOrder.subList(1, preOrderPos + 1);
            preOrderRight = preOrder.subList(preOrderPos + 1, preOrder.size());
            
            node.left = rebuildBinaryTree(preOrderLeft, inOrderLeft);
            node.right = rebuildBinaryTree(preOrderRight, inOrderRight);
        }

        return node;
    }
    
    public BinaryNodeJ rebuildBinaryTreeEfficient(List<T> preOrder, List<T> inOrder, 
            int preOrderIndex, int inOrderIndex, int length) {
        
        BinaryNodeJ node = null;
        
        if(length == 0) {
            return node;
        }
        
        T nodeData = preOrder.get(preOrderIndex);
        node = new BinaryNodeJ(nodeData);
        
        //Need to calculate relative index where the current noda data is present in inOrder traversal
        int rootIndex = 0;
        for(int count = inOrderIndex; count < inOrder.size(); count++) {
            T inOrderData = inOrder.get(count);
            if(inOrderData == nodeData) {
                break;
            }
            rootIndex ++;
        }
        
        node.left = rebuildBinaryTreeEfficient(preOrder, inOrder, 
                preOrderIndex + 1, inOrderIndex, rootIndex);
        node.right = rebuildBinaryTreeEfficient(preOrder, inOrder, 
                preOrderIndex + rootIndex +1, inOrderIndex + rootIndex + 1, length - (rootIndex + 1));
        
        return node;
    }

    public static void main(String[] args) {
        RebuildBinaryTree<String> rbt = new RebuildBinaryTree<String>();
        List<String> preOrder = new ArrayList<String>();
        List<String> inOrder = new ArrayList<String>();

        /**
         *      A
         *     / \
         *    B   C
         *   / \ / \
         *  D  E F  G
         * / \     / \
         *J  H    I   K
         *
         * Pre-order: A,B,D,J,H,E,C,F,G,I,K
         * In-order: J,D,H,B,E,A,F,C,I,G,K
         * Post-order: J,H,D,E,B,F,I,K,G,C,A 
         * Breadth-first: A,B,C,D,E,F,G,J,H,I,K
         */
        //Pre-order: A,B,D,H,E,C,F,G,I
        preOrder.add("A");
        preOrder.add("B");
        preOrder.add("D");
//        preOrder.add("J");
        preOrder.add("H");
        preOrder.add("E");
        preOrder.add("C");
        preOrder.add("F");
        preOrder.add("G");
        preOrder.add("I");
        preOrder.add("K");

        //In-order: D,H,B,E,A,F,C,I,G
//        inOrder.add("J");
        inOrder.add("D");
        inOrder.add("H");
        inOrder.add("B");
        inOrder.add("E");
        inOrder.add("A");
        inOrder.add("F");
        inOrder.add("C");
        inOrder.add("I");
        inOrder.add("G");
        inOrder.add("K");

        BinaryNodeJ root = rbt.rebuildBinaryTree(preOrder, inOrder);
        TraverseBinaryTree traverse = new TraverseBinaryTree();
        traverse.printTree(root);
        
        BinaryNodeJ root2 = rbt.rebuildBinaryTreeEfficient(preOrder, inOrder, 0, 0, 10);
        traverse.printTree(root2);
        
    }
}
