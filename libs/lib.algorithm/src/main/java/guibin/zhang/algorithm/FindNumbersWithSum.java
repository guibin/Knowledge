package guibin.zhang.algorithm;

import java.util.Arrays;

/**
 * Given a set S of integers and another integer x, determine whether or not
 * there exist two elements in S whose sum is exactly x.
 *
 * @author guibin
 */
public class FindNumbersWithSum {

    public boolean find(int[] s, int x) {
        boolean flag = false;
        //Firstly, sort the s;
        Arrays.sort(s);

        //Then search the numbers with sum x
        int i = 0;
        int j = s.length - 1;
        while (i <= j) {
            if (s[i] + s[j] == x) {
                return true;
            }
            else {
                if(s[i] + s[j] < x) {
                    i = i + 1;
                } else {
                    j = j - 1;
                }
            }
        }

        return flag;
    }
    
    public static void main(String[] args) {
        FindNumbersWithSum f = new FindNumbersWithSum();
        int[] s = {1, 8, 10, 3, 20, 21, 42};
        System.out.println(f.find(s, 30));
        System.out.println(f.find(s, 9));
        System.out.println(f.find(s, 12));
        System.out.println(f.find(s, 22));
        System.out.println(f.find(s, 44));
    }
}
