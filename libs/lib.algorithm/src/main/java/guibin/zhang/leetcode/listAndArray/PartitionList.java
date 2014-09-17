package guibin.zhang.leetcode.listAndArray;

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
        ListNode curr = head;
        ListNode sHead = new ListNode(0);
        ListNode sCurr = sHead;
        ListNode bHead = new ListNode(0);
        ListNode bCurr = bHead;
        
        while (curr != null) {
            if (curr.val < x) {
                sCurr.next = curr;
                
                sCurr = sCurr.next;
                curr = curr.next;
                sCurr.next = null;
            } else {
                bCurr.next = curr;
                
                bCurr = bCurr.next;
                curr = curr.next;
                bCurr.next = null;
            }
        }
        
        if(sHead != sCurr) {
            sCurr.next = bHead.next;
            return sHead.next;
        } else {
            return bHead.next;
        }
    }
    
    public static void main(String[] args) {
        PartitionList pl = new PartitionList();
        ListNode a = pl.new ListNode(1);
        ListNode b = pl.new ListNode(1);
        a.next = b;
        
        ListNode curr = pl.partition(a, 2);
        while(curr != null) {
            System.out.print(curr.val + ",");
            curr = curr.next;
        }
        System.out.println("Done");
    }
}
