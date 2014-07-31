package guibin.zhang.leetcode.listAndArray;

/**
 * In linked list how to find the 1/3rd and 2/3rd nodes efficiently... 
 * 
 * ex : 1-->2-->3-->4-->5-->6. 
 * 
 * outout : 
 * 1/3rd - 2 
 * 2/3rd - 4
 * 
 * Question link: http://www.careercup.com/question?id=10782829
 * 
 * Thought:
 * Use 3 pointers, 
 * 1st pointer pointing to the 1st node.. 
 * 2nd pointer pointing to the 2nd node.. 
 * 3rd pointer pointing to the 3rd node... 
 * 1st pointer move one time 
 * 2nd pointer move two times.. 
 * 3rd pointer move three times... 
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class LinkedListOneThirdNode {
    
    public class ListNode {
        public int val;
        public ListNode next;
        
        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }
    
    public static void findOneThirdNode(ListNode head) {

        ListNode first = head;
        ListNode second = null, third = null;

        if (first != null) {
            second = first.next;
        }
        if (second != null) {
            third = second.next;
        }

        while (true) {
            for (int i = 0; i < 3; i++) {
                if (third.next != null) {
                    third = third.next;
                }
            }
            if (third.next == null) break;
            
            for (int i = 0; i < 2; i++) {
                if (second != null) {
                    second = second.next;
                }
            }
            
            if (first != null) {
                first = first.next;
            }
            
        }
        
        System.out.println((first == null ? "N" : first.val) + " : " + 
                (second == null ? "N" : second.val) + " : " + 
                (third == null ? "N" : third.val));
    }
    
    public static void main(String[] args) {
        LinkedListOneThirdNode tn = new LinkedListOneThirdNode();
        ListNode a = tn.new ListNode(1);
        ListNode b = tn.new ListNode(2);
        a.next = b;
        ListNode c = tn.new ListNode(3);
        b.next = c;
        ListNode d = tn.new ListNode(4);
        c.next = d;
        ListNode e = tn.new ListNode(5);
        d.next = e;
        ListNode f = tn.new ListNode(6);
        e.next = f;
        ListNode g = tn.new ListNode(7);
        f.next = g;
        
        findOneThirdNode(a);
    }
}
