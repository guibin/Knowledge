package guibin.zhang.leecode.stack;

import java.util.Stack;

/**
 * 
 * Given a string containing just the characters '(' and ')', 
 * find the length of the longest valid (well-formed) parentheses substring.
 * 
 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
 * 
 * Another example is ")()())", where the longest valid parentheses substring is "()()", 
 * which has length = 4.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class LongestValidParentheses {
    
    public int longestValidParentheses(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        int last = -1;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                    if(stack.isEmpty()) {
                        max = Math.max(max, i - last);
                    } else {
                        max = Math.max(max, i - stack.peek());
                    }
                } else {
                    //Save the no match position.
                    last = i;
                }
            }
        }
        return max;
    }
}
