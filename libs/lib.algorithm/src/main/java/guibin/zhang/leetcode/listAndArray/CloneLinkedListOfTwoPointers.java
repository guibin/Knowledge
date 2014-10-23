package guibin.zhang.leetcode.listAndArray;

/**
 * 
 * Clone a linked structure with two pointers per node. 
 * Suppose that you are given a reference to the first node of a linked structure 
 * where each node has two pointers: 
 * one pointer to the next node in the sequence (as in a standard singly-linked list) 
 * and one pointer to an arbitrary node.
 * 
 * Design a linear-time algorithm to create a copy of the doubly-linked structure. 
 * You MAY modify the original linked structure, 
 * but you must end up with two copies of the original.
 * 
 * http://www.geeksforgeeks.org/a-linked-list-with-next-and-arbit-pointer/
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class CloneLinkedListOfTwoPointers {
    
    public class ListNode {
        public int val;
        public ListNode next;
        public ListNode random;
        
        public ListNode(int x) {
            this.val = x;
            this.next = null;
            this.random = null;
        }
    }
    
    public ListNode cloneList(ListNode head) {
        
        // 1. The first step is to insert the copied node
        ListNode curr = head;
        while (curr != null) {
            // Save curr.next
            ListNode nxt = curr.next;
            // Create the copie node
            ListNode copy = new ListNode(curr.val);
            
            // Link the copy into list
            copy.next = nxt;
            curr.next = copy;
            // Move curr forward
            curr = nxt;
        }
        
        // 2. Copy the random pointer
        curr = head;
        while (curr != null) {
            curr.next.random = curr.random.next;
            curr = curr.next.next;
        }
        
        // 2. Split the origin linked list and the copied one
        curr = head;
        ListNode nHead = curr.next;
        while (curr != null) {
            
            ListNode nxt = curr.next;
            curr.next = nxt.next;//Cut off the curr with its neighbor
            
            //Cut off the nxt with its neibor and point to next nxt
            if (nxt.next != null) {
                nxt.next = nxt.next.next;
            }
            curr = curr.next;
        }
        return nHead;
    }
}
