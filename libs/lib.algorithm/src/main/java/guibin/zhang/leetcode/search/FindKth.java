package guibin.zhang.leetcode.search;

/**
 * Must write this code very proficiently.
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class FindKth {
    
    /**
     * Find the k-th largest items from TWO SORTED arrays.
     * @param a The first sorted array
     * @param startA start position inclusive
     * @param endA end position inclusive
     * @param b The second sorted array
     * @param startB start position inclusive
     * @param endB end position inclusive
     * @param k The expected position, 1 based
     * @return 
     */
    public double findKthFromTwoSortedArray (int[] a, int startA, int endA, int[] b, int startB, int endB, int k) {
        
        int m = endA - startA + 1;
        int n = endB - startB + 1;
        
        //Always assume a.length <= b.length
        if (m > n) {
            return findKthFromTwoSortedArray(b, startB, endB, a, startA, endA, k);
        }
        
        //Two edge cases
        if (m == 0) {
            return b[startB + k - 1];
        }
        if (k == 1) {
            return Math.min(a[startA], b[startB]);
        }
        
        //Devide k into two parts
        int pa = Math.min(k/2, m);
        int pb = k - pa;
        
        if (a[startA + pa - 1] < b[startB + pb - 1]) {
            //Discard the partA from a
            return findKthFromTwoSortedArray(a, startA + pa, endA, b, startB, endB, k - pa);
        } else if (a[startA + pa - 1] > b[startB + pb - 1]) {
            //Discard the partB from b
            return findKthFromTwoSortedArray(a, startA, endA, b, startB + pb, endB, k - pb);
        } else {
            return a[startA + pa - 1];
        }
    }
    
    /**
     * Find the k-th largest items from the unsorted arrays nums.
     * It is vary similar with quick sort.
     * 
     * http://www.youtube.com/watch?v=pdmZpAJ2uBM
     * 
     * @param nums The candidate array.
     * @param start Start point inclusive.
     * @param end end point inclusive.
     * @param k The expected position, 1 based.
     * @return 
     */
    public int findKthLargest(int[] nums, int start, int end, int k) {
        
        int pivot = start;//Assume the povit position is the first element.
        //Keep start and end untouched, and copy values for processing in method.
        int left = start;
        int right = end;
        
        while (left <= right) {
            //Scan from left to right to find a value that is larger than pivot value.
            while (left <= right && nums[left] <= nums[pivot]) {
                left++;
            }
            //Scan from right to left to find a value that is less than pivot value.
            while (left <= right && nums[right] >= nums[pivot]) {
                right--;
            }
            //Swap it if it is valid.
            if (left < right) {
                swap(nums, left, right);
            }
        }
        //After the loop, place the pivot to the correct position.
        swap(nums, pivot, right);
        
        //Now it is different from QuickSort here.
        //Firstly check if we can return fro here.
        if (k == right + 1) {//Because k is 1 based, right and left is 0 based.
            return nums[right];
        } else if (k > right + 1) {//The k-th largest item should be in the right part.
            return findKthLargest(nums, right + 1, end, k);
        } else {//The k-th largest item should be in the left part.
            return findKthLargest(nums, start, right - 1, k);
        }
    }
    
    public void quickSort(int[] nums, int start, int end) {
        
        //To avoid bad cases.
        if(start >= end) return;
        
        int pivot = start;
        int left = start;
        int right = end;
        
        while (left <= right) {
            while (left <= right && nums[left] <= nums[pivot]) {
                left ++;
            }
            while (left <= right && nums[right] >= nums[pivot]) {
                right --;
            }
            if (left < right) {
                swap(nums, left, right);
            }
        }
        swap(nums, pivot, right);
        
        //Now sort the left partition
        quickSort(nums, start, right - 1);
        //Sort the right partition
        quickSort(nums, right + 1, end);
    }
    
    
    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    
    public static void main(String[] args) {
        int a[] = {};
        int b[] = {2, 3};
        FindKth fk = new FindKth();
        System.out.println(fk.findKthFromTwoSortedArray(a, 0, a.length - 1, b, 0, b.length - 1, 2));
        
        int[] nums = new int[20];
        java.util.Random r = new java.util.Random();
        for (int i = 0; i < 20; i++) {
            nums[i] = r.nextInt(100);
            System.out.print(nums[i] + ",");
        }
        int[] randoms = java.util.Arrays.copyOfRange(nums, 0, 20);
        
        System.out.println();
        System.out.println("The 10-th largest value is: " + fk.findKthLargest(nums, 0, 19, 10));
        System.out.println("After searching, the array: ");
        for (int i = 0; i < 20; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
        System.out.println("After sorting, the array:");
        fk.quickSort(nums, 0, 19);
        for (int i = 0; i < 20; i++) {
            System.out.print(nums[i] + ",");
        }
        System.out.println();
    }
}
