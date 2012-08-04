package guibin.zhang.datastructure;

/**
 *
 * @author Guibin Zhang <guibin.beijing at gmail.com>
 */
public class SinglyList<T> {
    protected SinglyListNode<T> head, tail;
    private SinglyListNode<T> current;

    public SinglyList() {
        head = tail = null;
    }

    /**
     * Prepare for the traverse
     * Actually create a the current node to point to the head of the singly list.
     */
    public void prepare() {
        current = new SinglyListNode<T>(null);
        current.next = head;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean hasNext() {
        if(isEmpty()) return false;
        else if(current.next == null) return false;
        else return true;
    }

    public SinglyListNode<T> next() {
        current = current.next;
        return current;
    }

    public SinglyListNode<T> getNextNode() {
        return current.next;
    }

    public void addToHead(T data) {
        SinglyListNode<T> node = new SinglyListNode<T>(data);

        if(head == null) {
            head = node;
            tail = node;
        } else {
            /**
             * new node  ->  o  ->  o  ... -> o
             * ^             ^               ^
             * |             |                |
             * node         head             tail
             */
            node.next = head;
            head = node;
        }
    }

    public void addToTail(T data) {
        SinglyListNode<T> node = new SinglyListNode<T>(data);
        if(head == null) {
            head = node;
            tail = node;
        } else {
            /**
             * o  ->  o ... -> o
             * ^      ^        ^
             * |      |        |
             * head  tail     node
             */
            tail.next = node;
            tail = node;
        }
    }

    public void printAllNode() {
        SinglyListNode<T> temp = null;
        for(prepare(); hasNext(); next()) {
            temp = getNextNode();
            System.out.print(temp.data + "");
        }
        System.out.println();
        
    }

    public SinglyList<T> reverse(SinglyList<T> target) {
        SinglyList<T> result = new SinglyList<T>();
        for(target.prepare(); target.hasNext(); target.next()) {
            result.addToHead(target.getNextNode().data);
        }
        
        return result;
    }

    public static void main(String[] args) {
        SinglyList<String> sList = new SinglyList<String>();
        sList.addToHead("A");
        sList.addToHead("B");
        sList.addToHead("D");
        sList.printAllNode();

        System.out.print("After reverse>>>: ");
        SinglyList<String> res = sList.reverse(sList);
        res.printAllNode();
    }

}
