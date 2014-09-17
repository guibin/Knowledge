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
        public int val;
        public ListNode next;
        
        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }
    public boolean hasCycle(ListNode head) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ListNode curr = head;
        ListNode fast = head;
        
        while(fast != null) {
            curr = curr.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
            //Be careful the null test.
            if (fast != null && curr == fast) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * s: slow steps, 2s: fast steps.
     * r: The perimeter of the cycle.
     * a: The steps from beginning to the first node on the cycle.
     * b: The steps from a to the intersection of slow and fast.
     * L: Total length of the list.
     * 
     * s = a + b
     * 2s = a + nr + b
     * => 2(a + b) = nr + a + b => nr = a + b => a = nr - b => a = (n-1)r + r - b
     * 
     * This means that the distance from beginning to the first node on cycle
     * is equal to the distance from the intersection of slow and fast to the first node on cycle.
     * 
     * @param head
     * @return 
     */
    public ListNode findFistNodeOnCycle(ListNode head) {

        ListNode curr = head;
        ListNode fast = head;
        while (fast != null) {
            curr = curr.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
            //Be careful the null test.
            if (fast != null && curr == fast) {
                
                // There is cycle on the linkedlist, here start to figure out the cross node.
                ListNode curr2 = head;
                while (curr2 != null) {
                    curr2 = curr2.next;
                    curr = curr.next;
                    if (curr2 == curr) {
                        return curr2;
                    }
                }
                
            }
        }
        
        return null;
    }
}
