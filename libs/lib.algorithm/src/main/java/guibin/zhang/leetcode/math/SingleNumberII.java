package guibin.zhang.leetcode.math;

/**
 *
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * 
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * 
 * http://fisherlei.blogspot.com/2013/11/leetcode-single-number-ii-solution.html
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
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
    
    public int singleNumber_v2 (int[] A) {
        
        int ones = 0, twos = 0, threes = 0;
        
        for(int i=0; i<A.length; i++)
        {
            threes = twos & A[i];//已经出现两次并且再次出现
            twos = twos | ones & A[i];//曾经出现两次的或者曾经出现一次但是再次出现的
            ones = ones | A[i];//出现一次的
            twos = twos & ~threes;//当某一位出现三次后，就从出现两次中消去
            ones = ones & ~threes;//当某一位出现三次后，就从出现一次中消去
        }
        return ones;
    }
}
