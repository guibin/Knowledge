package guibin.zhang.leetcode.string;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Guibin Zhang <gzhang at radiumone.com>
 */
public class RemoveComments {

    public static final char SLASH = '/';
    public static final char ASTERISK = '*';

    
    public static void removeComments(File input) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(input));
            boolean commented = false;
            boolean doubleQuoted = false;
            boolean singleQuoted = false;
            String line = br.readLine();
            while (line != null) {
                int length = line.length();
                //Maintain the comment status, keep output the non-comments based on the flag commentted.
                for (int i = 0; i < length; i++) {
                    if (line.charAt(i) == '\'' && !commented) {
                        singleQuoted = !singleQuoted;
                        System.out.print(String.valueOf(line.charAt(i)));
                    } else if (line.charAt(i) == '\"' && !commented && !singleQuoted) {
                        doubleQuoted = !doubleQuoted;
                        System.out.print(String.valueOf(line.charAt(i)));
                    } else if (!doubleQuoted && line.charAt(i) == SLASH && (i + 1) < length && line.charAt(i + 1) == ASTERISK) {
                        commented = true;
                        i++;
                    } else if (!doubleQuoted && commented && line.charAt(i) == ASTERISK && (i + 1) < length && line.charAt(i + 1) == SLASH) {
                        commented = false;
                        i++;
                    } else if (!doubleQuoted && !commented) {
                        System.out.print(String.valueOf(line.charAt(i)));
                    }
                }
                System.out.println();
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args){
        File input = new File("./src/main/java/guibin/zhang/leetcode/string/RestoreIPAddresses.java"); 
        removeComments(input);
    }
}
