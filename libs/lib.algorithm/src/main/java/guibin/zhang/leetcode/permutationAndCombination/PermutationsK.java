package guibin.zhang.leetcode.permutationAndCombination;

/**
 *
 * it takes two command-line arguments n and k, and prints out all P(n, k) = n! / (n-k)! permutations 
 * that contain exactly k of the n elements. 
 * Below is the desired output when k = 2 and n = 4. You need not print them out in any particular order.
 * ab ac ad ba bc bd ca cb cd da db dc 
 * 
 * @author gzhang
 */
public class PermutationsK {
    
    
    /**
     * Thoughts:
     * f(abcd, 2) =>
     * a + f(bcd, 1) && b + f(acd, 1) && c + f(abd, 1) && d + f(abc, 1)
     * f(bcd, 1) => b + f(cd, 0) && c + f(bd, 0) && d + f(bc, 0)
     * f(acd, 1) => a + f(cd, 0) && c + f(ad, 0) && d + f(ac, 0)
     * f(abd, 1) => a + f(bd, 0) && b + f(ad, 0) && d + f(ab, 0)
     * f(abc, 1) => a + f(bc, 0) && b + f(ac, 0) && c + f(ab, 0)
     * f(cd, 0) => ""
     * f(bd, 0) => ""
     * f(bc, 0) => ""
     * f(ab, 0) => ""
     * .... => ""
     * 
     * @param beginning
     * @param ending
     * @param k 
     */
    public void permutate(String beginning, String ending, int k) {
        
        if (k == 0) {
          System.out.println(beginning);
        } else {
            for (int i = 0; i < ending.length() && k > 0; i++) {
                String nEnding = ending.substring(0, i) + ending.substring(i + 1);
                permutate(beginning + ending.charAt(i), nEnding, k - 1);
            }
        }
    }
    
    public void permutate(char[] input, int len, int k) {
        
        if(k == 0) {
            for (int i = len; i < input.length; i++) {
                System.out.print(input[i]);
            } 
            System.out.println();
        }
        for (int i = 0; i < len; i++) {
            swap(input, i, len - 1);
            permutate(input, len - 1, k - 1);
            swap(input, i, len - 1);
        }
    }
    
    public void swap(char[] num, int i, int j) {
        char temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }
    
    public static void main(String[] args) {
        PermutationsK pk = new PermutationsK();
//        pk.permutate("", "ABCDE", 3);
        pk.permutate("ABCDE".toCharArray(), 5, 2);
    }
}
