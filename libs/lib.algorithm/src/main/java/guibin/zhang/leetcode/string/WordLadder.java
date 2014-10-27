package guibin.zhang.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
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
 * It is a BFS problem.
 * http://mattcb.blogspot.com/2013/02/word-ladder.html
 * http://wizardrichard.blogspot.com/2013/03/leetcode-word-ladder.html
 * http://blog.csdn.net/guixunlong/article/details/8840371
 * http://discuss.leetcode.com/questions/1108/word-ladder
 * 
 * 
 * BFS can only be used to find shortest path in a graph if:
 * 1. There are no loops 
 * 2. All edges have same weight or no weight. 
 * 
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class WordLadder {

    public int ladderLength(String start, String end, Set<String> dict) {
        
        //current -> prev
        Map<String, String> path = new HashMap<>();
        Set<String> visit = new HashSet<>();
        //bfs queue
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visit.add(start);
        
        while(!queue.isEmpty()) {
            String prev = queue.remove();
            //Traverse all the adjacent words
            for(String curr : adjacent(prev, dict)) {
                if(curr.equals(end)) {//Get the shortest path, count the steps
                    int count = 1;
                    while(prev != null) {
                        prev = path.get(prev);
                        count ++;
                    }
                    return count;
                }
                //Didn't get the shortest path, just record the path, add to queue
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

        Set<String> rest = new HashSet<>();
        char[] sArr = str.toCharArray();
        for (int i = 0; i < sArr.length; i++) {
            char origin = sArr[i];//Save the original char
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != origin) {
                    sArr[i] = c;
                    String temp = new String(sArr);
                    if (dict.contains(temp)) {
                        rest.add(temp);
                    }
                }
            }
            //Remember to recover to original string
            sArr[i] = origin;
        }
        return rest;
    }
    
    /**
     * This can only pass the small judge, but TLE when testing the large judge.
     * @param start
     * @param end
     * @param dict
     * @return 
     */
    public int ladderLength_v2(String start, String end, HashSet<String> dict) {
        
        LinkedList<String> queue = new LinkedList<>();
        LinkedList<Integer> len = new LinkedList<>();
        
        queue.add(start);
        len.add(1);
        dict.remove(start);
        
        while(!queue.isEmpty()) {
            String current = queue.remove();
            int depth = len.remove();
            if(current.equals(end)) {
                return depth;
            }
            
            Iterator<String> it = dict.iterator();
            while(it.hasNext()) {
                String tmp = it.next();
                if(isAdjacent(tmp, current)) {
                    queue.add(tmp);
                    len.add(depth + 1);
                    it.remove();
                }
            }
        }
        return 0;
    }
    
    /**
     * Judge whether the two input string has only the difference of one character.
     * @param a
     * @param b
     * @return 
     */
    public boolean isAdjacent(String a, String b) {
        
        boolean flag = true;
        int counter = 0;
        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) {
                counter ++;
                
                if(counter > 1) {
                    flag = false;
                    return flag;
                }
            }
        }
        
        return flag;
    }
}
