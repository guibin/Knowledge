package guibin.zhang.leetcode.matrix;

/**
 * 
 *  Given a 2D board and a word, find if the word exists in the grid.
 *  
 *  The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *  
 *  For example,
 *  Given board =
 *  
 *  [
 *    ["ABCE"],
 *    ["SFCS"],
 *    ["ADEE"]
 *  ]
 *  word = "ABCCED", -> returns true,
 *  word = "SEE", -> returns true,
 *  word = "ABCB", -> returns false.
 *  
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class WordSearch {
    
    /**
     * Run Status: Accepted!
     * Program Runtime: 712 milli secs
     * Progress: 76/76 test cases passed.
     * 
     * @param board
     * @param word
     * @return 
     */
    public boolean exist(char[][] board, String word) {
        
        int rows = board.length;
        int cols = board[0].length;
        
        if(rows * cols < word.length()) {
            return false;
        }
        
        boolean[][] visited = new boolean[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if (search(board, word, i, j, 0, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean search(char[][] board, String word, int i, int j,  int idx, boolean[][] visited) {
        
        int rows = board.length;
        int cols = board[0].length;

        //Go to the end of the target
        if (idx == word.length()) return true;
        
        //Go to outside of board
        if (i < 0 || i >= rows || j < 0 || j >= cols) return false;
        
        //Has been visited or not the target
        if (visited[i][j] || board[i][j] != word.charAt(idx)) return false;
        
        visited[i][j] = true;
        boolean result = search(board, word, i - 1, j, idx + 1, visited) || 
                search(board, word, i + 1, j, idx + 1, visited) || 
                search(board, word, i, j - 1, idx + 1, visited) || 
                search(board, word, i, j + 1, idx + 1, visited);
        visited[i][j] = false;
        
        return result;
    }
}

