package guibin.zhang.leecode.stack;

import java.util.Stack;

/**
 * 
 * Given an absolute path for a file (Unix-style), simplify it.
 * 
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * 
 * Corner Cases:
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 * 
 * Run Status: Accepted!
 * Program Runtime: 652 milli secs
 * Progress: 243/243 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SimplifyPath {
    
    public String simplifyPath(String path) {

        StringBuilder sb = new StringBuilder("/");

        Stack<String> stack = new Stack<String>();
        String[] arr = path.split("/");
        for (String s : arr) {
            if ("..".equals(s)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if ("".equals(s) || ".".equals(s)) {
                //Do nothing, just discard the segment.
            } else if (s.length() > 0) {
                stack.push(s);
            }
        }

        if (stack.isEmpty()) {
            return sb.toString();
        }
        while (!stack.isEmpty()) {
            sb.insert(1, stack.pop() + "/");
        }
        if (sb.length() > 1) {
            sb.delete(sb.length() - 1, sb.length());
        }

        return sb.toString();
    }
   
}
