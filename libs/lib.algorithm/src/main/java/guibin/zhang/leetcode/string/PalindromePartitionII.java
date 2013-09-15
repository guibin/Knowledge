package guibin.zhang.leecode.string;

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
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class PalindromePartitionII {
    
    private Map<String, Integer> stringToNumberOfCuts = new HashMap<String, Integer>();
    private Map<String, Boolean> stringToIsPalindrome = new HashMap<String, Boolean>();
    
    /**
     * http://polythinking.wordpress.com/2013/06/09/leetcode-palindrome-partitioning-ii/
     * Solution 1: 记忆化搜索实现，能pass small data set，但运行large data set里 Time Limit Exceeded。
     * 
     * 这题一般人一看就是DP，DP公式也很容易推出，算是一道简单的DP。
     * dp(i)=min(1+dp(j+1) if s(i,j) is palindrome, j from i until n)
     * 从以上的分析时间复杂度为O(n^3), 主要是因为检查回文也需要O(n)的时间。因此这题有意思的一点就是如何降低时间复杂度到O(n^2)？
     * 
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
     * 游标从字符串两边开始向中间移动，依次比较前后相应位置上的字符。
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
    
    
    /**
     * http://fisherlei.blogspot.com/2013/03/leetcode-palindrome-partitioning-ii.html
     * http://discuss.leetcode.com/questions/1266/palindrome-partitioning-ii
     * http://blog.csdn.net/binary_search/article/details/8738061
     * http://blog.sina.com.cn/s/blog_b9285de20101iwqt.html
     * http://discuss.leetcode.com/questions/1266/palindrome-partitioning-ii
     * @param s
     * @return 
     */
    public int minCut_v2(String s) {
        
        int length = s.length();
        
        /**
         * The length of numberOfCuts is length+1 instead of length is because:
         * numberOfCuts[i] means the minimum cuts of s[i:]
         * So s[0] is the whole string, s[length] is the last empty string
         */
        int[] numberOfCuts = new int[length + 1];
        boolean[][] palindrome = new boolean[length][length];//Initialized as false by default
        
        /**
         * numberOfCuts[i] means minimum number of cuts for the sub-string s[from i to end].
         * 
         * For the worst case, the string is cut by each char.
         * since a single char is always a palindrome, max cut string s needs is (s.length() - 1). 
         * 
         * numberOfCuts[i] = Math.min(numberOfCuts[i], 1 + numberOfCuts[j + 1]) if s[i,j] is palindrome
         * min{ 第一段被切割的次数 + 第二段被切割的次数 + 1 }
         * 
         */
        for(int i = 0; i <= length; i++) { //Initialize the numberOfCuts with the worst case.
            numberOfCuts[i] = length - 1 - i;
        }
        
        /**
         * 如何判断[i,j]是否是回文？每次都从i到j比较一遍，这样比较直观，参考上面的 isPalindrome，
         * 但是太浪费了，这里也是一个DP问题。
         * 
         * Define: palindrome[i][j] = true if [i][j] is palindrome, 
         * Then palindrome P[i][j] = str[i] == str[j] && P[i+1][j-1];
         * This method of detecting the palindrome is to reduce the time complexity from O(n) to O(1)
         * 
         * That means: 要检查一个回文只需要知道头尾的字符相等，并且中间的字串已经成为了回文即可。O(1)复杂度。
         * 
         *  Notion: i is on the left and j is on the right
         *  a   b   a   b   b   b   a   b   b   a   b   a
         *                  i               j
         *                     i+1     j-1
         * 
         * j - i < 2 ====>>>>>> j - 1 < i + 1, that is when s[j - 1] is on the left of s[i + 1]
         * 
         * i = length - 2 means start from the last element but one.
         * Since the last element is s[length - 1], so the last but one is s[length - 2]
         */
        for(int i = length - 2; i >= 0; i--) {
            for(int j = i; j < length; j++) {
                //This condition below is to to judge whether s[i,j] is palindrome.
                //Question, why when j-1 < i+1, it is palindorme???
                if(s.charAt(i) == s.charAt(j) && (j - 1 < i + 1 || palindrome[i + 1][j - 1])) {
                    palindrome[i][j] = true;
                    /**
                     * The expression below equals to:
                     * result = Math.min(result, minCut_v1(s.substring(0, i)) + 1 + minCut_v1(s.substring(i)));
                     * 
                     * which means:
                     * if s[i,j] is palindrome, then numberOfCut[i:] = numberOfCut[i:j] + numberOfCut[j+1:]
                     *                                                        |
                     *                                           s[i,j] is palindrome, so it is 1
                     */
                    numberOfCuts[i] = Math.min(numberOfCuts[i], numberOfCuts[j + 1] + 1);
                }
            }
        }
        
        return numberOfCuts[0];
    }
    
    public static void main(String[] args) {
        PalindromePartitionII pp = new PalindromePartitionII();
        System.out.println("ab: " + pp.isPalindrome("ab"));
        System.out.println("aba: " + pp.isPalindrome("aba"));
        System.out.println("abba: " + pp.isPalindrome("abba"));
        System.out.println("abcba: " + pp.isPalindrome("abcba"));
        System.out.println("abcdba: " + pp.isPalindrome("abcdba"));
        
        System.out.println("------minCut_v1------");
        System.out.println("a: " + pp.minCut_v1("a"));
        System.out.println("ab: " + pp.minCut_v1("ab"));
        System.out.println("aba: " + pp.minCut_v1("aba"));
        System.out.println("abac: " + pp.minCut_v1("abac"));
        System.out.println("cabababcbc: " + pp.minCut_v1("cabababcbc"));
        System.out.println("ltsqjodzeriqdtyewsrpfscozbyrpidadvsmlylqrviuqiynbscgmhulkvdzdicgdwvquigoepiwxjlydogpxdahyfhdnljshgjeprsvgctgnfgqtnfsqizonirdtcvblehcwbzedsmrxtjsipkyxk: " + pp.minCut_v1("ltsqjodzeriqdtyewsrpfscozbyrpidadvsmlylqrviuqiynbscgmhulkvdzdicgdwvquigoepiwxjlydogpxdahyfhdnljshgjeprsvgctgnfgqtnfsqizonirdtcvblehcwbzedsmrxtjsipkyxk"));
        
        System.out.println("------minCut_v2------");
        System.out.println("a: " + pp.minCut_v2("a"));
        System.out.println("ab: " + pp.minCut_v2("ab"));
        System.out.println("aba: " + pp.minCut_v2("aba"));
        System.out.println("abac: " + pp.minCut_v2("abac"));
        System.out.println("cabababcbc: " + pp.minCut_v2("cabababcbc"));
        System.out.println("ltsqjodzeriqdtyewsrpfscozbyrpidadvsmlylqrviuqiynbscgmhulkvdzdicgdwvquigoepiwxjlydogpxdahyfhdnljshgjeprsvgctgnfgqtnfsqizonirdtcvblehcwbzedsmrxtjsipkyxk: " + pp.minCut_v2("ltsqjodzeriqdtyewsrpfscozbyrpidadvsmlylqrviuqiynbscgmhulkvdzdicgdwvquigoepiwxjlydogpxdahyfhdnljshgjeprsvgctgnfgqtnfsqizonirdtcvblehcwbzedsmrxtjsipkyxk"));
    }
}
