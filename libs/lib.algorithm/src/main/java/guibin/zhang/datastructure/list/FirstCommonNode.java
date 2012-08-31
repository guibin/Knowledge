package guibin.zhang.datastructure.list;

/**
 * Find the first common node in the list with head pHead1 and the list with
 * head pHead2 Input: pHead1 - the head of the first list pHead2 - the head of
 * the second list
 *
 * Return: the first common node in two list. If there is no common nodes,
 * return NULL
 *
 * @author guibin
 */
public class FirstCommonNode {

    
    public SinglyListNode find(SinglyListNode<Integer> head1, SinglyListNode<Integer> head2) {
        
        int length1 = lengthOf(head1);
        int length2 = lengthOf(head2);
        int diff = length1 - length2;
        SinglyListNode<Integer> longListHead = head1;
        SinglyListNode<Integer> shortListHead = head2;
        
        if(length1 < length2) {
            diff = length2 - length1;
            longListHead = head2;
            shortListHead = head1;
        }
        
        // Move on the longer list
        for(int i=0; i<diff; i++) {
            longListHead = longListHead.next;
        }
        
        // Move on both lists
        while(longListHead != null && shortListHead != null && longListHead.data != shortListHead.data) {
            longListHead = longListHead.next;
            shortListHead = shortListHead.next;
        }
        
        // Get the first common node in two lists
        SinglyListNode<Integer> commonNode = (longListHead == shortListHead) ? longListHead : null;
        
        return commonNode;
    }
    
    private int lengthOf(SinglyListNode node) {

        int counter = 0;
        SinglyListNode current = node;
        while (current != null) {
            current = current.next;
            counter++;
        }
        return counter;
    }
    
    public static void main(String[] args) {
        
        //1 -> 2 -> 3 -> 8 -> 9
        SinglyListNode<Integer> node9 = new SinglyListNode<Integer>(9);
        SinglyListNode<Integer> node8 = new SinglyListNode<Integer>(8, node9);
        SinglyListNode<Integer> node4 = new SinglyListNode<Integer>(4, node8);
        SinglyListNode<Integer> node3 = new SinglyListNode<Integer>(3, node4);
        SinglyListNode<Integer> node2 = new SinglyListNode<Integer>(2, node3);
        SinglyListNode<Integer> node1 = new SinglyListNode<Integer>(1, node2);
        
        //6 -> 7 -> 8 -> 9
        SinglyListNode<Integer> node7 = new SinglyListNode<Integer>(7, node8);
        SinglyListNode<Integer> node6 = new SinglyListNode<Integer>(6, node7);
        
        FirstCommonNode fcn = new FirstCommonNode();
        SinglyListNode<Integer> result = fcn.find(node1, node6);
        System.out.println("The first common node is " + result.data);
    }
}
