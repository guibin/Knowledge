package guibin.zhang.algorithm.lrucache;

/**
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ValueHolder<K, V> {
    
    V value;
    ListNode<K> locationInQueue;
    
    public ValueHolder(V val, ListNode<K> lq) {
        this.value = val;
        this.locationInQueue = lq;
    }
}
