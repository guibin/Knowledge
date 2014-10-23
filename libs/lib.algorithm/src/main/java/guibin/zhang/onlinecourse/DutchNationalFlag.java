package guibin.zhang.onlinecourse;

/**
 * Dutch national flag. 
 * Given an array of N buckets, each containing a red, white, or blue pebble, sort them by color. 
 * The allowed operations are:
 * swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
 * color(i): color of pebble in bucket i.
 * 
 * The performance requirements are as follows:
 * At most N calls to color().
 * At most N calls to swap().
 * Constant extra space.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class DutchNationalFlag {
    
    
    //It is very smiliar with the QuickSort3Way
    public void sort(Character[] flags) {
        
        int red = 0;// Initial index for red
        int blue = flags.length - 1;//Initial index for blue
        int i = 0;
        while (i <= blue) {
            if (flags[i] == 'r') {
                swap(flags, red++, i++);
            } else  if (flags[i] == 'b') {
                swap(flags, blue--, i);
                //Here don't increase i, since after swap, the flags[i] has not been checked.
            } else {
                i ++;
            }
        }
    }
    
    public void swap(Character[] t, int i, int j) {
        Character tmp = t[i];
        t[i] = t[j];
        t[j] = tmp;
    }
    
    public static void main(String[] args) {
        DutchNationalFlag f = new DutchNationalFlag();
        
        Character[] t = {'b', 'r', 'w', 'w', 'r', 'b', 'r'};
        f.sort(t);
        for (char c : t) {
            System.out.print(c);
        }
        System.out.println();;
    }
}
