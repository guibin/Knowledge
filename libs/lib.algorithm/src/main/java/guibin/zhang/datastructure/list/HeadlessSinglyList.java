package guibin.zhang.datastructure.list;

/**
 * Given a headless singly list, a pointer points to a node in the list, 
 * please remove this node from this list.
 * 
 * @author Guibin Zhang <guibin.beijing at gmail.com>
 */
public class HeadlessSinglyList<T> {
    
    /**
     * Provided pointer "current" point to B, how to delete B from this list?
     * 
     * ..... A  ->  B  ->  C  ->  D  -> ...
     *              ^      ^
     *              |      |
     *          pCurrent  pNext
     * 
     * We cannot delete B directly, because we haven't the header, cannot get A,
     * and cannot make A point to C in order to delete B.
     * But we know B, and all the node behind B, so we can deem B as header, 
     * delete the node after B, namely C, then copy C'data to B
     * 
     * @param args 
     */
    public static void main(String[] args) {
        SinglyListNode<String> nodeA = new SinglyListNode<String>("A");
        SinglyListNode<String> nodeB = new SinglyListNode<String>("B");
        SinglyListNode<String> nodeC = new SinglyListNode<String>("C");
        SinglyListNode<String> nodeD = new SinglyListNode<String>("D");
        
        //Construct singly list A -> B -> C -> D
        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        
        //Show the original list
        SinglyListNode<String> h = new SinglyListNode<String>(null);
        h.next = nodeA;
        System.out.println("Singly list:");
        while(h.next != null) {
            System.out.println(h.next.data);
            h = h.next;
        }
        
        
        //Now pCurrent points to B, suppose we don't konw anything before nodeB, 
        //we don't know the head of this list either, let delete B now.
        SinglyListNode<String> pCurrent = new SinglyListNode<String>(null);
        pCurrent.next = nodeB;
        String tempData = pCurrent.next.next.data;//save the C's data
        //Delete C
        pCurrent.next.next = pCurrent.next.next.next;
        //Copy the data in C to B
        pCurrent.next.data = tempData;
        
        //Print the result
        SinglyListNode<String> c = new SinglyListNode<String>(null);
        c.next = nodeA;
        System.out.println("After deleting B:");
        while(c.next != null) {
            System.out.println(c.next.data);
            c = c.next;
        }
    }
    
}
