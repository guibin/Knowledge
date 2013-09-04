package guibin.zhang.leecode.permutation;

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
    
    public ArrayList<String> generateParenthesis(int n) {
        
        ArrayList<String> result = new ArrayList<String>();
        char[] array = new char[n * 2];
        generate(n, 0, 0, array, result);
        return result;
    }
    
    public void generate(int n, int left, int right, char[] array, ArrayList<String> result) {
        
        int k = left + right;
        if (k == 2 * n) {
            result.add(new String(array));
            return;
        }
        if (left < n) {
            array[k] = '(';
            generate(n, left + 1, right, array, result);
        }
        if (right < left) {
            array[k] = ')';
            generate(n, left, right + 1, array, result);
        }
    }
    
    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        List<String> result = gp.generateParenthesis(3);
        for (String s : result) {
            System.out.println(s);
        }
    }
}
