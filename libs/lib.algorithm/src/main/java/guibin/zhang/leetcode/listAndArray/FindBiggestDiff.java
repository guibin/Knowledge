package guibin.zhang.leetcode.listAndArray;

/**
 * Given the int array, find out index of the biggest diff.
 * Require the bigger item be ahead of the smaller one.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class FindBiggestDiff {
    
    public static int[] find(int[] a) {
        
        int[] result = new int[2];
        int max = a[0];
        int maxDiff = 0;
        int start = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
                start = i;
            }
            int diff = max - a[i];
            if (diff > maxDiff) {
                maxDiff = diff;
                result[0] = start;
                result[1] = i;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] a = {1, 4, 3, 7};
        int[] result = find(a);
        System.out.println(a[result[0]] + "," + a[result[1]]);
    }
}
