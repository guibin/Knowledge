package guibin.zhang.algorithm.string;

import java.util.Arrays;

/**
 * Write a method which will remove any given character from a String.
 * 
 * @author guibin
 */
public class RemoveAnyCharacter {
    
    public String remove(char c, String from) {
        
        char[] original = from.toCharArray();
        
        int counter = 0;
        for(int i=0; i < original.length; i++) {
            if(original[i] == c) {
                counter ++;
                continue;
            }
            
            if(counter > 0) {
                original[i - counter] = original[i];
            }
        }
        
        return new String(Arrays.copyOfRange(original, 0, original.length - counter));
    }
    
    public String remove2(char c, String from) {
        
        StringBuilder sb = new StringBuilder();
        char[] original = from.toCharArray();
        
        for(int i=0; i<from.length(); i++) {
            if(original[i] != c) {
                sb.append(original[i]);
            }
        }
            
        return sb.toString();
    }
    
    public static void main(String[] args) {
        RemoveAnyCharacter ra = new RemoveAnyCharacter();
        
        System.out.println(ra.remove('c', "abcdced") + ", " + ra.remove2('c', "abcdced"));
        System.out.println(ra.remove('f', "abcdced") + ", " + ra.remove2('f', "abcdced"));
    }
    
}
