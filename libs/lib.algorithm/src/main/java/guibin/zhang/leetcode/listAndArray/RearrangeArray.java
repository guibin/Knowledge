package guibin.zhang.leetcode.listAndArray;

import java.util.Arrays;

/**
 * 
 * Rearrange positive and negative numbers in O(n) time and O(1) extra space.
 * 
 * An array contains both positive and negative numbers in random order. 
 * Rearrange the array elements so that positive and negative numbers are placed alternatively. 
 * Number of positive and negative numbers need not be equal. 
 * If there are more positive numbers they appear at the end of the array. 
 * If there are more negative numbers, they too appear in the end of the array.
 * 
 * For example, if the input array is [-1, 2, -3, 4, 5, 6, -7, 8, 9], 
 * then the output should be [9, -7, 8, -3, 5, -1, 2, 4, 6]
 * 
 * Question link: http://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers-publish/
 * 
 * Thought:
 * The solution is to first separate positive and negative numbers using partition process of QuickSort. 
 * In the partition process, consider 0 as value of pivot element 
 * so that all negative numbers are placed before positive numbers. 
 * Once negative and positive numbers are separated, 
 * we start from the first negative number and first positive number, 
 * and swap every alternate negative number with next positive number.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class RearrangeArray {
    
    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    
    public static int partition(int[] a) {
        
        int i = 0;
        int j = a.length - 1;
        while(true) {
            while (i < a.length && a[i] < 0) {
                i++;
            }
            while (j > 0 && a[j] > 0) {
                j--;
            }
            
            if (i >= j) break;
            swap(a, i, j);
        }
        System.out.println(i + "," + j);
        return i;
    }
    
    public static void rearrange(int[] a) {
        
        int nIdx = 1;
        int pIdx = partition(a);
        
        while (nIdx < a.length && pIdx < a.length && nIdx < pIdx && a[nIdx] < 0) {
            swap(a, nIdx, pIdx);
            nIdx += 2;//Only the odd index should be swapped
            pIdx += 1;
        }
        
    }
    
    public static void main(String[] args) {
        int[] a = {-1, 2, -3, 4, 5, -6, -7, -8, -9};
        int idx = partition(a);
        System.out.println("After partition, idx = " + idx);
        Arrays.stream(a).forEach(i -> System.out.print(i + ","));
        System.out.println();
        
        System.out.println("After rearrange:");
        rearrange(a);
        Arrays.stream(a).forEach(i -> System.out.print(i + ","));
        System.out.println();
    }
}
