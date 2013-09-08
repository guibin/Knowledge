package guibin.zhang.leecode.matrix;

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
    
    public boolean exist_tte(char[][] board, String word) {
        
        int rows = board.length;
        int cols = board[0].length;
        
        if(rows == 1 && cols == 1 && word.length() == 1 && board[0][0] == word.charAt(0)) {
            return true;
        }
        if(rows * cols < word.length()) {
            return false;
        }
        
        //This flag is used to avoid the duplicated visit.
        boolean[][] visited = null;
        
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                visited = new boolean[rows][cols];
                if(search_tte(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean search_tte(char[][] board, int i, int j, String word, int idx, boolean[][] visited) {
        
        int rows = board.length;
        int cols = board[0].length;

        if (i < 0 || i >= rows || j < 0 || j >= cols || idx > word.length() - 1 || visited[i][j]) {
            return false;
        } 

        if (board[i][j] == word.charAt(idx)) {
            visited[i][j] = true;
            if (idx == word.length() - 1) {
                return true;
            } else {
                return search_tte(board, i - 1, j, word, idx + 1, visited)
                        || search_tte(board, i + 1, j, word, idx + 1, visited)
                        || search_tte(board, i, j - 1, word, idx + 1, visited)
                        || search_tte(board, i, j + 1, word, idx + 1, visited);
            }
        } else {
            return false;
        }
    }
    
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
        
        if(rows == 1 && cols == 1 && word.length() == 1 && board[0][0] == word.charAt(0)) {
            return true;
        }
        if(rows * cols < word.length()) {
            return false;
        }
        
        //This flag is used to avoid the duplicated visit.
        boolean[][] visited = new boolean[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(search(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean search(char[][] board, int i, int j, String word, int idx, boolean[][] visited) {
        
        int rows = board.length;
        int cols = board[0].length;

        if (board[i][j] != word.charAt(idx)) {
           return false; 
        }
        else {
            visited[i][j] = true;
            if (idx == word.length() - 1) {
                return true;
            } else {
                
                if(i - 1 >= 0 && idx + 1 < word.length() && !visited[i - 1][j] && 
                        search(board, i - 1, j, word, idx + 1, visited)) {
                    return true;
                }
                
                if(i + 1 < rows && idx + 1 < word.length() && !visited[i + 1][j] && 
                        search(board, i + 1, j, word, idx + 1, visited)) {
                    return true;
                }
                
                if(j - 1 >= 0 && idx + 1 < word.length() && !visited[i][j - 1] && 
                        search(board, i, j - 1, word, idx + 1, visited)) {
                    return true;
                }
                
                if(j + 1 < cols && idx + 1 < word.length() && !visited[i][j + 1] && 
                        search(board, i, j + 1, word, idx + 1, visited)) {
                    return true;
                }
                visited[i][j] = false;
                return false;
            }
        }
    }
}

