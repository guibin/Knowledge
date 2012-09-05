package guibin.zhang.datastructure.tree;

/**
 *
 * @author guibin
 */
public class BinaryNodeJ<T> {
    
    public BinaryNodeJ left = null;
    public BinaryNodeJ right = null;
    public BinaryNodeJ parent = null;
    public T data = null;

    public int depth = 0;
    
    public BinaryNodeJ(T data) {
        this.data = data;
    }
    
    public BinaryNodeJ(BinaryNodeJ left, T data, BinaryNodeJ right) {
        this.left = left;
        this.right = right;
        this.data = data;
    }
    
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }    
    
    public static BinaryNodeJ<String> getDemoTree() {
        /**
         *     A
         *    /  \
         *   B    C
         *  / \  / \
         * D  E F   G
         *  \      /
         *   H    I
         * 
         * Pre-order: A,B,D,H,E,C,F,G,I
         * In-order: D,H,B,E,A,F,C,I,G
         * Post-order: H,D,E,B,F,I,G,C,A
         * Breadth-first: A,B,C,D,E,F,G,H,I
         */
        BinaryNodeJ<String> H = new BinaryNodeJ<String>("H");
        BinaryNodeJ<String> I = new BinaryNodeJ<String>("I");
        
        BinaryNodeJ<String> D = new BinaryNodeJ<String>(null, "D", H);
        BinaryNodeJ<String> E = new BinaryNodeJ<String>("E");
        
        BinaryNodeJ<String> F = new BinaryNodeJ<String>("F");
        BinaryNodeJ<String> G = new BinaryNodeJ<String>(I, "G", null);
        
        BinaryNodeJ<String> B = new BinaryNodeJ<String>(D, "B", E);
        BinaryNodeJ<String> C = new BinaryNodeJ<String>(F, "C", G);
        BinaryNodeJ<String> A = new BinaryNodeJ<String>(B, "A", C);
        
        return A;
    }
    
    public static BinaryNodeJ<Integer> getBinarySearchTree() {
       /**
         *     12
         *    /  \
         *   8    15
         *  / \  / \
         * 5  10 14 17
         *  \      /
         *   7    16
         * 
         */
        BinaryNodeJ<Integer> H = new BinaryNodeJ<Integer>(7);
        BinaryNodeJ<Integer> I = new BinaryNodeJ<Integer>(16);
        
        BinaryNodeJ<Integer> D = new BinaryNodeJ<Integer>(null, 5, H);
        BinaryNodeJ<Integer> E = new BinaryNodeJ<Integer>(10);
        
        BinaryNodeJ<Integer> F = new BinaryNodeJ<Integer>(14);
        BinaryNodeJ<Integer> G = new BinaryNodeJ<Integer>(I, 17, null);
        
        BinaryNodeJ<Integer> B = new BinaryNodeJ<Integer>(D, 8, E);
        BinaryNodeJ<Integer> C = new BinaryNodeJ<Integer>(F, 15, G);
        BinaryNodeJ<Integer> A = new BinaryNodeJ<Integer>(B, 12, C);
        
        return A;
    }
}
