package guibin.zhang.leetcode.listAndArray;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * How to Find Common Elements of Two UnSorted Array?
 * 
 * 1. Iterate the small array to create the HashSet.
 * 2. Iterate the big array to search in the HashSet.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class CommonElementsInTwoArray {
    
    public List<Integer> findCommentElements(int[] a, int[] b) {
        
        //Make sure a is small, and b is large
        if (a.length > b.length) {
            return findCommentElements(b, a);
        }
        
        List<Integer> result = new ArrayList<>();
        Set<Integer> s = new HashSet<>();
        //Add the small array to the set
        for (int i : a) {
            s.add(i);
        }
        
        //Leverage set to judge the common elements
        for (int j : b) {
            //HashSet.contains is O(1).
            if (s.contains(j)) {
                result.add(j);
            }
        }
        
        return result;
    }
}
