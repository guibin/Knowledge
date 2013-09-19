package guibin.zhang.leetcode.listAndArray;

/**
 *
 * You are given two linked lists representing two non-negative numbers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class AddTwoNumbers {
    
    public class ListNode {
        int val;
        ListNode next;
        
        public ListNode(int x) {
            this.val = x;
            this.next = null;
        }
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int carry = 0;
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int sum = 0;
        ListNode node = null;
        while (curr1 != null && curr2 != null) {
            sum = curr1.val + curr2.val + carry;
            if (sum > 9) {
                carry = sum / 10;
                node = new ListNode(sum % 10);
            } else {
                carry = 0;
                node = new ListNode(sum);
            }
            curr.next = node;
            curr = curr.next;
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        
        if (curr1 != null || curr2 != null) {
            ListNode remaining = null;
            if (curr1 != null) {
                remaining = curr1;
            }
            if (curr2 != null) {
                remaining = curr2;
            }
            while (remaining != null) {
                sum = carry + remaining.val;
                if (sum > 9) {
                    carry = sum / 10;
                    node = new ListNode(sum % 10);
                } else {
                    carry = 0;
                    node = new ListNode(sum);
                }
                curr.next = node;
                curr = curr.next;
                remaining = remaining.next;
            }
        }
        //Note the last carry.
        if (carry > 0) {
            node = new ListNode(carry);
            curr.next = node;
        }
        return dummy.next;
    }
    
    public static void main(String[] args) {
        AddTwoNumbers at = new AddTwoNumbers();
        ListNode a = at.new ListNode(1);
        ListNode d = at.new ListNode(8);
        a.next = d;
        ListNode b = at.new ListNode(0);
        ListNode c = at.addTwoNumbers(a, b);
        while(c != null) {
            System.out.print(c.val + " ->");
            c = c.next;
        }
        System.out.println();
    }
}
