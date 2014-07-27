package guibin.zhang.onlinecourse;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Document search. 
 * Design an algorithm that takes a sequence of N document words 
 * and a sequence of M query words and find the shortest interval 
 * in which the M query words appear in the document in the order given. 
 * The length of an interval is the number of words in that interval.
 * 
 * Web Exercise 38 of http://algs4.cs.princeton.edu/13stacks/
 * 
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class MultiworldSearch {

    private static Scanner scanner;
    // used to read the entire input
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

    public static String readAll() {
        if (!scanner.hasNextLine()) {
            return "";
        }

        String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
        // not that important to reset delimeter, since now scanner is empty
        scanner.useDelimiter(WHITESPACE_PATTERN); // but let's do it anyway
        return result;
    }

    /**
     * Execution: java MultiwordSearch query1 query2 ... < input.txt
         * @
     *
     * param args
     */
    public static void main(String[] args) {
        String[] words = readAll().split("\\s+");

        // construct queues[j] = sequence of positions of jth key word
        Queue<Integer>[] queues = (Queue<Integer>[]) new Queue[args.length];
        for (int j = 0; j < args.length; j++) {
            queues[j] = new LinkedList();
        }

        // Scan each words to search in the keywords, if found, enqueue the index.
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < args.length; j++) {
                if (words[i].equals(args[j])) {
                    queues[j].offer(i);
                }
            }
        }

        // repeatedly find smallest interval starting at position of queues[0]
        boolean done = false;
        int bestlo = -1, besthi = words.length;
        while (!queues[0].isEmpty()) {
            int lo = queues[0].poll();
            int hi = lo;
            // Iterate each keyword, to find each index which is bigger than previous one
            for (int j = 1; j < args.length; j++) {
                while (!queues[j].isEmpty() && queues[j].peek() <= hi) {
                    queues[j].poll();
                }
                if (queues[j].isEmpty()) {
                    done = true;
                    break;
                } else {
                    hi = queues[j].peek();
                }
            }
            if (!done && hi - lo < besthi - bestlo) {
                besthi = hi;
                bestlo = lo;
            }
        }

        if (bestlo >= 0) {
            for (int i = bestlo; i <= besthi; i++) {
                System.out.print(words[i] + " ");
            }
            System.out.println();
        } else {
            System.out.println("NOT FOUND");
        }
    }
}
