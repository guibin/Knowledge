package guibin.zhang.leecode;

/**
 * 
 * Given a linked list and a value x, 
 * partition it such that all nodes less than x come before nodes greater than or equal to x.
 * 
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * 
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 * 
 * Run Status: Accepted!
 * Program Runtime: 640 milli secs
 * Progress: 166/166 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class PartitionList {

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode partition(ListNode head, int x) {
        
        ListNode sHead = null;
        ListNode sCurr = null;
        ListNode bHead = null;
        ListNode curr = head;
        ListNode last = null;
        
        while(curr != null) {
            if(sHead != null && bHead != null) {
                if(curr.val < x) {
                    sCurr.next = curr;
                    sCurr = curr;
                    curr = curr.next;
                    sCurr.next = null;//Note: when connect a node, be sure to break the node after that.
                } else {
                    last.next = curr;
                    last = curr;
                    curr = curr.next;
                    last.next = null;
                }
            } else if(sHead == null && bHead != null) {
                if(curr.val < x) {
                    sHead = curr;
                    sCurr = curr;
                    curr = curr.next;
                    sCurr.next = null;
                } else {
                    last.next = curr;
                    last = curr;
                    curr = curr.next;
                    last.next = null;
                }
            } else if(sHead != null && bHead == null) {
                if(curr.val < x) {
                    sCurr.next = curr;
                    sCurr = curr;
                    curr = curr.next;
                    sCurr.next = null;
                } else {
                    bHead = curr;
                    last = curr;
                    curr = curr.next;
                    last.next = null;
                }
            } else { //both are null
                if(curr.val < x) {
                    sHead = curr;
                    sCurr = curr;
                    curr = curr.next;
                    sCurr.next = null;
                } else {
                    bHead = curr;
                    last = curr;
                    curr = curr.next;
                    last.next = null;
                }
            }
        }
        
        if(sHead != null) {
            sCurr.next = bHead;
            return sHead;
        } else {
            return head;
        }
    }
    
    public ListNode partition_v2(ListNode head, int x) {
        ListNode curr = head;
        ListNode sHead = null;
        ListNode sCurr = null;
        ListNode bHead = null;
        ListNode bCurr = null;
        
        while (curr != null) {
            if (curr.val < x) {
                if (sHead == null) {
                    sHead = curr;
                } else {
                    sCurr.next = curr;
                }
                sCurr = curr;
                curr = curr.next;
                sCurr.next = null;
            } else {
                if(bHead == null) {
                    bHead = curr;
                } else {
                    bCurr.next = curr;
                }
                bCurr = curr;
                curr = curr.next;
                bCurr.next = null;
            }
        }
        if(sHead != null) {
            sCurr.next = bHead;
            return sHead;
        } else {
            return bHead;
        }
    }
    
    public static void main(String[] args) {
        PartitionList pl = new PartitionList();
        ListNode a = pl.new ListNode(1);
        ListNode b = pl.new ListNode(1);
        a.next = b;
        
        ListNode curr = pl.partition_v2(a, 2);
        while(curr != null) {
            System.out.print(curr.val + ",");
            curr = curr.next;
        }
        System.out.println("Done");
    }
}
