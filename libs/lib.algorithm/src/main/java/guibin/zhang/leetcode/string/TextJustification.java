package guibin.zhang.leetcode.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given an array of words and a length L, format the text such that each line 
 * has exactly L characters and is fully (left and right) justified.
 * 
 * You should pack your words in a greedy approach; that is, pack as many words 
 * as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 * 
 * Extra spaces between words should be distributed as evenly as possible. 
 * If the number of spaces on a line do not divide evenly between words, 
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 * 
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 * 
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 * 
 * Return the formatted lines as:
 * 
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * Note: Each word is guaranteed not to exceed L in length.
 * 
 * Corner Cases:
 * A line other than the last line might contain only one word. What should you do in this case?
 * In this case, that line should be left-justified.
 * 
 * Run Status: Accepted!
 * Program Runtime: 692 milli secs
 * Progress: 24/24 test cases passed.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class TextJustification {
    
    public List<String> fullJustify_v3(String[] words, int L) {
        
        ArrayList<String> result = new ArrayList<String>();
        if (L == 0) {
            result.add("");
            return result;
        }
        //Use stack to track each line
        Stack<String> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < words.length; i++) {
            if ((stack.size() + words[i].length() + count <= L)) {
                stack.push(words[i]);
                count += words[i].length();
            } else {
                int numOfBlank = L - count;
                result.add(generateLine(stack, numOfBlank));
                stack.push(words[i]);
                count = words[i].length();
            }
        }
        //Process the last line
        count = 0;
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            String curr = stack.pop();
            sb.insert(0, curr);
            count += curr.length();
            if (count < L && !stack.isEmpty()) {
                sb.insert(0, " ");
                count++;
            }
        }
        if (L - count > 0) {
            sb.append(space(L - count));
        }
        result.add(sb.toString());
        return result;
    }
    
    private String generateLine(Stack<String> stack, int numOfBlank) {
        StringBuilder sb = new StringBuilder();
        if (stack.size() == 1) {
            sb.append(stack.pop());
            sb.append(space(numOfBlank));
        } else {
            while(!stack.isEmpty()) {
                String curr = stack.pop();
                sb.insert(0, curr);
                if (!stack.isEmpty()) {
                    sb.insert(0, space(numOfBlank/stack.size()));
                    numOfBlank -= numOfBlank/stack.size();
                }
            }
        }
        return sb.toString();
    }
    
    private String space(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(' ');
        }
        return sb.toString();
    }
    
    
    /**
     * http://www.elvisyu.com/text-justification/#more-315
     * @param words
     * @param L
     * @return 
     */
    public List<String> fullJustify_v2(String[] words, int L) {
        
        int wordsCount = words.length;
        List<String> result = new ArrayList<>();
        
        int curLen = 0;
        int lastI = 0; //lastI represent the sequence of each word in every row
        for (int i = 0; i <= wordsCount; i++) {
            //only process when row length larger than L or at the end
            if (i == wordsCount || curLen + words[i].length() + i - lastI > L) {
                StringBuffer buf = new StringBuffer();
                int spaceCount = L - curLen;
                int spaceSlots = i - lastI - 1;
                if (spaceSlots == 0 || i == wordsCount) {    //add space case 1
                    for(int j = lastI; j < i; j++){
                        buf.append(words[j]);
                        if(j != i - 1)
                            appendSpace(buf, 1);
                    }
                    appendSpace(buf, L - buf.length());
                } else {                                     //add space case 2
                    int spaceEach = spaceCount / spaceSlots;
                    int spaceExtra = spaceCount % spaceSlots;
                    for (int j = lastI; j < i; j++) {    
                        buf.append(words[j]);
                        if (j != i - 1)
                            appendSpace(buf, spaceEach + (j - lastI < spaceExtra ? 1 : 0));
                    }
                }
                result.add(buf.toString());
                lastI = i;
                curLen = 0;
            }
            if (i < wordsCount)               //keep finding words if it doesn't end or row length is smaller than L
                curLen += words[i].length();
        }
        return result;
    }
    
    private void appendSpace(StringBuffer sb, int count) {
        for (int i = 0; i < count; i++)
            sb.append(' ');
    }
    
    
    public ArrayList<String> fullJustify(String[] words, int L) {

        ArrayList<String> result = new ArrayList<String>();

        if (L == 0) {
            result.add(words[0]);
            return result;
        }

        int total = 0;
        int start = 0;

        for (int i = 0; i < words.length; i++) {
            int len = words[i].length();
            total += len;

            if (total + i - start == L) {
                result.add(generateLine(words, start, i + 1, total, L));
                total = 0;
                start = i + 1;
            } else if (total + i - start > L) {
                result.add(generateLine(words, start, i, total - len, L));
                total = len;
                start = i;
            }
        }
        
        if (total > 0) {
            result.add(generateLine(words, start, words.length, total, L));
        } else if (total == 0 && result.size() == 0 && L > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < L; i++) {
                sb.append(' ');
            }
            result.add(sb.toString());
        }
        
        return result;
    }

    /**
     * 
     * @param words
     * @param start start index of words, inclusive
     * @param end end index of words, exclusive
     * @param wordsLength actual total length of words in this line
     * @param L expected number of characters
     * @return 
     */
    public String generateLine(String[] words, int start, int end, int wordsLength, int L) {
        StringBuilder sb = new StringBuilder();
        int slots = end - start - 1;
        int spaces = L - wordsLength;
        int reminder = slots == 0 ? 0 : spaces % slots;
        int pads = slots == 0 ? spaces : spaces / slots;
        boolean lastLine = end == words.length;
        
        for (int j = start; j < end; j++) {
            sb.append(words[j]);
            for (int i = 0; i < pads; i++) {
                if(!lastLine) {
                    sb.append(' ');
                } else {//Note when lastline, just append one space between each words.
                    sb.append(' ');
                    break;
                }
            }
            //Prepend the reminder's space from the start slot. Note the start slot of "j - start + 1"
            if (j - start + 1 <= reminder && !lastLine) {
                sb.append(' ');
            }
        }
        int diff = sb.length() - L;
        if (diff > 0) {
            sb.delete(L, sb.length());
        } else if (diff < 0) { //The last line case
            for(int i = 0; i < -diff; i++) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    
    public static void main(String[] args) {
        TextJustification tj = new TextJustification();
        String[] words = {"Here","is","an","example","of","text","justification."};
        ArrayList<String> result = tj.fullJustify(words, 16);
        System.out.println("result size: " + result.size());
        for(String s : result) {
            String ss = s.replace(" ", ".");
            System.out.println(ss);
        }
    }
}
