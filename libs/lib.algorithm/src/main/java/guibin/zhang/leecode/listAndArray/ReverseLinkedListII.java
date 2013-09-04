package guibin.zhang.leecode.listAndArray;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * 
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * 
 * return 1->4->3->2->5->NULL.
 * 
 * Note:
 * Given m, n satisfy the following condition:
 * 1 ? m ? n ? length of list.
 * 
 * Run Status: Accepted!
 * Program Runtime: 564 milli secs
 * Progress: 44/44 test cases passed.
* 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ReverseLinkedListII {
    
    public class ListNode {
        int val;
        ListNode next;
        
        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }
    
    
    public ListNode reverseBetween(ListNode head, int m, int n) {

        ListNode curr = head;
        ListNode prev = null;
        ListNode nxt = null;
        int i = 1;
        if (curr == null || curr.next == null || m == n) {
            return curr;
        }
        
        
        while(i < m) {
            i ++;
            prev = curr;
            curr = curr.next;
        }
        //Save prev
        ListNode prevM = prev;
        //Save curr
        ListNode nodeM = curr;
        
        while(i <= n) {
            i ++;
            //a) Save the curr.next
            nxt = curr.next;
            //b) Set the pointer to prev, cut off the pointer to nxt.
            curr.next = prev;
            //c) Move pre to curr
            prev = curr;
            //d) Move curr to nxt
            curr = nxt;
        }
/**
 *    a -> b -> c -> d -> e -> f
 * 
 *  case a: prevM != null
 *head prevM nodeM   prev  curr
 * |    |    |         |    |
 * a -> b    c <- d <- e    f -> g
 *      |    |         ^    ^
 *      |    |_________|____|
 *      |______________|
 * 
 * case b: prevM == null
 *  prevM nodeM    prev  curr
 *    |    |         |    |
 *  null   a <- b <- c    d -> e -> f
 *       / |              ^
 *      /  |______________|
 *    head
 */        
        if (prevM != null) {
            prevM.next = prev;
            nodeM.next = curr;
            return head;
        } else {
            nodeM.next = curr;
            return prev;
        }
    }
    
    public void print(ListNode n) {
        ListNode p = n;
        while(p != null) {
            System.out.print(p.val + " -> ");
            p = p.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        ReverseLinkedListII rl = new ReverseLinkedListII();
        ListNode a = rl.new ListNode(1);
        ListNode b = rl.new ListNode(2);
        ListNode c = rl.new ListNode(3);
        ListNode d = rl.new ListNode(4);
        ListNode e = rl.new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        System.out.println("Original:");
        rl.print(a);
        System.out.println("Reversed iterately:");
        System.out.println("m = 2, n = 4");
        rl.print(rl.reverseBetween(a, 2, 4));
        System.out.println("m = 1, n = 2");
        rl.print(rl.reverseBetween(a, 1, 2));
    }
}
