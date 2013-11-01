package guibin.zhang.leetcode.permutationAndCombination;

/**
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class CharacterPowerSet {
    
    public void combine(char[] arr, int k, int startId, char[] branch, int numOfElem) {
        
        if (k == numOfElem) {
            System.out.println(branch);
            return;
        }
        
        for (int i = startId; i < arr.length; i++) {
            branch[numOfElem++] = arr[i];
            combine(arr, k, ++startId, branch, numOfElem);
            numOfElem --;
        }
    }
    
    public void powerSet(char[] arr) {
        
        for (int i = 0; i < arr.length; i++) {
            char[] branch = new char[i];
            combine(arr, i, 0, branch, 0);
        }
    }
    
    public static void main(String[] args) {
        
        CharacterPowerSet cps = new CharacterPowerSet();
        char[] arr = "ABCDE".toCharArray();
        cps.powerSet(arr);
    }
}
