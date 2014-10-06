package guibin.zhang.leetcode.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, 
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * 
 * Return all such possible sentences.
 * 
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * 
 * A solution is ["cats and dog", "cat sand dog"]
 * 
 * Thoughts: Since we need return all possible results, so we must use DFS. 
 * However, if we only use DFS, we will calculate too many same cases. 
 * Therefore, we combine DP and DFS.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class WordBreakII {
    
    public List<String> wordBreak(String s, Set<String> dict) {
        
        List<String> result = new ArrayList<>();
        String temp = "";
        boolean[] possible = new boolean[s.length() + 1];
        possible[s.length()] = true; 
        //wordBreak I to compute the possible array
        for (int start = s.length() - 1; start >= 0; start--) {
            for (int end = s.length(); end > start; end--) {
                possible[start] = possible[end] && dict.contains(s.substring(start, end));
                if (possible[start]) break;
            }
        }
        
        search(s, dict, result, temp, 0, possible);
        
        return result;
    }
    
    public void search(String s, Set<String> dict, List<String> result, String branch, int idx, boolean[] possible) {
        
        if (idx == s.length()) {
            result.add(branch.trim());
            return;
        }
        
        for (int i = idx; i < s.length(); i ++) {
            if (possible[i + 1] && dict.contains(s.substring(idx, i + 1))) {
                String tmp = branch + s.substring(idx, i + 1) + " ";
                search(s, dict, result, tmp, i + 1, possible);
            }
        }
    }
}
