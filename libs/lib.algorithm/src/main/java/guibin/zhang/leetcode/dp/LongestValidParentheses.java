package guibin.zhang.leetcode.dp;

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
                    //If found the matched, pop it.
                    stack.pop();
                    if(stack.isEmpty()) {
                        //If empty, check the distance from the first '(', which is last.
                        max = Math.max(max, i - last);
                    } else {
                        //If not empty, use the peek to find the index of the nearest '(' - 1;
                        max = Math.max(max, i - stack.peek());
                    }
                } else {
                    //Not matched, refresh the last, save the no match position.
                    last = i;
                }
            }
        }
        return max;
    }
    
    /**
     * 
     * 这道题可以用一维动态规划逆向求解。假设输入括号表达式为String s，维护一个长度为s.length的一维数组dp[]，数组元素初始化为0。
     * dp[i]表示从s[i]到s[s.length - 1]最长的有效匹配括号子串长度。则存在如下关系：
     * 1. dp[s.length - 1] = 0;
     * 2. 从 s.length - 2 -> 0 逆向求dp[], 并记录其最大值。
     *    若s[i] == '('，则在s中从i开始到s.length - 1计算s[i]的值。
     *    这个计算分为两步，通过dp[i + 1]进行的（注意dp[i + 1]已经在上一步求解）：
     *    1.1 在s中寻找从i + 1开始的有效括号匹配子串长度，即dp[i + 1]，
     *        跳过这段有效的括号子串，查看下一个字符，其下标为j = i + 1 + dp[i + 1]。
     *        若j没有越界，并且s[j] == ‘)’，则s[i ... j]为有效括号匹配，dp[i] =dp[i + 1] + 2。
     *    1.2 在求得了s[i ... j]的有效匹配长度之后，若j + 1没有越界，则dp[i]的值还要加上从j + 1开始的最长有效匹配，即dp[j + 1]。
     * 
     * @param s
     * @return 
     */
    public int longestValidParentheses_dp(String s) {
        
        int n = s.length();
        int[] dp = new int[n];
        java.util.Arrays.fill(dp, 0);
        int max = 0;
        
        dp[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                int j = i + 1 + dp[i + 1];
                if (j < n && s.charAt(j) == ')') {
                    dp[i] = dp[i + 1] + 2;
                    int k = 0;
                    if (j + 1 < n) {
                        k = dp[j + 1];  
                    }
                    dp[i] += k;
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
}
