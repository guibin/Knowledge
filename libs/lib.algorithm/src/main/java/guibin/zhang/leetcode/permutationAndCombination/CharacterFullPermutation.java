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
     * Expected output:
     * ABC => ABC, ACB, BAC ,BCA ,CAB, CBA
     * 
     * @param arr The source array.
     * @param branch The target array, which contains the permutation result.
     * @param idx The index for target array.
     * @param visited The array which is used to track the visiting status.
     */
    public void generatePermutationsWithoutDuplication(char[] arr, char[] branch, int idx, boolean[] visited) {
        
        if (idx == arr.length) {
            System.out.println(branch);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                branch[idx++] = arr[i];
                visited[i] = true;
                generatePermutationsWithoutDuplication(arr, branch, idx, visited);
                visited[i] = false;
                idx --;
            }
        }
    }
    
    /**
     * 
     * Idea: one loop + one recursion + startId, but startId doesn't self-increase while recursing.
     * 
     * Expected output: 
     * 
     * ABC => 
     * 
     * AAA, AAB, AAC
     * ABA, ABB, ABC
     * ACA, ACB, ACC
     * BAA, BAB, BAC
     * BBA, BBB, BBC
     * BCA, BCB, BCC
     * CAA, CAB, CAC
     * CBA, CBB, CBC
     * CCA, CCB, CCC
     * 
     * @param arr The source array.
     * @param branch The target array, which contains the permutation result.
     * @param idx The index for target array.
     * @param startId Start index to recurse.
     */
    public void generatePermutationsWithDuplication(char[] arr, char[] branch, int idx, int startId) {
        
        if (idx == arr.length) {
            System.out.println(branch);
            return;
        }
        for (int i = startId; i < arr.length; i++) {
            branch[idx++] = arr[i];
            generatePermutationsWithDuplication(arr, branch, idx, startId);
            idx --;
        }
    }
    
    /**
     * Idea: one loop + one recursion + startId, but startId DOES self-increase while recursing.
     * 
     * Expected output: 
     * 
     * ABC => AB, AC, BC
     * 
     * @param arr The source array.
     * @param branch The target array, which contains the permutation result.
     * @param idx The index for target array.
     * @param startId Start index to recurse.
     * @param k Combination of k elements from the source array.
     */
    public void generateCombination(char[] arr, char[] branch, int idx, int startId, int k) {
        
        if (idx == k) {
            System.out.println(branch);
            return;
        }
        for (int i = startId; i < arr.length; i++) {
            branch[idx++] = arr[i];
            generateCombination(arr, branch, idx, ++startId, k);
            idx --;
        }
    }
    
    
    public static void main(String[] args) {
        
        CharacterFullPermutation cp = new CharacterFullPermutation();
        
        String str = "ABC";
        int n = str.length();
        char[] arr = str.toCharArray();
        boolean[] visited = new boolean[n];
        char[] branch = new char[n];
        
        System.out.println("-------Full permutation----------");
        cp.generatePermutationsWithoutDuplication(arr, branch, 0, visited);
        System.out.println("-------Duplicated permutation----------");
        cp.generatePermutationsWithDuplication(arr, branch, 0, 0);
        System.out.println("-------Combination----------");
        int k = 2;
        branch = new char[k];
        cp.generateCombination(arr, branch, 0, 0, 2);
    }
}
