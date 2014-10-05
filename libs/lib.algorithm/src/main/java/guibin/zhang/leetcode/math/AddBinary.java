package guibin.zhang.leetcode.math;

/**
 *
 * Given two binary strings, return their sum (also a binary string).
 *
 * For example, a = "11" b = "1" Return "100".
 *
 * Run Status: Accepted!
 * Program Runtime: 648 milli secs
 * Progress: 294/294 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class AddBinary {

    public String addBinary(String a, String b) {
        
        if (a==null ||a.length()==0) return b;
        if (b==null || b.length()==0) return a;
        
        StringBuilder sb = new StringBuilder();
        
        int posA = a.length() - 1;
        int posB = b.length() - 1;
        int carry = 0;
        
        while (posA >= 0 || posB >= 0 || carry > 0) {
            int num1 = posA >= 0 ? a.charAt(posA) - '0' : 0;
            int num2 = posB >= 0 ? b.charAt(posB) - '0' : 0;
            int curr = (num1 + num2 + carry) % 2;
            carry = (num1 + num2 + carry) / 2;
            sb.insert(0, curr);
            posA--;
            posB--;
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        AddBinary ab = new AddBinary();
        System.out.println(ab.addBinary("101111", "10"));
        System.out.println(ab.addBinary("100", "110010"));
        System.out.println(ab.addBinary("101111", "10"));
    }
}
