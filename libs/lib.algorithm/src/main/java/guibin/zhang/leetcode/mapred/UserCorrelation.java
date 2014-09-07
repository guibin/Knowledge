package guibin.zhang.leetcode.mapred;

/**
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class UserCorrelation {
   /**
    * Given input looks like below: 
    * U1:[Q1, Q3, …]
    * U2:[Q1, Q2, …]
    * U3:[Q1, …]
    * U4:[Q2, …]
    * Which means User 1 executed Query1, Query3, ..., User 2 executed Query1, Query2, ...
    * 
    * Please compute the similarity of any two users, which means: 
    * generate all the possible combination of Users that share the same query.
    * The expected output should look like:
    * <U1, U2>: [Q1]
    * <U2, U3>: [Q1]
    * <U4, U5>: [...]
    * 
    * 
    * ********** Solution 1 *********:
    * 
    * Job 1:
    * Mapper input
    * U1:[Q1, Q3, …]
    * U2:[Q1, Q2, …]
    * U3:[Q1, …]
    * U4:[Q2, …]
    * 
    * Mapper output
    * Q1:U1
    * Q3:U1
    * Q1:U2
    * Q2:U2
    * Q1:U3
    * Q2:U4
    * 
    * Reducer Input
    * Q1: [U1, U2, U3, …]
    * Q2: [U2, U4, …]
    * Q3: [U1, …]
    * 
    * If the value list of each Q can be fitted in memory, we can
    * collect each value into a list in memory, do permutation with the value list,
    * then emit the value:
    * 
    * Reducer output
    * <U1, U2>: Q1
    * <U1, U3>: Q1
    * <U2, U3>: Q1
    * <U2, U1>: Q1
    * <U2, U3>: Q1
    * ...
    * 
    * Job 2:
    * IdenticalMapper
    * Reducer output
    * <U1, U2>: [Q1, Q4, ...]
    * <U1, U3>: [Q1, Q5, ...]
    * <U2, U3>: [Q1, Qx.....]
    * 
    * 
    * 
    * 
    ********** Solution 2 *********:
    * If Job1's Reducer Input cannot be fit into memory:
    * 
    * Job 1 Mapper remains same as solution 1.
    * Job 1:
    * Mapper input
    * U1:[Q1, Q3, …]
    * U2:[Q1, Q2, …]
    * U3:[Q1, …]
    * U4:[Q2, …]
    * 
    * Mapper output
    * Q1:U1
    * Q3:U1
    * Q1:U2
    * Q2:U2
    * Q1:U3
    * Q2:U4
    * 
    * Reducer Input
    * Q1: [U1, U2, U3, …]
    * Q2: [U2, U4, …]
    * Q3: [U1, …]
    * 
    * Reducer Output
    * Q1: [U1, U2, U3, …]
    * Q2: [U2, U4, …]
    * Q3: [U1, …]
    * 
    * Job 2:
    * A) Customize the InputFormat, to control the length of each split, typically make it less than 128M.
    * Refer to NLineInputFormat: https://gist.github.com/airawat/6639753
    * 
    * Mapper input:
    * Q1: [U1, U2, U3, …]
    * Q2: [U2, U4, …]
    * Q3: [U1, …]
    * 
    * Do the permutation for the value in mapper, emit the permutation as key, Q as value
    * Mapper output:
    * <U1,U1>: Q1
    * <U1,U2>: Q1
    * <U1,U3>: Q1
    * <U2,U1>: Q1
    * <U2,U2>: Q1
    * <U2,U3>: Q1
    * <U2,U4>: Q1
    * <U3,U1>: Q1
    * <U3,U2>: Q1
    * <U3,U3>: Q1
    * <U2,U2>: Q2
    * <U2,U4>: Q2
    * <U4,U2>: Q2
    * <U4,U4>: Q2
    * 
    * Reducer input:
    * <U1, U2>: [Q1, Q4, ...]
    * <U1, U3>: [Q1, Q5, ...]
    * <U2, U3>: [Q1, Qx.....]
    * 
    * 
    */ 
}
