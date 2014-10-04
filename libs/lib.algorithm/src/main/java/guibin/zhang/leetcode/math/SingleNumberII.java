package guibin.zhang.leetcode;

/**
 *
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * 
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * 
 * http://fisherlei.blogspot.com/2013/11/leetcode-single-number-ii-solution.html
 * 
 * @author Guibin Zhang <gzhang at radiumone.com>
 */
public class SingleNumberII {
    
    public int singleNumber(int[] A) {
        
        int result = 0;
        for (int i = 0; i < 32; i++) {//Since int is 32 bits, use 32.
            //Use count to make the statistics that how many times each digit appears.
            int count = 0;
            for (int j = 0; j < A.length; j++) {
                //1. right shift each digit to the right most position
                //2. AND 1 will get 1 if the digit is 1, othersise get 0.
                //3. Aggregarte it to count
                count += (A[j] >> i) & 1;
            }
            result |= (count % 3) << i;
        }
        
        return result;
    }
}
