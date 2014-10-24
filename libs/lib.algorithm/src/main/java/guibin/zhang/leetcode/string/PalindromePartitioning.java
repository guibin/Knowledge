package guibin.zhang.leetcode.string;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab",
 * Return
 * 
 *   [
 *     ["aa","b"],
 *     ["a","a","b"]
 *   ]
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class PalindromePartitioning {
    
    public List<List<String>> partition(String s) {
        
        int n = s.length();
        //isPalindrom[i][j] => s.substring(i, j+1) is palindrome
        boolean[][] isPalindrome = new boolean[n][n];
        //A Single character is palindrome
        for (int i = 0; i < n; i++)
            isPalindrome[i][i] = true;
        //Scan from end to start
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    //i and j are neighbor or the string between i and j is palindrome
                    if (i + 1 == j || isPalindrome[i + 1][j - 1])
                        isPalindrome[i][j] = true;
                }
            }
        }
        List<List<String>> result = new LinkedList<>();
        List<String> branch = new LinkedList<>();
        partition(s, 0, isPalindrome, branch, result);
        return result;
    }
    
    private void partition(String s, int start, boolean[][] isPalindrome, List<String> branch, List<List<String>> result) {
        if (start == s.length()) {
            List<String> newList = new LinkedList<>();
            newList.addAll(branch);
            result.add(newList);
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome[start][i]) {
                branch.add(s.substring(start, i + 1));
                partition(s, i + 1, isPalindrome, branch, result);
                branch.remove(branch.size() - 1);
            }
        }
    }
}
