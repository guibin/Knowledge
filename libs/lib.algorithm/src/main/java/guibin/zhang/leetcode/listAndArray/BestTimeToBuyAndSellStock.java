package guibin.zhang.leetcode.listAndArray;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
 * design an algorithm to find the maximum profit.
 * 
 * 
 * At first glance, you might think that finding the minimum and maximum value would do, 
 * but it does have a hidden restriction, that is: You must buy before you can sell.
 * 
 * The question is equivalent to the following:
 * Find i and j that maximizes Aj – Ai, where i < j.
 * There is an obvious O(N2) solution, but in fact we can do better in just O(N).
 * 
 * To solve this problem efficiently, you would need to track the minimum value’s index. 
 * As you traverse, update the minimum value’s index when a new minimum is met. 
 * Then, compare the difference of the current element with the minimum value. 
 * Save the buy and sell time when the difference exceeds our maximum difference 
 * (also update the maximum difference).
 * 
 * 
 * http://leetcode.com/2010/11/best-time-to-buy-and-sell-stock.html
 * http://leetcode.com/2010/11/best-time-to-buy-and-sell-stock.html
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class BestTimeToBuyAndSellStock {
    
    public int maxProfit_v2(int[] prices) {
        
        int lowest = 0;
        int maxProfit = 0;
        
        if(prices.length > 0) {
            lowest = prices[0];
            
            for(int i = 0; i < prices.length; i++) {
                if(lowest > prices[i]) {
                    lowest = prices[i];
                }
                maxProfit = Math.max(maxProfit, prices[i] - lowest);
            }
        }
        return maxProfit;
    }
    
    
    /**
     * Brute force
     * @param prices
     * @return 
     */
    public int maxProfit(int[] prices) {
        
        int maxProfit = 0;
        
        for(int i = 0; i < prices.length; i++) {
            int buy = prices[i];
            for(int j = i; j < prices.length; j++) {
                int sell = prices[j];
                int profit = sell - buy;
                maxProfit = profit > maxProfit ? profit : maxProfit;
            }
        }
        
        return maxProfit;
    }
    
    public int maxProfit_v3(int[] prices) {
        
        int min = 0;//index of min price
        int maxProfit = 0;
        int buy = 0;//index of buy
        int sell = 0;//index of sell
        
        if(prices.length > 0) {
            for(int i = 0; i < prices.length; i++) {
                if(prices[i] < prices[min]) {
                    min = i;
                }
                int diff = prices[i] - prices[min];
                if(diff > maxProfit) {
                    buy = min;
                    sell = i;
                    maxProfit = diff;
                }
            }
        }
        return maxProfit;
    }
}
