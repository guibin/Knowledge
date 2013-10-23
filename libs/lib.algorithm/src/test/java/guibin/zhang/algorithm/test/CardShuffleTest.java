package guibin.zhang.algorithm.test;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class CardShuffleTest {
    
    public CardShuffleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testShuffle() {
        LinkedList<Integer> cards = new LinkedList<Integer>();
        cards.add(1);
        cards.add(2);
        cards.add(3);
        cards.add(4);
        cards.add(5);
        CardShuffle cs = new CardShuffle(cards);
        assertEquals(cs.shuffle(), 5);
    }
    
    @Test
    public void testShuffle2() {
        LinkedList<Integer> cards = new LinkedList<Integer>();
        cards.add(1);
        cards.add(2);
        cards.add(3);
        cards.add(4);
        cards.add(5);
        cards.add(6);
        cards.add(7);
        cards.add(8);
        CardShuffle cs = new CardShuffle(cards);
        assertEquals(cs.shuffle(), 4);
    }
}