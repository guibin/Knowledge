package guibin.zhang.leecode;

/**
 *
 * Implement pow(x, n).
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class Pow {

    public double pow(double x, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        double res = 1.0;
        int m = Math.abs(n);

        if (m == 0) {
            res = 1.0;
        } else if (m == 1) {
            res = x;
        } else if (m % 2 == 0) {
            //Note the d; if the code is: res = pow(x, m / 2) * pow(x, m / 2); 
            //then it is same with x*x*x*x....
            double d = pow(x, m / 2);
            res = d * d;
        } else {
            double d = pow(x, (m - 1) / 2);
            res = x * d * d;
        }


        if (n >= 0) {
            return res;
        } else {
            return 1.0 / res;
        }
    }
}
