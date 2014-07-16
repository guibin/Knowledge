package guibin.zhang.leetcode.listAndArray;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class FindArrayPairsSum {
    
    /**
     * Find the pair in an array whose sum with complexity O(n). 
     * This assumes the array passed is sorted array and there are no duplicates in the array.
     * 
     * http://tekmarathon.com/2013/03/23/array-algos-find-all-pairs-in-an-array-that-sum-up-to-particular-number-with-best-complexity/
     * 
     * @param a sorted array in ascending order.
     * @param k target to sum
     */
    public void findPairsInSortedArray(int[] a, int k) {
        
        int i = 0, j = a.length - 1;
        int sum = 0;
        
        StringBuilder result = new StringBuilder();
        while (i < j) {
            sum = a[i] + a[j];
            if (sum < k) {
                i++;
            } else if (sum > k) {
                j--;
            } else { //Here we got the answer
                result.append(a[i] + "," + a[j] + "; ");
                i++;
                j--;
            }
        }
        System.out.println("Found pairs: " + result.toString());
    }
    
    /**
     * 
     * @param a The array is not sorted, and may contains duplicated elements.
     * @param k 
     */
    public void findPairsInArray(int[] a, int k) {
        
        StringBuilder result = new StringBuilder();
        
        //Key: the complement of a[i], Value: the times it occurs
        Map<Integer, Integer> m = new HashMap<>();
        for (int i : a) {
            if (!m.containsKey(k - i)) {
                m.put(k - i, 1);
            } else {
                m.put(k - i, m.get(k - i) + 1);
            }
        }
        
        for (int i : a) {
            if (m.containsKey(i) && m.get(i) > 0 && m.get(k - i) > 0) {
                if (k - i != i) {
                    m.put(i, m.get(i) - 1);
                    m.put(k - i, m.get(k - i) - 1);
                    result.append(i + "," + (k - i) + "; ");
                } else { //This is to avoid the case of: 8,8; target = 16, but only one 8 in array.
                    if (m.get(i) > 1) {
                        m.put(i, m.get(i) - 1);
                        m.put(k - i, m.get(k - i) - 1);
                        result.append(i + "," + (k - i) + "; ");
                    }
                }
            }
        }
        System.out.println("Found pairs: " + result.toString());
    }
    
    public static void main(String[] args) {
        int[] a = {1, 3, 4, 5, 7, 8, 10, 12, 13, 15, 18};
        FindArrayPairsSum f = new FindArrayPairsSum();
        f.findPairsInSortedArray(a, 16);
        
        int[] b = {1, 3, 4, 5, 7, 8, 10, 12, 13, 15, 18, 3, 12, 11};
        f.findPairsInArray(b, 16);
    }
}
