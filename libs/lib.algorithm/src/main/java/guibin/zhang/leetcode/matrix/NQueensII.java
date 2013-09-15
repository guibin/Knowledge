package guibin.zhang.leetcode.matrix;

/**
 * 
 * Follow up for N-Queens problem.
 * 
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 * 
 * 
 * Run Status: Accepted!
 * Program Runtime: 1404 milli secs
 * Progress: 12/12 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class NQueensII {
    public int totalNQueens(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int[] loc = new int[n];
        int[] result = new int[1];
        dfs(result, loc, 0, n);
        return result[0];
    }

    public void dfs(int[] result, int[] loc, int curr, int n) {

        if (curr == n) {
            result[0]++;
            return;
        }
        for (int i = 0; i < n; i++) {
            //Note: curr is the row index, i is the cols index.
            loc[curr] = i;
            if (isValid(loc, curr)) {
                dfs(result, loc, curr + 1, n);
            }
        }
    }

    public boolean isValid(int[] loc, int curr) {
        for (int i = 0; i < curr; i++) {
            if (loc[curr] == loc[i] || Math.abs(loc[curr] - loc[i]) == curr - i) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        NQueensII nq = new NQueensII();
        System.out.println(nq.totalNQueens(4));
    }
}
