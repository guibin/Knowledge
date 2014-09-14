package guibin.zhang.leetcode.permutationAndCombination;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * 
 * For example, given n = 3, a solution set is:
 * 
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 * 
 * https://github.com/rffffffff007/leetcode/blob/master/Generate%20Parentheses.java
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class GenerateParentheses {
    
    public List<String> generateParenthesis(int n) {
        
        List<String> result = new ArrayList<>();
        char[] array = new char[n * 2];
        generate(n, 0, 0, array, result);
        return result;
    }
    
    public void generate(int n, int left, int right, char[] branch, List<String> result) {
        
        int k = left + right;
        if (k == 2 * n) {
            result.add(new String(branch));
            return;
        }
        if (left < n) {
            branch[k] = '(';
            generate(n, left + 1, right, branch, result);
        }
        if (right < left) {
            branch[k] = ')';
            generate(n, left, right + 1, branch, result);
        }
    }
    
    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        List<String> result = gp.generateParenthesis(3);
        result.stream().forEach((s) -> {
            System.out.println(s);
        });
    }
}
