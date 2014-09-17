package guibin.zhang.leetcode.listAndArray;

import guibin.zhang.leetcode.tree.MaxPriorityQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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

    public class ListNode implements Comparable {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public int compareTo(Object o) {
            if (this.val < ((ListNode)o).val) return -1;
            else if (this.val > ((ListNode)o).val) return 1;
            else return 0;
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
    
    /**
     * This class is for merging the sorted array lists.
     */
    public class HeapNode implements Comparable{
        
        int from;//Which list it comes from
        int val;//Value of this HeapNode
        int currPos;//Current position on the source array
        
        public HeapNode(int from, int value, int currPos) {
            this.from = from;
            this.val = value;
            this.currPos = currPos;
        }

        @Override
        public int compareTo(Object o) {
            if (this.val > ((HeapNode)o).val) return 1;
            else if (this.val < ((HeapNode)o).val) return -1;
            else return 0;
        }
    }
    
    public ListNode mergeKLists_v2(List<ListNode> list) throws Exception {
        
//        MaxPriorityQueue<ListNode> maxHeap = new MaxPriorityQueue<>(list.size());//The heap is buggy
        Queue<ListNode> maxHeap = new PriorityQueue<>(list.size());
        ListNode head = new ListNode(0);
        
        //Build a maxHeap from each head of the input list
        for (ListNode h : list) {
            maxHeap.add(h);
        }
        
        ListNode curr = head;//The head of the new merged list.
        while (!maxHeap.isEmpty()) {
            //Get the max one in the heap
            ListNode h = maxHeap.remove();
            //Add the next node to the maxheap
            if (h.next != null) {
                maxHeap.add(h.next);
            }
            
            curr.next = h;
            h.next = null;
            curr = curr.next;
        }
        
        return head.next;
    }
    
    public static void main(String[] args) throws Exception {
        MergeKSortedLists mks = new MergeKSortedLists();
        ListNode a = mks.new ListNode(1);
        ListNode b = mks.new ListNode(3);
        ListNode c = mks.new ListNode(6);
        ListNode d = mks.new ListNode(10);
        a.next = b;
        b.next = c;
        c.next = d;
        
        ListNode e = mks.new ListNode(2);
        ListNode f = mks.new ListNode(3);
        ListNode g = mks.new ListNode(8);
        e.next = f;
        f.next = g;
        
        ListNode h = mks.new ListNode(4);
        ListNode i = mks.new ListNode(6);
        ListNode j = mks.new ListNode(6);
        h.next = i;
        i.next = j;
        
        List<ListNode> list = new ArrayList<>();
        list.add(a);
        list.add(e);
        list.add(h);
        ListNode head = mks.mergeKLists_v2(list);
        
        System.out.println("After merge:");
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + ", ");
            curr = curr.next;
        }
        System.out.println();
    }
}
