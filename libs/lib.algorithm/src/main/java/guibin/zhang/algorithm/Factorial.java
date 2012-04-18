package guibin.zhang.algorithm;

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
       
        return 0;
    }
    
}
