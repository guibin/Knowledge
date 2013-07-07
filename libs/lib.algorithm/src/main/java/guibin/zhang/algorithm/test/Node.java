package guibin.zhang.datastructure.list;

/**
 *
 * @author Guibin Zhang <guibin.beijing at gmail.com>
 */

public class SinglyListNode<T> {
    
    public T data; //The data field
    public SinglyListNode<T> next; //The pointer to the next node.

    public SinglyListNode(T data) {
        this(data, null);
    }

    public SinglyListNode(T data, SinglyListNode<T> node) {
        this.data = data;
        this.next = node;
    }
}
