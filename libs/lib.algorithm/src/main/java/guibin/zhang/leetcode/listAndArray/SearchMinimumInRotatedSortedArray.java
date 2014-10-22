package guibin.zhang.leetcode.listAndArray;

/**
 * 
 * Find Minimum in Rotated Sorted Array.
 * 
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class SearchMinimumInRotatedSortedArray {
    
    public int findMin(int[] num) {
        
        int result = num[0];//Default case: [1]
        int start = 0, end = num.length - 1;
        
        while (start <= end) {
            int mid = start + (end - start)/2;
            //Case: [1, 2]
            if (num[start] < num[end]) {
                return num[start];  
                //Normal acceptable case
            } else if ((mid - 1) >= 0 && num[mid - 1] > num[mid]) {
                return num[mid];
            } else if (num[mid] >= num[start]) {
                start = mid + 1;
            } else if (num[mid] <= num[end]) {
                end = mid - 1;
            } else {
                return result;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        SearchMinimumInRotatedSortedArray sm = new SearchMinimumInRotatedSortedArray();
        int[] num = { 1};
        int result = sm.findMin(num);
        System.out.println(result);
    }
}
