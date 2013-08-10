package guibin.zhang.leecode;

/**
 * 
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * 
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 * 
 * Run Status: Accepted!
 * Program Runtime: 700 milli secs
 * Progress: 229/229 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class RotateList {
    public class ListNode {
        int val;
        ListNode next;
        
        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }
    
    public ListNode rotateRight(ListNode head, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (n == 0 || head == null) {
            return head;
        }
        ListNode curr = head;
        ListNode leftTail = head;
        int i = 0;
        
        //Adance n steps.
        for (; i < n && curr != null; i++) {
            curr = curr.next;
        }
        
        if (curr == null) {
            if (i == n) {
                return head;
            } else {//Note this case, when n > the length of list.
                int reminder = n % i;
                curr = head;
                for (i = 0; i < reminder; i++) {
                    curr = curr.next;
                }
            }
        }
        
        while (curr.next != null) {
            leftTail = leftTail.next;
            curr = curr.next;
        }
        curr.next = head;
        ListNode rightHead = leftTail.next;
        leftTail.next = null;
        return rightHead;
    }
    
    public static void main(String[] args) {
        RotateList rl = new RotateList();
        ListNode a = rl.new ListNode(1);
        ListNode b = rl.new ListNode(2);
        
        a.next = b;
        ListNode n = rl.rotateRight(a, 3);
        while (n != null) {
            System.out.print(n.val + " -> ");
            n = n.next;
        }
        System.out.println("NULL");
    }
}
