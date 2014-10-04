package guibin.zhang.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * 
 * Given a non-negative integer n representing the total number of bits in the code, 
 *   print the sequence of gray code. A gray code sequence must begin with 0.
 * 
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * Note:
 * For a given n, a gray code sequence is not uniquely defined.
 * 
 * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
 * 
 * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class GrayCode {
    
    /**
     * http://www.lifeincode.net/programming/leetcode-gray-code-java/
     * To solve this problem, we need to look at the gray code sequence. Assuming n = 3, we have:
     * 
     * 000
     * 001
     * 011
     * 010
     * 110
     * 111
     * 101
     * 100
     * 
     * We can see that the last two digits of 4 codes at the bottom is 
     * just the descending sequence of the first 4 codes. 
     * The first 4 codes are 0, 1, 3, 2. So, 
     * we can easily get the last 4 codes: 2 + 4, 3 + 4, 1 + 4, 0 + 4, which is 6, 7, 5, 4. 
     * We can keep doing this until we reach n digits.
     * 
     * @param n
     * @return 
     */
    public List<Integer> grayCode_v3(int n) {
        
        List<Integer> result = new ArrayList<>();
        result.add(0);
        for (int i = 0; i < n; i++) {
            int size = result.size();
            for (int j = size - 1; j >= 0; j--) {
                result.add(result.get(j) + size);
            }
        }
        
        return result;
    }
    
    /**
     * http://fisherlei.blogspot.com/2012/12/leetcode-gray-code.html
     * http://baike.baidu.com/view/358724.htm
     * @param n
     * @return 
     */
    public List<Integer> grayCode_v2(int n) {
        
        List<Integer> result = new ArrayList<>();
        if (n == 0) {
            result.add(0);
            return result;
        }
        result.add(0);
        result.add(1);
        for (int i = 1; i < n; i++) {
            List<Integer> tmp = new ArrayList<>(result);
            Integer a = 1 << i;
            for (int k = result.size() - 1; k >= 0; k--) {
                tmp.add(result.get(k) + a);
            }
            result = tmp;
        }
        return result;
    }
    
    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(n == 0) {
            result.add(0);
            return result;
        } else if(n == 1) {
            result.add(0);
            result.add(1);
            return result;
        }
        
        int num = (int)Math.pow(2, n) - 1;
        result.add(num);
        for(int i = n; i >0; i --) {
            num = num >> 1;
            result.add(num);
        }
        num = (int)Math.pow(2, n - 1);
        result.add(num);
        for(int j = n; j > 2; j--) {
            num += num >> 1;
            result.add(num);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        GrayCode gc = new GrayCode();
        List<Integer> result = gc.grayCode_v2(5);
        int j = 0;
        for(int i : result) {
            System.out.println((j++) + "\t" + Integer.toBinaryString(i) + "\t" + i );
        }
        System.out.println();
        System.out.println("n = " + 5);
    }
}
