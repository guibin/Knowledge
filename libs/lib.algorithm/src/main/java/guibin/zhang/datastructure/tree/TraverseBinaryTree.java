package guibin.zhang.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author guibin
 */
public class TraverseBinaryTree {

    public List<BinaryNodeJ> breadthTraverse(BinaryNodeJ root) {
        Queue<BinaryNodeJ> queue = new LinkedList<BinaryNodeJ>();
        List<BinaryNodeJ> result = new ArrayList<BinaryNodeJ>();
        BinaryNodeJ current = null;
        root.setDepth(1);

        if (root != null) {
            queue.offer(root);
            while (!queue.isEmpty()) {
                current = queue.poll();
                //Access the node
                result.add(current);

                if (current.left != null) {
                    current.left.setDepth(current.getDepth() + 1);
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    current.right.setDepth(current.getDepth() + 1);
                    queue.offer(current.right);
                }
            }
        }

        return result;
    }

    public void printTree(BinaryNodeJ root) {
        List<BinaryNodeJ> result = breadthTraverse(root);
        
        StringBuilder sb = new StringBuilder();
        
        int currentDepth = 1;
        for (BinaryNodeJ b : result) {
            if (b.getDepth() == currentDepth) {
                sb.append(b.data.toString());
            } else {
                sb.append("\n");
                sb.append(b.data.toString());
                currentDepth++;
            }
        }
        System.out.println("-------Tree---------");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        BinaryNodeJ root = BinaryNodeJ.getDemoTree();
        TraverseBinaryTree tbt = new TraverseBinaryTree();
        tbt.printTree(root);
    }
}
