package guibin.zhang.leecode.string;

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
        // Start typing your Java solution below
        // DO NOT write main() function
        int lenA = a.length();
        int lenB = b.length();
        String mS = null;
        String nS = null;
        int m = 0;
        int n = 0;
        StringBuilder sb = null;
        if (lenA > lenB) {
            sb = new StringBuilder(a);
            m = lenA;
            mS = a;
            n = lenB;
            nS = b;
        } else {
            sb = new StringBuilder(b);
            m = lenB;
            mS = b;
            n = lenA;
            nS = a;
        }

        boolean carry = false;
        for (int i = n - 1; i >= 0; i--) {
            //Note the logic for j; Draft an example to compute it.
            int j = i + m - n;
            if (nS.charAt(i) - '0' + mS.charAt(j) - '0' == 1) {
                if (!carry) {
                    sb.setCharAt(j, '1');
                } else {
                    sb.setCharAt(j, '0');
                    carry = true;
                }
            } else if (nS.charAt(i) - '0' + mS.charAt(j) - '0' == 0) {
                if (!carry) {
                    sb.setCharAt(j, '0');
                } else {
                    sb.setCharAt(j, '1');
                    carry = false;
                }
            } else {
                if (!carry) {
                    sb.setCharAt(j, '0');
                } else {
                    sb.setCharAt(j, '1');
                }
                carry = true;
            }
        }

        if (!carry) {
            return sb.toString();
        } else {
            //Note the logic for i, draft an example to compute it.
            for (int i = m - n - 1; i >= 0; i--) {
                if (mS.charAt(i) == '0') {
                    if (carry) {
                        sb.setCharAt(i, '1');
                        carry = false;
                    }
                } else {
                    if (carry) {
                        sb.setCharAt(i, '0');
                        carry = true;
                    }
                }
            }
            if (carry) {
                sb.insert(0, '1');
            }
            return sb.toString();
        }
    }
    
    public static void main(String[] args) {
        AddBinary ab = new AddBinary();
        System.out.println(ab.addBinary("101111", "10"));
        System.out.println(ab.addBinary("100", "110010"));
        System.out.println(ab.addBinary("101111", "10"));
    }
}
