package guibin.zhang.leetcode.permutationAndCombination;

/**
 * There are at most 8 servers in a data center. 
 * Each server has got a capacity/memory limit. 
 * There can be at most 8 tasks that need to be scheduled on those servers. 
 * Each task requires certain capacity/memory to run, 
 * and each server can handle multiple tasks as long as the capacity limit is not hit. 
 * 
 * Write a program to see if all of the given tasks can be scheduled or not on the servers? 
 * 
 * Ex: 
 * Servers capacity limits: 8, 16, 8, 32 
 * Tasks capacity needs: 18, 4, 8, 4, 6, 6, 8, 8 
 * For this example, the program should say 'true'. 
 * 
 * Ex2: 
 * Server capacity limits: 1, 3 
 * Task capacity needs: 4 
 * For this example, program should return false. 
 * 
 * Got some idea that this needs to be solved using dynamic programming concept, 
 * but could not figure out exact solution.
 * 
 * Question link: http://www.careercup.com/question?id=6282171643854848
 * 
 * Idea:
 * This question can be solved by DFS.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class ResourceArrangement {
    
    public static boolean canArrange(int[] servers, int[] tasks, boolean[] arranged) {
        
        boolean allArranged = true;
        for (boolean b : arranged) {
            allArranged &= b;
        }
        if (allArranged) return true;
        
        //For each task[i], make all the combination of server.
        for (int i = 0; i < tasks.length; i++) {
            if (!arranged[i]) {
                arranged[i] = true;
                for (int j = 0; j < servers.length; j++) {
                    if (servers[j] >= tasks[i]) {
                        servers[j] = servers[j] - tasks[i];
                        if (canArrange(servers, tasks, arranged)) {
                            return true;
                        }
                        servers[j] = servers[j] + tasks[i];
                    }
                }
                arranged[i] = false;
            }
        }
        return false;
    }
    
    public static boolean canArrange(int[] servers, int[] tasks) {
        boolean[] arranged = new boolean[tasks.length];
        return canArrange(servers, tasks, arranged);
    }
    
    public static void main(String[] args) {
        int[] servers = {8, 16, 8, 32};
        int[] tasks = {18, 4, 8, 4, 6, 6, 8, 8 };
        System.out.println(canArrange(servers, tasks));
        
        int[] servers2 = {1, 3};
        int[] tasks2 = {4};
        System.out.println(canArrange(servers2, tasks2));
    }
}
