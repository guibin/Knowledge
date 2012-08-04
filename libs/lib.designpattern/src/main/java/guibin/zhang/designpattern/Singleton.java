package guibin.zhang.designpattern;

/**
 * Using volatile please refer to "Fixing Double-Checked Locking using Volatile" 
 * of http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html
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
