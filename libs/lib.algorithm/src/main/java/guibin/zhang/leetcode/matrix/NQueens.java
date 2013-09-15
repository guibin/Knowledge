package guibin.zhang.leetcode.matrix;

import java.util.ArrayList;

/**
 * 
 * The n-queens puzzle is the problem of placing n queens on an n*n chessboard 
 * such that no two queens attack each other.
 * 
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * 
 * Each solution contains a distinct board configuration of the n-queens' placement, 
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 * 
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * 
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 * 
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 
 * 
 * http://blog.csdn.net/u011095253/article/details/9158473
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class NQueens {

    /**
     * 我们把这一题分成几个小问题: 
     * 1. 传统的dfs递归
     * 2. 验证放置Queen的地方是否合法
     * 3. 输出Board结果
     * 
     * 用一个数组对应Board里面的每一行，数组每一个值对应这一行放置Queen的列号
     * 比如： int[ ] {3,1,4,2} 代表放置的地点分别为[1,3], [2,1], [3,4], [4,2].
     * 这么一来，我们用很简单的用数组表示了整个Board，而且在isValid函数里判断的时候会非常简洁，
     * 而且把输出Board单独隔离了出来
     * 
     * @param n
     * @return 
     */
    public ArrayList<String[]> solveNQueens(int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        ArrayList<String[]> res = new ArrayList<String[]>();

        int[] loc = new int[n];
        dfs(res, loc, 0, n);
        return res;
    }

    public void dfs(ArrayList<String[]> res, int[] loc, int curr, int n) {
        if (curr == n) {
            printBoard(res, loc, n);
        } else {
            //Note: 要遍历全部可能性，一个循环加一个递归即可
            for (int i = 0; i < n; i++) {
                loc[curr] = i;//Index is the row id, the value is the col id
                if (isValid(loc, curr)) {
                    dfs(res, loc, curr + 1, n);
                }
            }
        }
    }

    public boolean isValid(int[] loc, int curr) {
        for (int i = 0; i < curr; i++) {
            //Since i != curr, the row id is not same.
            //loc[i] and loc[curr] are the col id, they should not be same.
            //If they are in the same diagonal direction, the diffOfColIds == diffOfRowIds
            if (loc[i] == loc[curr] || Math.abs(loc[i] - loc[curr]) == curr - i) {
                return false;
            }
        }
        return true;
    }

    public void printBoard(ArrayList<String[]> res, int[] loc, int n) {
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {//i -> row id
            String row = new String();
            for (int j = 0; j < n; j++) {
                if (j == loc[i]) {//loc[i] -> colid, loc[i] == j is to find the col id
                    row += "Q";
                } else {
                    row += ".";
                }
                ans[i] = row;
            }
        }
        res.add(ans);
    }
}
