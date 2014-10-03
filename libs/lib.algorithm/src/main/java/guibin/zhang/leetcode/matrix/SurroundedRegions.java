package guibin.zhang.leetcode.matrix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region .
 * For example,
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 
 * http://n00tc0d3r.blogspot.com/2013/06/surrounded-regions.html
 * http://blog.csdn.net/tuantuanls/article/details/8746458
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SurroundedRegions {
    
    /**
     * Solved by mark DFS
     * 
     * @param board 
     */
    public void solve_v2(char[][] board) {
        
        if(board.length <= 0 || board[0].length <= 0) {
            return;
        }
        
        int rows = board.length;
        int columns = board[0].length;
        
        //The first column and last column
        for(int i = 0; i < rows; i++) {
            markDFS(board, i, 0);
            if(columns > 1) {
                markDFS(board, i, columns - 1);
            }
        }
        
        //The first row and the last row
        for(int j = 0; j < columns; j++) {
            markDFS(board, 0, j);
            if(rows > 1) {
                markDFS(board, rows - 1, j);
            }
        }
        
        System.out.println("--------N-------");
        printlnResult(board);
        
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(board[i][j] == 'N') {
                    board[i][j] = 'O';
                } else if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    
    private void markDFS(char[][] board, int x, int y) {
        int rows = board.length;
        int columns = board[0].length;
        
        if (board[x][y] == 'O') {
            //mark current node
            board[x][y] = 'N';
            
            //mark its neighbour if needed
            if (x - 1 >= 0) markDFS(board, x - 1, y);
            if (x + 1 < rows) markDFS(board, x + 1, y);
            if (y - 1 >= 0) markDFS(board, x, y - 1);
            if (y + 1 < columns) markDFS(board, x, y + 1);
        }
    }
    
    
    /****************************
     * 
     * 1. Start from those boundary cells, use BFS or DFS to traverse all non-flipable 'O' cells, 
     *    and mark them with a special sign, say 'N'.
     * 2. Revisit the board, flip all remaining 'O' cells to 'X' and also flip back 'N' cells to 'O'.
     * 
     * @param board 
     * **************************
     */
    public void solve_bfs(char[][] board) {
        
        if(board.length <= 0 || board[0].length <= 0) return;
        int rows = board.length;
        int columns = board[0].length;
        
        // Start from 'O's on the edge and mark connected ones as non-flipable.  
        // first and last column
        for(int i = 0; i < rows; i++) {
            markBFS_std(board, i, 0);
            if(columns > 1) markBFS_std(board, i, columns - 1);
        }
        
        // first and last row
        for(int j = 0; j < columns; j++) {
            markBFS_std(board, 0, j);
            if(rows > 1) markBFS_std(board, rows - 1, j);
        }
        
        System.out.println("--------N-------");
        printlnResult(board);
        
        // Then flip and flip again
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(board[i][j]  == 'O') {
                    board[i][j] = 'X';
                }
                else if(board[i][j] == 'N') {
                    board[i][j] = 'O';
                }
            }
        }
        
    }
    
    /**
     * Standard BFS traverse.
     * 
     * @param board
     * @param row
     * @param col 
     */
    private void markBFS_std(char[][] board, int row, int col) {
        
        int rows = board.length;
        int columns = board[0].length;
        Queue<Integer> q = new LinkedList<>();
        
        if (board[row][col] == 'O') {//Here only add candidates to queue
            q.add(row * columns + col);    
        }
        
        while(!q.isEmpty()) {
            //Access
            int cell = q.remove();
            int x = cell/columns;
            int y = cell%columns;
            
            //Only find the 'O' cell to mark, others are ignored.
            if (board[x][y] == 'O') {
                //Mark '0' cess as 'N'
                board[x][y] = 'N';
                //Add down/up/right/left to queue
                if (x + 1 < rows) q.add((x + 1) * columns + y);
                if (x - 1 >= 0) q.add((x - 1) * columns + y);
                if (y + 1 < columns) q.add(x * columns + y + 1);
                if (y - 1 >= 0) q.add(x * columns + y - 1);
            }
        }
    }
    
    public void printlnResult(char[][] board) {
        int rows = board.length;
        int columns = board[0].length;
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]).append(",");
            }
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }
    
    /**
     * First question to yourself is which 'O' cells would or would not be flipped to 'X'.
     * 
     * According to the definition, if 'O' cell is surrounded by 'X' cells, i.e. up/down/left/right cells are 'X'.
     * The first thought could be for each 'O' cell, add it to an array, 
     * check its up/down/left/right and if it is another 'O' cell, 
     * continue until, it hits all 'X' cells or it hits boundary. 
     * In the former case, cells in the array can all be flipped to'X'; 
     * while in the latter case, cells in the array cannot be flipped.
     * 
     * But it is not easy to check cells in the middle of board.
     * Notice that any 'O' cells that connected (directly or indirectly) to a boundary 'O' cell can NOT be flipped. 
     * So, we don't need to keep track of both flip-able and non-flip-able 'O' cells. 
     * We can start from **boundary cells** and use BFS or DFS to find out all its neighbor 'O' cells.
     * 
     * The algorithm will be something like:
     * 1. Start from those boundary cells, use BFS or DFS to traverse all non-flipable 'O' cells, 
     *    and mark them with a special sign, say 'N'.
     * 2. Revisit the board, flip all remaining 'O' cells to 'X' and also flip back 'N' cells to 'O'.
     * 
     * @param board 
     */
    public void solve_v1(char[][] board) {
        
        if(board.length <= 0 || board[0].length <= 0) return;
        int rows = board.length;
        int columns = board[0].length;
        
        // Start from 'O's on the edge and mark connected ones as non-flipable.  
        // first and last column
        for(int i = 0; i < rows; i++) {
            markBFS(board, i, 0);
            if(columns > 1) markBFS(board, i, columns - 1);
        }
        
        // first and last row
        for(int j = 0; j < columns; j++) {
            markBFS(board, 0, j);
            if(rows > 1) markBFS(board, rows - 1, j);
        }
        
        // Then flip and flip again
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(board[i][j]  == 'O') {
                    board[i][j] = 'X';
                }
                else if(board[i][j] == 'N') {
                    board[i][j] = 'O';
                }
            }
        }
        
    }
    
    private void mark(char[][] board, int row, int col, Queue<Integer> queue) {
        
        int rows = board.length;
        int columns = board[0].length;
        
        //Only find the 'O' cell to mark, others are ignored.
        if(row < 0 || row >= rows || col < 0 || col >= columns || board[row][col] != 'O')
            return;
        
        //For the frst round, mark the non-flipable cells to 'N'.
        board[row][col] = 'N';
        
        //Since BFS, after access, off the cell to queue. 
        //The representation of cell is very **clever**
        queue.add(row * columns + col);   
    }
    
    private void markBFS(char[][] board, int row, int col) {
        int columns = board[0].length;
        Queue<Integer> queue = new LinkedList<>();
        
        mark(board, row, col, queue);
        while(!queue.isEmpty()) {
            int cell = queue.remove();
            int x = cell/columns;
            int y = cell%columns;
            
            mark(board, x + 1, y, queue);
            mark(board, x - 1, y, queue);
            mark(board, x, y + 1, queue);
            mark(board, x, y - 1, queue);
        }
    }
    
    public static void main(String[] args) {
        SurroundedRegions sr = new SurroundedRegions();
        
//        char[][] board1 = {"XOXX".toCharArray(), 
//                           "OXOX".toCharArray(), 
//                           "XOXO".toCharArray(), 
//                           "OXOX".toCharArray()};
        
//        char[][] board2 = {"OXXOX".toCharArray(), 
//                           "XOOXO".toCharArray() ,
//                           "XOXOX".toCharArray() ,
//                           "OXOOO".toCharArray() ,
//                           "XXOXO".toCharArray()};
        
        char[][] board2 = {"XOXX".toCharArray(), 
                           "OXOX".toCharArray(), 
                           "XOXO".toCharArray(), 
                           "OXOX".toCharArray()};
        
//        char[][] board3 = {"XOXX".toCharArray(), 
//                           "OXOX".toCharArray(), 
//                           "XOXO".toCharArray(), 
//                           "OXOX".toCharArray()};
        char[][] board1 = {"OOO".toCharArray(), 
                           "OOO".toCharArray(), 
                           "OOO".toCharArray()};
        
        char[][] board3 = {"OOO".toCharArray(), 
                           "OOO".toCharArray(), 
                           "OOO".toCharArray()};
        
        sr.printlnResult(board1);
        sr.solve_v1(board1);
        sr.printlnResult(board1);

        System.out.println("-----------bfs standard-----------");
        sr.printlnResult(board3);
        sr.solve_bfs(board3);
        sr.printlnResult(board3);
        
        System.out.println("-------------v2---------");
        sr.printlnResult(board2);
        sr.solve_v2(board2);
        sr.printlnResult(board2);
    }
}
