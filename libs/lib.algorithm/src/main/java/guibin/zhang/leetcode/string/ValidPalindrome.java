package guibin.zhang.leetcode.string;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * 
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * 
 * Note:
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 * 
 * For the purpose of this problem, we define empty string as valid palindrome.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        String str = s.toLowerCase();
        int i = 0, j = str.length() - 1;
        while (i < j) {
            //Skip the non-alphanumeric from left
            if (!isAlphanumeric(str.charAt(i))){
                i++;
                continue;
            }
            //Skip the non-alphanumeric from right
            if (!isAlphanumeric(str.charAt(j))) {
                j--;
                continue;
            }
            if (str.charAt(i) == str.charAt(j) ) {
                i ++;
                j --;
            } else {
                return false;
            }
        }
        return true;
    }
    
    public boolean isAlphanumeric(char c) {
        return (c >= '0' && c <='9' || c <= 'z' && c >= 'a');
    }
    
    public static void main(String[] args) {
        ValidPalindrome vp = new ValidPalindrome();
        System.out.println(vp.isPalindrome("1a2"));
        System.out.println(vp.isPalindrome("A man, a plan, a canal: Panama"));
    }
}
