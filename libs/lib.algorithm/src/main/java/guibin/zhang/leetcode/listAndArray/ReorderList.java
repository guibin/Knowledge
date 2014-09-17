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
        a.next = b;
        b.next = c;
        c.next = d;
        
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
