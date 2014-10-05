package guibin.zhang.leetcode.listAndArray;

/**
 *
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * 
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
 * You begin the journey with an empty tank at one of the gas stations.
 * 
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * 
 * Note:
 * The solution is guaranteed to be unique.
 * 
 * 
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class GasStation {
    
    /**
     * first, if total gas is enough for total cost, this point can be easily checked by sum all gas[i]-cost[i].
     * second, what is the start point? it is a little bit harder to finish in O (n).
     * 
     * however, we can consider this problem in another angel which is if the gas[i]-cost[i]<0 which
     * mean the i can not been an start point. 
     *  
     * So we assume start point at 0, and we also declare two varibles which are sum and total, 
     * sum is used to record the total   from gas[i]-cost[i], 0<=i<=n, unitl  i position . 
     * if sum <0 which mean before i can not be start point, then we increase start pont one position and sum changed back to 0. 
     * 
     * However, at the end , if the final start point is what we want? maybe, it is also decided by the
     * total valuable. total is the sum of all gas[i]-cost[i]. our final start point is
     * an valid one  only when total>= 0,  or we should return -1;
     * 
     * @param gas
     * @param cost
     * @return 
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        
        if (gas == null || cost == null || 
                gas.length != cost.length || 
                gas.length == 0 || cost.length == 0) 
            return -1;
        
        int sum = 0, total = 0, start = 0;
        
        for (int i = 0; i < gas.length; i++) {
            sum += gas[i] - cost[i];
            total += sum;
            if (sum < 0) {
                sum = 0;
                start = i + 1;
            }
        }
        if (total < 0) return -1;
        return start;
    }
}
