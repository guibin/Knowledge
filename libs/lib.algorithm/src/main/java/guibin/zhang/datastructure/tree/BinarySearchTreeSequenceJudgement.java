package guibin.zhang.datastructure.tree;

/**
 * Give an array of integer, judge whether this array is the result of a post/pre order 
 * traverse of the binary search tree.
 * 
 * @author guibin
 */
public class BinarySearchTreeSequenceJudgement {
  
        /**
         *     12
         *    /  \
         *   8    15
         *  / \  / \
         * 5  10 14 17
         *  \      /
         *   7    16
         * 
         * Post order traverse should be 7, 5, 10, 8, 14, 16, 17, 15, 12
         * According to the feature of the binary search tree and post traverse,
         * the last item is the root, all the items less than root should be the left sub-tree,
         * all the items great than the root should be the right sub-tree
         * We can verify the array based on this logic.
         * 
         */
    public boolean verifyPostOrderBinarySearchTree(int[] sequence, int start, int end) {
        
        if(sequence == null || (start > end) || (end < 1)) {
            return false;
        }
        
        //The node in left sub tree is less than root
        int root = sequence[end - 1];
        int i = start;
        for(; i < end - 1; i++) {
            if(sequence[i] > root) {
                break;
            }
        }
        
        //The node in right sub tree is greater than root
        int j = i;
        for(; j < end - 1; j++) {
            if(sequence[j] < root) {
                return false;
            }
        }
        
        boolean left = true;
        if(start < i)
            left = verifyPostOrderBinarySearchTree(sequence, start, i);
        boolean right = true;
        if(i <= end - 1 && end > 1)
            right = verifyPostOrderBinarySearchTree(sequence, i, end - 1);
        
        return left && right;
    }
    
    
    /**
     * This method works.
     * @param sequence
     * @return 
     */
    public boolean verifyPostOrderBinarySearchTree(int[] sequence) {
        if(sequence == null)
            return false;
        
        //The node in left sub tree is less than root
        int root = sequence[sequence.length - 1];
        int i = 0;
        for(; i < sequence.length; i++) {
            if(sequence[i] > root) {
                break;
            }
        }
        
        //The node in right sub tree is greater than root
        int j = i;
        for(; j < sequence.length; j++) {
            if(sequence[j] < root) {
                return false;
            }
        }
        
        boolean left = true;
        boolean right = true;
        if(i > 0 && i < sequence.length) {
            left = verifyPostOrderBinarySearchTree(subArrayLeft(sequence, i));
            right = verifyPostOrderBinarySearchTree(subArrayRight(sequence, i));
        }
        
        return left && right;
    }
    
    /**
     * 
     * @param sequence
     * @param mid The No.mid position, exclusive
     * @return 
     */
    private int[] subArrayLeft(int[] sequence, int mid) {
        int[] result = new int[mid];
        for(int i = 0; i < mid; i++) {
            result[i] = sequence[i];
        }
        return result;
    }
    
    /**
     * 
     * @param sequence
     * @param mid The No.mid position, inclusive
     * @return 
     */
    private int[] subArrayRight(int[] sequence, int mid) {
        int[] result = new int[sequence.length - mid];
        int j = 0;
        for(int i = mid; i < sequence.length; i++, j++){
            result[j] = sequence[i];
        }
        return result;
    }
    
    public static void main(String[] args) {
        BinarySearchTreeSequenceJudgement bstsj = new BinarySearchTreeSequenceJudgement();
        int[] sequence = {7, 5, 10, 8, 14, 16, 17, 15, 12};
//        boolean flag = bstsj.verifyPostOrderBinarySearchTree(sequence, 0, 9);
        boolean flag = bstsj.verifyPostOrderBinarySearchTree(sequence);
        System.out.println(flag);
        
        int[] sequence2 = {1, 2, 3, 4, 5, 6, 7, 18, 9, 10};
//        boolean flag2 = bstsj.verifyPostOrderBinarySearchTree(sequence2, 0, 9);
        boolean flag2 = bstsj.verifyPostOrderBinarySearchTree(sequence2);
        System.out.println(flag2);
    }
}
