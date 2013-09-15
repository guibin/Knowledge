package guibin.zhang.leetcode.string;

/**
 * 
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * 
 * If the last word does not exist, return 0.
 * 
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * 
 * For example, 
 * Given s = "Hello World",
 * return 5.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (s == null || s.trim().length() == 0) {
            return 0;
        }
        char[] arr = s.trim().toCharArray();
        
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == ' ') {
                return arr.length - 1 - i;
            }
        }
        return arr.length;
    }
    
    public int lengthOfLastWord_v2(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        
        if(s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();        
        char[] arr = s.toCharArray();
        int end = len - 1;
        
        //Note the first condition is end >= 0, otherwise it will OutofIndexException.
        while (end >= 0 && arr[end] == ' ') {
            end --;
        }
        
        for (int i = end; i >= 0; i--) {
            if (arr[i] == ' ') {
                return end - i;
            }
        }
        
        return end + 1;
    }
    
    public static void main(String[] args) {
        LengthOfLastWord lw = new LengthOfLastWord();
        System.out.println(lw.lengthOfLastWord_v2(" "));
    }
}
