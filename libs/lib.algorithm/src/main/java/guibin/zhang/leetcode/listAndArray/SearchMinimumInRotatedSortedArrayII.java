package guibin.zhang.leetcode.listAndArray;

/**
 * 
 * Find Minimum in Rotated Sorted Array with duplicates.
 * 
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * 
 * The array may contain duplicates.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class SearchMinimumInRotatedSortedArrayII {
    
    public int findMin(int[] num) {
        
        int start = 0, end = num.length - 1;
        
        while (start <= end) {
            //Case [1,3] and [3,1]
            if (start + 1 == end) return Math.min(num[start], num[end]);
            
            int mid = start + (end - start)/2;
            if (num[mid] > num[start]) {//On left part
                //Case: [1, 2, 3]
                if (num[start] < num[end]) {
                    return num[start];
                } else {
                    start = mid + 1;
                }
            } else if (num[mid] < num[start]) {//On right part
                //Normal acceptable case
                if ((mid - 1) >= 0 && num[mid - 1] > num[mid]) {
                    return num[mid];
                } else {
                    end = mid - 1;
                }
            } else {//Duplications
                //Case: [1,2,3,1,1]
                start ++;
            }
        }
        
        return num[end];//Case: [1]
    }
}
