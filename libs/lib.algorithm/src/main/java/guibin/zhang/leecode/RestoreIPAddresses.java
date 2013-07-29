package guibin.zhang.leecode;

import java.util.ArrayList;

/**
 *
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * 
 * For example:
 * Given "25525511135",
 * 
 * return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class RestoreIPAddresses {
    
    static final int IP_COUNT = 4;
    static final int IP_LEN = 3;
    
    public ArrayList<String> restoreIpAddresses_error(String s) {
        
        ArrayList<String> result = new ArrayList<String>();
        int length = s.length();
        if(length < 4 || length > 12) {
            result.add(null);
            return result;
        }
        if("0000".equals(s)) {
            result.add("0.0.0.0");
            return result;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= length - 3; i ++) {
            String s01 = s.substring(0, i);
            int seg01 = Integer.parseInt(s01);
            for(int j = i + 1; j <= length - 2 && !s01.startsWith("0") && seg01 <= 255 && seg01 >= 0; j ++) {
                String s02 = s.substring(i, j);
                int seg02 = Integer.parseInt(s02);
                for(int k = j + 1; k <= length - 1 && !s02.startsWith("0") && seg02 <= 255 && seg02 >= 0; k ++) {
                    String s03 = s.substring(j, k);
                    int seg03 = Integer.parseInt(s03);
                    for(int m = k + 1; m <= length && !s03.startsWith("0") && seg03 <= 255 && seg03 >= 0; m ++) {
                        String s04 = s.substring(k, m);
                        int seg04 = Integer.parseInt(s04);
                        if(!s04.startsWith("0") && seg04 <= 255 && seg04 >= 0) {
                            sb.append(seg01).append(".").append(seg02).append(".").append(seg03).append(".").append(seg04);
                            result.add(sb.toString());
                            sb = new StringBuilder();
                        }
                    }
                }
            }
        }
        return result;
    }
    
    public ArrayList<String> restoreIpAddresses(String s) {
        
        ArrayList<String> res = new ArrayList<String>();
        searchIp(s.toCharArray(), 0, new int[IP_COUNT], res);
        return res;
    }
    
    /**
     * 
     * @param cs
     * @param index is from 0 to 4, it is the # of the segment of the ip address
     * @param ipIndexs ipIndex[0] is the position of the first point, ipIndex[1] is the position of the second point
     *                 ipIndex[3] is the position of the third point, ipIndex[3] is the end position of the string
     * @param res 
     */
    private void searchIp(char[] cs, int index, int[] ipIndexs, ArrayList<String> res) {
        
        int n = cs.length;
        /**
         * index == 4 means all the ipIndexs has been filled, 
         * the validation of each segment has been finished.
         */
        if (index == IP_COUNT) {
            if (ipIndexs[IP_COUNT - 1] != n) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < IP_COUNT; i++) {
                int s = i == 0 ? 0 : ipIndexs[i - 1];
                int e = ipIndexs[i];
                sb.append(cs, s, e - s);
                if (i != IP_COUNT - 1) {
                    sb.append('.');
                }
            }
            res.add(sb.toString());
            return;
        }

        int start = index == 0 ? 0 : ipIndexs[index - 1];
        for (int i = 0; i < IP_LEN && start + i < n; i++) {
            if (isValid(cs, start, start + i + 1)) {
                ipIndexs[index] = start + i + 1;
                searchIp(cs, index + 1, ipIndexs, res);
            }
        }
    }
    
    private boolean isValid(char[] cs, int s, int e) {
        int n = e - s;
        if (n == 0 || n > IP_LEN)
            return false;
        if (cs[s] == '0') {
            return n == 1;
        }
        int sum = 0;
        for (int i = s; i < e; i++) {
            sum *= 10;
            sum += cs[i] - '0';
        }
        return sum <= 255;
    }
    
    public static void main(String[] args) {
        RestoreIPAddresses ri = new RestoreIPAddresses();
        for(String s : ri.restoreIpAddresses("25525511135")) {
            System.out.println(s);
        }
    }
}
