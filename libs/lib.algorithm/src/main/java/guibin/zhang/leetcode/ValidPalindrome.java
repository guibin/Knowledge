package guibin.zhang.leecode;

/**
 * 
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * 
 * Note:
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 * 
 * For the purpose of this problem, we define empty string as valid palindrome.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ValidPalindrome {
    
    public boolean isPalindrome(String s) {
        
        boolean flag = true;
        int i = 0;
        int j = s.length() - 1;
        s = s.toLowerCase();
        
        while(i < j) {
            char a = s.charAt(i);
            char b = s.charAt(j);
            if(!isAlphanumeric(a)) {
                i ++;
                continue;
            }
            if(!isAlphanumeric(b)) {
                j --;
                continue;
            }
            if(a != b) {
                return false;
            } else {
                i++;
                j--;
            }
            
        }
        
        return flag;
    }
    
    /**
     * Note: alphanumeric characters = alphabet + number
     * @param c
     * @return 
     */
    public boolean isAlphanumeric(char c) {
        
        boolean flag = false;
        if((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || (c >= 'A' && c<= 'Z')) {
            flag = true;
        }
        return flag;
    }
    
    public static void main(String[] args) {
        ValidPalindrome vp = new ValidPalindrome();
        System.out.println("A man, a plan, a canal: Panama: " + vp.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println("race car: " + vp.isPalindrome("race car"));
        System.out.println("A man, a plan, a canal: Panama: " + vp.isPalindrome("A man, a plan, a canal: Panama"));
       
    }
}
