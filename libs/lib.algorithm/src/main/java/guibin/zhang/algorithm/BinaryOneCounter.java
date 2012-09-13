package guibin.zhang.algorithm;

/**
 * 
 * Biancheng Zhimei
 * 
 * Chapter 2.1 Count the amount of '1's in an integer's binary form
 * 
 * To solve this question, we have to be recall the definition of the java bitwise 
 * and bit shift operation, please refer to http://docs.oracle.com/javase/tutorial/java/nutsandbolts/op3.html
 * 
 * 1. The signed left shift operator "<<" shifts a bit pattern to the left,
 * 2. the signed right shift operator ">>" shifts a bit pattern to the right.
 * 3. The unsigned right shift operator ">>>" shifts a zero into the leftmost position,
 * 4. while the leftmost position after ">>" depends on sign extension.
 * 
 * Convert from binary to int: Integer.parseInt("101011", 2); => 43
 * Convert from int to binary: Integer.toBinaryString(128);
 * 
 * Extension:
 * Given two positive integers A and B in binary form, if we want to change A to B,
 * how many bits should be changed in A? Namely how many different bit between A and B?
 * 
 * Answer:
 * 1. int C = A ^ B // Get the result of A xor B
 * 2. Using the existing method to compute the amount of 1s in C, that is it.
 * 
 * @author Guibin Zhang <guibin.beijing at gmail.com>
 */
public class BinaryOneCounter {

    /**
     * Simply right shift the number. The complexity is log(v), 
     * the v is the total amount of 1 and 0 in the integer's binary form.
     * 
     * This method is buggy. When input is negative, for example 0x8000000, when shift 
     * one bit to right, 0x8000000 => 0xC000000, instead of 0x4000000. Because
     * before the shift, it is a negative number, after the shift, it still should be 
     * a negative number. After the shift, the high bit will be set to 1 by the system.
     * If we shift it to right all the time, finally this number will become to 0xFFFFFF,
     * then this program falls into dead loop.
     * 
     * @param n
     * @return 
     */
    public int count1(int n) {

        int tmp = n;
        int counter = 0;
        while (tmp != 0) {
            if ((tmp & 0x1) == 1) {
                counter++;
            }
            tmp >>>= 1;
        }
        return counter;
    }
    
    /**
     * A) If the last bit of the binary format of an integer is not 1, such as
     * 64 =>        01 000 000
     * 64 - 1 =>    00 111 111
     * then 64 & (64 - 1) = 0 
     * 
     * 11 000 000
     * 10 111 111
     * 
     * The complexity is log(w), w is the amount of 1 in n.
     * 
     * B) if the last bit of the binary format of an integer is 1, such as
     * 
     * 25 =>        00 011 001
     * 25 - 1 =>    00 011 000
     * then 25&24 = 24
     * 
     * 24  =>       00 011 000 
     * 23  =>       00 010 111
     * 24&23 = 16
     * 
     * 16&15 = 0
     * 
     * @param n
     * @return 
     */
    public int count2(int n) {
        int counter = 0;
        while(n != 0) {
            n &= n-1;
            counter ++;
        }
        return counter;
    }
    
    public static void main(String[] args) {
        BinaryOneCounter boc = new BinaryOneCounter();
        int i = -99;
        System.out.println(i + " has " + boc.count1(i) + " 1s, binary format " + Integer.toBinaryString(i));
        System.out.println(i + " has " + boc.count2(i) + " 1s, binary format " + Integer.toBinaryString(i));
    }
}
