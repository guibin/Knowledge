package guibin.zhang.algorithm;

/**
 * Conceptually, a merge sort works as follows
 * 1. Divide the unsorted list into n sublists, 
 *    each containing 1 element (a list of 1 element is considered sorted).
 * 2. Repeatedly Merge sublists to produce new sublists until there is only 1 sublist remaining. 
 *    (This will be the sorted list.)
 * 
 * 
 * @author guibin
 */
public class MergeSortJ {
    
    //http://www.mycstutorials.com/articles/sorting/mergesort
    public void mergeSort(int array[]) {
        
        if(array.length > 1) {
            //SORT------
            // number of elements in sub-array 1
            int elementsInA1 = array.length / 2;
            // number of elements in sub-array 2
            int elementsInA2 = array.length - elementsInA1;
            
            // declare and initialize the two arrays once we've determined their sizes
            int[] arr1 = new int[elementsInA1];
            int[] arr2 = new int[elementsInA2];
            
            // copy the first part of 'array' into 'arr1', causing arr1 to become full
            System.arraycopy(array, 0, arr1, 0, elementsInA1);
            // copy the remaining elements of 'array' into 'arr2', causing arr2 to become full
            System.arraycopy(array, elementsInA1, arr2, 0, elementsInA2);
            
            // recursively call mergeSort on each of the two sub-arrays that we've just created
            mergeSort(arr1);
            mergeSort(arr2);
            // note: when mergeSort returns, arr1 and arr2 will both be sorted!
            // it's not magic, the merging is done below, that's how mergesort works
            
            //MERGE-----
            int i = 0, j = 0, k = 0;
            // the below loop will run until one of the sub-arrays becomes empty
            while(j < arr1.length && k < arr2.length) {
                array[i++] = arr1[j] < arr2[k] ? arr1[j++] : arr2[k++];
            }
            while(j < arr1.length) {
                array[i++] = arr1[j++];
            }
            while(k < arr2.length) {
                array[i++] = arr2[k++];
            }
            
        }
        
    }
    
    /**
     * http://kosbie.net/cmu/summer-08/15-100/handouts/IterativeMergeSort.java
     * @param array
     * @param aux
     * @param left inclusive
     * @param right exclusive
     */
    public void mergeSort2(int[] array, int[] aux, int left, int right) {
        
        if (right - left <= 1) {
            return;//base case
        }
        
        //Sort each half recursively
        int mid = left + (right - left) / 2;
        mergeSort2(array, aux, left, mid);
        mergeSort2(array, aux, mid, right);
        
        //Merge back together
        merge(array, aux, left, mid, right);
    }
    
    /**
     * Merge the two sub-arrays: [left, mid) and [mid, right) to array.
     * @param array The target array to merge to.
     * @param aux The swap space.
     * @param left Start position inclusive.
     * @param mid Middle position.
     * @param right End position exclusive.
     */
    private void merge(int[] array, int[] aux, int left, int mid, int right) {
        
        //add two tests to first verify "mid" and "right" are in range
        if (mid >=  array.length) {
            return;
        }
        // This condition is for mergeSortIteratively.
        if (right > array.length) {
            right = array.length;
        }
        
        int i = left, j = mid;
        for (int k = left; k < right; k ++) {
            if (i == mid) {
                //Merge the remaining right part
                System.out.println("k=" + k + ", j=" + j);
                aux[k] = array[j++];
            } else if (j == right) {
                //Merge the remaining left part
                aux[k] = array[i++];
            } else if (array[j] < array[i]) {
                //Merge from the two parts
                aux[k] = array[j++];
            } else {
                aux[k] = array[i++];
            }
        }
        //Copy back to array
        System.arraycopy(aux, left, array, left, right - left);
    }
    
    /**
     * How it works:  
     * on the first pass, we merge a[0] and a[1], 
     * then we merge a[2] and a[3], and so on.
     * So the array of n elements contains n/2 sorted sub-arrays of size 2. 
     * On the second pass, we merge a[0]-a[3], a[4]-a[7], and so on, 
     * so the array of n elements contains n/4 sorted sub-arrays of size 4.  
     * We keep doubling our "blockSize" until it reaches n, and we're done.
     * 
     * @param array 
     */
    public void mergeSortIteratively(int[] array) {
        
        int[] aux = new int[array.length];
        for (int blockSize = 1; blockSize < array.length; blockSize *= 2) {
            for (int start = 0; start < array.length; start += 2 * blockSize) {
                merge(array, aux, start, start + blockSize, start + 2 * blockSize);
            }
        }
    }
    
    
    private void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i + ",");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        MergeSortJ ms = new MergeSortJ();
        int[] array = {3, 1, 4, 5, 9, 2, 6, 8, 7, 77};
        ms.mergeSort(array);
        ms.printArray(array);
        
        System.out.println("merge sort 2:");
        int[] array2 = {5, 3, 4, 1, 9, 2, 6, 4, 7, 78};
        ms.mergeSort2(array2, new int[array2.length], 0, array2.length);
        ms.printArray(array2);
        
        System.out.println("merge sort iteratively:");
        int[] array3 = {5, 3, 4, 1, 9, 2, 6, 4, 7, 78};
        ms.mergeSortIteratively(array3);
        ms.printArray(array3);
    }
}
