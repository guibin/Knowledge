package guibin.zhang.leetcode.stack;

import java.util.Arrays;

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
    
    public static void main(String[] args) {
        int[] a = {7, 5, 6, 3, 4, 1, 2, 9, 11};
        guibin.zhang.leetcode.listAndArray.NextBiggerNumber.replaceWithBiggerNumberAtRight(a);
        Arrays.stream(a).forEach((i) -> System.out.print(i + ","));
        System.out.println();
        
        int[] b = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        guibin.zhang.leetcode.listAndArray.NextBiggerNumber.replaceWithBiggerNumberAtRight(b);
        Arrays.stream(b).forEach((i) -> System.out.print(i + ","));
        System.out.println();
    }
}
