package guibin.zhang.leetcode.permutationAndCombination;

/**
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class CharacterPowerSet {
    
    /**
     * Combination, select k items from arr.length items.
     * @param arr
     * @param branch
     * @param idx index of branch
     * @param startId
     * @param k the targeting k items
     */
    public void combine(char[] arr, char[] branch, int idx, int startId, int k) {
        
        if (k == idx) {
            System.out.println(branch);
            return;
        }
        
        for (int i = startId; i < arr.length; i++) {
            branch[idx++] = arr[i];
            combine(arr, branch, idx, ++startId, k);
            idx --;
        }
    }
    
    public void powerSet(char[] arr) {
        
        for (int i = 1; i <= arr.length; i++) {
            char[] branch = new char[i];
            combine(arr, branch, 0,  0, i);
        }
    }
    
    
    /**
     * Input "ABCD", the expected power set looks like:
     * 
     * Actually it is the combination.
     * 
     * A
     * B
     * C
     * D
     * AB
     * AC
     * AD
     * BC
     * BD
     * CD
     * ABC
     * ABD
     * ACD
     * BCD
     * ABCD
     * 
     * @param args 
     */
    public static void main(String[] args) {
        
        CharacterPowerSet cps = new CharacterPowerSet();
        char[] arr = "ABCD".toCharArray();
        cps.powerSet(arr);
    }
}
