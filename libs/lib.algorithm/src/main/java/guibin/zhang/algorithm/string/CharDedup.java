package guibin.zhang.algorithm.string;

/**
 *
 * @author guibin
 */
public class CharDedup {
    
    /**
     * Given a string, remove the duplicated character from it.
     * Limitation: Don't use any additional data structure.
     * @param str
     * @return 
     */
    public String dedup1(String str) {
        char[] arr = str.toCharArray();
        int mask = 0;
        int j = -1;//Mark the end of the dedupped chars.
        for(int i = 0; i < arr.length && j < arr.length;) {
            
            int tmp = arr[i] - 'a';
            if((mask & (1 << tmp)) > 0) {//duplicated
                i ++;
            } else {
                mask |= (1 << tmp);
                j ++;
                arr[j] = arr[i];
                i ++;
            }
        }
        
        for(int m = j + 1; m < arr.length; m++) {
            arr[m] = 'X';
        }
        
        return new String(arr);
    }
    
    public String dedup2(String str) {
        char[] arr = str.toCharArray();
        int i = 0;//Mark the end of the dedupped chars.
        int j = 1;//Moving from 1 to the end of the char array to compare with each j.
        
        while(i < arr.length && j < arr.length) {
            int k = 0;
            for(; k<=i; k++) {
                if(arr[k] == arr[j]) {
                    break;
                }
            }
            
            //Note: after the loop, k should be i+1.
            if((k-1 == i) && (arr[i] != arr[j])) {//No duplicated
                i ++;
                arr[i] = arr[j];
                j ++;
            } else {
                j ++;
            }
        }
        
        for(int m=i + 1; m<str.length(); m++) {
            arr[m] = 'X';
        }
        
        return new String(arr);
    }
    
    public static void main(String[] args) {
        CharDedup cd = new CharDedup();
        System.out.println(cd.dedup1("aabbbcd"));
        System.out.println(cd.dedup2("aabbbcd"));
        System.out.println(cd.dedup1("helolm"));
        System.out.println(cd.dedup2("helolm"));
        System.out.println(cd.dedup1("Myinternetcmcc"));
        System.out.println(cd.dedup2("Myinternetcmcc"));
    }
}
