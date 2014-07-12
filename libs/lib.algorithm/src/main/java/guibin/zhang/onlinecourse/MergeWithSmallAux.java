package guibin.zhang.onlinecourse;

import java.util.Arrays;

/**
 * Merging with smaller auxiliary array. 
 * Suppose that the subarray a[0] to a[N-1] is sorted and the subarray a[N] to a[2*N-1] is sorted. 
 * How can you merge the two subarrays so that a[0] to a[2*N-1] is sorted 
 * using an auxiliary array of size N (instead of 2N)?
 * 
 * @author Guibin Zhang <gzhang at radiumone.com>
 */
public class MergeWithSmallAux {
    
    
    /**
     * 
     * http://www.algoqueue.com/algoqueue/default/view/983040/merging-two-sorted-arrays-with-smaller-auxiliary-array
     * @param a original array
     * @param aux Auxiliary array is only half length of a.
     * @param N length of auxiliary array and half of the length of a.
     */
    public void mergeWithSmallerAuxiliaryArray(Comparable[] a, Comparable[] aux, int N) {
        
        for(int k = 0; k < N; k ++) {
            aux[k] = a[k];
        }
        
        //i - index of aux array
        //j - index of right part of a
        //k - index of merged array
        int i = 0, j = N, k = 0;
        while (k < a.length) {
            if (i >= N)
                a[k++] = a[j++];
            else if (j >= a.length) 
                a[k++] = aux[i++];
            else if (aux[i].compareTo(a[j]) < 0)
                a[k++] = aux[i++];
            else {
                a[k++] = a[j++];
            }
        }
    }
    
    public static void main(String[] args) {

        Comparable[] a = {40, 61, 70, 71, 99, 20, 51, 55, 75, 100};
        MergeWithSmallAux m = new MergeWithSmallAux();
        int N = a.length / 2;
        Comparable[] aux = new Comparable[N];
        m.mergeWithSmallerAuxiliaryArray(a, aux, N);
        System.out.println("After merging:");
        Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
        System.out.println();
    }
}
