package guibin.zhang.leetcode.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * a/b 输出小数的表示。比如 2/5=0.4 要输出 0.4.
 * 如果有无限循环小数则把 重复的数放在（）里。比如 1/6 应该返回 0.1(6)
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class DecimalString {
    
    public static String getDecimal(int a, int b){
        
        if (a == 0) return "0.0";
        if (b == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(a/b);
        sb.append(".");
        
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> decimals = new ArrayList<>();
        
        int idx = 0, c = a;
        while (c%b != 0 && !map.containsKey(c%b)) {
            map.put(c%b, idx);
            c = c%b * 10;
            decimals.add(c/b);//Collect the result
            idx ++;
        }
        
        if (c%b == 0) {//Divided completely
            for (int i : decimals) {
                sb.append(i);
            }
            sb.append("(0)");
        } else {
            idx = map.get(c%b);
            for (int i = 0; i < idx; i++) {
                sb.append(decimals.get(i));
            }
            sb.append("(");
            for (int i = idx; i < decimals.size(); i++) {
                sb.append(decimals.get(i));
            }
            sb.append(")");
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(DecimalString.getDecimal(2, 3));
        System.out.println(DecimalString.getDecimal(1, 11));
        System.out.println(DecimalString.getDecimal(1, 6));
        System.out.println(DecimalString.getDecimal(1, 3));
        System.out.println(DecimalString.getDecimal(13, 3));
        System.out.println(DecimalString.getDecimal(1, 7));
        System.out.println(DecimalString.getDecimal(7, 9));
        System.out.println(DecimalString.getDecimal(9, 9));
    }
}
