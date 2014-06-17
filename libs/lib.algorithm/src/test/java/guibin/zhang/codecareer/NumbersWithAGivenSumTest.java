package guibin.zhang.codecareer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guibin Zhang
 */
public class NumbersWithAGivenSumTest {
    
    public NumbersWithAGivenSumTest() {
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

    /**
     * Test of determine method, of class NumbersWithAGivenSum.
     */
    @Test
    public void testsumToZero() {
        System.out.println("sumToZero");
        int[] nums = {1, 2, 4, 7, 11, 15};
        NumbersWithAGivenSum instance = new NumbersWithAGivenSum();
        assertFalse(instance.sumToZero(nums));
        
        int[] nums2 = {1, 2, 4, 7, -11, 15, -16};
        assertTrue(instance.sumToZero(nums2));
    }

    /**
     * Test of sumToTarget method, of class NumbersWithAGivenSum.
     */
    @Test
    public void testSumToTarget() {
        System.out.println("sumToTarget");
        NumbersWithAGivenSum instance = new NumbersWithAGivenSum();
        
        int[] nums = {1, 2, 4, 7, 11, 15};
        int target = 15;
        int[] expResult = {4, 11};
        assertArrayEquals(instance.sumToTarget(nums, target), expResult);
        
        int[] nums2 = {1, 2, 4, 7, 11, 15};
        target = 18;
        int[] expResult2 = {7, 11};
        assertArrayEquals(instance.sumToTarget(nums2, target), expResult2);
    }
    
}
