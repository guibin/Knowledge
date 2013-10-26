package guibin.zhang.algorithm.lrucache;

/**
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class LinkedList<K> {
    
    ListNode<K> head = null;
    ListNode<K> tail = null;
    
    public ListNode<K> add(K val) {
        
        ListNode<K> node = new ListNode<K>(val);
        
        if (head == null) {
            head = tail = node;
        } else {
            assert(tail != null);
            //Append node to the tail of the list
            tail.nxt = node;
            node.prv = tail;
            //Move tail to the tail of the list
            tail = tail.nxt;
        }
        
        return tail;
    }
    
    /**
     * Remove the head and return the head's value
     * @return 
     */
    public K remove() {
        
        if (head == null) {
            return null;
        }
        
        K value = head.value;
        if (head.nxt == null) {
            head = null;
            tail = null;
        } else {
            head = head.nxt;
            head.prv = null;
        }
        
        return value;
    }
    
    public void remove(ListNode<K> node) {
        
        ListNode<K> prev = node.prv;
        ListNode<K> next = node.nxt;
        
        if (prev == null) {
            head = next;
        } else {
            prev.nxt = next;
        }
        
        if (next == null) {
            tail = prev;
        } else {
            next.prv = prev;
        }
    }
}
