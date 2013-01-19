package guibin.zhang.algorithm;

/**
 * Given a sequence of integers sorted in ascending order, find the target value in this sequence.
 * 
 * @author guibin
 */
public class BinarySearch {
    
    /**
     * Search the target in arr which is in ascendent order. Find the value in a sorted sequence.
     * @param arr The array to be searched.
     * @param target The target
     * @return If find return the index of the target, otherwise return -1.
     */
    public int search(int[] arr, int target) {
        if(arr == null) return -1;
        
        int lo = 0;
        int hi = arr.length;
        while(lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if(arr[mid] == target) {
                return mid;
            }
            else if(arr[mid] < target) {
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        
        //Not found
        return -1; 
    }
    
    
    /**
     * Given an array A and a target value, return the index of the first element 
     * in A equal to or greater than the target value.
     * 
     * @param arr
     * @param target
     * @return If find return the index of the target, otherwise return -1.
     */
    public int searchFirstGreaterValue(int[] arr, int target) {
        if(arr == null) return -1;
        
        int lo = 0;
        int hi = arr.length - 1;
        while(lo < hi) {
            int mid = lo + (hi - lo)/2;
            if(f(arr[mid], target)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        
        //Here, must be lo == hi
        if(f(arr[lo],target)) {//Found
            return lo;
        } else {//Not found
            return -1;
        }
    }
    
    private boolean f(int value, int target) {
        return value >= target;
    }
    
    /**
     * Given an array A and a target value, return the index of the last element 
     * in A less than the target value.
     * 
     * @param arr
     * @param target
     * @return If find return the index of the target, otherwise return -1.
     */
    public int searchLastLessValue(int[] arr, int target) {
        if(arr == null) return -1;
        
        int lo = 0;
        int hi = arr.length - 1;
        while(lo < hi) {
            int mid = lo + (hi - lo + 1)/2;
            if(f(arr[mid], target)) {
                hi = mid - 1;
            } else {
                lo = mid;
            }
        }
            
        //Here, must be lo == hi
        if(f(arr[lo], target)) {
            return -1;
        } else {
            return lo;
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {0, 5, 13, 19, 22, 41, 55, 68, 72, 81, 98, 110};
        BinarySearch bs = new BinarySearch();
        int a = bs.searchFirstGreaterValue(arr, 14);//3
        int b = bs.searchFirstGreaterValue(arr, 56);//7
        int c = bs.searchFirstGreaterValue(arr, 120);//-1
        int d = bs.searchFirstGreaterValue(arr, 0);//0
        
        System.out.println(a + "," + b + "," + c + "," + d);
        
        int e = bs.search(arr, 6);//-1
        int f = bs.search(arr, 68);//7
        int g = bs.search(arr, 110);//11
        int h = bs.search(arr, 0);//0
        System.out.println(e + "," + f + "," + g + "," + h);
        
        int i = bs.searchLastLessValue(arr, 21);//3
        int j = bs.searchLastLessValue(arr, 0);//-1
        int k = bs.searchLastLessValue(arr, 110);//10
        int l = bs.searchLastLessValue(arr, 71);//7
        System.out.println(i + "," + j + "," + k + "," + l);
    }
}
