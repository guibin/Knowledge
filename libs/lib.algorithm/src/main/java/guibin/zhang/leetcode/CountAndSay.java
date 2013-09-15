package guibin.zhang.leetcode;

/**
 * 
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * 
 * Given an integer n, generate the nth sequence.
 * 
 * Note: The sequence of integers will be represented as a string.
 * 
 * 
 * Run Status: Accepted!
 * Program Runtime: 540 milli secs
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class CountAndSay {

    public String countAndSay(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        String s = "1";
        for (int i = 1; i < n; i++) {
            s = countAndSay(s);
            System.out.println("-->" + s);
        }
        return s;
    }

    public String countAndSay(String s) {

        StringBuilder sb = new StringBuilder();
        int counter = 1;
        if (s.length() == 1) {
            sb.append(counter).append(s.charAt(0));
            return sb.toString();
        } else if (s.length() == 0) {
            return "";
        }

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                sb.append(counter).append(s.charAt(i));
                counter = 1;
            } else {
                counter++;
            }
        }
        //Note the last one, when last one is not equal to previous one
        if (s.charAt(s.length() - 1) != s.charAt(s.length() - 2)) {
            counter = 1;
        }
        sb.append(counter).append(s.charAt(s.length() - 1));

        return sb.toString();
    }
    
    public static void main(String[] args) {
        CountAndSay ca = new CountAndSay();
        System.out.println(ca.countAndSay(8));
    }
}
