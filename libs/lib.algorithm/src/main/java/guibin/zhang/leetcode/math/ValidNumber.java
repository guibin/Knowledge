package guibin.zhang.leetcode.math;

/**
 * Validate if a given string is numeric.
 * 
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * Note: It is intended for the problem statement to be ambiguous. 
 * You should gather all requirements up front before implementing one.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class ValidNumber {
    
    public boolean isNumber(String s) {
        
        s = s.trim();
        int dotnum = 0;
        int eNum = 0;
        int dotpos = -1;
        int epos = -1;
        int digitnum = 0;

        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            } else if (s.charAt(i) == 'e') {
                eNum++;
                epos = i;
            }
            else if (s.charAt(i) == '.') {
                dotnum++;
                dotpos = i;
            } else if (!Character.isDigit(s.charAt(i)))
                return false;
            else digitnum++;
        }
        if (dotnum > 1 || eNum > 1 || epos == 0 || epos == s.length()-1 || 
                digitnum == 0 || 
                (eNum == 1 && dotnum == 1 && ((epos < dotpos) || dotpos == 0))) return false;

        return true; 
    }
    
    public static void main(String[] args) {
        ValidNumber vn = new ValidNumber();
        System.out.println(vn.isNumber(".1"));
    }
}
