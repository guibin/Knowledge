package guibin.zhang.leecode.permutationAndCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int len = numbers.length;
        int start = 0;
        int end = len - 1;
        int[] result = new int[2];
        
        //number -> List<index>
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < len; i++) {
            if (map.containsKey(numbers[i])) {
                List<Integer> list = map.get(numbers[i]);
                list.add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(numbers[i], list);
            }
        }
        
        for (int i = 0; i < len; i++) {
            int dest = target - numbers[i];
            if (map.containsKey(dest) && dest != numbers[i]) {
                result[0] = map.get(numbers[i]).get(0) + 1;
                result[1] = map.get(dest).get(0) + 1;
                Arrays.sort(result);
                return result;
            }  else if (map.containsKey(dest) && dest == numbers[i] && map.get(dest).size() > 1) {
                result[0] = map.get(dest).get(0) + 1;
                result[1] = map.get(dest).get(1) + 1;
                Arrays.sort(result);
                return result;
            }
        }
        return result;
    }
}
