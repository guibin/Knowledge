package interview;

import java.util.HashSet;
import java.util.Set;

/**
 * July 16th, Cloudera.
 * 
 * Only one existing method: isWord.
 * Please output if the given string can be concatenated by the words from the existing dictionary.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class FindWordConcatenation {

    private static Set<String> words = new HashSet<String>();

    static {
        words.add("at");
        words.add("the");
        words.add("enemy");
        words.add("gates");
        // ...
    }

    public static boolean isWord(String w) {
        return words.contains(w);
    }

    public static boolean isConcatenationOfWords(String str) {
        // Fill me in
        return isConcatenationOfWords(str, 0);
    }

    private static boolean isConcatenationOfWords(String str, int start) {

        int i = start;
        while (i <= str.length()) {
            String s = str.substring(start, i);
            if (isWord(s))  {
                System.out.println(s);
                if (i < str.length()) {
                   return isConcatenationOfWords(str, i);
                } else {
                    return true;
                }
            } else {
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("enemyatthegates: " + isConcatenationOfWords("enemyatthegates"));
        System.out.println("asdfasdfadsf: " + isConcatenationOfWords("asdfasdfadsf"));
    }
}
