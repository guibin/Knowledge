package guibin.zhang.leetcode.binary;

/**
 *
 * You have an array of integers(size N), such that each integer is present an odd number of time, 
 * except 3 of them(which are present even number of times), Find the three numbers.
 * 
 * Only XOR based solution was permitted. 
 * Time Complexity: O(N) 
 * Space Complexity: O(1) 
 * 
 * Sample Input: 
 * {1,6,4,1,4,5,8,8,4,6,8,8,9,7,9,5,9,5} 
 * Sample Output: 
 * 1 6 8
 * 
 * Question link: http://www.careercup.com/question?id=5707243546738688
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class SearchNumbers {
    
    public static void searchNumbersOccurEventTimes(int[] data) {
        
        // configured for positive, single digit integers per sample input.
        final int DIGITS = 1;
        //This is a map to computing the times of show.
        int[] counters = new int[12 * DIGITS];
        
        for (int i = 0; i < data.length; i++) {
            int index = data[i];
            if (index > counters.length || index < 0) {
                return;
            }
            if (counters[index] == 0) {
                counters[index] = 1;
            } else {
                // XOR with negative 1 to create one's complement per question requirement
                counters[index] ^= -1;
                // counters[index] = ~counters[index]; // equivalently, use
                // uniary complement operator to create one's complement
            }
        }
        // show the counter results
        // 0 means not encountered
        // +1 means odd
        // -2 means even (one's complement of 1 = -2 and vice versa)
        // a ^ -1 = b, b ^ -1 = a, it is symmetrical. So choose any number can distinguish the odd and even
        for (int i = 0; i < counters.length; i ++) {
            if (counters[i] == -2) {
                System.out.print(i + ",");
            }
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        int[] data = { 1, 6, 4, 1, 4, 5, 8, 8, 4, 6, 8, 8, 9, 7, 9, 5, 9, 5, 11};
        searchNumbersOccurEventTimes(data);
    }
}
