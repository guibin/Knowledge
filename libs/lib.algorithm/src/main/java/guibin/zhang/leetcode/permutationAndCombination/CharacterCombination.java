package guibin.zhang.leetcode.permutationAndCombination;

/**
 * 
 * Generating combinations of k elements.
 * 
 * Generating combinations of k elements from the given set follows similar algorithm used 
 * to generate all permutations, but since we don't want to repeat an a character even in a different order 
 * we have to force the recursive calls to NOT to follow the branches that repeat a set of characters.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class CharacterCombination {
    
    /**
     * 
     * @param arr The source array.
     * @param k Combination of k elements from the source array.
     * @param startId Start index to recurse.
     * @param branch The target array, which contains the combination result.
     * @param numOfElem The index for target array.
     */
    public void combine(char[] arr, int k, int startId, char[] branch, int numOfElem) {
        
        if (numOfElem == k) {
            System.out.println(branch);
            return;
        }
        
        for (int i = startId; i < arr.length; i++) {
            branch[numOfElem++] = arr[i];
            combine(arr, k, ++startId, branch, numOfElem);
            -- numOfElem;
        }
    }
    
    public static void main(String[] args) {
        
        CharacterCombination cc = new CharacterCombination();
        char[] arr = "ABCDE".toCharArray();
        int k = 3;
        char[] branch = new char[k];
        cc.combine(arr, k, 0, branch, 0);
        
        /**
         * The expected output should be:
            ABC
            ABD
            ABE
            ACD
            ACE
            ADE
            BCD
            BCE
            BDE
            CDE
         */
    }
}
