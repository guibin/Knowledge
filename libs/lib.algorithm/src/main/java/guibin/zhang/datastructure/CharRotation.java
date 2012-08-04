package guibin.zhang.datastructure;

/**
 * Given two strings s1 and s2, please check if s2 can be got by s1 rotation
 * For example: if s1 = AABCD and s2 = CDAA, then return true.
 * if s1 = ABCD and s2 = ACBD, then return false.
 * 
 * Answer:  (s1 + s1).contains(s2) 
 * AABCDAABCD contains CDAB => AABCD can be rotated to CDAA
 * 
 * @author Guibin Zhang <guibin.beijing at gmail.com>
 */
public class CharRotation {
    private String s1 = null;
    private String s2 = null;
    
    public CharRotation(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
    }
    
    public boolean canBeRotated() {
        return (s1 + s1).contains(s2);
    }
    
    public static void main(String[] args) {
        CharRotation cr = new CharRotation("AABCD", "CDAA");
        System.out.println(cr.canBeRotated());
        
        cr = new CharRotation("AABCD", "ACBD");
        System.out.println(cr.canBeRotated());
    }
}
