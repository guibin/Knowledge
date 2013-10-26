package guibin.zhang.algorithm.lrucache;

/**
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ListNode<K> {
    
    ListNode<K> prv;
    ListNode<K> nxt;
    
    K value;
    
    public ListNode(K val) {
        this.value = val;
        prv = null;
        nxt = null;
    }
}
