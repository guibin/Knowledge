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
        dfs(result, loc, 0);
        return result[0];
    }

    public void dfs(int[] result, int[] branch, int idx) {

        if (idx == branch.length) {
            result[0]++;
            return;
        }
        
        for (int i = 0; i < branch.length; i++) {
            //Note: curr is the row index, i is the cols index.
            branch[idx] = i;
            if (isValid(branch, idx)) {
                dfs(result, branch, idx + 1);
            }
        }
    }

    public boolean isValid(int[] branch, int idx) {
        
        for (int i = 0; i < idx; i++) {
            if (branch[idx] == branch[i] || Math.abs(branch[idx] - branch[i]) == idx - i) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        NQueensII nq = new NQueensII();
        System.out.println(nq.totalNQueens(5));
    }
}
