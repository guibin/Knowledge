package guibin.zhang.leetcode.permutationAndCombination;

/**
 * 
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * 
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 * 
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * 
 * Note: Given n will be between 1 and 9 inclusive.
 * 
 * http://tech-wonderland.net/blog/leetcode-permutation-sequence.html
 * 
 * (1) DFS and enumerate all the sequences by the increasing order, 
 *     when enumerate the k-th sequence, return it. This would be TLE for large test case
 * (2) Actually there is O(n) solution. 
 *     From pure mathematical view, for example we want to find k-th sequence of length n, 
 *     basically let a[1]…a[n] denote the k-th sequence, think about this: 
 *     except a[1] there are totally (n-1)! different permutations for a[2]…a[n], 
 *     then a[1] = k / (n-1)! and we need to find k % (n-1)! -th element is the a[2]…[an] sequence, 
 *     this is recursively the same problem. 
 *     Just be careful when dealing with some border cases like k is exactly (n-1)! or 3 * (n-1)! sort of these cases.
 * 
 * http://discuss.leetcode.com/questions/237/permutation-sequence
 * 
 * Here is the idea. 
 * For each k, divide the number of previous permutations into some factorials, 
 * for each factorial, count how many and save this info into an index array. 
 * Based on this array, we can choose which digit at each position. 
 * e.g, for n=4, k=20, it has 19 permutations before, 
 * 19 = 3*3! + 0*2! + 1*1! + 0, so the index[] would be 0,1,0,3 (reversing order), 
 * which can be interpreted as the order of digit in an ordered array, 
 * e.g, 3 means arr[3] in [1,2,3,4] which is 4.
 * 
 * http://fisherlei.blogspot.com/2013/04/leetcode-permutation-sequence-solution.html
 * 
 * 假设有n个元素，第K个permutation是
 * a1, a2, a3, .....   ..., an
 * 那么a1是哪一个数字呢？
 * 
 * 那么这里，我们把a1去掉，那么剩下的permutation为
 * a2, a3, .... .... an, 共计n-1个元素。 n-1个元素共有(n-1)!组排列，那么这里就可以知道
 * 
 * 设变量K1 = K
 * a1 = K1 / (n-1)!
 * 
 * 同理，a2的值可以推导为
 * a2 = K2 / (n-2)!
 * K2 = K1 % (n-1)!
 *  .......
 * a(n-1) = K(n-1) / 1!
 * K(n-1) = K(n-2) /2!
 * 
 * an = K(n-1)
 * 
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int[] factorials = fullFactorial(n);
        int permutation = factorials[n];
        if (k > permutation) {
            return null;
        }
        
        int[] result = new int[n];
        int start = 1;
        int end = n;
        int mid = start + (end - start)/2;
        while (start <= end) {
            if (factorials[mid] < k) {
                start = mid + 1;
            } else if (factorials[mid] > k) {
                end = mid - 1;
            } else {
                break;
            }
            mid = start + (end - start)/2;
        }
        
        int f = 0;
        if (factorials[mid] > k) {
            f = mid - 1;
        } else {
            f = mid;
        }
        result[0] = f + 1;
        
        return null;
    }
    
    public int[] fullFactorial(int n) {
        int[] result = new int[n + 1];
        result[0] = 1;
        result[1] = 1;
        for (int i = 2; i <= n; i++) {
            result[i] = result[i - 1] * i;
        }
        return result;
    }
}
