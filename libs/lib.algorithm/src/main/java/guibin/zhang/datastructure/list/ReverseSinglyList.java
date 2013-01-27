package guibin.zhang.datastructure.list;

/**
 * Given a singly list, reverse this list and return the head.
 * 
 * @author guibin
 */
public class ReverseSinglyList {
    
    /**
     *      p    n,p' n'
     *      |    |    |
     * A -> B    C -> E
     *                |
     *               tmp
     * 
     * Reverse from head to tail.
     * 
     * @param p The head pointed to part that has been reversed, the first part.
     * @param n The head pointed to the remaining part, the second part.
     * @return The head of the reversed list, which is actual the last node of the list.
     */
    public SinglyListNode<String> reverseRecursively(SinglyListNode<String> p, SinglyListNode<String> n) {
        
        if(p == null) return null;
        
        if (n == null) {
            return p;
        } else {
            if (p.next == n) {
                p.next = null;
            }

            SinglyListNode<String> temp = n.next;
            n.next = p;
            return reverseRecursively(n, temp);
        }
    }
    
    /**
     * Reverse from tail to head.
     * 
     * @param p The head of the linked list to be reversed.
     * @return The head of the reversed list, which is actual the last node of the list.
     */
    public SinglyListNode<String> reverseRecursively(SinglyListNode<String> p) {
        
        if(p.next == null) 
            return p;
        
        //Traverse the linked list to the end.
        SinglyListNode<String> tail = reverseRecursively(p.next);
        
        /**
         * Here the newNode is actual the tail node of the linked list, and p is the last but one.
         */        
        p.next = null;
        
        /**
         * tail is always the tail of the linked list, which is the head of the reversed part.
         */
        SinglyListNode<String> n = tail;
        
        /**
         * Traverse to the tail of the reversed part.
         */
        while(n.next != null) {
            n = n.next;
        }
        
        /**
         * Append the current node to the tail of the reversed part.
         */
        n.next = p;
        
        return tail;
    }
    
    /**
     * Reverse from tail to head.
     * 
     * @param p The head of the linked list to be reversed.
     * @return The head of the reversed list, which is actual the last node of the list.
     */
    public SinglyListNode<String> reverseRecursively2(SinglyListNode<String> p) {
        
        if(p.next == null)
            return p;
        
        /**
         * Remember the next one to be reversed.
         */
        SinglyListNode<String> n = p.next;
        SinglyListNode<String> tail = reverseRecursively2(p.next);
        /**
         * After reverseRecursively2(p.next), suppose all the node after p.next has been reversed.
         */
        
        /**
         * Reverse the p and n.
         */
        p.next = null;
        n.next = p;
        
        /**
         * Always return the tail.
         */
        return tail;
    }
    
    public SinglyListNode<String> reverse(SinglyListNode<String> node) {
        
        SinglyListNode<String> result = null;
        SinglyListNode<String> curr = node;
        
        while(curr != null) {
            SinglyListNode<String> n = curr.next;
            curr.next = result;
            result = curr;
            curr = n;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        SinglyListNode<String> a = new SinglyListNode<String>("A");
        SinglyListNode<String> b = new SinglyListNode<String>("B");
        SinglyListNode<String> c = new SinglyListNode<String>("C");
        SinglyListNode<String> d = new SinglyListNode<String>("D");
        SinglyListNode<String> e = new SinglyListNode<String>("E");
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        
        System.out.println("Before reverse:");
        a.print();
        
        ReverseSinglyList rsl = new ReverseSinglyList();
        SinglyListNode<String> x = rsl.reverseRecursively(a, b);
        System.out.println("After reverse:");
        x.print();
        
        SinglyListNode<String> y = rsl.reverseRecursively(x);
        System.out.println("After reverse:");
        y.print();
        
        SinglyListNode<String> z = rsl.reverseRecursively2(y);
        System.out.println("After reverse:");
        z.print();
        
        SinglyListNode<String> m = rsl.reverse(z);
        System.out.println("After reverse:");
        m.print();
    }
}
