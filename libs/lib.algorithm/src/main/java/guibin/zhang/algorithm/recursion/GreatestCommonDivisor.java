package guibin.zhang.algorithm.recursion;

/**
 * We can efficiently compute the gcd using the following property, 
 * which holds for positive integers p and q:
 * If p > q, the gcd of p and q is the same as the gcd of q and p % q.
 * 
 * @author Guibin Zhang
 */
public class GreatestCommonDivisor {
    
    /**
     * Suppose p > q, get the greatest common divisor of p and q.
     * @param p
     * @param q
     * @return 
     */
    public int gcd(int p, int q) {
        
        if(p < q) {
            gcd(q, p);
        }
        
        if (q == 0) {
            return p;
        } else {
            return gcd(q, p % q);
        }
    }
    
    public static void main(String[] args) {
        GreatestCommonDivisor g = new GreatestCommonDivisor();
        System.out.println("gcd 128, 64 =>" + g.gcd(128, 64));
        System.out.println("gcd 64, 124 =>" + g.gcd(64, 124));
    }
}
