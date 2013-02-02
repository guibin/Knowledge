package guibin.zhang.algorithm.string;

/**
 * Palindrome are those String whose reverse is equal to original
 * 
 * @author guibin
 */
public class Palindrome {
    
    public boolean isPalindrome(String s) {
        
        char[] arr = s.toCharArray();
        int length = arr.length;
        for(int i=0; i<length/2; i++) {
            if(arr[i] != arr[length - 1 -i]) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Compute the reverse of the number, then judge whether it is palindrom.
     * @param num
     * @return 
     */
    public boolean isPalindrome(int num) {
        
        int original = num;
        int reverse = 0;
        while(original != 0) {
            int reminder = original % 10;
            original = original / 10;
            reverse = reverse * 10 + reminder;
        }
        
        if(reverse == num) return true;
        else return false;
    }
    
    public String longestPalindrome(String s) {
        
        for(int i = s.length(); i > 1; i --) {
            for(int j = 0; j + i <= s.length(); j ++) {
                String target = s.substring(j, i+j);
                if(isPalindrome(target)) {
                    return target;
                }
            }
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        
        Palindrome p = new Palindrome();
        System.out.println("isPalindrome: abcba - " + p.isPalindrome("abcba"));
        System.out.println("isPalindrome: abcb - " + p.isPalindrome("abcb"));
        System.out.println("isPalindrome: abccba - " + p.isPalindrome("abccba"));
        System.out.println("isPalindrome: abcdcba - " + p.isPalindrome("abcdcba"));
        
        System.out.println("isPalindrome: " + 12321 + " - " + p.isPalindrome(12321));
        System.out.println("isPalindrome: " + 1232 + " - " + p.isPalindrome(1232));
        System.out.println("isPalindrome: " + 123321 + " - " + p.isPalindrome(123321));
        
        System.out.println("Longest palindrome: abcdcba - " + p.longestPalindrome("abcdcba"));
        System.out.println("Longest palindrome: aecdcba - " + p.longestPalindrome("aecdcba"));
        System.out.println("Longest palindrome: aabccbccd - " + p.longestPalindrome("aabccbccd"));
        System.out.println("Longest palindrome: abcdef - " + p.longestPalindrome("abcdeef"));
        System.out.println("Longest palindrome: abcdef - " + p.longestPalindrome("abcdef"));
        
        //Note the below function will not throw the IndexOutofBoundException
        System.out.println("Any Exception here? " + "abc".substring(2, 2));
    }
}
