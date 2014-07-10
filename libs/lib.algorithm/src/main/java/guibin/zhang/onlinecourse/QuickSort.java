package guibin.zhang.onlinecourse;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * https://d396qusza40orc.cloudfront.net/algs4partI/slides/23Quicksort.pdf
 * 
 * @author Guibin Zhang <guibin.zhang@gmail.com>
 */
public class QuickSort {
    
    /**
     * Partition the array of a from lo(inclusive) to hi(inclusive) using a[lo] as pivot.
     * @param a Array to be partitioned.
     * @param lo The low index inclusive. Choose a[lo] as the partitioning element(pivot).
     * @param hi The high index inclusive.
     * @return The index j of the array a so that no larger entry to the left of j, 
     *         and no smaller entry to the right of j. And a[j] == a[lo]
     */
    public int partition2(Comparable[] a, int lo, int hi) {
        
        int i = lo + 1;
        int j = hi;
        while (true) {
            //Scan from lo to hi to reach the first element that is greater than pivot
            while (i < hi && a[i].compareTo(a[lo]) <= 0) {
                i++;
            }
            //Scan from hi to lo to reach the first element that is less than pivot
            while (j > lo && a[j].compareTo(a[lo]) >= 0) {
                j--;
            }
            
            if (i >= j) break;
            
            swap(a, i, j);
        }
        swap(a, lo, j);
        
        return j;
    }
    
    public int partition(Comparable[] a, int lo, int hi) {
        
        int i = lo;
        int j = hi + 1;
        while (true) {
            //Scan from lo to hi to reach the first element that is greater than pivot
            while (a[++i].compareTo(a[lo]) <= 0) {
                if (i == hi) break;
            }
            //Scan from hi to lo to reach the first element that is less than pivot
            while (a[--j].compareTo(a[lo]) >= 0) {
                if (j == lo) break;
            }
            
            if (i >= j) break;
            
            swap(a, i, j);
        }
        swap(a, lo, j);
        
        return j;
    }
    
    public void sort(Comparable[] a, int lo, int hi) {
        
        if (hi <= lo) return;
        
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }
    
    /**
     * Knuth shuffle.
     * In iteration i, pick integer r between i and N-1 uniformly at random,
     * Or between 0 to i uniformly at random
     * Swap a[i] and a[r]
     * @param a 
     */
    public void shuffle(Object[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            //uniformly random number between i and n-1 inclusive
            int r = i + (int)(Math.random() * (N - i));
            swap(a, i, r);
        }
    }
    
    private void swap(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    
    public static void main(String[] args) {
//        Random rd = new Random();
//        Comparable[] a = new Comparable[10];
//        System.out.println("Target array: ");
//        for (int i = 0; i < 10; i++) {
//            a[i] = rd.nextInt(1000);
//            System.out.print(a[i] + ",");
//        }
//        System.out.println();
        
        Comparable[] a = {50, 61, 44, 32, 99, 87, 51, 50, 50, 12};
        QuickSort s = new QuickSort();
        int j = s.partition2(a, 0, a.length - 1);
        System.out.println("The partition index is " + j);
        System.out.println("After partition, the array becomes: ");
        Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
        
        System.out.println();
        System.out.println("After sorting");
        s.sort(a, 0, a.length - 1);
        Arrays.stream(a).forEach((c) -> {System.out.print(c + ",");});
        System.out.println();
        
        System.out.println("After shffule");
        s.shuffle(a);
        Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
        System.out.println();
    }
}
