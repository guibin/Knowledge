package guibin.zhang.leecode;

/**
 * 
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * 
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 * 
 * 
 * Run Status: Accepted!
 * Program Runtime: 676 milli secs
 * Progress: 164/164 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class RemoveDuplicatesFromSortedList {
    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    public ListNode deleteDuplicates(ListNode head) {
        
        ListNode curr = head;
        ListNode prev = null;
        
        while(curr != null) {
            if(prev == null) {
                prev = curr;
                curr = curr.next;
            } else {
                if(curr.val == prev.val) {
                    ListNode tmp = curr;
                    curr = curr.next;
                    tmp.next = null;//To avoid the memory leak
                    prev.next = curr;
                } else {
                    prev = curr;
                    curr = curr.next;
                }
            }
        }
        
        return head;
    }
}
