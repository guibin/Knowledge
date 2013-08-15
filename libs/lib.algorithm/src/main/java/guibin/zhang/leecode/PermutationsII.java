package guibin.zhang.leecode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * 
 * For example,
 * [1,1,2] have the following unique permutations:
 * [1,1,2], [1,2,1], and [2,1,1].
 * 
 * http://tech-wonderland.net/blog/leetcode-permutations-i-and-ii.html
 * http://blog.csdn.net/morewindows/article/details/7370155
 * 
 * 
 * Facing this kind of problem, just consider this is a similar one to the previous one, 
 * but need some modifications. In this problem, what we need it to cut some of the subtrees.  e.g. 122
 *                  122
 *          /         |       \
 *        122        212       X  (here because 2=2, we don't need to swap again)
 *      /     \     /    \
 *     122    X    212 221 

So, if there exist same element after current swap, there there is no need to swap again.
Details see code.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class PermutationsII {

    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        Integer[] n = new Integer[num.length];
        for (int i = 0; i < num.length; i++) {
            n[i] = num[i];
        }
        perm(n, 0, num.length - 1, result);
        return result;
    }

    
    public boolean isSwap(int start, int i, Integer[] num) {
        for (int j = start; j < i; j++) {
            if (num[j] == num[i]) {
                return false;
            }
        }
        return true;
    }

    public void perm(Integer[] num, int start, int end, ArrayList<ArrayList<Integer>> result) {
        if (start == end) {
            result.add(new ArrayList<Integer>(Arrays.asList(num)));
        } else {
            for (int i = start; i <= end; i++) {
                //Before swaping, judge is there any identical emelent with element i in [start, i)
                if (!isSwap(start, i, num)) {
                    continue;
                }
                //Swap the first element with the rest of the element, including itself.
                swap(num, start, i);
                perm(num, start + 1, end, result);
                swap(num, start, i);
            }
        }
    }

    public void swap(Integer[] num, int i, int j) {
        Integer temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
}
