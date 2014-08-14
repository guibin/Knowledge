package guibin.zhang.leetcode.dp;

/**
 * Count all possible walks from a source to a destination with exactly k edges
 * Given a directed and two vertices ‘u’ and ‘v’ in it, 
 * count all possible walks from ‘u’ to ‘v’ with exactly k edges on the walk.
 * 
 * The graph is given as adjacency matrix representation where value of graph[i][j] as 1 
 * indicates that there is an edge from vertex i to vertex j 
 * and a value 0 indicates no edge from i to j.
 * 
 * For example consider the following graph. 
 * Let source ‘u’ be vertex 0, destination ‘v’ be 3 and k be 2. 
 * The output should be 2 as there are two walk from 0 to 3 with exactly 2 edges. The walks are {0, 2, 3} and {0, 1, 3}
 * 
 * 0 -> 1
 * 0 -> 2
 * 0 -> 3
 * 2 -> 3
 * 1 -> 3
 * 
 * Question link: http://www.geeksforgeeks.org/count-possible-paths-source-destination-exactly-k-edges/
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class GraphCountWalk {
    
    /**
     * The efficiency is O(V **k), k is the number of vertices of the give graph.
     * 
     * @param graph 
     * @param u start point
     * @param v end point
     * @param k k steps required
     * @return Possible ways of path from u to v with k steps.
     */
    public static int countWalksNaive(int[][] graph, int u, int v, int k) {
        
        // The first special case: (u == v) => start point is same as end point.
        // So we have only one possible way to arrive v, return 1.
        if (k == 0 && u == v) return 1;
        // The second base case: graph[u][v] == 1 means there is a way from u to v
        // So this is a possible way, return 1.
        if (k == 1 && graph[u][v] == 1) return 1;
        // This is the invalid edge case, when k less than or equal 0, impossible, return 0
        if (k <= 0) return 0;
        
        // Initialize the count
        int count = 0;
        
        // Go to all adjacents of u and recur
        for (int j = 0; j < graph.length; j++) {
            //This means there is path from u to j, we will try u -> 0~n, each edge from u
            if (graph[u][j] == 1) {
                //Then continue try from j to v
                count += countWalksNaive(graph, j, v, k - 1);
            }
        }
        return count;
    }
    
    /**
     * We can optimize the above solution using Dynamic Programming. 
     * 
     * The idea is to build a 3D table where 
     * first dimension is source, 
     * second dimension is destination, 
     * third dimension is number of edges from source to destination, 
     * and the value is count of walks. 
     * 
     * Like other Dynamic Programming problems, we fill the 3D table in bottom up manner.
     * 
     * @param graph
     * @param u
     * @param v
     * @param k
     * @return 
     */
    public static int countWalks(int[][] graph, int u, int v, int k) {
        
        // Table to be filled up using DP. The value count[i][j][e] will
        // store count of possible walks from i to j with exactly k edges
        int[][][] count = new int[graph.length][graph.length][k + 1];
        
        // Loop for number of edges from 0 to k
        for (int e = 0; e <= k; e++) {
            for (int i = 0; i < graph.length; i ++) {//for source
                for (int j = 0; j < graph.length; j++) {//for destination
                    //initialize value
                    count[i][j][e] = 0;
                    
                    //from base cases
                    if (e == 0 && i == j) count[i][j][e] = 1;
                    if (e == 1 && graph[i][j] == 1) count[i][j][e] = 1;
                    
                    //Go the adjancent only when number of edges is more than 1
                    if (e > 1) {
                        for (int a = 0; a < graph.length; a ++) {// adjacent of source i
                            if (graph[i][a] == 1) {//There is a path from i to a
                                count[i][j][e] += count[a][j][e - 1];
                            }
                        }
                    }
                }
            }
        }
        
        return count[u][v][k];
    }
    
    public static void main(String[] args) {
        int[][] graph = {{0, 1, 1, 1},
                         {0, 0, 0, 1},
                         {0, 0, 0, 1},
                         {0, 0, 0, 0}
                        };
        int u = 0, v = 3, k = 2;
        System.out.println(countWalksNaive(graph, u, v, k));
        System.out.println(countWalks(graph, u, v, k));
    }
}
