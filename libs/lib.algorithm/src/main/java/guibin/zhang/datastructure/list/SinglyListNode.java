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
    
    public void print() {
        String delimiter = " -> ";
        StringBuilder sb = new StringBuilder();
        
        SinglyListNode<T> curr = this;
        while(curr != null && curr.data != null) {
            sb.append(curr.data.toString()).append(delimiter);
            curr = curr.next;
        }
        
        if(sb.length() > delimiter.length()) {
            sb.delete(sb.length() - delimiter.length(), sb.length());
        }
        System.out.println(sb.toString());
    }
}
