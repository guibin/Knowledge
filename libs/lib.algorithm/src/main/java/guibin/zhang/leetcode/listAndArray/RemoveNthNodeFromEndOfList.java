package guibin.zhang.leetcode.listAndArray;

/**
 *
 * Given a linked list, remove the nth node from the end of list and return its head.
 * 
 * For example,
 * 
 *    Given linked list: 1->2->3->4->5, and n = 2.
 * 
 *    After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 * 
 * Run Status: Accepted!
 * Program Runtime: 680 milli secs
 * Progress: 207/207 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class RemoveNthNodeFromEndOfList {
    
    public class ListNode {
        int val;
        ListNode next;
        
        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ListNode p1 = head;
        ListNode p2 = head;
        
        int i = 0;
        for (; i < n && p2 != null; i++) {
            p2 = p2.next;
        }
        
        if (i == n) {
            //If i == n && p2 == null that means this list only has n nodes, just remove the head.
            if (p2 == null) {
                return head.next;
                
            } else {
                while (p2.next != null) {
                    p2 = p2.next;
                    p1 = p1.next;
                }
                ListNode tmp = p1.next;
                p1.next = tmp.next;
                tmp.next = null;
            }
            
        }
        return head;
    }
}
