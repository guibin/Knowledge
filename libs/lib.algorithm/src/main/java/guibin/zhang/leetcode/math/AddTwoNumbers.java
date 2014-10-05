package guibin.zhang.leetcode.math;

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
        
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        
        while(l1 != null || l2 != null || carry > 0) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            
            int currVal = (num1 + num2 + carry) % 10;
            carry = (num1 + num2 + carry) / 10;
            
            ListNode node = new ListNode(currVal);
            curr.next = node;
            curr = curr.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return dummy.next;
    }
    
    public ListNode addTwoNumbers_v2(ListNode l1, ListNode l2) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;
        int sum = 0;
        while(l1 != null && l2 != null) {
            sum = l1.val + l2.val + carry;
            ListNode node = new ListNode(sum % 10);
            curr.next = node;
            carry = sum / 10;
            l1 = l1.next;
            l2 = l2.next;
            curr = curr.next;
        }
        
        while (l1 != null) {
            sum = l1.val + carry;
            ListNode node = new ListNode(sum % 10);
            curr.next = node;
            carry = sum / 10;
            curr = curr.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            sum = l2.val + carry;
            ListNode node = new ListNode(sum % 10);
            curr.next = node;
            carry = sum / 10;
            curr = curr.next;
            l2 = l2.next;
        }
        if (carry > 0) {
            ListNode node = new ListNode(carry);
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
