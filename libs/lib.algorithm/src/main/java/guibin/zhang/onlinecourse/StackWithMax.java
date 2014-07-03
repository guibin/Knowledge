package guibin.zhang.onlinecourse;

import java.util.Stack;

/**
 * Stack with max. 
 * Create a data structure that efficiently supports the stack operations (push and pop) 
 * and also a return-the-maximum operation. Assume the elements are reals numbers 
 * so that you can compare them.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 * @param <T>
 */
public class StackWithMax<T extends Comparable> {
    
    private Stack<T> data = new Stack<>();
    private Stack<T> maximums = new Stack<>();
    
    public T pop() {
        maximums.pop();
        return data.pop();
    }
    
    public T pop2() {
        T result = data.pop();
        if (result == maximums.peek()) {
            maximums.pop();
        }
        return result;
    }
    
    public void push(T v) {
        data.push(v);
        
        if (maximums.isEmpty()) {
            maximums.push(v);
        } else {
            if (v.compareTo(maximums.peek()) > 0) {
                maximums.push(v);
            } else {
                maximums.push(maximums.peek());
            }
        }
    }
    
    public void push2(T v) {
        data.push(v);
        if (maximums.isEmpty()) {
            maximums.push(v);
        } else {
            if (v.compareTo(maximums.peek()) >= 0) {
                maximums.push(v);
            }
        }
    }
    
    public T max() {
        System.out.println("Max: " + maximums.peek());
        return maximums.peek();
    }
    
    public static void main(String[] args) {
        StackWithMax<Integer> s = new StackWithMax<>();
        s.push(6);
        s.push(9);
        s.push(8);
        s.max();  //9
        s.push(11);
        s.push(5);
        s.max();  //11
        s.pop();
        s.max();  //11
        s.pop();
        s.max();  //9
        s.pop();
        s.pop();
        s.max();  //6
        s.pop();
        System.out.println("-----");
        s.push2(6);
        s.push2(9);
        s.push2(8);
        s.max();  //9
        s.push2(11);
        s.push2(5);
        s.max();  //11
        s.pop2();
        s.max();  //11
        s.pop2();
        s.max();  //9
        s.pop2();
        s.pop2();
        s.max();  //6
        s.pop2();
    }
}
