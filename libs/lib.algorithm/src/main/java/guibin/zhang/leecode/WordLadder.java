package guibin.zhang.leecode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (start and end), and a dictionary, find the length of
 * shortest transformation sequence from start to end, such that:
 *
 * Only one letter can be changed at a time Each intermediate word must exist in
 * the dictionary For example,
 *
 * Given: start = "hit" end = "cog" dict = ["hot","dot","dog","lot","log"] As
 * one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *
 * return its length 5.
 *
 * Note:
 *
 * Return 0 if there is no such transformation sequence. All words have the same
 * length. All words contain only lowercase alphabetic characters.
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class WordLadder {

    public int ladderLength(String start, String end, HashSet<String> dict) {
        
        //current -> prev
        HashMap<String, String> path = new HashMap<String, String>();
        Set<String> visit = new HashSet<String>();
        //bfs queue
        Queue<String> queue = new LinkedList<String>();
        queue.add(start);
        visit.add(start);
        
        while(queue.size() > 0) {
            String prev = queue.poll();
            //Traverse all the adjacent words
            for(String curr : adjacent(prev, dict)) {
                if(curr.equals(end)) {
                    int count = 1;
                    while(prev != null) {
                        prev = path.get(prev);
                        count ++;
                    }
                    return count;
                }
                
                if(!visit.contains(curr)) {
                    path.put(curr, prev);
                    visit.add(curr);
                    queue.add(curr);
                }
            }
        }
        return 0;
    }

    /**
     * Find all the adjacent string of str.
     * @param str
     * @param dict
     * @return 
     */
    public Set<String> adjacent(String str, Set<String> dict) {

        Set<String> rest = new HashSet<String>();
        char[] sArr = str.toCharArray();
        for (int i = 0; i < sArr.length; i++) {
            char origin = sArr[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != origin) {
                    sArr[i] = c;
                    String temp = new String(sArr);
                    if (dict.contains(temp)) {
                        rest.add(temp);
                    }
                }
            }
            sArr[i] = origin;
        }
        return rest;
    }
}
