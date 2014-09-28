package guibin.zhang.leetcode.listAndArray;

/**
 *
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * @author Guibin Zhang
 */
public class MergeSortList {
    
    public class ListNode {
        int val;
        ListNode next;
        
        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }
 
    public ListNode sortList(ListNode head) {
        // Sort merge
        // Return condition
        if(head == null || head.next == null) {
            return head;
        }
        
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //Now the fast reaches the end of the list, slow is right at the middle of the list.
        
        //fast is the head of the second half part.
        fast = slow.next;
        //cut off the tail pointer to the second half part. Now slow points to the end of first part.
        slow.next = null;
        
        //Now we get the two sublist head and fast, recursively split/sort them.
        slow = sortList(head);
        fast = sortList(fast);
        
        //Now the two list slow and fast is sorted, merge them.
        return merge(slow, fast);
    }
    
    public ListNode merge(ListNode slow, ListNode fast) {
        
        ListNode head = new ListNode(0);
        ListNode curr = head;
        
        while(slow != null && fast != null) {
            if(slow.val < fast.val) {
                curr.next = slow;
                slow = slow.next;
            } else {
                curr.next = fast;
                fast = fast.next;
            }
            curr = curr.next;
        }
        if (slow != null) {
            curr.next = slow;
        }
        if (fast != null) {
            curr.next = fast;
        }
        
        return head.next;
    }
    
    public static void main(String[] args) {
        MergeSortList mks = new MergeSortList();
        
        ListNode a = mks.new ListNode(103);
        ListNode b = mks.new ListNode(33);
        ListNode c = mks.new ListNode(6);
        ListNode d = mks.new ListNode(101);
        ListNode e = mks.new ListNode(21);
        ListNode f = mks.new ListNode(3);
        ListNode g = mks.new ListNode(86);
        ListNode h = mks.new ListNode(41);
        ListNode i = mks.new ListNode(6);
        ListNode j = mks.new ListNode(65);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = h;
        h.next = i;
        i.next = j;
        
        ListNode curr = mks.sortList(a);
        while (curr != null) {
            System.out.print(curr.val + ", ");
            curr = curr.next;
        }
        System.out.println();
    }
}
