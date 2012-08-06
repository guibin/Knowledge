package guibin.zhang.datastructure;

/**
 *
 * @author guibin
 */
public class BinaryNodeJ {
    
    public BinaryNodeJ left = null;
    public BinaryNodeJ right = null;
    public Object data = null;
    
    public BinaryNodeJ(Object data) {
        this.data = data;
    }
    
    public BinaryNodeJ(BinaryNodeJ left, Object data, BinaryNodeJ right) {
        this.left = left;
        this.right = right;
        this.data = data;
    }
}
