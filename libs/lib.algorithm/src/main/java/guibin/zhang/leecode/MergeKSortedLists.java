package guibin.zhang.leecode;

import java.util.ArrayList;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 *
 * Run Status: Accepted!
 * Program Runtime: 584 milli secs
 * Progress: 129/129 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class MergeKSortedLists {

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ListNode head = new ListNode(0);
        ListNode curr = head;
        int size = lists.size();

        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        boolean finished = false;
        while (!finished) {
            for (int i = 0; i < size; i++) {
                ListNode node = lists.get(i);
                if (node != null && node.val < min) {
                    min = node.val;
                    minIndex = i;
                }
            }
            if (minIndex > -1) {
                ListNode n = lists.get(minIndex);
                curr.next = n;
                curr = curr.next;
                lists.set(minIndex, n.next);
                //Note: reset the min and minIndex
                min = Integer.MAX_VALUE;
                minIndex = -1;
            } else {
                finished = true;
            }
        }

        return head.next;
    }
}
