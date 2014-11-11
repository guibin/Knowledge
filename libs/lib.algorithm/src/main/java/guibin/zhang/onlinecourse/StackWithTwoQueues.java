package guibin.zhang.onlinecourse;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Give two queue with standard add and remove operation, 
 * implement the standard queue operation push and pop.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class StackWithTwoQueues<T> {
    
    Queue<T> queueA = new LinkedList<>();
    Queue<T> queueB = new LinkedList<>();
    
    
    public void push(T t) {
        //Add the t to non-empty list
        if (queueA.isEmpty() && queueB.isEmpty()) {
            queueA.add(t);
        } else if (queueA.isEmpty()) {
            queueB.add(t);
        } else {
            queueA.add(t);
        }
    }
    
    public T pop() {
        
        T result = null;
        //Copy the previous items from the non-empty queue to the empty one except the last item,
        //and return last item.
        if (queueA.isEmpty() && queueB.isEmpty()) {
            
        } else if (queueA.isEmpty()) {
            int count = queueB.size();
            for (int i = 0; i < count - 1; i++) {
                queueA.add(queueB.remove());
            }
            result = queueB.remove();
        } else {
            int count = queueA.size();
            for (int i = 0; i < count - 1; i++) {
                queueB.add(queueA.remove());
            }
            result = queueA.remove();
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        StackWithTwoQueues<Integer> stack = new StackWithTwoQueues<>();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());//2
        stack.push(3);
        System.out.println(stack.pop());//3
        System.out.println(stack.pop());//1
        System.out.println(stack.pop());//null
    }
}
