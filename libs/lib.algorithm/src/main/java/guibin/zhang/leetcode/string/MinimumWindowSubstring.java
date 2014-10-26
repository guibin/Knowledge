package guibin.zhang.leetcode.string;

/**
 * 
 * Given a string S and a string T, find the minimum window in S 
 * which will contain all the characters in T in complexity O(n).
 * 
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * 
 * Minimum window is "BANC".
 * 
 * Note:
 * If there is no such window in S that covers all characters in T, return the emtpy string "".
 * 
 * If there are multiple such windows, you are guaranteed that 
 * there will always be only one unique minimum window in S.
 * 
 * http://huntfor.iteye.com/blog/2083485
 * 
 * 
 * 可以利用两个指针扫描（两个指针分别为start，i），以S = “e b a d b a c c b”（忽略空格），T = “abc”为例：
 * 0 1 2 3 4 5 6 7 8
 * 初始化 start = i = 0
 * 1）i 逐渐往后扫描S直到窗口S[start…i]包含所有T的字符，此时i = 6（字符c的位置）
 * 2）缩减窗口：此时我们注意到窗口并不是最小的，需要调整 start 来缩减窗口。
 * 
 * 
 * 缩减规则为：如果S[start]不在T中 或者 S[start]在T中但是删除后窗口依然可以包含T中的所有字符，
 * 那么start = start+1， 直到不满足上述两个缩减规则。缩减后i即为窗口的起始位置，
 * 此例中从e开始窗口中要依次删掉e、b、a、d，start最后等于4 ，那么得到一个窗口大小为6-4+1 = 3
 * 
 * 
 * 3）start = start+1(此时窗口肯定不会包含所有的T中的字符)，跳转到步骤2继续寻找下一个窗口。
 * 本例中还以找到一个窗口start = 5，i = 8，比上个窗口大，因此最终的最小窗口是S[4…6]
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class MinimumWindowSubstring {
    
    public String minWindow_v2(String S, String T) {
        
        char[] sHash = new char[128];
        char[] tHash = new char[128];
        int found = 0;
        int start = 0;
        String result = "";
        for (int i = 0; i < T.length(); i++) {
            tHash[T.charAt(i)] ++;
        }
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (tHash[c] > 0) {//Only continue while this char is in T
                sHash[c]++;
                if (sHash[c] <= tHash[c]) found++;
            
                if (found == T.length()) {
                    while (start <= i) {
                        char t = S.charAt(start);
                        if (tHash[t] == 0 || (sHash[t]) > tHash[t]) {
                            sHash[t]--;
                            start ++;
                        } else {
                            String str = S.substring(start, i + 1);
                            if (result.length() == 0 || str.length() < result.length()) result = str;
                            found --;//Update found
                            sHash[t]--;//Update sHash
                            start ++;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }
    
    
    public String minWindow(String S, String T) {
        
        int[] tHash = new int[128];
        //Make statistics of T
        for (int i = 0; i < T.length(); i++) {
            tHash[T.charAt(i)] ++;
        }
        
        int[] sHash = new int[128];
        int found = 0;
        //The min length window
        int begin = -1, end = S.length(), minLen = 1 + S.length();
        
        for (int start = 0, i = 0; i < S.length(); i++) {
            char curr = S.charAt(i);
            //If curr in T, make statistics of S, otherwise continue to scan
            if (tHash[curr] != 0) {
                sHash[curr] ++;
            
                //This is necessary to avoid duplicated characters in S
                if (sHash[curr] <= tHash[curr]) found++;

                //Found the first windows that contains all the characters in T
                if (found == T.length()) {
                    //Shrink the window
                    while (start < i) {
                        char sChar = S.charAt(start);
                        if (tHash[sChar] == 0 || 
                                //This is case that there are more duplicated characters in S
                                (tHash[sChar] != 0 && --sHash[sChar] >= tHash[sChar])) {
                            start ++;
                        } else {
                            break;
                        }
                    }
                    //Compute the min window
                    if (i - start + 1 < minLen) {
                        minLen = i - start + 1;
                        begin = start;
                        end = i;
                    }
                    found --;
                    start ++;
                }
            }
        }
        
        return begin == -1 ? "" : S.substring(begin, end + 1);
    }
    
    
    public static void main(String[] args) {
        MinimumWindowSubstring mw = new MinimumWindowSubstring();
        mw.minWindow_v2("bdab", "ab");
    }
}
