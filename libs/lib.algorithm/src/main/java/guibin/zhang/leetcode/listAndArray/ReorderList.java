package guibin.zhang.leetcode.listAndArray;

import java.util.HashMap;

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
        HashMap<Integer, ListNode> map = new HashMap<Integer, ListNode>();
        ListNode curr = head;
        int i = 0;
        while(curr != null) {
            map.put(i, curr);
            i++;
            curr = curr.next;
        }
        int len = map.size();
        
        if (len <= 2) return;
        
        for (int j = 0; j < len/2; j++) {
            if (j + 1 < len - 1 - j) {//To avoid the adjacent case. if dest is next to src, skip swap.
                ListNode src = map.get(j);
                ListNode dest = map.get(len - 1 - j);
                ListNode prev = map.get(len - 2 - j);
                ListNode nxt = src.next;
                src.next = dest;
                dest.next = nxt;
                prev.next = null;
            }
        }
    }
}
