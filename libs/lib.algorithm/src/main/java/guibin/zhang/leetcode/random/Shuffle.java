package guibin.zhang.leetcode.random;

import java.util.Arrays;

/**
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class Shuffle {
    
    public class ListNode {
        public Comparable val;
        public ListNode next;
        
        public ListNode(Comparable x) {
            this.val = x;
            this.next = null;
        }
    }
    
    /**
     * Shuffling a linked list. 
     * Given a singly-linked list containing N items, 
     * rearrange the items uniformly at random. 
     * Your algorithm should consume a logarithmic (or constant) amount of extra memory 
     * and run in time proportional to NlogN in the worst case.
     * 
     * http://stackoverflow.com/questions/12167630/algorithm-for-shuffling-a-linked-list-in-n-log-n-time
     * 
     * @param head 
     */
    public ListNode shuffleLinkedList(ListNode head) {
        
        if (size(head) <= 1) return head;
        
        //Split the list into two evenly
        ListNode[] heads = split(head);

        //Shuffle each one
        shuffleLinkedList(heads[0]);
        shuffleLinkedList(heads[1]);
        
        //Append the dummy node for the shorter one.
        balance(heads[0], heads[1]);
        
        //Merge the two sub-list randomly into one
        ListNode result = mergeRandomly(heads[0], heads[1]);
        
        //Remove the dummy node in the result
        return result;
    }
    
    /**
     * Split the linked list into two
     * @param head
     * @return 
     */
    public ListNode[] split(ListNode head) {
        ListNode headA = null;
        ListNode currA = null;
        ListNode headB = null;
        ListNode currB = null;
        ListNode curr = head;
        ListNode[] result = new ListNode[2];
        
        while (curr != null) {
            if (headA == null) {
                headA = curr;
                curr = curr.next;
                currA = headA;
                currA.next = null;
            } else {
                currA.next = curr;
                currA = currA.next;
                curr = curr.next;
                currA.next = null;
            }

            if (curr != null) {
                if (headB == null) {
                    headB = curr;
                    curr = curr.next;
                    currB = headB;
                    currB.next = null;
                } else {
                    currB.next = curr;
                    currB = currB.next;
                    curr = curr.next;
                    currB.next = null;
                }
            }
        }
        
        result[0] = headA;
        result[1] = headB;
        return result;
    }
    
    public int size(ListNode n) {
        ListNode curr = n;
        int s = 0;
        while (curr != null) {
            s ++;
            curr = curr.next;
        }
        return s;
    }
    
    /**
     * Make the two list have the same length, if not append the dummy node with value -1;
     * @param headA
     * @param headB 
     */
    public void balance(ListNode headA, ListNode headB) {
        
        ListNode currA = headA, currB = headB;
        while (currA.next != null && currB.next != null) {
            currA = currA.next;
            currB = currB.next;
        }
        
        while (currA.next != null) {
            ListNode dummy = new ListNode(-1);
            currB.next = dummy;
            currB = currB.next;
            currA = currA.next;
        }
        
        while (currB.next != null) {
            ListNode dummy = new ListNode(-1);
            currA.next = dummy;
            currA = currA.next;
            currB = currB.next;
        }
    }
    
    public ListNode mergeRandomly(ListNode headA, ListNode headB) {
        
        ListNode currA = headA, currB = headB, head = new ListNode(-1), curr = head;
        
        while (currA != null && currB != null) {
            int r = (int)(Math.random() * 10);
            //Flip the coin
            if (r < 5) {
                curr.next = currA;
                currA = currA.next;
                curr = curr.next;
                curr.next = null;
            } else {
                curr.next = currB;
                currB = currB.next;
                curr = curr.next;
                curr.next = null;
            }
        }
        
        if (currA != null) {
            curr.next = currA;
        }
        if (currB != null) {
            curr.next = currB;
        }
        
        return head.next;
    }
    
    //---------------------------------------------------------------
    
    /**
     * Idea:
     * While traveling through the array, swap the current item with a random 
     * item after current.
     * 
     * Iterate the array from 0 to N,
     * generate the r which is random in [i, N)
     * swap a[i] with a[r].
     * 
     * @param a 
     */
    public void shuffleArray(Comparable[] a) {
        
        int N = a.length;
        for (int i = 0; i < N; i++) {
            //Math.random() is [0, 1) => 
            //(int)(Math.random() * (N - i) is [0, N-i) =>
            //i + (int)(Math.random() * (N - i)) is [i, N)
            int r = i + (int)(Math.random() * (N - i));
            swap(a, i, r);
        }
    }
    
    private void swap(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    
    public void print(ListNode n) {
        ListNode curr = n;
        while(curr != null) {
            System.out.print(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Comparable[] a = {50, 61, 44, 32, 99, 87, 51, 50, 50, 12};
        Shuffle s = new Shuffle();
        s.shuffleArray(a);
        System.out.println("After shuffle:");
        Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
        System.out.println();
        
        ListNode na = s.new ListNode(1);
        ListNode nb = s.new ListNode(2);
        na.next = nb;
        ListNode nc = s.new ListNode(3);
        nb.next = nc;
        ListNode nd = s.new ListNode(4);
        nc.next = nd;
        ListNode ne = s.new ListNode(5);
        nd.next = ne;
        ListNode nf = s.new ListNode(6);
        ne.next = nf;
        ListNode ng = s.new ListNode(7);
        nf.next = ng;
        ListNode nh = s.new ListNode(8);
        ng.next = nh;
        ListNode nj = s.new ListNode(9);
        nh.next = nj;
        s.print(na);
        
        ListNode[] heads = s.split(na);
        System.out.println("Size of A: " + s.size(heads[0]));
        s.print(heads[0]);
        System.out.println("Size of B: " + s.size(heads[1]));
        s.print(heads[1]);
        
        System.out.println("After balance:");
        s.balance(heads[0], heads[1]);
        s.print(heads[0]);
        s.print(heads[1]);
        
        ListNode head = s.shuffleLinkedList(na);
        System.out.println("After shuffle:");
        s.print(head);
    }
}
