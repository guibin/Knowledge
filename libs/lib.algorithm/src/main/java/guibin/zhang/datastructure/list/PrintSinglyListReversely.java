package guibin.zhang.datastructure.list;

import java.util.Stack;

/**
 * No5 of the book A. Print the singly list from the tail to the head.
 *
 * @author guibin
 */
public class PrintSinglyListReversely {

    /**
     * Based on the stack.
     *
     * @param head
     */
    public void printSinlyListReverselyVersion1(SinglyListNode<String> head) {
        Stack<SinglyListNode> stack = new Stack<SinglyListNode>();
        SinglyListNode<String> current = head;

        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop().data.toString());
        }
    }

    /**
     * Based on the recursive
     *
     * @param head
     */
    public void printSinlyListReverselyVersion2(SinglyListNode<String> head) {

        if (head != null) {
            if (head.next != null) {
                printSinlyListReverselyVersion2(head.next);
            }
        }
        System.out.println(head.data.toString());

    }

    public static void main(String[] args) {
        SinglyListNode<String> a = new SinglyListNode<String>("A");
        SinglyListNode<String> b = new SinglyListNode<String>("B");
        SinglyListNode<String> c = new SinglyListNode<String>("C");
        SinglyListNode<String> d = new SinglyListNode<String>("D");
        SinglyListNode<String> e = new SinglyListNode<String>("E");
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        PrintSinglyListReversely pslr = new PrintSinglyListReversely();
//        pslr.printSinlyListReverselyVersion1(a);
        pslr.printSinlyListReverselyVersion2(a);
    }
}
