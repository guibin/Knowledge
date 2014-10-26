package guibin.zhang.leetcode.listAndArray;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. 
 * You may complete as many transactions as you like 
 * (ie, buy one and sell one share of the stock multiple times). 
 * 
 * However, you may not engage in multiple transactions at the same time 
 * (ie, you must sell the stock before you buy again).
 * 
 * http://discuss.leetcode.com/questions/286/best-time-to-buy-and-sell-stock-ii
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class BestTimeToBuyAndSellStockII {
    
    /**
     * 因为不限制交易次数， 并且同一个价格可以卖再买，因此累加所有差价就OK
     * @param prices
     * @return 
     */
    public int maxProfit_v2(int[] prices) {
        
        if (prices == null || prices.length == 0) return 0;
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit += Math.max((prices[i] - prices[i - 1]), 0);
        }
        return profit;
    }
}
