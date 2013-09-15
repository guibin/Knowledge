package guibin.zhang.leetcode.listAndArray;

/**
 *
 * Given a linked list, swap every two adjacent nodes and return its head.
 * 
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * 
 * Your algorithm should use only constant space. 
 * You may not modify the values in the list, only nodes itself can be changed.
 * 
 * Run Status: Accepted!
 * Program Runtime: 632 milli secs
 * Progress: 55/55 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SwapNodesInPairs {
    
    public class ListNode {
        int val;
        ListNode next;
        
        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }
    
    public ListNode swapPairs(ListNode head) {
        
        if(head == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int i = 0;
        while(head != null) {
            i++;
            if(i % 2 == 0) {
                pre = reverse(pre, head.next);
                head = pre.next;
            } else {
                head = head.next;
            }
        }
            
        return dummy.next;
    }
    
    public ListNode reverse(ListNode pre, ListNode next) {
        
        ListNode last = pre.next;
        ListNode curr = last.next;
        
        while(curr != next) {
            last.next = curr.next;
            curr.next = pre.next;
            pre.next = curr;
            curr = last.next;
        }
        
        return last;
    }
    
    public static void main(String[] args) {
        SwapNodesInPairs sp = new SwapNodesInPairs();
        ListNode a = sp.new ListNode(1);
        ListNode b = sp.new ListNode(2);
        ListNode c = sp.new ListNode(3);
        ListNode d = sp.new ListNode(4);
        ListNode e = sp.new ListNode(5);
        ListNode f = sp.new ListNode(6);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        ListNode res = sp.swapPairs(a);
        while(res != null) {
            System.out.print(res.val + " -> ");
            res = res.next;
        }
        System.out.println("");
    }
}
