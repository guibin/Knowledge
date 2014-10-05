package guibin.zhang.leetcode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Graph representation.
 * http://www.geeksforgeeks.org/graph-and-its-representations/
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class GraphRepresentation {
    
    /**
     * Representation 1 of graph: AdjacencyMatrix.
     * 
     * Pros: Representation is easier to implement and follow.
     * Removing an edge takes O(1) time. 
     * Queries like whether there is an edge from vertex ‘u’ to vertex ‘v’ are efficient and can be done O(1).
     * 
     * Cons: Consumes more space O(V^2). 
     * Even if the graph is sparse(contains less number of edges), it consumes the same space. 
     * Adding a vertex is O(V^2) time.
     */
    public class AdjacencyMatrix {
        
        public AdjacencyMatrix(int v) {
            this.v = v;
        }
        
        int v; // Number of verticles
        // Use a v by v 2d array/matrix to represent the graph.
        // A slot G[i][j] = 1 indicates that there is an edge from vertex i to vertex j. 
        // Adjacency matrix for undirected graph is always symmetric.
        // If adj[i][j] = w, then there is an edge from vertex i to vertex j with weight w.
        int[][] G = new int[v][v];
    }
    
    /**
     * Representation 2 of graph: AdjacencyList.
     * 
     * Size of the map is equal to number of vertices. 
     * 
     * Pros: Saves space O(|V|+|E|). 
     * In the worst case, there can be C(V, 2) number of edges in a graph thus consuming O(V^2) space. 
     * Adding a vertex is easier.
     * 
     * Cons: Queries like whether there is an edge from vertex u to vertex v are not efficient and can be done O(V).
     * 
     */
    public class AdjacencyList {
        
        Map<Integer, List<Integer>> G;
        
        public AdjacencyList(int numOfVertices) {
            G = new HashMap<>();
            for (int i = 0; i < numOfVertices; i++) {
                G.put(i, new ArrayList<>());
            }
        }
        
        /**
         * Add an edge from vertex s to d.
         * @param s source vertex
         * @param d destination vertex
         */
        public void addEdge(int s, int d) {
            List<Integer> slist = G.get(s);
            slist.add(d);
        }
        
        public List<Integer> getEdge(int s) {
            return G.get(s);
        }
    }
    
    
}
