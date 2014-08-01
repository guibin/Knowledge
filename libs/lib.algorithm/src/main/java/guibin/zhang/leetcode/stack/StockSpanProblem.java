package guibin.zhang.leetcode.stack;

import java.util.Arrays;

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
    
    //Please check code guibin.zhang.leetcode.listAndArray.StockSpanProblem
    
    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] S = guibin.zhang.leetcode.listAndArray.StockSpanProblem.calculateSpan(prices);
        Arrays.stream(S).forEach(i -> System.out.print(i + ","));
        System.out.println();
    }
}
