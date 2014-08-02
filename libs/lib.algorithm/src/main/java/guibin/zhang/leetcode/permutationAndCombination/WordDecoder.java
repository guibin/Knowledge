package guibin.zhang.leetcode.permutationAndCombination;

/**
 *
 * 1 represent A, 2 rep B etc and 26 rep Z. 
 * Given a number, find number of possible decoding for this number. 
 * No need to consider number starts with zero. 
 * 
 * Eg: input – 1234, output – 3(ABCD, AWD, LCD)
 * 
 * Question link: http://www.careercup.com/question?id=5120347909128192
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class WordDecoder {
    
    public static void decode(String lstr, String numstr) {
        
        char[] CHARY = ((String)"ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        
        int len = numstr.length();
        if (len == 0 && lstr.length() > 0) {
            System.out.println(lstr);
            return;
        } else if (len > 2) {
            len = 2;
        }
        //Try valid 1 and 2 digits encoding
        for (int i = 1; i <= len; i++) {
            int n = Integer.parseInt(numstr.substring(0, i));
            if (n >= 1 && n <= 26) {
                decode(lstr + CHARY[n - 1], numstr.substring(i));
            }
        }
    }
    
    
    public static void combination(String lstr, String rstr) {
        
        System.out.println(lstr + " : " + rstr);

        //One loop + recursion can iterate all the combinations
        for (int i = 1; i <= rstr.length(); i++) {
            String s = rstr.substring(0, i);
            combination(s, rstr.substring(i));
        }
    }
    
    public static void main(String[] args) {
        
        decode("", "1234");
        combination("", "abcd");
    }
}
