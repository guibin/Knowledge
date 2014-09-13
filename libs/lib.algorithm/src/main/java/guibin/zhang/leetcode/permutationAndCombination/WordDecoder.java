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
    
    /**
     * Given a function getRandomTripplet() which returns a random triplet of letters from a string. 
     * 
     * You don't know the string using calls to this function you have to correctly guess the string. 
     * the length of the string is also given. 
     * 
     * Lets say the string is helloworld the function getRandomTriplet will return things like 
     * hle, hew, wld, owo 
     * the function maintains the relative order of the letters. so it will never return 
     * ohl: since h is before o in the string. 
     * owe: since w is after e 
     * 
     * The string is not known you are only given length of the string.
     * 
     * Question link: http://www.careercup.com/question?id=5678056593162240
     * 
     */
    public static void printRandomTripplet(char[] a, char[] branch, int idx, int startId, int k) {
        
        if (idx == k) {
            System.out.println(new String(branch));
            return;
        } 
        for (int i = startId; i < a.length; i ++) {
            branch[idx++] = a[i];
            printRandomTripplet(a, branch, idx, i + 1, k);
            idx --;
        }
    }
    
    public static void main(String[] args) {
        
        decode("", "1234");
        
        System.out.println("====Combination====");
        combination("", "abcd");
        
        System.out.println("====Ramdon Tripplet====");
        int k = 3;
        char[] branch = new char[k];
        printRandomTripplet("abcd".toCharArray(), branch, 0, 0, k);
    }
}
