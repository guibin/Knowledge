package guibin.zhang.datastructure.misc;

import java.util.Stack;

/**
 * Write a program to sort a stack in ascending order.
 * You should not make any assumptions about how the stack is implemented.
 * The following are the only functions that should be used to write this program: push | pop | peek | isEmpty
 * 
 * @author guibin
 */
public class SortByStack {
    public Stack<Integer> sort(Stack<Integer> s) {
        
        Stack<Integer> result = new Stack<>();
        Stack<Integer> s2 = new Stack<>();//temporary saving
        
        if(s.isEmpty()) {
            return result;
        }
        
        result.push(s.pop());
        while (!s.isEmpty()) {
            int temp = s.pop();
            while (!result.isEmpty() && temp > result.peek()) {
                s2.push(result.pop());
            }
            result.push(temp);

            while (!s2.isEmpty()) {
                result.push(s2.pop());
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        
        SortByStack sbs = new SortByStack();
        Stack<Integer> s = new Stack<>();
        s.push(2);
        s.push(200);
        s.push(134);
        s.push(66);
        s.push(18);
        Stack<Integer> res = sbs.sort(s);
        while(!res.isEmpty()) {
            System.out.println(res.pop());
        }
    }
}
