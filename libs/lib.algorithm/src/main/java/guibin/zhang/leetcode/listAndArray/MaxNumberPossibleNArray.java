package guibin.zhang.leetcode.listAndArray;

/**
 * 
 * Given an unsorted array of integers, you need to return maximum possible n in the array
 * such that the array consists at least n values greater than or equals to n. 
 * 
 * Array can contain duplicate values. 
 * Sample input : [1, 2, 3, 4] -- output : 2 
 * Sample input : [900, 2, 901, 3, 1000] -- output: 3
 * 
 * Question link: http://www.careercup.com/question?id=5094709806497792
 * 
 * Idea:
 * Sort the array in descending order. 
 * Loop over it from the beginning till you find A[i] where i > A[i]. 
 * This would be nlog(n).
 * 
 * So we can sort in a tricky way.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class MaxNumberPossibleNArray {
    public static int searchMaxNumberPossibleN(int[] a) {
        
        int[] counter = new int[a.length + 1];
        //Use value as index of counter to sort it. 
        //Since the required n must be less than or equal to the length of the array, 
        //So we will ignore the values lagger than len.
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= a.length) {
                counter[a.length] += 1;
                //ignore the negative values
            } else if (a[i] > 0){
                counter[a[i]] += 1;
            }
        }
        int sum = 0;
        for (int j = counter.length - 1; j >0; j--) {
            sum += counter[j];
            if (sum >= j) {
                return j;
            }
        }
        return 0;
    }
    
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        System.out.println(searchMaxNumberPossibleN(a));
        int[] b = {900, 2, 901, 3, 1000};
        System.out.println(searchMaxNumberPossibleN(b));
        int[] c = {900, 10, 901, 11, 1000};
        System.out.println(searchMaxNumberPossibleN(c));
    }
}
