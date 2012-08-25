package guibin.zhang.algorithm;

/**
 *
 * @author guibin
 */
public class InsertSortStraight {
    
    /**
     * User the straight insert sort to sort the numbers
     * Time complexity: O(N^2), Space complexity: O(1)
     * @param numbers
     * @return 
     */
    public int[] sort(int[] numbers) {
        
        if(numbers == null || numbers.length <= 0) {
            return null;
        }
        
        int postKey = 0;//postKey start from index 1
        for(int i=1; i<numbers.length; i++) {
            //The number to be inserted
            postKey = numbers[i];
            
            //Searching the first number which is >= postKey
            int j = 0;
            for(j=i-1; j>=0; j--) {
                if(postKey < numbers[j]) {
                    //Shift backward
                    numbers[j+1] = numbers[j];
                } else {
                    break;
                }
            }
            numbers[j+1] = postKey;
        }
        
        return numbers;
    }
    
    public static void main(String[] args) {
        InsertSortStraight iss = new InsertSortStraight();
        int[] numbers = {2,4,9,1,3,8,5,6};
        int[] result = iss.sort(numbers);
        for(int i: result) {
            System.out.println(i);
        }
    }
}
