package guibin.zhang.leecode.permutationAndCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Given an array of strings, return all groups of strings that are anagrams.
 * 
 * Note: All inputs will be in lower-case.
 * 
 * For example:
 * Input:　　["tea","and","ate","eat","den"]
 * Output:   ["tea","ate","eat"]
 * 
 * http://www.cnblogs.com/AnnieKim/archive/2013/04/25/3041982.html
 * 
 * 
 * Run Status: Accepted!
 * Program Runtime: 1068 milli secs
 * Progress: 100/100 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class Anagrams {
    public ArrayList<String> anagrams(String[] strs) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        ArrayList<String> res = new ArrayList<String>();
        
        for (int i = 0; i < strs.length; i++) {
            char[] cs = strs[i].toCharArray();
            //Anagrams(回文构词法): 是由颠倒字母顺序组成的单词, 因此回文的文字排序之后一定是相同的
            Arrays.sort(cs);
            String tmp = new String(cs);
            
            if (map.containsKey(tmp)) {
                if (map.get(tmp) != -1) {
                    res.add(strs[map.get(tmp)]);
                    map.put(tmp, -1);
                }
                res.add(strs[i]);
                
            } else {
                map.put(tmp, i);
            }
        }
        return res;
    }
}
