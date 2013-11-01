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
     * @param idx The index for target array.
     * @param visited The array which is used to track the visiting status.
     */
    public void generatePermutations(char[] arr, char[] branch, int idx, boolean[] visited) {
        
        if (idx == arr.length) {
            System.out.println(branch);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                branch[idx++] = arr[i];
                visited[i] = true;
                generatePermutations(arr, branch, idx, visited);
                visited[i] = false;
                idx --;
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
        cp.generatePermutations(arr, branch, 0, visited);
        
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
