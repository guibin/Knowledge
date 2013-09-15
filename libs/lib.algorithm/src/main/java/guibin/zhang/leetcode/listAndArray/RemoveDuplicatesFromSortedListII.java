package guibin.zhang.leetcode.listAndArray;

/**
 * 
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * 
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 * 
 * Run Status: Accepted!
 * Program Runtime: 672 milli secs
 * Progress: 166/166 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class RemoveDuplicatesFromSortedListII {
    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    public ListNode deleteDuplicates(ListNode head) {
        
        if(head == null || head.next == null) {
            return head;
        }
        
        ListNode curr = head;
        ListNode prev = null;
        ListNode pprev = null;
        ListNode nHead = null;
        Boolean del = false;
        while (curr != null) {
            if (prev == null) {
                prev = curr;
                curr = curr.next;
            } else {
                if (prev.val == curr.val) {
                    prev = curr;
                    curr = curr.next;
                    del = true;
                } else {
                    if (del) {
                        prev.next = null;
                        prev = null;
                        if (pprev != null) {
                            pprev.next = curr;
                            prev = curr;
                        } else {
                            prev = curr;
                            nHead = prev;
                        }
                        curr = curr.next;
                        del = false;
                    } else {
                        pprev = prev;
                        if (nHead == null) {
                            nHead = pprev;
                        }
                        prev = curr;
                        curr = curr.next;
                    }
                }
            }
        }
        
        //Note the edge case: The last several nodes are same
        if (del && pprev != null) {
            pprev.next = null;
        } else if (del && pprev == null) {
            return null;
        }
        
        return nHead;
    }
    
    public ListNode deleteDuplicates_v2(ListNode head) {

        ListNode cur = head, pre = null;
        ListNode duplicate = null;
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                duplicate = cur;
                cur.next = cur.next.next;
            } else if (duplicate != null && cur.val == duplicate.val) {
                cur = cur.next;
                if (pre != null) {
                    pre.next = cur;
                } else {
                    head = cur;
                }
            } else {
                duplicate = null;
                pre = cur;
                cur = cur.next;
            }
        }
        return head;
    }
    
    public static void main(String[] args) {
        RemoveDuplicatesFromSortedListII rd = new RemoveDuplicatesFromSortedListII();
        ListNode a = rd.new ListNode(0);
        ListNode b = rd.new ListNode(0);
        ListNode c = rd.new ListNode(1);
        ListNode d = rd.new ListNode(1);
        ListNode e = rd.new ListNode(3);
        ListNode f = rd.new ListNode(3);
        ListNode g = rd.new ListNode(3);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        ListNode res = rd.deleteDuplicates(a);
        while(res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
