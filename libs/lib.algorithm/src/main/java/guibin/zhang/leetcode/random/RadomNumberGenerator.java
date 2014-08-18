package guibin.zhang.leetcode.random;

/**
 * Given n numbers, each with some frequency of occurrence. 
 * Return a random number with probability proportional to its frequency of occurrence.
 * 
 * Example:
 * 
 * Let following be the given numbers.
 *   arr[] = {10, 30, 20, 40}  
 * 
 * Let following be the frequencies of given numbers.
 *   freq[] = {1, 6, 2, 1}  
 * 
 * The output should be
 *   10 with probability 1/10
 *   30 with probability 6/10
 *   20 with probability 2/10
 *   40 with probability 1/10 
 * 
 * Question link: http://www.geeksforgeeks.org/random-number-generator-in-arbitrary-probability-distribution-fashion/
 * 
 * Method 1:
 * Huge memory consumption method:
 * 
 * One simple method is to take an auxiliary array (say aux[]) and 
 * duplicate the numbers according to their frequency of occurrence. 
 * Generate a random number(say r) between 0 to Sum-1(including both), 
 * where Sum represents summation of frequency array (freq[] in above example). 
 * Return the random number aux[r].
 * 
 * when frequency of occurrence is high. If the input is 997, 8761 and 1, this method is clearly not efficient.
 * 
 * Method 2:
 * O(n) extra space where n is number of elements in input arrays.
 * 
 * How can we reduce the memory consumption? Following is detailed algorithm that 
 * uses O(n) extra space where n is number of elements in input arrays.
 * 
 * 1.	Take an auxiliary array (say prefix[]) of size n.
 * 2.	Populate it with prefix sum, such that prefix[i] represents sum of numbers from 0 to i.
 * 3.	Generate a random number(say r) between 1 to Sum(including both), where Sum represents summation of input frequency array.
 * 4.	Find index of Ceil of random number generated in step #3 in the prefix array. Let the index be indexc.
 * 5.	Return the random number arr[indexc], where arr[] contains the input n numbers.
 * 
 *   Before we go to the implementation part, let us have quick look at the algorithm with an example:
 *       arr[]: {10, 20, 30}
 *       freq[]: {2, 3, 1}
 *       Prefix[]: {2, 5, 6}
 *   Since last entry in prefix is 6, all possible values of r are [1, 2, 3, 4, 5, 6]
 *          1: Ceil is 2. Random number generated is 10.
 *       2: Ceil is 2. Random number generated is 10.
 *          3: Ceil is 5. Random number generated is 20.
 *          4: Ceil is 5. Random number generated is 20.
 *          5: Ceil is 5. Random number generated is 20.
 *          6. Ceil is 6. Random number generated is 30.
 * 
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class RadomNumberGenerator {
    
    /**
     * 
     * @param a
     * @param r the random number
     * @param lo
     * @param hi
     * @return Find the index that a[index] >= r
     */
    public static int findCeil(int[] a, int r, int lo, int hi) {
        int mid;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (a[mid] < r) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return a[lo] >= r ? lo : -1;
    }
    
    public static int customizedRandom(int[] a, int[] freq) {
        //Create and fill the prefix array
        int[] prefix = new int[a.length];
        prefix[0] = freq[0];
        for (int i = 1; i < a.length; i++) {
            prefix[i] = freq[i - 1] + freq[i];
        }
        
        //Math.random() is [0, 1) => r [prefix[0], prefix[len - 1])
        int r = (int)(Math.random() * prefix[a.length - 1]) + prefix[0];
        //findCeil convert the random value to index of original array.
        int indexCeil = findCeil(prefix, r, 0, a.length - 1);
        return a[indexCeil];   
    }
    
    public static void main(String[] args) {
        int[] a = {2, 5, 6, 9};
        int[] freq = {1, 3, 5, 1};
        for (int i = 0; i < 10; i ++) {
            System.out.println(customizedRandom(a, freq));
        }
//        System.out.println(a[findCeil(a, 4, 0, a.length - 1)]);
    }
    
}
