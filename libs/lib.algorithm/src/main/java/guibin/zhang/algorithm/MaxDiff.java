package guibin.zhang.algorithm;

/**
 * Given an integer array, a diff is defined as one item substract the one on its right.
 * Please find the maximum diff among all these diffs.
 * For example: in the array {2, 4, 1, 16, 7, 5, 11, 9}, the max diff should be 11, namely 16 - 5
 * 
 * Please refer to http://zhedahht.blog.163.com/blog/static/2541117420116135376632/
 * 
 * Give h < i, diff[i]≥number[h]-number[i], We need to find the max(diff[i])
 * If we have got the diff[i], How to get the diff[i+1]?
 * When we are finding the diff[i+1], we need to find the max before the No.i+1, 
 * There are two cases regarding this max, 
 * A) The max is the max before,
 * B) The max is the No.i
 * 
 * @author guibin
 */
public class MaxDiff {

    public static int findTheMaxDiffFrom(int[] numbers) {
        
        if(numbers == null || numbers.length < 2) {
            System.out.println("Invalid input numbers");
            return 0;
        }
        
        String str = "";
        for(int i : numbers) {
            str = i + ",";
        }
        System.out.println("Finding the max diff from {" + str + "}");
        
        int max = numbers[0];
        int maxDiff = max - numbers[1];
        
        for(int i = 2; i < numbers.length; i++) {
            if(numbers[i - 1] > max) {
                max = numbers[i - 1];
            }
            int currentDiff = max - numbers[i];
            if(currentDiff > maxDiff) {
                maxDiff = currentDiff;
            }
        }
        
        return maxDiff;
    }
    
    public static void main(String[] args) {
        
    }
}
