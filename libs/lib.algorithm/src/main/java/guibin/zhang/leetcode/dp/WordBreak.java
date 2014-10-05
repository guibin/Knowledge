package guibin.zhang.leetcode.dp;

import java.util.Set;

/**
 *
 * Given a string s and a dictionary of words dict, 
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * 
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * 
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 * 316 ms
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class WordBreak {
    
    /**
     * http://www.programcreek.com/2012/12/leetcode-solution-word-break/
     * 
     * Define an array t[] such that t[i]==true => 0~(i-1) can be segmented using dictionary
     * Initial state t[0] == true
     * 
     * INPUT: "programcreek", ["programcree","program","creek"]. 
     * We should get all possible matches, not stop at "programcree".
     * 
     * @param s
     * @param dict
     * @return 
     */
    public boolean wordBreak_v2(String s, Set<String> dict) {
        
        boolean[] t = new boolean[s.length() + 1];
        t[0] = true;//set first to be true, why?
        //Because we need initial state
        
        for (int i = 0; i < s.length(); i++) {
            //should continue from match position
            if (t[i]) {
                for (String a : dict) {
                    int end = i + a.length();
                    if (end <= s.length() && !t[end]) {
                        if (s.substring(i, end).equals(a)) t[end] = true;
                    }
                }
            }
        }
        
        return t[s.length()];
    }
    
    
    public boolean wordBreak(String s, Set<String> dict) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int len = s.length();
        //x: start point, y: end point of the substring.
        boolean[][] array = new boolean[len + 1][len + 1];
        
        //Test the substring that start from 0.
        for (int y = 1; y <= len; y ++) {
            if(dict.contains(s.substring(0, y))) {
                array[0][y] = true;
            }
        }
        
        //Traverse the substring whose previous substring was true.
        for (int x = 0; x < len; x ++) {
            for (int y = 1; y <= len; y++) {
                if (array[x][y]) {//This condition is to 
                    int xx = y;
                    for (int yy = xx + 1; yy <= len; yy ++) {
                        if(dict.contains(s.substring(xx, yy))) {
                            array[xx][yy] = true;
                        }
                    }
                }
            }
        }
        
        //Finally check the status that ends at len.
        for (int x = 0; x < len; x ++) {
            if (array[x][len]) {
                return true;
            }
        }
        return false;
    }
}
