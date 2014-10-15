package guibin.zhang.leetcode.listAndArray;

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
        
        //If curr hight is bigger than height in stack, push; otherwise pop and compute the area.
        //Keep track of the heigher height in stack.
        Stack<Integer> height = new Stack<>();
        //Indices save the start point. length = i - start.
        Stack<Integer> indices = new Stack<>();
        int start = 0;
        int maxArea = 0;
        for (int i = 0; i < histo.length; i++) {
            //Case 1, the height is larger thus it can be candidate of rectangle of *start*
            int h = histo[i];
            if (height.isEmpty() || h >= height.peek()) {
                height.push(h);
                indices.push(i);
            } else if (h < height.peek()) {
                //If current height is shorter, thus we need pop those larger heights, 
                //and compute the candidate rectangle's area size.
                while (!height.isEmpty() && h < height.peek()) {
                    start = indices.pop();
                    int area = height.pop() * (i - start);
                    maxArea = Math.max(maxArea, area);
                }

                height.push(h);
                //Note: here is lastIndex, not i;
                //indices is used to save the start index, so here should push lastIndex instead of i.
                indices.push(start);
            }
        }
        
        while (!height.isEmpty()) {
            //end - start: indices holds the start point.
            //Because at this time, the height in the stack of height are all greater than histo[curr] 
            //while curr = histo.length - 1. Since it is possible the last item of indices is (histo.length - 1),
            //so we should use (histo.length - indices.pop()) as width 
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
