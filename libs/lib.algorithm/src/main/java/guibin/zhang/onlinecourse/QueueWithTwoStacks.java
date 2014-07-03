package guibin.zhang.onlinecourse;

import java.util.Stack;

/**
 * Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 * @param <T>
 */
public class QueueWithTwoStacks<T> {
    Stack<T> stackA = new Stack<>();
    Stack<T> stackB = new Stack<>();
    
    public void enqueue(T v) {
        stackA.push(v);
    }
    
    public T dequeue() {
        T result = null;
        if(stackB.isEmpty()) {
            while(!stackA.isEmpty()) {
                stackB.push(stackA.pop());
            }
        }
        if(!stackB.isEmpty()) {
            result = stackB.pop();
        } 
        
        System.out.println("dequeue " + result);
        return result;
    }
    
    public static void main(String[] args) {
        QueueWithTwoStacks<Integer> q = new QueueWithTwoStacks<>();
        q.enqueue(1);
        q.enqueue(2);
        q.dequeue();
        q.enqueue(3);
        q.enqueue(4);
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
    }
}
