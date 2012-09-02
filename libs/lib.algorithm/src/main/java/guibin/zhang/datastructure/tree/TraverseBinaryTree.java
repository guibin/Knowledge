package guibin.zhang.datastructure.tree;

import java.util.*;

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
    
//--------------------------- print --------------------------------------------
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
    
    public void printList(List<BinaryNodeJ> list) {
        for(BinaryNodeJ node : list) {
            System.out.print(node.data + ",");
        }
        System.out.println();
    }
    
//--------------------------- Pre-Order ----------------------------------------
    public List<BinaryNodeJ> preOrderTraverseRecursively(BinaryNodeJ root) {
        List<BinaryNodeJ> result = new ArrayList<BinaryNodeJ>();
        preOrderRecursively(result, root);

        return result;
    }
    
    private void preOrderRecursively(List<BinaryNodeJ> result, BinaryNodeJ root) {
        
        if(root != null) {//Access the node
            result.add(root);
            preOrderRecursively(result, root.left);
            preOrderRecursively(result, root.right);
        }
    }
    
    
    public List<BinaryNodeJ> preOrderTraverse(BinaryNodeJ root) {
        List<BinaryNodeJ> result = new ArrayList<BinaryNodeJ>();
        Stack<BinaryNodeJ> stack = new Stack<BinaryNodeJ>();
        
        BinaryNodeJ currentNode = root;
        while(currentNode != null || !stack.empty()) {
            if(currentNode != null) {
                result.add(currentNode);//Access the node
                
                if(currentNode.right != null) {
                    stack.push(currentNode.right);
                }
                currentNode = currentNode.left;
            } else {
                currentNode = stack.pop();
            }
        }
        
        return result;
    }
    
//--------------------------- In-Order -----------------------------------------
    public List<BinaryNodeJ> inOrderTraverseRecursively(BinaryNodeJ root) {
        List<BinaryNodeJ> result = new ArrayList<BinaryNodeJ>();
        inOrderRecursively(result, root);

        return result;
    }
    
    private void inOrderRecursively(List<BinaryNodeJ> result, BinaryNodeJ root) {
        
        if(root != null) {
            inOrderRecursively(result, root.left);
            result.add(root); //Access the node
            inOrderRecursively(result, root.right);
        }
    }
    
    public List<BinaryNodeJ> inOrderTraverse(BinaryNodeJ root) {
        List<BinaryNodeJ> result = new ArrayList<BinaryNodeJ>();
        Stack<BinaryNodeJ> stack = new Stack<BinaryNodeJ>();
        
        BinaryNodeJ currentNode = root;
        
        while (currentNode != null || !stack.isEmpty()) {
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            if (!stack.isEmpty()) {
                currentNode = stack.pop();
                result.add(currentNode);//Access the node
                currentNode = currentNode.right;
            }
        }
        
        
        return result;
    }
    
//--------------------------- Post-Order ----------------------------------------
    public List<BinaryNodeJ> postOrderTraverseRecursively(BinaryNodeJ root) {
        List<BinaryNodeJ> result = new ArrayList<BinaryNodeJ>();
        postOrderRecursively(result, root);

        return result;
    }
    
    private void postOrderRecursively(List<BinaryNodeJ> result, BinaryNodeJ root) {
        
        if(root != null) {//Access the node
            postOrderRecursively(result, root.left);
            postOrderRecursively(result, root.right);
            result.add(root);
        }
    }
    
    public List<BinaryNodeJ> postOrderTraverse(BinaryNodeJ root) {
        List<BinaryNodeJ> result = new ArrayList<BinaryNodeJ>();
        Stack<BinaryNodeJ> stack = new Stack<BinaryNodeJ>();
        
        BinaryNodeJ current = root;
        while(current != null || !stack.isEmpty()) {
            while(current != null) {
                stack.push(current);
                current = current.left;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        BinaryNodeJ root = BinaryNodeJ.getDemoTree();
        TraverseBinaryTree tbt = new TraverseBinaryTree();
        tbt.printTree(root);
        
        List<BinaryNodeJ> result01 = tbt.preOrderTraverseRecursively(root);
        System.out.print("Pre-order traverse recursively: ");
        tbt.printList(result01);
        
        List<BinaryNodeJ> result02 = tbt.preOrderTraverse(root);
        System.out.print("Pre-order traverse: ");
        tbt.printList(result02);
        
        List<BinaryNodeJ> result03 = tbt.inOrderTraverseRecursively(root);
        System.out.print("In-order traverse recursively: ");
        tbt.printList(result03);
        
        List<BinaryNodeJ> result04 = tbt.inOrderTraverse(root);
        System.out.print("In-order traverse: ");
        tbt.printList(result04);
        
        List<BinaryNodeJ> result05 = tbt.postOrderTraverseRecursively(root);
        System.out.print("Post-order traverse recursively: ");
        tbt.printList(result05);
    }
}
