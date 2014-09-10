package guibin.zhang.leetcode.dp;

/**
 * 
 * You are climbing a stair case. It takes n steps to reach to the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * 
 * Run Status: Accepted!
 * Program Runtime: 472 milli secs
 * Progress: 45/45 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ClimbingStairs {
    
    /**
     * ways[i] means # of ways to climb i stairs.
     * 
     * When climbing n stairs, there are two ways:
     * A) Climbing the last two stairs + ways[n-2]
     * B) Climbing the last one stairs + ways[n-1]
     */
    public int climbStairs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        int[] ways = new int[n + 1];
        ways[0] = 0;
        ways[1] = 1;
        ways[2] = 2;
        
        for (int i = 3; i <= n; i++) {
            ways[i] = ways[i-2] + ways[i - 1];
        }
        
        return ways[n];
    }
    
    public int climbStairs_v2(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        int second = 1;//There is only 1 ways for the second step, 1 stair or nothing.
        int first = 2;//There is two ways for the first step, 1 stair or two stairs.
        int result = 0;
        
        for (int i = 3; i <= n; i++) {
            result = first + second;
            second = first;
            first = result;
        }
        return result;
    }
}
