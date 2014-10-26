package guibin.zhang.leetcode.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a roman numeral, convert it to an integer.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * I = 1;
 * V = 5;
 * X = 10;
 * L = 50;
 * C = 100;
 * D = 500;
 * M = 1000;
 * 
 * 相同的数字连写，所表示的数等于这些数字相加得到的数，如：Ⅲ = 3；
 * 小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数， 如：Ⅷ = 8；Ⅻ = 12；
 * 小的数字，（限于Ⅰ、X 和C）在大的数字的左边，所表示的数等于大数减小数得到的数，如：Ⅳ= 4；Ⅸ= 9；
 * 正常使用时，连写的数字重复不得超过三次。（表盘上的四点钟“IIII”例外）
 * 在一个数的上面画一条横线，表示这个数扩大1000倍。
 * 
 * 在左边出现的小的数字，仅仅会出现一次，因此只需要判断相邻两位的大小就可以知道是否是减法
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class RomanToInt {
    
    public int romanToInt(String s) {
        
        Map<Character,Integer > map = new HashMap< >();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        
        int result = 0;
        for(int i = 0; i < s.length(); i++){
            //Scan each char
            int curr = map.get(s.charAt(i));
            //Compare char with the one of right side
            if( (i + 1 < s.length()) && (curr < map.get(s.charAt(i+1))))
                //If right side is bigger, minus curr
                curr = -curr;
            result += curr;
        }
        
        return result;
    }
}
