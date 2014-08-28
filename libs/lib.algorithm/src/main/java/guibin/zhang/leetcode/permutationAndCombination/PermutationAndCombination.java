package guibin.zhang.leetcode.permutationAndCombination;

/**
 * Generating all permutations of a string (of characters).
 * 
 * Summary: Difference between permutation and combination is: ***startId for loop.***
 * A. If startId keep unchanged in while recursing, it is permutation with duplication.
 * B. If startId keep increased(self increase or i++ or i + 1), it is combination. 
 *    self increase is combination, i++ is combination with duplication.
 * C. If startId starts from 0 for loop, with a visited tracking array, it is permutation.
 * 
 * http://exceptional-code.blogspot.com/2012/09/generating-all-permutations.html
 * 
 * ***Permutation doesn't need startID, while combination needs.***
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class PermutationAndCombination {
    
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
     * @param k How many elements will be selected from source array
     */
    public void generatePermutations(char[] arr, char[] branch, int idx, boolean[] visited, int k) {
        
        if (idx == k) {
            System.out.println(branch);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                branch[idx++] = arr[i];
                visited[i] = true;
                generatePermutations(arr, branch, idx, visited, k);
                visited[i] = false;
                idx --;
            }
        }
    }
    
    /**
     * 
     * Idea: one loop + one recursion.
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
    public void generatePermutationsWithDuplication(char[] arr, char[] branch, int idx, int k) {
        
        if (idx == k) {
            System.out.println(branch);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            branch[idx++] = arr[i];
            generatePermutationsWithDuplication(arr, branch, idx, k);
            idx --;
        }
    }
    
    /**
     * 
     * Idea: one loop + one recursion.
     * 
     * Expected output:
     * 
     * ABC => AA, AB, AC, BA, BB, BC, CA, CB, CC
     * 
     * @param arr The source array.
     * @param branch The target array, which contains the permutation result.
     * @param idx The index for target array.
     * @param startId Start index to recurse.
     * @param k Permutation of k elements from the source array. P(n, k)
     */
    public void generatePermutationWithDuplicationV2(char[] arr, char[] branch, int idx, int k) {
        
        if (idx == k) {
            System.out.println(branch);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            branch[idx++] = arr[i];
            generatePermutationWithDuplicationV2(arr, branch, idx, k);
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
     * @param k Combination of k elements from the source array. C(n, k)
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
    
    /**
     * It is same with generateCombination.
     * 
     * Expected output: 
     * 
     * ABC => AB, AC, BC
     * 
     */
    public void generateCombinationV2(char[] arr, char[] branch, int idx, int startId, int k) {
        
        if (idx == k) {
            System.out.println(branch);
            return;
        }
        for (int i = startId; i < arr.length; i++) {
            branch[idx++] = arr[i];
            generateCombinationV2(arr, branch, idx, i + 1, k);
            idx --;
        }
    }
    
    /**
     * Idea: one loop + one recursion + startId, but startId is i while recursing.
     * 
     * Expected output:
     * 
     * ABC => AA, AB, AC, BB, BC, CC
     * 
     * @param arr The source array.
     * @param branch The target array, which contains the permutation result.
     * @param idx The index for target array.
     * @param startId Start index to recurse.
     * @param k Combination of k elements from the source array. C(n, k).
     */
    public void generateCombinationWithDuplication(char[] arr, char[] branch, int idx, int startId, int k) {
        
        if (idx == k) {
            System.out.println(branch);
            return;
        }
        for (int i = startId; i < arr.length; i++) {
            branch[idx++] = arr[i];
            generateCombinationWithDuplication(arr, branch, idx, i, k);
            idx --;
        }
    }
    
    
    public static void main(String[] args) {
        
        PermutationAndCombination cp = new PermutationAndCombination();
        
        String str = "ABC";
        int n = 2;
        char[] arr = str.toCharArray();
        boolean[] visited = new boolean[str.length()];
        char[] branch = new char[n];
        
        System.out.println("-------Full permutation----------");
        cp.generatePermutations(arr, branch, 0, visited, n);
        
        System.out.println("-------Duplicated permutation----------");
        cp.generatePermutationsWithDuplication(arr, branch, 0, n);
        
        System.out.println("-------Duplicated Permutation V2----------");
        str = "ABC";
        arr = str.toCharArray();
        n = str.length();
        int k = 2;
        branch = new char[k];
        cp.generatePermutationWithDuplicationV2(arr, branch, 0, k);
        
        System.out.println("-------Combination----------");
        str = "ABCD";
        k = 3;
        arr = str.toCharArray();
        branch = new char[k];
        cp.generateCombination(arr, branch, 0, 0, k);
        
        System.out.println("-------Combination V2----------");
        str = "ABCD";
        arr = str.toCharArray();
        n = str.length();
        k = 3;
        branch = new char[k];
        cp.generateCombinationV2(arr, branch, 0, 0, k);

        System.out.println("-------Duplicated Combination----------");
        str = "ABC";
        arr = str.toCharArray();
        n = str.length();
        k = 2;
        branch = new char[k];
        cp.generateCombinationWithDuplication(arr, branch, 0, 0, k);
        
    }
}
