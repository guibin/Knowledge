package guibin.zhang.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given a string, S, and a list of words, L, that are all of the same length. 
 * Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once 
 * and without any intervening characters.
 * 
 * For example, given:
 * S: "barfoothefoobarman"
 * L: ["foo", "bar"]
 * 
 * You should return the indices: [0,9].
 * (order does not matter).
 * 
 * Run Status: Accepted!
 * Program Runtime: 1376 milli secs
 * Progress: 160/160 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SubstringWithConcatenationOfAllWords {

    public ArrayList<Integer> findSubstring(String S, String[] L) {
        // Start typing your Java solution below
        // DO NOT write main() function
        Map<String, Integer> smap = new HashMap<String, Integer>();
        Map<String, Integer> currMap = new HashMap<String, Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        int wordSize = L[0].length();
        int wordCount = L.length;
        int concatLen = wordSize * wordCount;
        for (String str : L) {
            if (smap.containsKey(str)) {
                smap.put(str, smap.get(str) + 1);
            } else {
                smap.put(str, 1);
            }
        }

        int j = 0;
        //i is the start position
        for (int i = 0; i <= S.length() - concatLen; i++) {//Note here is <= instead of <
            currMap.clear();
            for (j = 0; j < wordCount; j++) {
                String str = S.substring(i + j * wordSize, i + (j + 1) * wordSize);
                if (smap.containsKey(str)) {
                    if (currMap.containsKey(str) && currMap.get(str) < smap.get(str)) {
                        currMap.put(str, currMap.get(str) + 1);
                    } else if (!currMap.containsKey(str)) {
                        currMap.put(str, 1);
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            if (j == wordCount) {
                res.add(i);
            }
        }
        return res;
    }
}
