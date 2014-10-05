package guibin.zhang.leetcode.listAndArray;

import scala.actors.threadpool.Arrays;

/**
 *
 * There are N children standing in a line. Each child is assigned a rating value.
 * 
 * You are giving candies to these children subjected to the following requirements:
 * 
 * Each child must have at least one candy.
 * Children with a higher rating get more candies than their neighbors.
 * What is the minimum candies you must give?
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class Candy {
    
    public int candy(int[] ratings) {
        
        int[] candies = new int[ratings.length];
        //Each child must have at least one candy
        Arrays.fill(candies, 1);
        
        //Scan from left to right
        for (int i = 1; i < ratings.length; i++) {
            //If right > left, add one more candy to right
            if (ratings[i] > ratings[i - 1]) candies[i] = candies[i - 1] + 1;
        }
        //Scan from right to left
        for (int j = ratings.length - 2; j >= 0; j--) {
            //If left > right, add one more candy to left
            if (ratings[j] > ratings[j + 1] && candies[j] <= candies[j + 1]) 
                candies[j] = candies[j + 1] + 1;
        }
        //Scan again to aggregrate all the candies.
        int sum = 0;
        for (int k : candies) {
            sum += k;
        }
        return sum;
    }
}
