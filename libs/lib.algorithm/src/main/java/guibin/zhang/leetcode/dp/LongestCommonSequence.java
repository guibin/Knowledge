package guibin.zhang.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * LCS Problem Statement: Given two sequences, find the length of longest subsequence present in both of them. 
 * A subsequence is a sequence that appears in the same relative order, 
 * but not necessarily contiguous. 
 * 
 * For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”. 
 * So a string of length n has 2^n different possible subsequences.
 * 
 * It is a classic computer science problem, the basis of diff 
 * (a file comparison program that outputs the differences between two files), 
 * and has applications in bioinformatics.
 * 
 * Question link: http://www.geeksforgeeks.org/dynamic-programming-set-4-longest-common-subsequence/
 * 
 * Solution Examples:
 * 1) Consider the input strings “AGGTAB” and “GXTXAYB”. 
 * Last characters match for the strings. So length of LCS can be written as:
 * L(“AGGTAB”, “GXTXAYB”) = 1 + L(“AGGTA”, “GXTXAY”)
 * 
 * 2) Consider the input strings “ABCDGH” and “AEDFHR. Last characters do not match for the strings. 
 * So length of LCS can be written as:
 * L(“ABCDGH”, “AEDFHR”) = MAX ( L(“ABCDG”, “AEDFHR”), L(“ABCDGH”, “AEDFH”) )
 * 
 * So the LCS problem has optimal substructure property as the main problem can be solved using solutions to subproblems.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class LongestCommonSequence {
    
    /**
     * 
     * @param x String x
     * @param y String y
     * @param m index of x
     * @param n index of y
     * @return Returns length of LCS for X[0..m-1], Y[0..n-1]
     * 
     */
    public int lcsNaive(String x, String y, int m, int n) {
        
        if (m == 0 || n == 0) {
            return 0;
        }
        if (x.charAt(m - 1) == y.charAt(n - 1)) {
            return 1 + lcsNaive(x, y, m - 1, n - 1);
        } else {
            return Math.max(lcsNaive(x, y, m, n - 1), lcsNaive(x, y, m - 1, n));
        }
    }
    
    public int lcsDP(String x, String y) {
        
        int[][] L = new int[x.length() + 1][y.length() + 1];
        
        // Build L[i][j] in bottom up fashion
        // L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1]
        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0 || j == 0) {
                    L[i][j] = 0;
                } else if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    L[i][j] = L[i - 1][j - 1] + 1;
                } else {
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
                    
                }
            }
        }
        
        List<String> result = new ArrayList<>();
        for (int i = x.length(), j = y.length(); i > 0 && j > 0; ) {
            if (x.charAt(i - 1) == y.charAt(j - 1)) {
                result.add(0, String.valueOf(x.charAt(i - 1)));
                i --;
                j --;
            } else if (L[i - 1][j] > L[i][j - 1]) {
                i --;
            } else {
                j --;
            }
        }
        result.forEach(i -> System.out.print(i + " "));
        System.out.println();
        
        return L[x.length()][y.length()];
    }
    
    public static void main(String[] args) {
        String x = "AGGTAB";
        String y = "GXTXAYB";
        LongestCommonSequence lcs = new LongestCommonSequence();
        System.out.println(lcs.lcsNaive(x, y, x.length(), y.length()));
        System.out.println(lcs.lcsDP(x, y));
    }
}
