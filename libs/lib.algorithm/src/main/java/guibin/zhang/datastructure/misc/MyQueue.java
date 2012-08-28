package guibin.zhang.datastructure.misc;

import java.util.Stack;

/**
 * implement the queue's enqueue and dequeue with stack
 * 
 * @author Guibin Zhang <guibin at Yahoo-inc>
 */

public class MyQueue {
    
    Stack<Object> s1 = new Stack<Object>();
    Stack<Object> s2 = new Stack<Object>();
    
    public void enqueue(Object o) {
        s1.push(o);
    }
    
    /**
     * When s2 is empty, pop all the items from s1 to s2.
     * Items in s1 is descending, items in s2 is ascending
     * @return 
     */
    public Object dequeue() {
        if(s2.isEmpty()) {
            while(!s1.empty()) {
                s2.push(s1.pop());
            }
        }
        
        if(!s2.isEmpty()) {
            return s2.pop();
        } else {
            return null;
        }
    }
    
    public boolean isEmpty() {
        return s1.isEmpty() || s2.isEmpty();
    }
    
    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        q.enqueue(4);
        q.enqueue(5);
        System.out.println(q.dequeue());
        q.enqueue(6);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}
