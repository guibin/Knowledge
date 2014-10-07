package guibin.zhang.leetcode.permutationAndCombination;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a digit string, return all possible letter combinations that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * 
 * 2 => a, b, c
 * 3 => d, e, f
 * 4 => g, h, i
 * 5 => j, k, l
 * 6 => m, n, o
 * 7 => p, q, r, s
 * 8 => t, u, v
 * 9 => w, x, y, z
 * 
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class LetterCombinationsOfAPhoneNumber {
    
    char[][] map = {
        {}, //0
        {}, //1
        {'a', 'b', 'c'},//2
        {'d', 'e', 'f'},//3
        {'g', 'h', 'i'},//4
        {'j', 'k', 'l'},//5
        {'m', 'n', 'o'},//6
        {'p', 'q', 'r', 's'},//7
        {'t', 'u', 'v'},//8
        {'w', 'x', 'y', 'z'}//9
    };
    
    public ArrayList<String> letterCombinations(String digits) {
        
        ArrayList<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        letterCombinations(digits, 0, sb, result);
        return result;
    }
    
    /**
     * 
     * @param digits The input digits
     * @param idx The position of the digits
     * @param sb StringBuilder for caching the current branch
     * @param result 
     */
    public void letterCombinations(String digits, int idx, StringBuilder sb, List<String> result) {
        
        if (idx == digits.length()) {
            result.add(sb.toString());
            return;
        }
        char[] candidates = map[digits.charAt(idx) - '0'];
        //Otherwise, when digits contains 0 and 1, the result will be empty.
        //Because letterCombinations cannot be involked, and finally the i < digits.length();
        if (candidates.length == 0) {
            letterCombinations(digits, idx + 1, sb, result);
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            sb.append(candidates[i]);
            letterCombinations(digits, idx + 1, sb, result);
            //Because each digit maps to multiple candidate characers, 
            //only one is needed in each combination.
            //So delete it.
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Permutating...");
        LetterCombinationsOfAPhoneNumber lc = new LetterCombinationsOfAPhoneNumber();
        List<String> result = lc.letterCombinations("2314");
        for(String s : result) {
            System.out.println(s);
        }
    }
}
