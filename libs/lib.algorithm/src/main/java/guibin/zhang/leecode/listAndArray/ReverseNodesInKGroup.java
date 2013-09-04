package guibin.zhang.leecode.listAndArray;

/**
 *
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 * 
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 * 
 * You may not alter the values in the nodes, only nodes itself may be changed.
 * 
 * Only constant memory is allowed.
 * 
 * For example,
 * Given this linked list: 1->2->3->4->5
 * 
 * For k = 2, you should return: 2->1->4->3->5
 * 
 * For k = 3, you should return: 3->2->1->4->5
 * 
 * 
 * http://www.cnblogs.com/lichen782/p/leetcode_Reverse_Nodes_in_kGroup.html
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ReverseNodesInKGroup {
    
    public class ListNode {
        int val;
        ListNode next;
        
        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }
    
    
    public ListNode reverseKGroup(ListNode head, int k) {
        
        if(head == null || k == 1) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int i = 0;
        while (head != null) {
            i ++;
            if (i % k == 0) {
                pre = reverse(pre, head.next);
                head = pre.next;
            } else {
                head = head.next;
            }
        }
        
        return dummy.next;
    }
    
    /**
     * Reverse a list between pre and next exclusively.
     * 
     * an example:
     * a linked list:
     * 0->1->2->3->4->5->6
     * |           |   
     * pre        next
     * 
     * after call pre = reverse(pre, next)
     * 0->3->2->1->4->5->6
     *          |  |
     *          pre next
     * 
     * @param pre
     * @param next
     * @return the reversed list's last node, which is the precedence of parameter next
     */
    public ListNode reverse(ListNode pre, ListNode next) {
        
        ListNode last = pre.next; //where first will be doomed "last"
        ListNode curr = last.next;
        while(curr != next) {//Note here is the next instead of null.
            last.next = curr.next;
            curr.next = pre.next;
            pre.next = curr;
            curr = last.next;
        }
        return last;
    }
    
    public ListNode reverseKGroup_buggy(ListNode head, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ListNode curr = head;
        ListNode nxt = null;
        ListNode prev = null;
        ListNode tail = null;
        ListNode res = null;
        int i = 0;
        
        while(curr != null) {
            if(i == 0) {
                tail = curr;
                ListNode t = curr;
                int n = 0;
                while (t != null && n < k) {
                    t = t.next;
                    n ++;
                }
                if (n != k) {
                    break;
                } else {
                    prev = null;
                }
            }
            if (i < k) {
                nxt = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nxt;
                i ++;
            } else {
                if(res == null) {
                    res = prev;
                }
                tail.next = curr;
                tail = null;
                i = 0;
            }
        }
        
        if (i == k && res == null) {
            res = prev;
        }
        return res == null ? head : res;
    }
    
    public static void main(String[] args) {
        ReverseNodesInKGroup rn = new ReverseNodesInKGroup();
        ListNode a = rn.new ListNode(1);
        ListNode b = rn.new ListNode(2);
        ListNode c = rn.new ListNode(3);
        ListNode d = rn.new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;
        ListNode res = rn.reverseKGroup_buggy(a, 2);
        
        while(res != null) {
            System.out.print(res.val + ",");
            res = res.next;
        }
        System.out.println("");
    }
}
