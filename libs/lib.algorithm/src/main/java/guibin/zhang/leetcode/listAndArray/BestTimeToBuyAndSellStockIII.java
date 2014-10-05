package guibin.zhang.leetcode.listAndArray;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete **at most two transactions**.
 * 
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * 
 * 
 * http://blog.unieagle.net/2012/12/05/leetcode%E9%A2%98%E7%9B%AE%EF%BC%9Abest-time-to-buy-and-sell-stock-iii%EF%BC%8C%E4%B8%80%E7%BB%B4%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92/
 * http://blog.csdn.net/guixunlong/article/details/8845649
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class BestTimeToBuyAndSellStockIII {
    
    public int maxProfit(int[] prices) {
        
        if(prices == null || prices.length < 2) return 0;
        
        int[] maxEndWith = new int[prices.length];
        int min = prices[0];
        int max = prices[prices.length - 1];
        int maxProfit = 0;
        //Keep track the min to compute the maxI, save it in maxEndWith
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            maxEndWith[i] = Math.max(prices[i] - min, maxEndWith[i - 1]);
        }
        //Keep track the max to compute the maxII
        for (int j = prices.length - 1; j >= 0; j--) {
            max = Math.max(prices[j], max);
            int profit = max - prices[j];//The profit from second transaction.
            maxProfit = Math.max(maxProfit, profit + maxEndWith[j]);
        }
        return maxProfit;
    }
    
    
    /**
     * This method is very clear, but Time Limit Exceed when judge large.
     * @param prices
     * @return 
     */
    public int maxProfit_naive(int[] prices) {
        
        if(prices.length < 2) return 0;
        int max = 0;
        for(int i = 0; i < prices.length; i++) {
            max = Math.max(max, maxProfitSingle(prices, 0, i) + maxProfitSingle(prices, i, prices.length - 1));
        }
        return max;
    }
    
    /**
     * Just Best Time To Buy And Sell Stock I, keep track of the lowest element, and compute the profit.
     * @param prices
     * @param start
     * @param end
     * @return 
     */
    public int maxProfitSingle(int[] prices, int start, int end) {
        
        int maxProfit = 0;
        int lowest = 0;
        if(end - start + 1 > 0) {
            lowest = prices[start];
            for(int i = start; i <= end; i++) {
                lowest = Math.min(lowest, prices[i]);
                maxProfit = Math.max(maxProfit, prices[i] - lowest);
            }
        }
        return maxProfit;
    }
    
    public static void main(String[] args) {
        int[] prices1 = {6,1,3,2,4,7};//7
        int[] prices2 = {2,1,4,5,2,9,7};//11
        int[] prices3 = {2,1,2,1,0,1,2};//3
        int[] prices4 = {1,2,4,2,5,7,2,4,9,0};//13
        int[] prices5 = {1,2,4,2,5,7,2,4,9,0,9};//17
        BestTimeToBuyAndSellStockIII btt = new BestTimeToBuyAndSellStockIII();
        btt.maxProfit_naive(prices1);
        btt.maxProfit_naive(prices2);
        btt.maxProfit_naive(prices3);
        btt.maxProfit_naive(prices4);
        btt.maxProfit_naive(prices5);
    }
    
}
