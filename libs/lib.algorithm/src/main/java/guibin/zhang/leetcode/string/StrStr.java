package guibin.zhang.leetcode.string;

/**
 *
 * Implement strStr().
 * 
 * Returns a pointer to the first occurrence of needle in haystack, or null if needle is not part of haystack.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class StrStr {
    
    public String strStr(String haystack, String needle) {
        
        if(needle.length() == 0) return haystack;
          
         int n = 0;
          
         //Here is <= instead of <, for handling case: "a", "a"
         for(int start = 0; start <= haystack.length() - needle.length(); start++){
             n = 0;
              
             while(n < needle.length() && haystack.charAt(start + n) == needle.charAt(n))
                  n++;
                   
             if(n == needle.length()) return haystack.substring(start);
         }
          
         return null;
    }
}
