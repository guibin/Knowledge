package guibin.zhang.leetcode.permutationAndCombination;

/**
 * Generating all permutations of a string (of characters).
 * 
 * http://exceptional-code.blogspot.com/2012/09/generating-all-permutations.html
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class CharacterFullPermutation {
    
    /**
     * Idea: one loop + one recursion + one tracking array.
     * 
     * @param arr The source array.
     * @param branch The target array, which contains the permutation result.
     * @param level The index for target array.
     * @param visited The array which is used to track the visiting status.
     */
    public void generatePermutations(char[] arr, char[] branch, int level, boolean[] visited) {
        
        if (level >= arr.length - 1) {
            System.out.println(branch);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                branch[++level] = arr[i];
                visited[i] = true;
                generatePermutations(arr, branch, level, visited);
                visited[i] = false;
                level --;
            }
        }
    }
    
    public static void main(String[] args) {
        
        CharacterFullPermutation cp = new CharacterFullPermutation();
        
        String str = "ABCD";
        int n = str.length();
        char[] arr = str.toCharArray();
        boolean[] visited = new boolean[n];
        char[] branch = new char[n];
        cp.generatePermutations(arr, branch, -1, visited);
        
        /**
         * The expected permutation ABCD is:
            ABCD
            ABDC
            ACBD
            ACDB
            ADBC
            ADCB
            BACD
            BADC
            BCAD
            BCDA
            BDAC
            BDCA
            CABD
            CADB
            CBAD
            CBDA
            CDAB
            CDBA
            DABC
            DACB
            DBAC
            DBCA
            DCAB
            DCBA
         */
    }
}
