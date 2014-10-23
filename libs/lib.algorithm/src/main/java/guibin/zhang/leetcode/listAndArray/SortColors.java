package guibin.zhang.leetcode.listAndArray;

import java.util.Arrays;

/**
 * Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * 
 * Note:
 * You are not suppose to use the library's sort function for this problem.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class SortColors {
    
    public void sortColors(int[] A) {
        
        int i = 0, lt = 0, gt = A.length - 1;
        //Here is i <= gt
        while (i <= gt) {
            if (A[i] == 0) {
                swap(A, lt, i);
                lt++;
                i++;
            } else if (A[i] == 2) {
                swap(A, gt, i);
                gt --;
            } else {
                i ++;
            }
        }
    }
    
    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    public static void main(String[] args) {
        SortColors sc = new SortColors();
        int[] A = {1, 0};
        sc.sortColors(A);
        Arrays.stream(A).forEach(e -> System.out.print(e));
        System.out.println();
    }
}
