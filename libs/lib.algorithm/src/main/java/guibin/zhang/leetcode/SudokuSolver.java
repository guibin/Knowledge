package guibin.zhang.leetcode;

import java.util.ArrayList;

/**
 *
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 * Empty cells are indicated by the character '.'.
 * 
 * You may assume that there will be only one unique solution.
 * 
 * http://www.kaixinwenda.com/article-huazi0204-8772099.html
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        ArrayList<int[]> empty = new ArrayList<int[]>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    int[] tmp = {i, j};
                    empty.add(tmp);
                }
            }
        }
        int len = empty.size();
        dfs(empty, board, 0, len);
    }
    
    public boolean isValid(int value, int row, int col, char[][] board) {
        for (int i = 0; i < 9; i++) {
            //check row
            if (Character.getNumericValue(board[row][i]) == value) {
                return false;
            }
            //check col
            if (Character.getNumericValue(board[i][col]) == value) {
                return false;
            }
            //check square
            int row_s = 3 * (int) (row / 3) + (int) i / 3;
            int col_s = 3 * (int) (col / 3) + i % 3;
            if (Character.getNumericValue(board[row_s][col_s]) == value) {
                return false;
            }
        }

        return true;
    }
    
    public boolean dfs(ArrayList<int[]> empty, char[][] board, int i, int len) {
        int[] index = empty.get(i);
        int row = index[0];
        int col = index[1];
        boolean res = false;
        for (int v = 1; v <= 9; v++) {
            if (isValid(v, row, col, board)) {
                board[row][col] = (char)(v + '0');
                if (i < len - 1) {
                    res = dfs(empty, board, i + 1, len);
                    if (res) {
                        break;
                    } else {//restore back to '.'
                        board[row][col] = '.';
                    }
                } else {//the last one
                    res = true;
                    break;
                }
            }
        }
        return res;
    }
    
    static final int N = 9;
    
    /**
     * https://github.com/rffffffff007/leetcode/blob/master/Sudoku%20Solver.java
     * 
     * @param board 
     */
    public void solveSudoku_v2(char[][] board) {
        solveSudoku(board, 0);
    }
    
    private boolean solveSudoku(char[][] board, int n) {
        if (n == N * N) {
            return true;
        }
        int x = n / N;
        int y = n % N;
        if (board[x][y] != '.') {
            return solveSudoku(board, n + 1);
        }
        int xi, yi;
        int[] choices = new int[N];
        for (int i = 0; i < N; i++) {
            //Row
            if (board[x][i] != '.') {
                choices[board[x][i] - '1'] = 1;
            }
            //Column
            if (board[i][y] != '.') {
                choices[board[i][y] - '1'] = 1;
            }
            //Square
            xi = x / 3 * 3 + i / 3;
            yi = y / 3 * 3 + i % 3;
            if (board[xi][yi] != '.') {
                choices[board[xi][yi] - '1'] = 1;
            }
        }
        for (int i = 0; i < N; i++) {
            if (choices[i] == 0) {
                board[x][y] = (char) ('1' + i);
                if (solveSudoku(board, n + 1)) {
                    return true;
                }
                board[x][y] = '.';
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        SudokuSolver ss = new SudokuSolver();
        char[][] board = 
        {{'.', '.', '.', '2', '.', '.', '.', '6', '3'},
         {'3', '.', '.', '.', '.', '5', '4', '.', '1'},
         {'.', '.', '1', '.', '.', '3', '9', '8', '.'},
         {'.', '.', '.', '.', '.', '.', '.', '9', '.'},
         {'.', '.', '.', '5', '3', '8', '.', '.', '.'},
         {'.', '3', '.', '.', '.', '.', '.', '.', '.'},
         {'.', '2', '6', '3', '.', '.', '5', '.', '.'},
         {'.', '5', '.', '3', '7', '.', '.', '.', '8'},
         {'4', '7', '.', '.', '.', '1', '.', '.', '.'},
        };
        
        ss.solveSudoku_v2(board);
        
        for(char[] cc : board) {
            for (char c : cc) {
                System.out.print(c + "\t");
            }
            System.out.println("");
        }
    }
}
