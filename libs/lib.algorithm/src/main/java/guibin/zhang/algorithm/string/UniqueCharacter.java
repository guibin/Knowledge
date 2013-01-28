package guibin.zhang.algorithm.string;

/**
 * Implement an algorithm to determine if a string(ASCII) has all unique characters.
 * 
 * @author guibin
 */
public class UniqueCharacter {
    
    /**
     * Solution 1:
     * Using a boolean array to track the occurrence of each possible character in ASCII set. 
     * 
     * Initially every value in that array is false, until the corresponding character value appears. 
     * If that character value appears again, 
     * then if the corresponding value in array is already true, return false.
     * 
     * Time: O(n), n is the length of string.
     * Space: O(n), the array named mask.
     * 
     * @param s Assume every character in string is in ASCII.
     * @return 
     */
    public boolean isUnique(String s) {
        
        boolean aux[] = new boolean[256];
        for(int i=0; i<s.length(); i++) {
            if(aux[s.charAt(i)])
                return false;
            else {
                aux[s.charAt(i)] = true;
            }
        }
        
        return true;
    }
    
    /**
     * Solution 2:
     * Arrays.sort() firstly, then compare the neighbor elements.
     * 
     */
    
    /**
     * If we don't use any additional structure, what shall we do?
     * 
     * For this solution, the condition is very limited. 
     * Here I use a mask to keep the occurrence of characters with corresponding bits. 
     * E.g. 'a' occurrence will be stored at bit 0, while 'c' occurrence will be stored at bit 2. 
     * So, if mask is 4, that means 'c' has appeared before, 
     * and only 'c' has appreared. 
     * But the cons of this approach is its limited the input string 
     * could only contain continuous and easy handling characters, 
     * while the length of these characters could not be larger than log2(MAX_INT or MAX_LONG). 
     * Here, the integer value is 32-bit, so we could have this approach.
     * 
     * @param s Assume every character in string is between a to z.
     */
    public boolean isUnique2(String s) {
        int mask = 0;
        for(int i = 0; i<s.length(); i++) {
            int tmp = s.charAt(i) - 'a';
            
            if((mask & (1 << tmp)) > 0) {
                return false;
            } else {
                mask |= (1 << tmp);
            }
        }
        return true;
    }
    
    
    public static void main(String[] args) {
        UniqueCharacter uc = new UniqueCharacter();
        System.out.println(uc.isUnique("abcd") + ", " + uc.isUnique2("abcd"));
        System.out.println(uc.isUnique("hello") + ", " + uc.isUnique2("hello"));
    }
}
