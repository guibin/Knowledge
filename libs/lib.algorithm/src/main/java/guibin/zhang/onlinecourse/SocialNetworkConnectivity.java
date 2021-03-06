package guibin.zhang.onlinecourse;

/**
 * 
 * Social network connectivity
 * 
 * https://class.coursera.org/algs4partI-005/quiz/attempt?quiz_id=89
 * 
 * Given a social network containing N members and a log file 
 * containing M timestamps at which times pairs of members formed friendships, 
 * design an algorithm to determine the earliest time at which 
 * all members are connected 
 * (i.e., every member is a friend of a friend of a friend ... of a friend). 
 * Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. 
 * 
 * The running time of your algorithm should be MlogN or better and use extra space proportional to N.
 * 
 * Reference
 * http://jznest.wordpress.com/2014/02/15/algorithms-part-i-social-network-connectivity-with-union-find/
 * https://gist.github.com/jingz8804/9026369
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class SocialNetworkConnectivity implements UnionFind{

    // Save the object to parent.
    private int[] ids;
    
    // Extra space to save the # of objects in the tree rooted at i.
    private int[] sz;
    
    // Extra space to save the height of root(i)
    private int[] height;
    
    // The number of connected components
    private int numOfConnectedComponent;
    
    // Keep track of the maxmium node in each connected component **for each root** of connected component
    private int[] maximum;
    
    public SocialNetworkConnectivity(int N) {
        // Set id of each object to itself. This need N array accesses.
        ids = new int[N];
        for (int i = 0; i < N; i++) {
            ids[i] = i;
            sz[i] = 1;
            maximum[i] = i;
            height[i] = 1;
        }
        numOfConnectedComponent = N;
    }
    
    /**
     * Find the root of the object.
     * ids[i] is the parent of i.
     * @param i
     * @return 
     */
    private int root(int i) {
        
        int n = i;
        int m = i;
        while (n != ids[n]) {
            // Method 1 for path compress:
            // Make each other node in path point to its grandparent, thereby halving path length.
            //ids[i] = ids[ids[i]];
            n = ids[n];
        }
        
        // Method 2 for path compress
        while (m != ids[m]) {
            int tmp = ids[m]; //Save the parent of m
            ids[m] = n;//Point m to the root
            m = tmp;//Continue to m's original parent
        }
        
        return n;
    }
    
    /**
     * Check whether a node is root.
     * @param i
     * @return true if i is root.
     */
    private boolean isRoot(int i) {
        return ids[i] == i;
    }
    
    /**
     * Change the root of q to point to root of q.
     * @param p
     * @param q 
     * @return the root of p and q
     */
    @Override
    public int union(int p, int q) {
        int i = root(p);
        int j = root(q);
        //Direct point parent of i to j. No polocy here.
        ids[i] = j;
        return j;
    }
    
    /**
     * Link root of small tree to the root of larger tree
     * @param p
     * @param q
     * @return The root of p and q after union.
     */
    public int unionBySize(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ) return rootP;
        int rstRoot;
        if (sz[rootP] < sz[rootQ]) {
            //point parent of p to q, since q is big tree
            ids[rootP] = rootQ;
            //Update cache for # of objects
            sz[rootQ] += sz[rootP];
            //Update the maxium
            if (maximum[rootQ] < maximum[rootP]) {
                maximum[rootQ] = maximum[rootP];
            }
            rstRoot = rootQ;
        } else {
            ids[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
            rstRoot = rootP;
            if (maximum[rootP] < maximum[rootQ]) {
                maximum[rootP] = maximum[rootQ];
            }
        }
        
        numOfConnectedComponent --;
        return rstRoot;
    }
    
    public int unionByHeight(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ) return rootP;
        int rstRoot;
        
        if (height[rootP] > height[rootQ]) {
            //point parent of q to p, since p is heigher tree
            ids[rootQ] = rootP;
            //Update the height[rootQ] to the highter one
            height[rootQ] = height[rootP];
            rstRoot = rootP;
        } else if (height[rootP] < height[rootQ]) {
            ids[rootP] = rootQ;
            height[rootP] = height[rootQ];
            rstRoot = rootQ;
        } else {
            //Link q to p when these two tree are same height.
            ids[rootP] = rootQ;
            rstRoot = rootQ;
            //increase height
            height[rootQ]++;
        }
        return rstRoot;
    }
    
    /**
     * If the two nodes have the same root, they are connected.
     * @param p
     * @param q
     * @return 
     */
    @Override
    public boolean isConnected(int p, int q) {
        return root(p) == root(q);
    }
    
    public boolean isAllConnected() {
        return numOfConnectedComponent == 1;
    }
    
    /**
     * Add a method find() to the union-find data type 
     * so that find(i) returns the largest element in the connected component containing i. 
     * The operations, union(), connected(), and find() should all take logarithmic time or better.
     * 
     * For example, if one of the connected components is {1,2,6,9}, 
     * then the find() method should return 9 for each of the four elements in the connected components.
     * 
     * @param i
     * @return
     */
    public int findMaxiumInSameComponent(int i) {
        return maximum[root(i)];
    }
}
