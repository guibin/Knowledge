package guibin.zhang.onlinecourse;

import java.util.Arrays;

/**
 *
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class Shuffle {
    /**
     * Knuth shuffle.
     * In iteration i, pick integer r between i and N-1 uniformly at random,
     * Or between 0 to i uniformly at random
     * Swap a[i] and a[r]
     * @param a 
     */
    public void shuffle(Object[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            //uniformly random number between i and n-1 inclusive
            //random() -> [0, 1)
            //random() * (N - i) -> [0, N - i)
            //i + random() * (N - i) -> [i, N)
            int r = i + (int)(Math.random() * (N - i));
            swap(a, i, r);
        }
    }
    
    private void swap(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
    
    public static void main(String[] args) {
        Comparable[] a = {50, 61, 44, 32, 99, 87, 51, 50, 50, 12};
        Shuffle sf = new Shuffle();
        sf.shuffle(a);
        Arrays.stream(a).forEach((c) -> System.out.print(c + ","));
        System.out.println();
    }
}
