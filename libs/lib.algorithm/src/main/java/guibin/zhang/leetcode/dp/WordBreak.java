package guibin.zhang.leetcode.dp;

import java.util.Arrays;
import java.util.HashSet;
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
     * Same with wordBreak_v2, just in reverse order from right to left.
     * @param s
     * @param dict
     * @return 
     */
    public boolean wordBreak_v3(String s, Set<String> dict) {
        
        boolean[] t = new boolean[s.length() + 1];
        t[s.length()] = true;
        //i is start position
        for (int start = s.length() - 1; start >= 0; start --) {
            //j is end position
            for (int end = s.length(); end > start; end--) {
                if (t[end] && dict.contains(s.substring(start, end))) {
                    t[start] = true;
                    break;
                }
            }
        }
        System.out.println("wordBreak_v3");
        for (boolean b : t) System.out.print(b + ",");
        System.out.println();
        return t[0];
    }
    
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
        
        for (int start = 0; start < s.length(); start++) {
            //should continue from match position
            if (t[start]) {
                for (String a : dict) {
                    int end = start + a.length();
                    if (end <= s.length() && !t[end]) {
                        if (s.substring(start, end).equals(a)) t[end] = true;
                    }
                }
            }
        }
        System.out.println("wordBreak_v2");
        for (boolean b : t) System.out.print(b + ",");
        System.out.println();
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
    
    public static void main(String[] args) {
        String a = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        //["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
        Set<String> dict = new HashSet<>();
        dict.add("a");
        dict.add("aa");
        dict.add("aaa");
        dict.add("aaaa");
        dict.add("aaaaa");
        dict.add("aaaaaa");
        dict.add("aaaaaaa");
        dict.add("aaaaaaaa");
        dict.add("aaaaaaaaa");
        dict.add("aaaaaaaaaa");
        WordBreak wb = new WordBreak();
        System.out.println("wordBreak_v2: " + wb.wordBreak_v2(a, dict));
        System.out.println("wordBreak_v3: " + wb.wordBreak_v3(a, dict));
    }
}
