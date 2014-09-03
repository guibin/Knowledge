package guibin.zhang.leetcode.string;

/**
 *
 * Determine whether an integer is a palindrome. Do this without extra space.
 * 
 * click to show spoilers.
 * 
 * Some hints:
 * Could negative integers be palindromes? (ie, -1)
 * 
 * If you are thinking of converting the integer to string, note the restriction of using extra space.
 * 
 * You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", 
 * you know that the reversed integer might overflow. How would you handle such case?
 * 
 * There is a more generic way of solving this problem
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (x < 0) return false;
        
        int result = 0;
        int y = x;//Note, y will be changed, so we need the copy of x.
        while (y != 0) {
            //y%10 is to get the least significant digit of y
            //Then *10 to shift left
            result = result * 10 + y % 10;
            y /= 10;//this is to shift y to right
        }
        System.out.println(x);
        System.out.println(result);
        return x == result;
    }
    
    public static void main(String[] args) {
        PalindromeNumber p = new PalindromeNumber();
        System.out.println(p.isPalindrome(214744741));
    }
}
