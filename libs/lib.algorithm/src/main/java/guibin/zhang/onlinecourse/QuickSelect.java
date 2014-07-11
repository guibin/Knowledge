package guibin.zhang.onlinecourse;

import java.util.Arrays;

/**
 * Given an array of N items, find a kth smallest item.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class QuickSelect {
    
    public Comparable selectK(Comparable[] a, int k) {
        
        Comparable result = null;
        int lo = 0, hi = a.length - 1;
        while (lo <= hi) {  // Note here is the equal
            int j = partition(a, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                result = a[j];
                break;
            }
        }
        
        return result;
    }
    
    public int partition(Comparable[] a, int lo, int hi) {
        
        if (lo == hi) return lo;//It is ok to add this quick judgement.
        
        int i = lo, j = hi + 1;
        while(true) {
            while (a[++i].compareTo(a[lo]) <= 0) {
                if (i == hi) break;
            }
            while (a[--j].compareTo(a[lo]) >= 0) {
                if (j == lo) break;
            }
            if(i >= j) break;
            swap(a, i, j);
        }
        swap(a, lo, j);
        
        return j;
    }
    
    private void swap(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    
    public static void main(String[] args) {
        Comparable[] a = {50, 61, 44, 32, 99, 87, 50, 52, 53, 12};
        QuickSelect qs = new QuickSelect();
        int k = 6;
        System.out.println("Target array: ");
        Arrays.stream(a).forEach((i) -> System.out.print(i + ","));
        System.out.println();
        System.out.println(k + "th is: " + qs.selectK(a, k));
    }
}
