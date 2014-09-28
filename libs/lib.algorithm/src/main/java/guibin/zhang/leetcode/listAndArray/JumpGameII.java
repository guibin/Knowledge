package guibin.zhang.leetcode.listAndArray;

/**
 * 
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that position.
 * 
 * Your goal is to reach the last index in the minimum number of jumps.
 * 
 * For example:
 * Given array A = [2,3,1,1,4]
 * 
 * The minimum number of jumps to reach the last index is 2. 
 * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 * 
 * 
 * http://blog.csdn.net/doublechen_it/article/details/7880311
 * http://tech-wonderland.net/blog/leetcode-jump-game-ii.html
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class JumpGameII {
    
    /**
     * Start from slot i, we use max to track the farthest slot we can reach within one jump from i 
     * and use next to track the next position where we need to jump. 
     * 
     * It is possible that the endpoint is unreachable (e.g. 3 2 1 0 0 0 0 0 0 1). 
     * In that case, at some jump point, we will find out that we are jumping back. 
     * Then game over.
     * 
     * Actually on each step, the farthest slot is (i + A[i]), 
     * @param A
     * @return 
     */
    public int jump(int[] A) {
        
        int steps = 0;
        int max = A[0];//current maximum distances, 
        int next = 0;//next maximum reachable distance
        for (int i = 0; i < A.length; i++) {
            if (i > next) {
                if (i > max) return -1;//Unreachable
                next = max;//Greedy algo.
                steps++;
            }
            max = Math.max(max, i + A[i]);
        }
        return steps;
    }
}
