package guibin.zhang.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.
 * 
 * For example, given:
 * S: "barfoothefoobarman"
 * L: ["foo", "bar"]
 * 
 * You should return the indices: [0,9].
 * (order does not matter).
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class SubstringwithConcatenationofAllWords {
    
    public List<Integer> findSubstring(String S, String[] L) {
        
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> dict = new HashMap<>();
        Map<String, Integer> curDict = new HashMap<>();
        int n = L[0].length();
        for(String s : L) {
            if (!dict.containsKey(s)) {
                dict.put(s, 1);
            } else {
                dict.put(s, dict.get(s)+1);
            }
        }
        
        for(int i = 0; i <= S.length() - L.length * n; i++) {
            curDict.clear();
            int j = 0;
            for (j = 0; j < L.length; j++) {
                int k = i + j * n;
                String temp = S.substring(k, k + n);
                
                if (!dict.containsKey(temp)) break;
                
                if(!curDict.containsKey(temp)){
                    curDict.put(temp, 1);
                } else {
                    curDict.put(temp, curDict.get(temp) + 1);
                }
                if(curDict.get(temp) > dict.get(temp)) break;
            }
            
            if(j == L.length) result.add(i);
        }
        return result;
    }
}
