package guibin.zhang.leetcode.listAndArray;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * You must do this in-place without altering the nodes' values.
 * 
 * For example,
 * Given {1,2,3,4}, reorder it to {1,4,2,3}.
 * 
 * 13 / 13 test cases passed.
 * Status: Accepted
 * Runtime: 648 ms
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ReorderList {
    public class ListNode {
        int val;
        ListNode next;
        
        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }
    
    public void reorderList(ListNode head) {
        
        if (head == null || head.next == null) return;
        //Find the middle node of the list
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode p1 = head;
        ListNode p2 = slow.next;
        slow.next = null;
        
        //Reverse the second part
        p2 = reverse(p2);
        
        //Merge the p1 and reversed p2
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while(p1 != null && p2 != null) {
            curr.next = p1;
            curr = curr.next;
            p1 = p1.next;
            curr.next = p2;
            curr = curr.next;
            p2 = p2.next;
        }
        
        if (p1 != null) curr.next = p1;
        if (p2 != null) curr.next = p2;
    }
    
    public ListNode reverse(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
        }
        return prev;
    }
    
    public void reorderList_v2(ListNode head) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        List<ListNode> list = new ArrayList<>();
        ListNode curr = head;
        int i = 0;
        while (curr != null) {
            list.add(i++, curr);
            curr = curr.next;
        }
        int len = list.size();
        if (len <= 2) return;
        
        for (int j = 0; j < len / 2; j++) {
            ListNode src = list.get(j);
            ListNode dest = list.get(len - 1 - j);
            ListNode prev = list.get(len - 2 - j);
            ListNode nxt = src.next;
            //To avoid the adjacent src and dest case.
            //Otherwise after prev.next = null, one node will be lost.
            if (prev != src) {
                src.next = dest;
                dest.next = nxt;
                prev.next = null;
            }
        }
    }
    
    public static void main(String[] args) {
        ReorderList r = new ReorderList();
        ListNode a = r.new ListNode(1);
        ListNode b = r.new ListNode(2);
        ListNode c = r.new ListNode(3);
        ListNode d = r.new ListNode(4);
        ListNode e = r.new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        
        System.out.println("Before re-order:");
        ListNode curr = a;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println();
        
        r.reorderList(a);
        
        System.out.println("After re-order:");
        curr = a;
        while (curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println();
    }
}
