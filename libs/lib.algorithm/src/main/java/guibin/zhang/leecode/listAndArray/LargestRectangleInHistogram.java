package guibin.zhang.leecode;

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
            if (height.isEmpty() || histo[i] > height.peek()) {
                height.push(histo[i]);
                indices.push(i);
            } else if (histo[i] < height.peek()) {
                while (!height.isEmpty() && histo[i] < height.peek()) {
                    lastIndex = indices.pop();
                    //Deem i as the end point, lastIndex as the start point, length = end - start
                    int tempArea = height.pop() * (i - lastIndex);
                    if (tempArea > maxArea) {
                        maxArea = tempArea;
                    }
                }
                height.push(histo[i]);
                //Note: here is lastIndex, not i;
                indices.push(lastIndex);
            }
        }
        
        while (!height.isEmpty()) {
            //end - start: indices holds the start point.
            int tempArea = height.pop() * (histo.length - indices.pop());
            if (tempArea > maxArea) {
                maxArea = tempArea;
            }
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
