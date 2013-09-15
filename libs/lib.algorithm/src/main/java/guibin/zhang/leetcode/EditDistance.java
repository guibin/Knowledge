package guibin.zhang.leecode;

/**
 * 
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
 * 
 * You have the following 3 operations permitted on a word:
 * 
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 * 
 * http://www.csse.monash.edu.au/~lloyd/tildeAlgDS/Dynamic/Edit/
 * http://www.youtube.com/watch?v=gSGf8P8D_uY
 * http://www.youtube.com/watch?v=dUSqwTC8TM8
 * http://www.youtube.com/watch?v=CB425OsE4Fo
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class EditDistance {
    /**
     * The following recurrence relations define the edit distance, d(s1,s2), of two strings s1 and s2:
     * 
     * d('', '') = 0               -- '' = empty string
     * d(s, '')  = d('', s) = |s|  -- i.e. length of s
     * d(s1+ch1, s2+ch2)
     *   = min( d(s1, s2) + if ch1=ch2 then 0 else 1 fi,
     *          d(s1+ch1, s2) + 1,
     *          d(s1, s2+ch2) + 1 )
     * 
     * A two-dimensional matrix, m[0..|s1|,0..|s2|] is used to hold the edit distance values:
     * 
     * m[i,j] = d(s1[1..i], s2[1..j])
     * 
     * m[0,0] = 0
     * m[i,0] = i,  i=1..|s1|
     * m[0,j] = j,  j=1..|s2|
     * 
     * m[i,j] = min(m[i-1,j-1]
     *              + if s1[i]=s2[j] then 0 else 1 fi,
     *              m[i-1, j] + 1,
     *              m[i, j-1] + 1 ),  i=1..|s1|, j=1..|s2|
     * 
     * Run Status: Accepted!
     * Program Runtime: 880 milli secs
     * Progress: 1145/1145 test cases passed.
     * 
     * @return 
     */
    public int minDistance(String word1, String word2) {

        if(word1.length() == 0) {
            return word2.length();
        }
        if(word2.length() == 0) {
            return word1.length();
        }
        
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] m = new int[len1 + 1][len2 + 1];
        m[0][0] = 0;
        for(int i = 1; i <= len1; i++) {
            m[i][0] = i;
        }
        for(int j = 1; j <= len2; j++) {
            m[0][j] = j;
        }
        
        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                int distance = Integer.MAX_VALUE;
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    distance = Math.min(m[i - 1][j - 1], distance);
                } else {
                    distance = Math.min(m[i - 1][j - 1] + 1, distance);
                }
                distance = Math.min(m[i - 1][j] + 1, distance);
                distance = Math.min(m[i][j - 1] + 1, distance);
                m[i][j] = distance;
            }
        }
        
        return m[len1][len2];
    }
    
    
    
    public static void main(String[] args) {
        EditDistance ed = new EditDistance();
        System.out.println(ed.minDistance("a", "a"));
    }
}
