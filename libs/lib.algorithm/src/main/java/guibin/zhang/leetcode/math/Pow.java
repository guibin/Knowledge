package guibin.zhang.leetcode.math;

/**
 *
 * Implement pow(x, n).
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class Pow {

    public double pow(double x, int n) {
        
        if (n == 0) return 1.0;
        if (n == 1) return x;
        if (n == -1) return 1.0/x;

        int m = Math.abs(n);
        double result;
        
        if (m % 2 == 0) {
            double d = pow(x, m/2);
            result =  d*d;
        } else {
            double d = pow(x, (m - 1)/2);
            result =  x * d * d;
        }
        
        if (n > 0) return result;
        
        return 1.0/result;
    }
}
