package guibin.zhang.leetcode.listAndArray;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Write a function to remove the duplicated characters from a string, 
 * and maintain the order of the characters. 
 * 
 * ex. “abracadabra” => “abrcd”
 * 
 * Question link: http://www.careercup.com/question?id=5091887140569088
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class RemoveDuplicateCharacter {
    
    public String removeDuplicate(String s) {
        
        char[] a = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int idx = -1;
        for (int i = 0; i < a.length; i++) {
            if (!set.contains(a[i])) {
                a[++idx] = a[i];
                set.add(a[idx]);
            }
        }
        return new String(Arrays.copyOfRange(a, 0, idx + 1));
    }
    
    //Use a boolean array to replace the HashSet
    public String removeDuplicate_v2(String s) {
        
        char[] a = s.toCharArray();
        boolean[] duplication = new boolean[256];
        int idx = -1;
        for (int i = 0; i < a.length; i++) {
            if (!duplication[a[i]]) {
                a[++idx] = a[i];
                duplication[a[i]] = true;
            }
        }
        return new String(Arrays.copyOfRange(a, 0, idx + 1));
    }
    
    public static void main(String[] args) {
        RemoveDuplicateCharacter rd = new RemoveDuplicateCharacter();
        String input = "abracadabra";
        String ret = rd.removeDuplicate(input);
        System.out.println(input + " -> " + ret);
        ret = rd.removeDuplicate_v2(input);
        System.out.println(input + " -> " + ret);
    }
}
