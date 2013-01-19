package guibin.zhang.algorithm.gametheory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guibin
 */
public class TakeCoin {
    
    /**
     * A game played by two players who take turns moving. 
     * At the beginning there are n coins. 
     * When it is a player's turn he can take away 1, 2 or 4 coins. 
     * The player who takes the last one away is declared the looser.
     * The question is: For what n will the first player win if they both play optimally?
     * 
     * @param n
     * @return 
     */
    public boolean winLoseRuleA(int n) {
        
        //Basic cases, it is my turn to take the coin, the win lose is as below:
        if(n == 1) return false;
        if(n == 2) return true;
        if(n == 3) return true;
        if(n == 4) return false;
        
        //Advance case, it is my turn to take the coin(take 1, 2 or 4 coins), 
        //if either case the counterpart can lose, then I must win.
        if(!winLoseRuleA(n-1) || !winLoseRuleA(n-2) || !winLoseRuleA(n-4)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean winLoseRuleAWithCache(int n, Map<Integer, Boolean> cache) {
        
        if (cache.containsKey(n)) {
            return cache.get(n);
        } else {
            if (n == 1 || n == 4) {
                cache.put(n, false);
                return false;
            }
            if(n == 2 || n == 3) {
                cache.put(n, true);
                return true;
            }
            
            if(!winLoseRuleAWithCache(n-1, cache) || !winLoseRuleAWithCache(n-2, cache) 
                    || !winLoseRuleAWithCache(n-4, cache)) {
                cache.put(n, true);
                return true;
            } else {
                cache.put(n, false);
                return false;
            }
        }
    }
    
    /**
     * A game, played by two players who take turns moving. 
     * At the beginning there are n coins. 
     * When it is a player's turn he can take away 1, 3 or 4 coins. 
     * The player who takes the last one away is declared the winner 
     * (in other words, the player who can not make a move is the loser). 
     * The question is: For what n will the first player win if they both play optimally?
     * 
     * @param n
     * @return 
     */
    public boolean winLoseRuleB(int n) {
        if(n == 1) return true;
        if(n == 2) return false;
        if(n == 3) return true;
        if(n == 4) return true;
        
        if(!winLoseRuleB(n-1) || !winLoseRuleB(n-3) || !winLoseRuleB(n-4)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean winLoseRuleBWithCache(int n, Map<Integer, Boolean> cache) {
        if(cache.containsKey(n)) {
            return cache.get(n);
        } else {
            if(n == 1 || n == 3 || n==4) {
                cache.put(n, true);
                return true;
            }
            if(n == 2) {
                cache.put(n, false);
                return false;
            }
            
            if (!winLoseRuleBWithCache(n - 1, cache) || !winLoseRuleBWithCache(n - 3, cache) 
                    || !winLoseRuleBWithCache(n - 4, cache)) {
                cache.put(n, true);
                return true;
            } else {
                cache.put(n, false);
                return false;
            }
        }
    }
    
    public static void main(String[] args) {
        TakeCoin tc = new TakeCoin();
        for(int i = 1; i<=1000; i++) {
            Long start = System.currentTimeMillis();
            boolean r2 = tc.winLoseRuleAWithCache(i, new HashMap<Integer, Boolean>());
            Long d2 = System.currentTimeMillis() - start;
            System.out.println(i + "\t" + r2 + "\t" + d2);
        }
        
        System.out.println("----------");
//        for(int i = 1; i<=60; i++) {
//            Long start = System.currentTimeMillis();
//            boolean r1 = tc.winLoseRuleA(i);
//            Long d1 = System.currentTimeMillis() - start;
//            System.out.println(i + "\t" + r1 +  "\t" + d1);
//        }
//        for(int i=1; i<50;  i++) {
//            Long start = System.currentTimeMillis();
//            boolean r3 = tc.winLoseRuleB(i);
//            Long d3 = System.currentTimeMillis() - start;
//            System.out.println(i + "\t" + r3 + "\t" + d3);
//        }
        System.out.println("----------");
        for(int i=1; i<1000;  i++) {
            Long start = System.currentTimeMillis();
            boolean r4 = tc.winLoseRuleBWithCache(i, new HashMap<Integer, Boolean>());
            Long d4 = System.currentTimeMillis() - start;
            System.out.println(i + "\t" + r4 + "\t" + d4);
        }
        
    }
}
