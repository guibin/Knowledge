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
 * @author Guibin Zhang <guibin.beijing at gmail.com>
 */
public class BinaryOneCounter {

    /**
     * Simply right shift the number. The complexity is log(v), 
     * the v is the total amount of 1 and 0 in the integer's binary form.
     * 
     * @param n
     * @return 
     */
    public int count1(int n) {

        int tmp = n;
        int counter = 0;
        while (tmp > 0) {
            if ((tmp & 0x1) == 1) {
                counter++;
            }
            tmp >>>= 1;
        }
        return counter;
    }
    
    /**
     * if 01 000 000 can be divided by 2 without reminder
     * 64 =>        01 000 000
     * 64 - 1 =>    00 111 111
     * then 64 & (64 - 1) = 0 
     * 
     * 11 000 000
     * 10 111 111
     * 
     * The complexity is log(w), w is the amount of 1 in n.
     * 
     * @param n
     * @return 
     */
    public int count2(int n) {
        int counter = 0;
        while(n > 0) {
            n &= n-1;
            counter ++;
        }
        return counter;
    }
    
    public static void main(String[] args) {
        BinaryOneCounter boc = new BinaryOneCounter();
        int i = 99;
        System.out.println(i + " has " + boc.count1(i) + " 1s, binary format " + Integer.toBinaryString(i));
        System.out.println(i + " has " + boc.count2(i) + " 1s, binary format " + Integer.toBinaryString(i));
    }
}
