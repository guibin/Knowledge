package guibin.zhang.onlinecourse;

import java.util.Arrays;

/**
 * 3 way Quick Sort is to resolve the duplicated keys in sorting array issue.
 * Defect: If you don't stop partitioning on the equal keys, 
 * the normal QuickSort will run into quadratic time.
 * 
 * Basically 3 way Quick Sort is to maintain three variant: lt, gt and i, 
 * to make a[x] < a[lt], a[lt] == a[y] == a[gt], a[gt] < a[z].
 * The value between a[lt](inclusive) and a[gt](inclusive) equals pivot value.
 * the value on the left side of lt < pivot value.
 * the value on the right side of gt > pivot value.
 * 
 * Algorithm:
 * 1. let a[lo] be the pivot v. 
 * 2. Scan i left to right:
 *  - a[i] < v: exchange a[lt] and a[i]; increment both lt and i.
 *  - a[i] > v: exchange a[gt] and a[i]; decrement gt.
 *  - a[i] == v: increment i.
 * 3. When i and gt come across, stop and return lt and gt.
 * 
 * https://d396qusza40orc.cloudfront.net/algs4partI/slides/23Quicksort.pdf
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class QuickSort3Way {
    
    public void sort(Comparable[] a, int lo, int hi) {
        
        if(lo >= hi) return;//Short cut to avoid invalid sort.
        
        int[] p = partition(a, lo, hi);
        sort(a, lo, p[0] - 1);
        sort(a, p[1] + 1, hi);
    }
    
    public int[] partition(Comparable[] a, int lo, int hi) {
        
        int i = lo, lt = lo, gt = hi;
        Comparable v = a[lo];//Pivot value
        int[] result = new int[2];
        
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {//Less than pivot value, swap i with lt, increse both i and lt.
                swap(a, i ++, lt ++);
            } else if (cmp > 0) {//Greater than pivot value, swap i with great, descrease gt.
                swap(a, i, gt --);
            } else {//Equal to pivot value, just increase i.
                i ++;
            }
        }
        
        result[0] = lt;
        result[1] = gt;
        return result;
    }
    
    private void swap(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    
    public static void main(String[] args) {
        Comparable[] a = {50, 61, 44, 32, 99, 87, 51, 50, 50, 12};
        QuickSort3Way s = new QuickSort3Way();
        s.sort(a, 0, a.length - 1);
        
        System.out.println("After sorting");
        Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
        System.out.println();
    }
}
