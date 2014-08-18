package guibin.zhang.leetcode.random;

/**
 * 
 * Given a function which produces a random integer in the range 1 to 5, 
 * write a function which produces a random integer in the range 1 to 7.
 * 
 * What is a simple solution?
 * What is an effective solution to reduce memory usage or run on a slower CPU?
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class ExpandARandomRange {
   
    private static int random5() {
        //Math.random() => [0, 1), (int)(Math.random() * 5) + 1 => [1, 6)
        return (int)(Math.random() * 5) + 1;
    }
    
    /**
     * Simplest solution, but the memory usage is not efficient.
     *  Think of it like this: 
     * imagine printing out this double-dimension array on paper, 
     * tacking it up to a dart board and randomly throwing darts at it. 
     * If you hit a non-zero value, it's a statistically random value between 1 and 7, 
     * since there are an equal number of non-zero values to choose from. 
     * If you hit a zero, just keep throwing the dart until you hit a non-zero. 
     * That's what this code is doing: the i and j indexes randomly select a location on the dart board, 
     * and if we don't get a good result, we keep throwing darts.
     * 
     * @return random number in [1, 7]
     */
    public static int random7() {
        int[][] vals = {
            {1, 2, 3, 4, 5},
            {6, 7, 1, 2, 3},
            {4, 5, 6, 7 ,1},
            {2, 3, 4, 5, 6},
            {7, 0, 0, 0, 0}
        };
        int result = 0;
        while (result == 0) {
            int i = random5();
            int j = random5();
            result = vals[i - 1][j - 1];
        }
        return result;
    }
    
    public static int random7_v2() {
        int i;
        do {
            i = 5 * (random5() - 1) + random5();
        } while (i > 21);

        return i % 7 + 1;
    }
    
    public static int random7_v3() {
        int r;
        r = 4 * (random5() - 1) + random5();//[0,16] + [1,5] => [1,21]
        
        return r % 7 + 1;
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(random7() + ", " + random7_v2() + ", " + random7_v3());
        }
    }
}
