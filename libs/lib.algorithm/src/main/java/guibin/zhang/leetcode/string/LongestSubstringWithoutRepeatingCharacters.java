package guibin.zhang.leetcode.string;

import java.util.HashSet;

/**
 * Longest Substring Without Repeating Characters
 * 
 * Given a string, find the length of the longest substring without repeating characters. 
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
 * For "bbbbb" the longest substring is "b", with the length of 1.
 * 
 * http://tianrunhe.wordpress.com/2012/07/30/longest-substring-without-repeating-characters/
 * 
 * Thoughts:
 * We want to finish this task in one scan. 
 * We can maintain a hashtable to detect if we have seen any character. 
 * We start from the beginning and walk through the array until we hit a repetition, 
 * let’s say, at position i. We then have two piece of valuable information right now:
 * 
 * 1. s[0..i-1] is a candidate of the longest substring without repetition. 
 * Therefore we can update the max length.
 * 
 * 2. There exists a position 0 <= j <= i-1, such that s[j] == s[i]. 
 * Substring starting from j is not a candidate because it has at least one repetition: s[j] and s[i]. 
 * Any substring ended at j will be shorter than the substring s[0..i-1] so we don’t need to look at them.
 * 
 * Then we can remove every elements in the hashtable from 0 to j, 
 * and make the start position of next candidate to j + 1. 
 * 
 * We keep walking and repeating this process until we hit the last element.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class LongestSubstringWithoutRepeatingCharacters {
    
    public int lengthOfLongestSubstring(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int max = 0;
        HashSet<Character> set = new HashSet<>();//Save the non-repetition characters start from last repetition.
        int start = 0;//Start idx of the sub-string without repetition
        for(int end = 0; end < s.length(); ++end) {
            char e = s.charAt(end);
            if(set.contains(e)) {
                max = Math.max(max, end - start);
                while(s.charAt(start) != s.charAt(end)) {
                    set.remove(s.charAt(start));
                    start++;
                }
                //Here s.charAt(end) is still in set, make the start point to "end + 1"
                start++;
            } else {
                set.add(e);
            }
        }
        //Here is the last segment till the end of the string
        max = Math.max(max, s.length() - start);
        return max;
    }
    
}
