package guibin.zhang.leetcode.permutationAndCombination;

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
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class NextPermutation {
    
    public void nextPermutation(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int n = num.length;
        int descI = n - 1;
        // find the top index of the last desc sub array .
         while(descI > 0 && num[descI - 1] >= num[descI]) {
            descI --;
        }
        // find the min value in sub array which is bigger than num[descI - 1]
        // and swap them.
        if (descI > 0) {
            for (int i = n - 1; i >= descI; i--) {
                if (num[i] > num[descI - 1]) {
                    swap(num, i, descI - 1);
                    break;
                }
            }
        }

        // reverse the sub array.
        reverse(num, descI, n - 1);
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
}
