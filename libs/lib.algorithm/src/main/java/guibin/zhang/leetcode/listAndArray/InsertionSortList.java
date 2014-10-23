package guibin.zhang.leetcode.listAndArray;

/**
 *
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class InsertionSortList {
    
    public class ListNode {
        public int val;
        public ListNode next;
        
        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }
    
    public ListNode insertionSortList(ListNode head) {
        
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        
        ListNode curr = head;
        ListNode prev;
        
        while (curr != null) {
            ListNode nxt = curr.next;
            
            //Each time search from start dummy, to get the insert before point.
            prev = dummy;
            while (prev.next != null && prev.next.val <= curr.val) {
                prev = prev.next;
            }
            //Insert curr between prev and prev.next
            curr.next = prev.next;
            prev.next = curr;
            
            //Move curr forward
            curr = nxt;
        }
        return dummy.next;
    }
    
    public static void main(String[] args) {
        InsertionSortList is = new InsertionSortList();
        ListNode a = is.new ListNode(5);
        ListNode b = is.new ListNode(8);
        ListNode c = is.new ListNode(9);
        ListNode d = is.new ListNode(6);
        ListNode e = is.new ListNode(7);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        is.insertionSortList(a);
    }
}
