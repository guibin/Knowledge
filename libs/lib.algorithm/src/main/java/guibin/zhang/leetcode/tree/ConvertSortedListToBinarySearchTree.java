package guibin.zhang.leetcode.tree;

/**
 * Given a singly linked list where elements are sorted in ascending order, 
 * convert it to a height balanced BST.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ConvertSortedListToBinarySearchTree {

    public class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    public TreeNode sortedListToBST(ListNode head) {
        
        if (head == null) {
            return null;
        }
        
        //Search the middle node P
        ListNode preP = null;//The node before the middle one.
        ListNode p = head;//Middle node
        ListNode p2 = head;//End end.
        while (p2 != null && p2.next != null) {
            preP = p;
            p = p.next;
            p2 = p2.next.next;
        }
        
        if (preP != null) {//To avoid the NullPointerException
            preP.next = null;
        }
        
        TreeNode root = new TreeNode(p.val);
        if (preP != null) {//To avoid StackOverflowException in the case that the List has ony single node
            root.left = sortedListToBST(head);
        }
        root.right = sortedListToBST(p.next);
        
        return root;
    }
    
    /**
     * Convert the list to array, then use sortedArrayToBST.
     * @param head
     * @return 
     */
    public TreeNode sortedListToBST_v2(ListNode head) {
        
        ListNode n = head;
        int size = 0;
        while(n != null) {
            size ++;
            n = n.next;
        }
        
        int[] num = new int[size];
        n = head;
        int i = 0;
        while(n != null) {
            num[i] = n.val;
            n = n.next;
            i++;
        }
        
        return sortedArrayToBST(num);
    }
    
    public TreeNode sortedArrayToBST(int[] num) {
        return sortedArrayToBST(num, 0, num.length - 1);
    }
    
    public TreeNode sortedArrayToBST(int[] num, int start, int end) {
        if(start <= end) {
            int mid = (start + end) / 2;
            TreeNode left = sortedArrayToBST(num, start, mid - 1);
            TreeNode right = sortedArrayToBST(num, mid + 1, end);
            TreeNode node = new TreeNode(num[mid]);
            node.left = left;
            node.right = right;
            return node;
        }
        return null;
    }
    
}
