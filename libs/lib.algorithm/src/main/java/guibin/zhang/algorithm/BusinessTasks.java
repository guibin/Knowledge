package guibin.zhang.algorithm;

import java.util.Arrays;

/**
 * Please find the problem statement at http://community.topcoder.com/stat?c=problem_statement&pm=1585&rd=6535 .
 * 
 * Problem Statement:
    	
 * A busy businessman has a number of equally important tasks which he must accomplish. 
 * To decide which of the tasks to perform first, he performs the following operation.
 * He writes down all his tasks in the form of a circular list, 
 * so the first task is adjacent to the last task. He then thinks of a positive number. 
 * This number is the random seed, which he calls n. 
 * Starting with the first task, he moves clockwise 
 * (from element 1 in the list to element 2 in the list and so on), 
 * counting from 1 to n. When his count reaches n, 
 * he removes that task from the list and starts counting from the next available task. 
 * He repeats this procedure until one task remains. 
 * It is this last task that he chooses to execute.

 * Given a String[] list representing the tasks and an int n, 
 * return the task which the businessman chooses to execute.
 * 
 * @author guibin
 */
public class BusinessTasks {
    /**
     * Use a auxiliary flag list to avoid the actual deleting items in list.
     * @param list The input task list.
     * @param n The No.n task will be removed/done.
     * @return 
     */
    public String getTask(String[] list, int n) {
        
        boolean[] aux = new boolean[list.length];
        Arrays.fill(aux, true);
        
        int validCounter = aux.length;//How many true left in aux till now.
        int currPos = 0;//The current index of the aux or list.
        int currCounter = 1;//Compare currCounter with n.
        
        while (validCounter > 1) {
            while ((currPos < aux.length) && (currCounter < n)) {
                if (aux[currPos]) {
                    currCounter++;
                }
                currPos++;
            }
            if (currPos == aux.length) {
                currPos = 0;
            }
            if (currCounter == n) {
                while (!aux[currPos]) {
                    currPos++;
                    if (currPos == aux.length) {
                        currPos = 0;
                    }
                }
                aux[currPos] = false;
                currPos++;
                currCounter = 1;
                validCounter--;
            }
        }
        
        int i=0;
        for(; i<aux.length; i++) {
            if(aux[i] == true) {
                break;
            }
        }
        return list[i];
    }
    
    /**
     * Actually delete the No.n task from task list.
     * @param list Task list.
     * @param n The No.n task will be removed/done.
     * @return The last task
     */
    public String getTask2(String[] list, int n) {
        
        int currPos = 0;
        while(list.length > 1) {
            int toBeDel = (currPos + n)%list.length;
            if(toBeDel == 0) {
                toBeDel = list.length - 1;
            } else {
                toBeDel = toBeDel - 1;
            }
            list = deleteAt(list, toBeDel);
            currPos = toBeDel;
        }
        
        return list[0];
    }
    
    public String[] deleteAt(String[] list, int n) {
        if(n < list.length && list.length > 1) {
            String[] result = new String[list.length - 1];
            for(int i=0, j=0; i<list.length; i++) {
                if(i != n) {
                    result[j] = list[i];
                    j++;
                } 
            }
            return result;
        }
        else {
            return null;
        }
    }
    
    public static void main(String[] args) {
        BusinessTasks bt = new BusinessTasks();
        String[] t0 = {"a","b","c","d"};
        long start = System.currentTimeMillis();
        System.out.println(bt.getTask(t0, 2) + "\t" + (System.currentTimeMillis() - start));//a
        System.out.println(bt.getTask2(t0, 2) + "\t" + (System.currentTimeMillis() - start));//a
        
        String[] t1 = {"a","b","c","d","e"};
        System.out.println(bt.getTask(t1, 3) + "\t" + (System.currentTimeMillis() - start));//d
        System.out.println(bt.getTask2(t1, 3) + "\t" + (System.currentTimeMillis() - start));//d
        
        String[] t2 = {"alpha","beta","gamma","delta","epsilon"};
        System.out.println(bt.getTask(t2, 1) + "\t" + (System.currentTimeMillis() - start));//epsilon
        System.out.println(bt.getTask2(t2, 1) + "\t" + (System.currentTimeMillis() - start));//epsilon
        
        String[] t3 = {"a","b"};
        System.out.println(bt.getTask(t3, 1000) + "\t" + (System.currentTimeMillis() - start));//a
        System.out.println(bt.getTask2(t3, 1000) + "\t" + (System.currentTimeMillis() - start));//a
        
        String[] t4 = {"zlqamum", "yjsrpybmq", "tjllfea", "fxjqzznvg", "nvhekxr", "am", "skmazcey", "piklp",
            "olcqvhg", "dnpo", "bhcfc", "y", "h", "fj", "bjeoaxglt", "oafduixsz", "kmtbaxu",
            "qgcxjbfx", "my", "mlhy", "bt", "bo", "q"};
        System.out.println(bt.getTask(t4, 9000000) + (System.currentTimeMillis() - start));//fxjqzznvg
        System.out.println(bt.getTask2(t4, 9000000) + (System.currentTimeMillis() - start));//fxjqzznvg
        
        String[] r0 = bt.deleteAt(t1, 0);
        for(String s : r0) {
            System.out.print(s + ",");
        }
        System.out.println();
        
        String[] r1 = bt.deleteAt(t1, 3);
        for(String s : r1) {
            System.out.print(s + ",");
        }
        System.out.println();
    }
}
