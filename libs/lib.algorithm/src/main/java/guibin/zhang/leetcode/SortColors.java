package guibin.zhang.leecode;

/**
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SortColors {
    
    /**
     * Run Status: Accepted!
     * Program Runtime: 604 milli secs
     * Progress: 86/86 test cases passed.
     * 
     * @param A 
     */
    public void sortColors(int[] A) {
        int[] tmp = new int[3];
        for(int i : A) {
            tmp[i]  += 1;
        }
        
        int m = 0;
        for(int j = 0; j < tmp.length; j++) {
            for(int k = 1; k <= tmp[j]; k ++) {
                A[m] = j;
                m ++;
            }
        }
    }
    
    public void sortColors_v2(int[] A) {
        int start = -1;
        int end = A.length;
        //Note: here i < end instead of A.length, since end is decreased, A.length is constant.
        for (int i = 0; i < end; i++) {
            if (A[i] == 0) {
                start ++;//Start <= i
                int tmp = A[start];
                A[start] = A[i];
                A[i] = tmp;
            } else if (A[i] == 2) {
                end --;
                int tmp = A[end];
                A[end] = A[i];
                A[i] = tmp;
                //Note: after swaping, the element has been changed, 
                //so need to i-- to rejudge the new element in the original position.
                i--;
            }
        }
        
    }
    
    public static void main(String[] args) {
        
        SortColors sc = new SortColors();
        int[] A = {2};
        sc.sortColors_v2(A);
        for(int i : A) {
            System.out.print(i + ",");
        }
        System.out.println("");
    }
}
