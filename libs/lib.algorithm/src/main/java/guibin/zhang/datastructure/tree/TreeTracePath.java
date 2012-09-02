package guibin.zhang.datastructure.tree;

import java.util.Stack;

/**
 *
 * Given the tree BinaryNodeJ.getDemoTree, trace path E of the tree means we want to get the sequence: A, B, E
 * 
 * Please refer to http://www.technicalypto.com/2010/02/trace-path-of-binary-tree.html
 * 
 * @author guibin
 */
public class TreeTracePath<T> {
    
    /**
     * It looks like a preOrder traverse of the tree.
     * @param root The binary tree to be traced
     * @param target The target to be searched
     * @param mainStack The trace path will be pushed into this stack, include the target itself at the top of the stack.
     * @return 
     */
    public boolean trace(BinaryNodeJ<T> root, T target, Stack<BinaryNodeJ<T>> mainStack) {
        mainStack.push(root);
        if(target == root.data) {
            return true;
        }
        if(root.left != null) {
            if(trace(root.left, target, mainStack)) 
                return true;
        }
        if(root.right != null) {
            if(trace(root.right, target, mainStack))
                return true;
        }
        mainStack.pop();
        return false;
    }
    
    public static void printResult(Stack<BinaryNodeJ<Integer>> result){
        for(BinaryNodeJ<Integer> n : result) {
            System.out.print(n.data + ",");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        TreeTracePath<Integer> ttp = new TreeTracePath<Integer>();
        BinaryNodeJ<Integer> root = BinaryNodeJ.getBinarySearchTree();
        Stack<BinaryNodeJ<Integer>> result = new Stack<BinaryNodeJ<Integer>>();
        ttp.trace(root, 7, result);
        printResult(result);
        
        result.clear();
        ttp.trace(root, 16, result);
        printResult(result);
        
        result.clear();
        ttp.trace(root, 15, result);
        printResult(result);
        
        result.clear();
        ttp.trace(root, 12, result);
        printResult(result);
    }
}
