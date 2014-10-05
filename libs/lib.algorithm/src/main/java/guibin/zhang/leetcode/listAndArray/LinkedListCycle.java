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
        
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            //Judge the cross node inside the loop
            if (fast == slow) return true;
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
    public ListNode detectCycle(ListNode head) {
        
        if (head == null || head.next == null) return null;
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;//linkedlist crossed here
        }
        
        if (fast == slow && fast != null) {
            ListNode p1 = head;
            ListNode p2 = slow;
            while (p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
            }
            return p1;
        } 
        
        return null;
    }
    
    public static void main(String[] args) {
        LinkedListCycle lc = new LinkedListCycle();
        
        ListNode a = lc.new ListNode(1);
        ListNode b = lc.new ListNode(2);
        a.next = b;
        
        lc.detectCycle(a);
    }
}
