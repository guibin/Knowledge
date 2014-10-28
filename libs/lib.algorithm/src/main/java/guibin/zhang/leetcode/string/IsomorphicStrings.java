package guibin.zhang.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Isomorphic Strings
 * 
 * Given two (dictionary) words as Strings, determine if they are isomorphic. Two words are called isomorphic 
 * if the letters in one word can be remapped to get the second word. Remapping a letter means replacing all 
 * occurrences of it with another letter while the ordering of the letters remains unchanged. No two letters 
 * may map to the same letter, but a letter may map to itself. 
 * 
 * Example: 
 * given "foo", "app"; returns true 
 * we can map 'f' -> 'a' and 'o' -> 'p' 
 * given "bar", "foo"; returns false 
 * we can't map both 'a' and 'r' to 'o' 
 * 
 * given "turtle", "tletur"; returns true 
 * we can map 't' -> 't', 'u' -> 'l', 'r' -> 'e', 'l' -> 'u', 'e' -'r' 
 * 
 * given "ab", "ca"; returns true 
 * we can map 'a' -> 'c', 'b'
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class IsomorphicStrings {
    
    public boolean isomorphic(String s1, String s2) {

       if (s1 == null && s2 == null) return true;
       if (s1 == null || s2 == null) return false;
       if (s1.length() != s2.length()) return false;

       Map<Character, Character> map1 = new HashMap<>();
       Map<Character, Character> map2 = new HashMap<>();
       for (int i = 0; i < s1.length(); i++) {
           char c1 = s1.charAt(i);
           char c2 = s2.charAt(i);
           
           if (map1.containsKey(c1)) {
               if (map1.get(c1) != c2) {
                   return false;
               }
           } else {
               if (map2.containsKey(c2)) {
                   return false;
               }
               //Build c1 -> c2 and c2 -> c1
               map1.put(c1, c2);
               map2.put(c2, c1);
           }
       }

       return true;
    }

    public static void main(String[] args) {
        IsomorphicStrings s = new IsomorphicStrings();
        System.out.println(s.isomorphic("", ""));
        System.out.println(s.isomorphic("foo", "app"));
        System.out.println(s.isomorphic("foo", "ppp"));
        System.out.println(s.isomorphic("ofo", "app"));
        System.out.println(s.isomorphic("bar", "foo"));
        System.out.println(s.isomorphic("turtle", "tletur"));
        System.out.println(s.isomorphic("tletur", "turtle"));
        System.out.println(s.isomorphic("turtle", "tletur"));
        System.out.println(s.isomorphic("tletur", "turtle"));
    }
}
