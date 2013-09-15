package guibin.zhang.leetcode.string;

/**
 *
 * Write a function to find the longest common prefix string amongst an array of strings.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class LongestCommonPrefix {
    
    public String longestCommonPrefix(String[] strs) {
        
        int arrLen = strs.length;
        int itemLen = strs[0].length();
        for (int i = 0; i < itemLen; i ++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < arrLen; j ++) {
                if (i > strs[j].length() - 1 || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        
        return strs[0];
    }
    
    public static void main(String[] args) {
        String[] strs = {"q", "qp"};
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        System.out.println(lcp.longestCommonPrefix(strs));
    }
}
