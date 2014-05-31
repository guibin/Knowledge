package guibin.zhang.algorithm.recursion;

/**
 * Prints out the binary representation of N.
 * 
 * @author Guibin Zhang
 */
public class BinaryRepresentation {
    
    public void int2Binary(int n, StringBuilder result) {
        
        if(n <= 0) return;
        
        //Print before the function, it will be in wrong order.
        int2Binary(n/2, result);
        //Print after the function, the order is correct.
        if(n%2 != 0) {
            result.append(1);
        } else {
            result.append(0);
        }
    }
    
    public static void main(String[] args) {
        BinaryRepresentation br = new BinaryRepresentation();
        StringBuilder result = new StringBuilder();
        br.int2Binary(100, result);
        System.out.println(result.toString());
    }
}
