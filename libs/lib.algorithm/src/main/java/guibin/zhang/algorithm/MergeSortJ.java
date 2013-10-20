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
    public int[] mergeSort(int array[]) {
        
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
            for(int i=0; i<elementsInA1; i++) {
                arr1[i] = array[i];
            }
            
            // copy the remaining elements of 'array' into 'arr2', causing arr2 to become full
            for(int i=0; i<elementsInA2; i++) {
                arr2[i] = array[elementsInA1 + i];
            }
            
            // recursively call mergeSort on each of the two sub-arrays that we've just created
            arr1 = mergeSort(arr1);
            arr2 = mergeSort(arr2);
            // note: when mergeSort returns, arr1 and arr2 will both be sorted!
            // it's not magic, the merging is done below, that's how mergesort works
            
            //MERGE-----
            int i = 0, j = 0, k = 0;
            // the below loop will run until one of the sub-arrays becomes empty
            while(j < arr1.length && k < arr2.length) {
                if(arr1[j] < arr2[k]) {
                    array[i++] = arr1[j++];
                } else {
                    array[i++] = arr2[k++];
                }
            }
            while(j < arr1.length) {
                array[i++] = arr1[j++];
            }
            while(k < arr2.length) {
                array[i++] = arr2[k++];
            }
            
        }
        
        return array;
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
    }
}
