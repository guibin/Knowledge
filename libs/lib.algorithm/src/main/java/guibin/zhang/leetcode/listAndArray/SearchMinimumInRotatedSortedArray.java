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
            
            //Case [1,3] and [3,1]
            if (start + 1 == end) return Math.min(num[start], num[end]);
            
            int mid = start + (end - start)/2;
            if (num[start] < num[end]) {//Case: [1, 2, 3, 4]
                return num[start];  
            } else if ((mid - 1) >= 0 && num[mid - 1] > num[mid]) {//Normal acceptable case
                return num[mid];
            } else if (num[mid] >= num[start]) {//On left part
                start = mid + 1;
            } else if (num[mid] <= num[end]) {//On right part
                end = mid - 1;
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
