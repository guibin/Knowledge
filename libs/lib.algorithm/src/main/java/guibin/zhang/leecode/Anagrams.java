package guibin.zhang.leecode;

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
