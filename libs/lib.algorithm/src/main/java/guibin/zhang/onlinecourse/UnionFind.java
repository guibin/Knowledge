package guibin.zhang.onlinecourse;

/**
 *
 * https://d396qusza40orc.cloudfront.net/algs4partI/slides/15UnionFind.pdf
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public interface UnionFind {
    
    /**
     * Connect two object i and j.
     * Simplify the issue, use just integer represent the object.
     * Later we can use symbol table to translate the actual object to integer.
     * 
     * @param i
     * @param j 
     * @return the root after union i and j
     */
    public int union(int i, int j);
    
    /**
     * Judge whether two object i and j are connected.
     * @param i
     * @param j
     * @return 
     */
    public boolean isConnected(int i, int j);
    
}
