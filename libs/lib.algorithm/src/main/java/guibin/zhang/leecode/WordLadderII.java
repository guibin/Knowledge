package guibin.zhang.leecode;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * Given two words (start and end), and a dictionary, 
 * find all shortest transformation sequence(s) from start to end, such that:
 * 
 * Only one letter can be changed at a time
 * Each intermediate word must exist in the dictionary
 * 
 * For example,
 * Given:
 * start = "hit"
 * end = "cog"
 * dict = ["hot","dot","dog","lot","log"]
 * 
 * Return 
 * [
 *  ["hit","hot","dot","dog","cog"],
 *  ["hit","hot","lot","log","cog"]
 * ].
 * 
 * Note:
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * 
 * http://kimixxxxxx.blogspot.com/2013/05/word-ladder-ii.html
 * http://discuss.leetcode.com/questions/1051/word-ladder-ii
 * http://www.cnblogs.com/shawnhue/archive/2013/06/05/leetcode_126.html
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class WordLadderII {
    
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict){
        ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
        ArrayList<String> path = new ArrayList<String>();
        
        //Find out all the result, regardless of the size of the result.
        wordLadderIIHelper(start, end, dict, path, ret);
        
        //Compute the minun size of the ret.
        int min = 0;
        for(ArrayList<String> item : ret) {
            min = (min == 0 || item.size() < min ? min = item.size() : min);
        }
        
        //Remove the results that are longer than min size.
        for(int i = 0; i < ret.size(); i++) {
            if(ret.get(i).size() > min) {
                ret.remove(i--);
            }
        }
        
        return ret;
    }
    
    /**
     * 
     * @param start
     * @param end
     * @param dict
     * @param path
     * @param ret 
     */
    public void wordLadderIIHelper(String start, String end, HashSet<String> dict, ArrayList<String> path, ArrayList<ArrayList<String>> ret) {
        
        path.add(start);
        char[] sArr = start.toCharArray();
        for(int i = 0; i < sArr.length; i++) {
            //Save the working character to origin
            char origin = sArr[i];
            //For each character of start, replace one character with 'a' to 'z'
            for(char c = 'a'; c <= 'z'; c++) {
                if(c != origin) {
                    sArr[i] = c;
                    String temp = new String(sArr);
                    //Reach the end
                    if(temp.equals(end)) {
                        path.add(temp);
                        //Got the result, add it to ret
                        ret.add(new ArrayList(path));
                        //remove the end to continue
                        path.remove(path.size() - 1);
                    }
                    if(dict.contains(temp) && !path.contains(temp)) {
                        wordLadderIIHelper(temp, end, dict, path, ret);
                    }
                }
            }
            //Recover the origin string
            sArr[i] = origin;
        }
        //Why???
        path.remove(path.size() - 1);
    }
    
    public static void main(String[] args) {
        WordLadderII wd = new WordLadderII();
        String start = "a";
        String end = "c";
        HashSet dict = new HashSet();
        dict.add("a");
        dict.add("b");
        dict.add("c");
        ArrayList<ArrayList<String>> ret = wd.findLadders(start, end, dict);
        for(ArrayList<String> l : ret) {
            for(String s : l) {
                System.out.print(s + ",");
            }
            System.out.println("\n");
        }
    }
}
