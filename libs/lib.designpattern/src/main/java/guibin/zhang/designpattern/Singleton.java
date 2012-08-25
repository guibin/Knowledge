package guibin.zhang.designpattern;

/**
 * Using volatile please refer to "Fixing Double-Checked Locking using Volatile" 
 * of http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
 * 
 * system will not allow a write of a volatile to be reordered with respect to any previous read or write, 
 * and a read of a volatile cannot be reordered with respect to any following read or write. 
 * 
 * @author guibin
 */
public class Singleton {
    private Singleton() {}
    
    private static volatile Singleton instance = null;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
