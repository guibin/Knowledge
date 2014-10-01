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
        ListNode curr = dummy;
        ListNode prev = dummy;
        int i = 0;
        while(curr != null) {
            if(i > 0 && i % 2 == 0) {
                //Save next
                ListNode nxt = curr.next;
                //Tail of the reversed nodes becomes to p1 of next round
                prev = reverse(prev, nxt);
                curr = nxt;
            } else {
                curr = curr.next;
            }
            i++;
        }
        
        return dummy.next;
    }
    
    /**
     * Standard reverse nodes between p1 and p2 exclusively.
     * 
     * @param p1
     * @param p2
     * @return Tail of the reversed nodes.
     */
    public ListNode reverse(ListNode p1, ListNode p2) {
        
        ListNode curr = p1.next;
        ListNode tailer = curr;//Remember the tailer of the list between p1 and p2
        ListNode prev = null;
        ListNode nxt = null;
        
        //Reverse the nodes between p1 and p2 normally
        while (curr != p2) {
            nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
        }
        //Fix connectivity between the three segments.
        tailer.next = p2;
        p1.next = prev;
        
        return tailer;
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
