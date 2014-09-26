package guibin.zhang.leetcode.permutationAndCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * Permutation iterate version.
     * 
     * Expected output:
     * ABC => CBA, BCA, BAC, CAB, ACB, ABC
     * 
     * http://www.programcreek.com/2013/02/leetcode-permutations-java/
     * 
     * @param arr
     * @return 
     */
    public List<List<Character>> generatePermutations(char[] arr) {
        
        List<List<Character>> result = new ArrayList<>();
        
        //Start from an empty list
        result.add(new ArrayList<>());
        
        for (int i = 0; i < arr.length; i++) {
            //list of list in current iteration of the array arr
            List<List<Character>> curr = new ArrayList<>();
            for (List<Character> list : result) {
                // # of locations to insert is largest index + 1
                for (int j = 0; j < list.size() + 1; j++) {
                    // + add num[i] to different locations
                    list.add(j, arr[i]);
                    
                    curr.add(new ArrayList<>(list));
//                    list.forEach(c -> System.out.print(c));
//                    System.out.println();
                    
                    // - remove num[i] add
                    list.remove(j);
                }
            }
            result = curr;
        }
        
        System.out.println("----------");
        System.out.println("Permutation Iteration Result:");
        result.forEach(item -> {item.forEach(it -> System.out.print(it)); System.out.println();});
        System.out.println("----------");
            
        return result;
    }
    
    
    /**
     * Iteration version of full permutation.
     * 
     * Expected output:
     * 
     * ABC =>
     * A
     * B
     * BA
     * AB
     * C
     * CA
     * AC
     * CB
     * BC
     * CBA
     * BCA
     * BAC
     * CAB
     * ACB
     * ABC
     * 
     * @param arr
     * @return 
     */
    public List<List<Character>> generateFullPermutations(char[] arr) {
        
        List<List<Character>> result = new ArrayList<>();
        
        //Start from an empty list
        result.add(new ArrayList<>());
        
        for (int i = 0; i < arr.length; i++) {
            
            int size = result.size();
            for (int k = 0; k < size; k++) {
                List<Character> list = result.get(k);
                // # of locations to insert is largest index + 1
                for (int j = 0; j < list.size() + 1; j++) {
                    // + add num[i] to different locations
                    list.add(j, arr[i]);
                    
                    result.add(new ArrayList<>(list));
//                    list.forEach(c -> System.out.print(c));
//                    System.out.println();
                    
                    // - remove num[i] add
                    list.remove(j);
                }
            }
        }
        
        System.out.println("----------");
        System.out.println("Full permutation Result:");
        result.forEach(item -> {item.forEach(it -> System.out.print(it)); System.out.println();});
        System.out.println("----------");
            
        return result;
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
     * 
     * One loop + one recursion + (i+1) or (++startId) generate the full combination.
     * 
     * Expected output:
     * 
     * ABC =>
     * A,
     * A,B,
     * A,B,C,
     * A,B,C,D,
     * A,B,D,
     * A,C,
     * A,C,D,
     * A,D,
     * B,
     * B,C,
     * B,C,D,
     * B,D,
     * C,
     * C,D,
     * D,
     */
    public void generateFullCombination(char[] arr, List<Character> branch, 
                                        List<List<Character>> result, int startId) {
        
        for (int i = startId; i < arr.length; i++) {
            branch.add(arr[i]);
            result.add(new ArrayList<>(branch));
            branch.forEach(item -> System.out.print(item + ","));
            System.out.println();
            
//            generateFullCombination(arr, branch, result, ++startId);// ++startId is same with i + 1
            generateFullCombination(arr, branch, result, i + 1);
            branch.remove(branch.size() - 1);
        }
    }
    
    /**
     * Iteration version of full combination.
     * @param arr 
     */
    public void generateFullCombinationIteration(char[] arr) {
        Arrays.sort(arr);
        List<List<Character>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        //**Select each item from arr**.
        for (int i = 0; i < arr.length; i++) {
            //**Pick up the existing result, add the item to it**.
            //Cannot use result.size() directly, since in each iteration, 
            //size of result is increasing, which will cause the dead loop.
            int size = result.size();
            for (int j = 0; j < size; j ++) {
                List<Character>  branch = new ArrayList<>(result.get(j));
                branch.add(arr[i]);
                result.add(branch);
            }
        }
        
        result.forEach(list -> {list.forEach(i -> System.out.print(i + ", "));
                                System.out.println();});
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
        
        System.out.println("-------permutation Iteration verion----------");
        cp.generatePermutations(arr);
        
        System.out.println("-------Full Full permutation Iteration verion----------");
        cp.generateFullPermutations(arr);
        
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

        System.out.println("-------Full Combination----------");
        char[] arr2 = "ABC".toCharArray();
        List<Character> branch2 = new ArrayList<>();
        List<List<Character>> result2 = new ArrayList<>();
        cp.generateFullCombination(arr2, branch2, result2, 0);
        
        System.out.println("-------Full Combination Iteration----------");
        cp.generateFullCombinationIteration(arr2);
        
        System.out.println("-------Duplicated Combination----------");
        str = "ABC";
        arr = str.toCharArray();
        n = str.length();
        k = 2;
        branch = new char[k];
        cp.generateCombinationWithDuplication(arr, branch, 0, 0, k);
        
    }
}
