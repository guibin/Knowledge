package guibin.zhang.leetcode.listAndArray;

/**
 * Two finite, strictly increasing, integer sequences are given. 
 * Any common integer between the two sequences constitute an intersection point. 
 * Take for example the following two sequences where intersection points are 
 * printed in bold: 
 * First  = 1 2 3 5 7 9 20 25 30 40 55 56 57 60 62 
 * Second = 1 4 7 11 14 25 44 47 55 57 100 
 * 
 * You can "walk" over these two sequences in the following way: 
 * 1. You may start at the beginning of any of the two sequences. Now start moving forward. 
 * 2. At each intersection point, you have the choice of either continuing with the same sequence you’re currently on, or switching to the other sequence. 
 * The objective is to find a path that produces the maximum sum of data you walked over. 
 * 
 * In the above example, the largest possible sum is 453 which is the result of adding 
 * 1, 2, 3, 5, 7, 9, 20, 25, 44, 47, 55, 56, 57, 60, 62 
 * 
 * 
 * 
 * Question link: http://www.careercup.com/question?id=6261752413028352
 * 
 * This is just a slightly different take on the merge routine in merge sort. 
 * It does not require recursion nor does it require dynamic programming. 
 * It is implemented iteratively, with the efficiency being O(n+m), 
 * where n and m are the sizes of the two arrays. 
 * 
 * The basic idea is as follows: 
 * 1) Let A and B denote the two arrays 
 * 2) Let idxA be the current index of the array A, and let idxB be the current index of array B 
 * 3) While the element at the current index of A (i.e. A[idxA]) is 
 *    less then the element at the current index of B (B[idxB]) increment idxA, 
 *    and add each element to a running total (call it sumA) 
 * 4) Now that A[idxA] > B[idxB], increment idxB of B until B[idxB] is not less than A[idxA], 
 *    and also add it to a running total (call it sumB) 
 * 5) If at this point, A[idxA] is equal to B[idxB], we have found our intersection point. 
 *    So add the maximum of sumA and sumB i.e. max(sumA,sumB) to the totalresult. 
 *    Set sumA and sumB to zero, and repeat the whole process until all the elements in A and B have been examined. 

 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class SearchMaxSumInTwoSortedArray {
    
    public static int searchMaxSum(int[] A, int[] B) {
        
        int idxA = 0, idxB = 0, sumA = 0, sumB = 0, total = 0;
        while (idxA < A.length || idxB < B.length) {
            //When B is consumed or both are not consumed and A[currA] < B[currB]
            while (idxB >= B.length && idxA < A.length || idxA < A.length && A[idxA] < B[idxB]) {
                sumA += A[idxA ++];
            }
            //When A is consumed or both are not consumed and B[currB] < A[currA]
            while (idxA >= A.length && idxB < B.length || idxB < B.length && B[idxB] < A[idxA]) {
                sumB += B[idxB ++];
            }
            //When A[currA] == B[currB], select a bigger sum to add to total, then reset sum.
            if (idxA < A.length && idxB < B.length && A[idxA] == B[idxB]) {
                sumA += A[idxA ++];
                sumB += B[idxB ++];
                total += Math.max(sumA, sumB);
                sumA = sumB = 0;
            }
        }
        total += Math.max(sumA, sumB);
        
        return total;
    }
    
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 5, 7, 9, 20, 25, 30, 40, 55, 56, 57, 60, 62};
        int[] B = {1, 4, 7, 11, 14, 25, 44, 47, 55, 57, 100};
        int ret = searchMaxSum(A, B);
        System.out.println("Max sum is " + ret);
    }
}
