package guibin.zhang.leetcode.permutationAndCombination;

import java.util.Arrays;

/**
 *
 * Implement next permutation, which rearranges numbers into the lexicographically 
 * next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the 
 * lowest possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place, do not allocate extra memory.
 * 
 * Here are some examples. Inputs are in the left-hand column and its 
 * corresponding outputs are in the right-hand column.
 * 
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 1,3,2 → 2,1,3
 * 
 * http://fisherlei.blogspot.com/2012/12/leetcode-next-permutation.html
 * 1. From right to left, find the first digit(call it Partition Number), which violate the increase trend.
 * 2. From right to left, find the first digit which is larger than Partition Number, let's call it Change Number.
 * 3. Swap the Partition Number and Change Number.
 * 4. Reverse all the digits on the right of partition index.
 * 
 * eg. 
 * 6, 8, 7, 4, 3, 2 -> 7, 2, 3, 4, 6, 8:
 * 1. find pivot 6.
 * 2. find change number 7.
 * 3. swap 6, 7 => 7, 8, 6, 4, 3, 2
 * 4. Reverse all the elements after pivot => 7, 2, 3, 4, 6, 8
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class NextPermutation {
    
    public void nextPermutation(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int n = num.length;
        int pivot = n - 1;
        // find the top index of the last desc sub array .
         while(pivot > 0 && num[pivot] <= num[pivot - 1]) {
            pivot --;
        }
        // find the min value in sub array which is bigger than num[pivot - 1]
        // and swap them.
        if (pivot > 0) {
            for (int i = n - 1; i >= pivot; i--) {
                if (num[i] > num[pivot - 1]) {
                    swap(num, i, pivot - 1);
                    break;
                }
            }
        }

        // reverse the sub array.
        reverse(num, pivot, n - 1);
    }

    private void reverse(int[] n, int a, int b) {
        while (a < b) {
            swap(n, a++, b--);
        }
    }

    private void swap(int[] n, int a, int b) {
        int tmp;
        tmp = n[b];
        n[b] = n[a];
        n[a] = tmp;
    }
    
    public static void main(String[] args) {

        NextPermutation np = new NextPermutation();
        int[] num = {1, 2, 3};
        Arrays.stream(num).forEach(i -> System.out.print(i + ","));
        System.out.print("=> ");
        np.nextPermutation(num);
        Arrays.stream(num).forEach(i -> System.out.print(i + ","));
        System.out.println();
        
        Arrays.stream(num).forEach(i -> System.out.print(i + ","));
        System.out.print("=> ");
        np.nextPermutation(num);
        Arrays.stream(num).forEach(i -> System.out.print(i + ","));
        System.out.println();
    }
}
