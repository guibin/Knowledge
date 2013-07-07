package guibin.zhang.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * 
 * http://blog.163.com/guixl_001/blog/static/417641042013319113320284/
 * 
 * @author guibin
 */
public class PalindromePartitionII {
    
    private Map<String, Integer> stringToNumberOfCuts = new HashMap<String, Integer>();
    private Map<String, Boolean> stringToIsPalindrome = new HashMap<String, Boolean>();
    
    /**
     * Solution 1: 记忆化搜索实现，能pass small data set，但运行large data set里 Time Limit Exceeded。
     * @param s
     * @return 
     */
    public int minCut_v1(String s) {
        if(s.length() == 1 || s.length() == 0) {//If the string only has one character, it is palindrome and needs 0 cut.
            return 0;
        }
        //首先检查缓存中是否存在该字符串
        if(stringToNumberOfCuts.containsKey(s)) {
            return stringToNumberOfCuts.get(s);
        }
        
        if(isPalindrome(s)) {//If the string itself is palindrome, needs 0 cut.
            stringToNumberOfCuts.put(s, 0);
            return 0;
        }
        
        //Each single character is palindrome, so the whole string needs length -1 cuts at most.
        int result = s.length() - 1;
        /**
         * 字符串被切割的次数 = min{ 第一段被切割的次数 + 第二段被切割的次数 + 1 }
         */
        for(int i = 1; i < s.length(); i++) {
            result = Math.min(result, minCut_v1(s.substring(0, i)) + 1 + minCut_v1(s.substring(i)));
        }
        stringToNumberOfCuts.put(s, result);
        
        return result;
    }
    
    /**
     * 判断字符串是否为palindrome.
     * @param s
     * @return 
     */
    public boolean isPalindrome(String s) {
        
        if(stringToIsPalindrome.containsKey(s)) {
            return stringToIsPalindrome.get(s);
        }
        
        int start = 0;
        int end = s.length() - 1;
        boolean result = true;
        while(start < end) {
            if(s.charAt(start) == s.charAt(end)) {
                start ++;
                end --;
            } else {
                result = false;
                break;
            }
        }
        stringToIsPalindrome.put(s, result);
        
        return result;
    }
    
    
    public static void main(String[] args) {
        PalindromePartitionII pp = new PalindromePartitionII();
        System.out.println("ab: " + pp.isPalindrome("ab"));
        System.out.println("aba: " + pp.isPalindrome("aba"));
        System.out.println("abba: " + pp.isPalindrome("abba"));
        System.out.println("abcba: " + pp.isPalindrome("abcba"));
        System.out.println("abcdba: " + pp.isPalindrome("abcdba"));
        
        System.out.println("------------");
        System.out.println("a: " + pp.minCut_v1("a"));
        System.out.println("ab: " + pp.minCut_v1("ab"));
        System.out.println("aba: " + pp.minCut_v1("aba"));
        System.out.println("abac: " + pp.minCut_v1("abac"));
        System.out.println("cabababcbc: " + pp.minCut_v1("cabababcbc"));
        System.out.println("ltsqjodzeriqdtyewsrpfscozbyrpidadvsmlylqrviuqiynbscgmhulkvdzdicgdwvquigoepiwxjlydogpxdahyfhdnljshgjeprsvgctgnfgqtnfsqizonirdtcvblehcwbzedsmrxtjsipkyxk: " + pp.minCut_v1("ltsqjodzeriqdtyewsrpfscozbyrpidadvsmlylqrviuqiynbscgmhulkvdzdicgdwvquigoepiwxjlydogpxdahyfhdnljshgjeprsvgctgnfgqtnfsqizonirdtcvblehcwbzedsmrxtjsipkyxk"));
    }
}
