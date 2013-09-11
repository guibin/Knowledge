package guibin.zhang.leecode.listAndArray;

import java.util.Stack;

/**
 *
 * Given n non-negative integers representing the histogram's bar height 
 * where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * 
 * 
 * 
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * 
 * 
 * 
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * 
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 * 
 * This problem is similar with ContainerWithMostWater and TrappingRainWater
 * 
 * http://www.youtube.com/watch?v=E5C5W6waHlo
 * http://blog.csdn.net/abcbc/article/details/8943485
 * http://blog.csdn.net/dgq8211/article/details/7740610
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] histo) {
        
        Stack<Integer> height = new Stack<Integer>();
        //Indices save the start point. length = i - start.
        Stack<Integer> indices = new Stack<Integer>();
        int lastIndex = 0;
        int maxArea = 0;
        for (int i = 0; i < histo.length; i++) {
            //Case 1, the height is larger thus we can be candidate of rectangle of *start*
            if (height.isEmpty() || histo[i] > height.peek()) {
                height.push(histo[i]);
                indices.push(i);
            } else if (histo[i] < height.peek()) {
                //If current height is shorter, thus we need pop those larger heights, 
                //and compute the candidate rectangle's area size.
                while (!height.isEmpty() && histo[i] < height.peek()) {
                    lastIndex = indices.pop();
                    //Deem i as the end point, lastIndex as the start point, length = end - start
                    int area = height.pop() * (i - lastIndex);
                    maxArea = Math.max(maxArea, area);
                }
                //After poping those unqualified start positions including current index, add current
                height.push(histo[i]);
                //Note: here is lastIndex, not i;
                indices.push(lastIndex);
            }
        }
        
        while (!height.isEmpty()) {
            //end - start: indices holds the start point.
            int area = height.pop() * (histo.length - indices.pop());
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
    
    public int largestRectangleArea_v2(int[] histo) {
        
        int maxArea = 0;
        int n = histo.length;
        int[] height = new int[n];
        int[] widths = new int[n];
        int top = 0;
        int w = 0;
        
        for (int h : histo) {
            if (top == 0 || h > height[top - 1]) {
                height[top] = h;
                widths[top] = 1;
                top ++;
            } else {
                w = 0;
                while (top > 0 && h <= height[top - 1]) {
                    w += widths[top - 1];
                    maxArea = Math.max(maxArea, height[top - 1] * w);
                    top --;
                }
                height[top] = h;
                widths[top] = w + 1;
                top ++;
            }
        }
        w = 0;
        while(top > 0) {
            w += widths[top - 1];
            maxArea = Math.max(maxArea, height[top - 1] * w);
            top --;
        }
        return maxArea;
    }
}
