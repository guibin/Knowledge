package guibin.zhang.algorithm;

import java.math.BigDecimal;

/**
 *
 * @author Guibin Zhang <guibin.beijing at gmail.com>
 * 
 * Biancheng Zhimei
 * 
 * Get the amount of ending zeros of N! without calculating N!.
 * 
 * Method 1:
 * Since we should not calculate N!, we consider to expand the N! to an expression.
 * 5! => 1*2*3*4*5 => one zero => one 5.
 * 10! => 1*2*3*4*5*6*7*8*9*10 => 1*2*3*4*5*6*7*8*9*(2*5) => 2 zero => two 5s
 * We guess the conclusion: The amount of 0 equals the amount 5 in the expanded expression.
 * 
 * Method 2:
 * 
 */
public class Factorial {

    /**
     * @param n
     * @return 
     */
    public int count1(int n) {

        int counter = 0;
        while (n > 0) {
            int m = n;
            while (m % 5 == 0) {
                counter++;
                m /= 5;
            }
            n--;
        }

        return counter;
    }
    
    /**
     * This recursive method could be "stack overflow" if n is too big.
     * 
     * @param n
     * @return 
     */
    public BigDecimal factorial2(int n) {
        if (n == 1) {
            return new BigDecimal(1);
        } else {
            return new BigDecimal(n).multiply(factorial(n - 1));
        }
    }
    
    public BigDecimal factorial(int n) {
        BigDecimal res = new BigDecimal(1);
        for(int i=1; i<=n; i++) {
            res = res.multiply(new BigDecimal(i));
        }
        
        return res;
    }

    public static void main(String[] args) {
        Factorial f = new Factorial();
        int i = 10;

        System.out.println(i + "! = " + f.factorial(i).toString() + ", has " + f.count1(i) + " 0s.");
        System.out.println(i + "! = " + f.factorial2(i).toString() + ", has " + f.count1(i) + " 0s.");
    }
}
