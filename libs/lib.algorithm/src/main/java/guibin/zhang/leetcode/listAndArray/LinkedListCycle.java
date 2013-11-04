package guibin.zhang.leetcode.listAndArray;

/**
 *
 * Given a linked list, determine if it has a cycle in it.
 * 
 * Follow up:
 * Can you solve it without using extra space?
 * 
 * 16 / 16 test cases passed.
 * Status: Accepted
 * Runtime: 448 ms
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class LinkedListCycle {
    
    public class ListNode {
        int val;
        ListNode next;
        
        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }
    public boolean hasCycle(ListNode head) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ListNode curr = head;
        if (curr == null) {
            return false;
        }
        ListNode fast = curr;
        while(fast != null) {
            curr = curr.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            } else {
                return false;
            }
            //Be careful the null test.
            if (fast != null && curr.val == fast.val) {
                return true;
            }
        }
        return false;
    }
}
