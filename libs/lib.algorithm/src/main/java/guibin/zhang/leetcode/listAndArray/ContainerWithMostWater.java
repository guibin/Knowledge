package guibin.zhang.leetcode.listAndArray;

/**
 *
 * Given n non-negative integers a1, a2, ..., an, 
 * where each represents a point at coordinate (i, ai). 
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 * Find two lines, which together with x-axis forms a container, 
 * such that the container contains the most water.
 * 
 * Note: You may not slant the container.
 * 
 * The thought is very similar with the **FourSum, ThreeSum**.
 * 
 * This problem is similar with LargestRectangleInHistogram and TrappingRainWater
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int len = height.length;
        int start = 0;
        int end = len - 1;
        int maxArea = 0;
        
        while(start < end) {
            int area = (end - start) * Math.min(height[start], height[end]);
            maxArea = Math.max(area, maxArea);
            if (height[start] < height[end]) {
                start ++;
            } else {
                end --;
            }
        }
        return maxArea;
    }
}
