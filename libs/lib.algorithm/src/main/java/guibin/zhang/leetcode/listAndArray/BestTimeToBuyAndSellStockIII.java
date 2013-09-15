package guibin.zhang.leecode.listAndArray;

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
    
    /**
     * This method is very clear, but Time Limit Exceed when judge large.
     * @param prices
     * @return 
     */
    public int maxProfit_v1(int[] prices) {
        
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
                if(prices[i] < lowest) {
                    lowest = prices[i];
                }
                maxProfit = Math.max(maxProfit, prices[i] - lowest);
            }
        }
        return maxProfit;
    }
    
    public int maxProfit_v2(int[] prices) {
        
        if(prices.length < 2) {
            return 0;
        }
        
        /**
         * stores the max profit in [0, ... , i] subarray in prices
         */
        int[] maxEndWith = new int[prices.length];
        //Build the maxEndWith
        int lowest = prices[0];
        int maxProfit = 0;
        maxEndWith[0] = 0;
        for(int i = 1; i < prices.length; i++) {
            int profit = prices[i] - lowest;
            if(profit > maxProfit) {
                maxProfit = profit;
            }
            maxEndWith[i] = maxProfit;
            if(prices[i] < lowest){
                lowest = prices[i];
            }
        }
        
        int result = maxEndWith[prices.length - 1];
        /**
         * reverse to check what is the maxprofit of [i, ... , n-1] subarray in prices
         * and meanwhile calculate the final result
         */
        int highest = prices[prices.length - 1];
        maxProfit = 0;
        for(int i = prices.length - 2; i >= 0; i--) {
            int profit = highest - prices[i];
            if(profit > maxProfit) {
                maxProfit = profit;
            }
            int finalProfit = maxProfit + maxEndWith[i];
            if(finalProfit > result) {
                result = finalProfit;
            }
            if(prices[i] > highest) {
                highest = prices[i];
            }
        }
        
        return result;
    }
    
    /**
     * This method is wrong.
     * 并非连续地波峰-波谷就是答案，而是最的两个峰－最低的两个谷，峰谷之间不一定相邻
     * @param prices
     * @return 
     */
    public int maxProfit_error(int[] prices) {
        
        int len = prices.length;
        //record the history of the buy and sell
        int[] buyAndSell = new int[len];//buy = 1; sell = -1; default = 0;
        
        if(len <= 1) {
            return 0;
        } else if(len == 2) {
            return Math.max(prices[1] - prices[0], 0);
        }
        
        int sell = 0;
        int buy = 0;
        int profit = 0;
        int first = 0;
        int second = 0;
        boolean sold = false;
        for(int i = 0; i < len - 1; i++) {
            if(i == 0 && prices[i] < prices[i + 1]) {
                buy = i;
                sold = false;
                buyAndSell[buy] = 1;
            } else if (i > 0) {
                // /\
                if (prices[i - 1] <= prices[i] && prices[i + 1] <= prices[i]) {
                    sell = i;
                    if (sell >= buy && !sold) {
                        profit = prices[sell] - prices[buy];
                        sold = true;
                        buyAndSell[sell] = -1;

                        if (profit > first) {
                            second = first;
                            first = profit;
                        } else if (profit > second) {
                            second = profit;
                        }
                    }
                } // \/
                else if (prices[i - 1] >= prices[i] && prices[i + 1] >= prices[i]) {
                    buy = i;
                    sold = false;
                    buyAndSell[buy] = 1;
                }
            }
            
        }
        
        if(sell < len - 1 && prices[len - 1] > prices[buy] && !sold) {
            sell = len - 1;
            profit = prices[sell] - prices[buy];
            buyAndSell[sell] = -1;
            
            if (profit > first) {
                second = first;
                first = profit;
            } else if (profit > second) {
                second = profit;
            }
        }
        
        StringBuilder sbPrices = new StringBuilder();
        StringBuilder sbBuyAndSell = new StringBuilder();
        for(int i = 0; i < len; i++) {
            sbPrices.append(prices[i]).append(",");
            sbBuyAndSell.append(buyAndSell[i]).append(",");
        }
        System.out.println("first=" + first + ", second=" + second + ", maxProfit=" + (first + second));
        System.out.println(sbPrices.toString());
        System.out.println(sbBuyAndSell.toString());
        
        return first + second;
    }
    
    public static void main(String[] args) {
        int[] prices1 = {6,1,3,2,4,7};//7
        int[] prices2 = {2,1,4,5,2,9,7};//11
        int[] prices3 = {2,1,2,1,0,1,2};//3
        int[] prices4 = {1,2,4,2,5,7,2,4,9,0};//13
        int[] prices5 = {1,2,4,2,5,7,2,4,9,0,9};//17
        BestTimeToBuyAndSellStockIII btt = new BestTimeToBuyAndSellStockIII();
        btt.maxProfit_v1(prices1);
        btt.maxProfit_v1(prices2);
        btt.maxProfit_v1(prices3);
        btt.maxProfit_v1(prices4);
        btt.maxProfit_v1(prices5);
    }
    
}
