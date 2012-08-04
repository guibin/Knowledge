package guibin.zhang.designpattern;

/**
 * Double-Checked Locking using Volatile
 * JDK5 and later extends the semantics for volatile so that 
 * the system will not allow a write of a volatile to be reordered with respect to any previous read or write, 
 * and a read of a volatile cannot be reordered with respect to any following read or write. 
 * 
 * Reading and writing references to immutable objects are atomic.
 * Using final to declare a variable immutable.
 * 
 * Reading and writing references to mutable objects are not atomic.
 * 
 * Locking in Java also forms these "happens-before" communication points. 
 * An unlock is the sender side, and a lock on the same variable is the receiver side. 
 * The reason that doesn't work for (non-volatile) double-checked locking is that 
 * only the writing thread ever performs the locking. 
 * The whole point of the idiom is that the reader side doesn't do the locking. 
 * Without the explicit communication in the form of the volatile variable, 
 * the reading thread will never see the update performed by the writer thread.
 * Refer to http://jeremymanson.blogspot.com/2008/11/what-volatile-means-in-java.html
 * 
 * 
 * @author guibin
 */
public class DoubleCheckedLocking {

    private volatile Helper h = null;

    public Helper getHelper() {
        if (h == null) {
            synchronized (this) {
                if (h == null) {
                    h = new Helper();
                }
            }
        }
        return h;
    }
}
