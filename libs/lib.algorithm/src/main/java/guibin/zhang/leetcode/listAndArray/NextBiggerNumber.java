package guibin.zhang.leetcode.listAndArray;

import java.util.Arrays;
import java.util.Stack;

/**
 * 
 * Replace element of an Array with nearest bigger number at right side of the Array in O(n).
 * 
 * Question link: http://www.careercup.com/question?id=6497025214382080
 * 
 * For example if the input Array is 
 * 7, 5, 6, 3, 4, 1, 2, 9, 11 
 * output array should be 
 * 9, 6, 9, 4, 9, 2, 9, 11, 11
 * 
 * Thought:
 * 1. Use a stack to help to track the next bigger number at right.
 * 2. Traverse **from right to left**.
 * 3. While traversing, if find the bigger one, just replace it, and push the current element to stack.
 * 4. Otherwise pop the stack until find the bigger element, then push the current element to stack.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class NextBiggerNumber {
   
    public static void replaceWithBiggerNumberAtRight (int[] a) {
        
        Stack<Integer> stack = new Stack<>();
        
        //Push the last element into stack
        stack.push(a[a.length - 1]);
        
        for (int i = a.length - 2; i >= 0; i--) {
            int curr = a[i];
            
            if (curr <= stack.peek()) {//Get the nearest bigger number at right
                a[i] = stack.peek();//Replace the element
            } else {
                //Pop until find the nearest bigger one
                while (!stack.isEmpty() && curr > stack.peek()) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    a[i] = stack.peek();
                }
            }
            
            stack.push(curr);
        }
    }
    
    public static void main(String[] args) {
        int[] a = {7, 5, 6, 3, 4, 1, 2, 9, 11};
        replaceWithBiggerNumberAtRight(a);
        Arrays.stream(a).forEach((i) -> System.out.print(i + ","));
        System.out.println();
        
        int[] b = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        replaceWithBiggerNumberAtRight(b);
        Arrays.stream(b).forEach((i) -> System.out.print(i + ","));
        System.out.println();
    }
}
