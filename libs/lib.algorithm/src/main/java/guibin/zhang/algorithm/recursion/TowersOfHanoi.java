package guibin.zhang.algorithm.recursion;

/**
 *
 * To move n disc from peg A to peg C:
 * 1 Move the upper n-1 discs from peg A to peg **B**, this need T(n-1) steps.
 * 2 Move the bottom one #n disc from peg A to peg **C**, this need 1 step.
 * 3 Move the n-1 discs from B to C, this need T(n-1) steps.
 * 
 * The total steps are T(n-1) + 1  + T(n-1) = 2T(n-1) + 1
 * 
 * @author Guibin Zhang
 */
public class TowersOfHanoi {
    
    public long hanoi(int n) {
        
        if(n == 1) {
            return 1;
        }
        else {
            return 2 * hanoi(n - 1) + 1;
        }
    }
    
    public static void main(String[] args) {
        TowersOfHanoi h = new TowersOfHanoi();
        System.out.println(h.hanoi(2));
        System.out.println(h.hanoi(3));
        System.out.println(h.hanoi(10));
        System.out.println(h.hanoi(20));
        System.out.println(h.hanoi(30));
        System.out.println(h.hanoi(60));
    }
}
