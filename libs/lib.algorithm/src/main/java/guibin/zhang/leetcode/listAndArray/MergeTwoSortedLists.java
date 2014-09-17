package guibin.zhang.leetcode.listAndArray;

/**
 *
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 *
 * Run Status: Accepted!
 * Program Runtime: 656 milli secs
 * Progress: 208/208 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class MergeTwoSortedLists {

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    public ListNode mergeTwoLists(ListNode L1, ListNode L2) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (L1 == null) {
            return L2;
        } else if (L2 == null) {
            return L1;
        }

        ListNode head = new ListNode(0);
        ListNode curr = head;

        while (L1 != null && L2 != null) {
            if (L1.val < L2.val) {
                curr.next = L1;
                L1 = L1.next;
            } else {
                curr.next = L2;
                L2 = L2.next;
            }
            
            curr = curr.next;
        }

        if (L1 != null) curr.next = L1;
        if (L2 != null) curr.next = L2;

        return head.next;
    }
}
