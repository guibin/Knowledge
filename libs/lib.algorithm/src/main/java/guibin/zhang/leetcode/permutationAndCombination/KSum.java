package guibin.zhang.leetcode.permutationAndCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
 * Find all unique quadruplets in the array which gives the sum of target.
 * 
 * Note:
 * 
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ? b ? c ? d)
 * The solution set must not contain duplicate quadruplets.
 *     For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 * 
 *     A solution set is:
 *     (-1,  0, 0, 1)
 *     (-2, -1, 1, 2)
 *     (-2,  0, 0, 2)
 * 
 * http://tech-wonderland.net/blog/summary-of-ksum-problems.html
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class KSum {

    /**
     * 
     * 2sum的算法复杂度是O(N log N) 因为排序用了N log N以及头尾指针的搜索是线性的，所以总体是O(N log N)，
     * 
     * 好了现在考虑3sum, 有了2sum其实3sum就不难了，这样想：先取出一个数，
     * 那么我只要在剩下的数字里面找到两个数字使得他们的和等于(target – 那个取出的数)就可以了吧。
     * 所以3sum就退化成了2sum, 取出一个数字，这样的数字有N个，所以3sum的算法复杂度就是O(N^2 ), 
     * 注意这里复杂度是N平方，因为你排序只需要排一次，后面的工作都是取出一个数字，然后找剩下的两个数字，
     * 找两个数字是2sum用头尾指针线性扫，这里很容易错误的将复杂度算成O(N^2 log N)，这个是不对的。
     * 
     * 我们继续的话4sum也就可以退化成3sum问题(copyright @sigmainfy)，那么以此类推，K-sum一步一步退化，最后也就是解决一个2sum的问题，
     * K sum的复杂度是O(n^(K-1))。 这个界好像是最好的界了，也就是K-sum问题最好也就能做到O(n^(K-1))复杂度
     * 
     * @param a The source array
     * @param begin The start index
     * @param k How many numbers to be summed.
     * @param target The target value
     * @return
     */
    public static List<List<Integer>> kSumInSortedArray(int[] a, int begin, int k, int target) {

        List<List<Integer>> result = new ArrayList<>();
        //visited is just for deduplication.
        //If int[] a doesn't has any deplication, then visited is not helpful.
        Set<Integer> visited = new HashSet<>();
        //Base case, Two sum.
        if (k == 2) {
            int i = begin, j = a.length - 1;
            while (i < j) {
                int sum = a[i] + a[j];
                if (sum == target && !visited.contains(a[i])) {
                    visited.add(a[i]);
                    List<Integer> branch = new ArrayList<>();
                    branch.add(a[i]);
                    branch.add(a[j]);
                    result.add(branch);
                    i++;
                    j--;
                } else if (sum < target) {
                    i++;
                } else { //sum > target
                    j--;
                }
            }
        } else {//Normal case, recursivly invoke
            for (int i = begin; i < a.length; i++) {
                if (!visited.contains(a[i])) {
                    visited.add(a[i]);
                    List<List<Integer>> subResult = kSumInSortedArray(a, i + 1, k - 1, target - a[i]);

                    if (subResult.size() > 0) {
                        for (int j = 0; j < subResult.size(); j++) {
                            //Add back the a[i] which is subtracted previously.
                            subResult.get(j).add(0, a[i]);//Prepend is to make the result in asending order.
                        }
                        result.addAll(subResult);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[] num = {1, 1, 0, -1, -1, 2, -2};
        System.out.println("zeroSumInSortedArray");
        Arrays.sort(num);
        List<List<Integer>> result2 = kSumInSortedArray(num, 0, 4, 0);
        result2.stream().forEach(
                (lt) -> {
                    lt.stream().forEach(
                            (i) -> {
                                System.out.print(i + ",");
                            });
                    System.out.println();
                });
    }
}
