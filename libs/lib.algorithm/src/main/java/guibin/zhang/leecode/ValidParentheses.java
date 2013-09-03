package guibin.zhang.leecode;

import java.util.Stack;

/**
 * 
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
 * determine if the input string is valid.
 * 
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * 
 * Run Status: Accepted!
 * Program Runtime: 556 milli secs
 * Progress: 61/61 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ValidParentheses {
    
    public boolean isValid(String s) {

        boolean flag =false;
        if(s == null) {
            return flag;
        }
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ')' || c == ']' || c == '}') {
                if(stack.isEmpty()) {
                    return false;
                } else {
                    char left = stack.pop();
                    if(left == '(' && c == ')' || left == '[' && c == ']' || left == '{' && c == '}') {
                        //continue;
                    } else {
                        return false;
                    }
                }
            } else {
                stack.push(c);
            }
        }
        
        if(!stack.isEmpty()) {
            return false;
        }
        return true;
    }
}
