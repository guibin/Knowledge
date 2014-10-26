package guibin.zhang.leetcode.string;

import java.util.ArrayList;
import java.util.List;

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
    
    public List<String> restoreIpAddresses_v3(String s) {
        ArrayList<String> res = new ArrayList<String>();  
        if (s.length()<4||s.length()>12) return res;  
        dfs(s,"",res,0);  
        return res; 
    }
    
    public void dfs(String s, String tmp, ArrayList<String> res, int count){  
        if (count == 3 && isValid(s)) {  
            res.add(tmp + s);  
            return;  
        }  
        for(int i=1; i<4 && i<s.length(); i++){  
            String substr = s.substring(0,i);  
            if (isValid(substr)){  
                dfs(s.substring(i), tmp + substr + '.', res, count+1);  
            }  
        }  
    }  
      
    public boolean isValid(String s){  
        if (s.charAt(0)=='0') return s.equals("0");  
        int num = Integer.parseInt(s);  
        return num<=255 && num>0;  
    }
    
    
    public List<String> restoreIpAddresses_v2(String s) {
        return f(s, 4);
    }
    
    private List<String> f(String s, int n) {
        List<String> r = new ArrayList<>();
        if(s.length()==0) return r;
        if(n==1) {
            if(check(s)) r.add(s);
            return r;
        }
        for(String t : f(s.substring(1), n-1)) r.add(s.substring(0,1)+"."+t);
        if(s.length()>=2 && check(s.substring(0,2))) {
            for(String t: f(s.substring(2), n-1)) r.add(s.substring(0,2)+"."+t);
        }
        if(s.length()>=3 && check(s.substring(0,3))) {
            for(String t: f(s.substring(3), n-1)) r.add(s.substring(0,3)+"."+t);
        }
        return r;
    }
    private boolean check(String s) {
        if(s.length()==0) return false;
        if(s.length()==1) return true;
        if(s.charAt(0)=='0') return false;
        if(s.length()>3) return false;
        int v= Integer.valueOf(s);
        return 10<=v && v<=255;
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
