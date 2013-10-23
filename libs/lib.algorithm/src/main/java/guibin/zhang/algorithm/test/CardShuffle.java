package guibin.zhang.algorithm.test;

import java.util.LinkedList;

/**
 * Shuffle Exercise.
 *  
 * The exercise solves a coding problem that involves shuffling a deck of cards. The 
 * problem description is as follows: 
 *  
 * You are given a deck containing 313 cards. While holding the deck: 
 *  
 * 1. Take the top card off the deck and set it on the table 
 * 2. Take the next card off the top and put it on the bottom of the deck in 
 *  your hand. 
 * 3. Continue steps 1 and 2 until all cards are on the table. This is a round. 
 * 4. Pick up the deck from the table and repeat steps 1-3 until the deck is in 
 *  the original order. 
 *  
 * Write a program to determine how many rounds it will take to put the deck back 
 * into the original order. This will involve creating a data structure to represent the 
 * order of the cards. This program should be written in Java. It should take a 
 * number of cards in the deck as a command line argument and write the result to 
 * stdout. 
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class CardShuffle {
   
    private LinkedList<Integer> original = null;//Original cards
    
    public CardShuffle (LinkedList<Integer> original) {
        //Clone to avoid original list gets changed outside of this class.
        this.original = (LinkedList<Integer>)original.clone();
    }
    
    /**
     * After shuffleOnce, the hand should be empty, and desk holds all the cards.
     * @param hand
     * @param desk 
     */
    private void shuffleOnce(LinkedList<Integer> hand, LinkedList<Integer> desk) {
        
        if (hand == null || desk == null) {
            return;
        }
        
        while (!hand.isEmpty()) {
            //Step 1: Take the top card off the deck and set it on the table.
            desk.push(hand.removeFirst());
            //Step 2: Take the next card off the top and put it on the bottom of the deck in your hand
            if (!hand.isEmpty()) {
                hand.addLast(hand.removeFirst());
            }
        }
    }
    
    /**
     * Shuffle the cards until it is in original order.
     * @return The number of rounds it takes to put the deck back into the original order
     */
    public int shuffle() {
        
        LinkedList<Integer> hand = (LinkedList<Integer>)original.clone();
        LinkedList<Integer> desk = new LinkedList<Integer>();
        int counter = 1;
        
        shuffleOnce(hand, desk);
        
        while (!isSameWith(desk)) {
            //Swap hand and desk
            LinkedList<Integer> tmp = hand;
            hand = desk;
            desk = tmp;
            
            shuffleOnce(hand, desk);
            counter++;
        }
        return counter;
    }
    
    /**
     * Judge whether the content of target is same with original's.
     * @param target
     * @return 
     */
    private boolean isSameWith(LinkedList<Integer> target) {
        
        assert(target != null && original.size() == target.size());
        
        for (int i = 0; i < original.size(); i++) {
            if (original.get(i) != target.get(i)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        
        if (args == null || args.length < 1) {
            System.err.println("Missing cards which is seperated by comma. Eg: 1,2,3,4,5");
            return;
        }
        LinkedList<Integer> originalCards = new LinkedList<Integer>();
        
        String cards = args[0];
        String[] arrCards = cards.split(",");
        
        for (String c : arrCards) {
            originalCards.add(Integer.parseInt(c));
        }
        
        CardShuffle cs = new CardShuffle(originalCards);
        int counter = cs.shuffle();
        
        System.out.println("It takes " + counter + " rounds to shuffle the cards: " + cards);
    }
}
