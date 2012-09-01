package guibin.zhang.datastructure.tree;

/**
 *
 * @author guibin
 */
public class BinaryNodeJ<T> {
    
    public BinaryNodeJ left = null;
    public BinaryNodeJ right = null;
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
    
    public static BinaryNodeJ getDemoTree() {
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
        BinaryNodeJ H = new BinaryNodeJ("H");
        BinaryNodeJ I = new BinaryNodeJ("I");
        
        BinaryNodeJ D = new BinaryNodeJ(null, "D", H);
        BinaryNodeJ E = new BinaryNodeJ("E");
        
        BinaryNodeJ F = new BinaryNodeJ("F");
        BinaryNodeJ G = new BinaryNodeJ(I, "G", null);
        
        BinaryNodeJ B = new BinaryNodeJ(D, "B", E);
        BinaryNodeJ C = new BinaryNodeJ(F, "C", G);
        BinaryNodeJ A = new BinaryNodeJ(B, "A", C);
        
        return A;
    }
}
