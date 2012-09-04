package guibin.zhang.datastructure.tree;

/**
 *
 * @author guibin
 */
public class BalancedBinaryTree {
    /**
     * This method will traverse the tree more than once, 
     * it is less efficient but straight forward.
     * @param node
     * @return 
     */
    public boolean isBalancedStraightWay(BinaryNodeJ node) {
        if(node == null) {
            return true;
        }
        
        BinaryTreeDepth btd = new BinaryTreeDepth();
        int left = btd.depthOfBinaryTree2(node.left);
        int right = btd.depthOfBinaryTree2(node.right);
        int diff = left - right;
        if(diff > 1 || diff < -1) {
            return false;
        }
        
        return isBalancedStraightWay(node.left) && isBalancedStraightWay(node.right);
    }
    
    
    /**
     * This method traverse the tree only once.
     * Before it traverse a node, we have traverse its left and right sub-trees, 
     * While traversing the left and right sub-tree, we can record their depth. 
     * In this way, we can judge one node whether it is balanced while traversing.
     * @param node
     * @param depth
     * @return 
     */
    public boolean isBalancedMoreEfficient(BinaryNodeJ node, int[] depth) {
        
        if(node == null) {
            depth[0] = 0;
            return true;
        }
        
        int[] left = {0};
        int[] right = {0};
        if(isBalancedMoreEfficient(node.left, left) && isBalancedMoreEfficient(node.right,right)) {
            int diff = left[0] - right[0];
            if(diff <= 1 && diff >= -1) {
                depth[0] = 1 + (left[0] > right[0] ? left[0] : right[0]);
                return true;
            }
        }
        
        return false;
    }
    
    
    /**
     * We can judge while traversing the tree.
     * @param node
     * @return 
     */
    public boolean isBalancedEfficientWay(BinaryNodeJ node, int depth) {
        
        int curDepth = 0, maxLeaf = 0, minLeaf = Integer.MAX_VALUE;
        
        if(node  == null) {
            return true;
        }
        
        while(node != null) {
            if(node.left == null || node.right == null) {
                maxLeaf = max(maxLeaf, curDepth);
                minLeaf = min(minLeaf, curDepth);
            }
            if(node.left != null) {
                curDepth += 1;
                node = node.left;
            } else {
                BinaryNodeJ last = node;
                while(node != null && (node.right == null || node.right == last)) {
                    curDepth -= 1;
                    last = node;
//                    node = node.parent;
                }
                if(node != null) {
                    curDepth += 1;
                    node = node.right;
                }
            }
        }
        
        return maxLeaf - minLeaf <= 1;
    }
    
    public int max(int maxLeaf, int curDepth) {
        return maxLeaf >= curDepth ? maxLeaf : curDepth;
    }
    
    public int min(int minLeaf, int curDepth) {
        return minLeaf < curDepth ? minLeaf : curDepth;
    }
    
    
    public static void main(String[] args) {
        /**
         *     A
         *    /  \
         *   B    C
         *  / \  / \
         * D  E F   G
         *  \      /
         *   H    I
         *  
         * 
         */
        BinaryNodeJ<String> H = new BinaryNodeJ<String>("H");
        BinaryNodeJ<String> I = new BinaryNodeJ<String>("I");
        
        BinaryNodeJ<String> D = new BinaryNodeJ<String>(null, "D", H);
//        BinaryNodeJ<String> E = new BinaryNodeJ<String>("E");
        
//        BinaryNodeJ<String> F = new BinaryNodeJ<String>("F");
        BinaryNodeJ<String> G = new BinaryNodeJ<String>(I, "G", null);
        
        BinaryNodeJ<String> B = new BinaryNodeJ<String>(D, "B", null);
        BinaryNodeJ<String> C = new BinaryNodeJ<String>(null, "C", G);
        BinaryNodeJ<String> A = new BinaryNodeJ<String>(B, "A", C);  
        
        BalancedBinaryTree bbt = new BalancedBinaryTree();
        int[] depth = {0};
        boolean flag = bbt.isBalancedMoreEfficient(A, depth);
        boolean flag2 = bbt.isBalancedStraightWay(A);
        System.out.println(flag + "," + flag2);
    }
}
