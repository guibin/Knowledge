package guibin.zhang.leetcode.listAndArray;

import java.util.Arrays;
import java.util.Stack;

/**
 * The stock span problem is a financial problem where 
 * we have a series of n daily price quotes for a stock 
 * and we need to calculate span of stock’s price for all n days. 
 * 
 * The span Si of the stock’s price on a given day i is defined as 
 * the maximum number of consecutive days just **before** the given day, 
 * for which the price of the stock on the current day is less than or equal to its price on the given day.
 * 
 * For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, 
 * then the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}
 * 
 * Question link: http://www.geeksforgeeks.org/the-stock-span-problem/
 * 
 * Thought of A Linear Time Complexity Method:
 * 
 * we use a stack as an abstract data type to store the days i, h(i), h(h(i)) and so on. 
 * When we go from day i-1 to i, we pop the days when the price of the stock was 
 * less than or equal to price[i] and then push the value of day i back into the stack.
 * 
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class StockSpanProblem {
    
    /**
     * 
     * @param price price array
     * @return span array for each price
     */
    public static int[] calculateSpan(int[] price) {
        
        int[] S = new int[price.length];
        Stack<Integer> stack = new Stack<>();
        // Push the first index 0.
        stack.push(0);
        // Span value of first day is always 1
        S[0] = 1;
        // Calculate the span value for the rest of the elements
        for (int i = 1; i < price.length; i++) {
            // Pop elements from stack while stack is not empty and top of
            // stack is smaller than price[i]
            while(!stack.isEmpty() && price[stack.peek()] < price[i]) {
                stack.pop();
            }
            // If stack is empty, that means element price[i] is bigger than any one before i.
            // So S[i] = i + 1.
            S[i] = stack.isEmpty() ? i + 1 : (i - stack.peek());
            
            stack.push(i);
        }
        return S;
    }
    
    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] S = calculateSpan(prices);
        Arrays.stream(S).forEach(i -> System.out.print(i + ","));
        System.out.println();
    }
}
