package guibin.zhang.datastructure;

/**
 * No3 of the book A.
 *
 * @author guibin
 */
public class SearchTwoDimensionalArray {

    /**
     * For example, the input is a two-dimensional array like below. items in
     * each line are sorted asc from left to right, items in each column are
     * sorted asc from top to down. Given an integer, if this integer exist in
     * the array, return true, other wise return false.
     *
     */
    int[][] arr = new int[][]{
               {1, 2, 8, 9}
            , {2, 4, 9, 12}
            , {4, 7, 10, 13}
            , {6, 8, 11, 15}
    };

    /**
     * We plan to search from the top right corner or the down left corner.
     *
     * @param dest The integer to be searched.
     * @param array The two dimensional array.
     * @return true for found. false for not
     */
    public boolean find(int dest, int[][] array) {
        boolean flag = false;

        if (array == null) {
            return flag;
        }

        int x = 0;
        int y = array[0].length - 1;
        while (x < array.length && y >= 0) {
            if (dest == array[x][y]) {
                flag = true;
                break;
            } else if (dest < array[x][y]) {
                y -= 1;
            } else if (dest > array[x][y]) {
                x += 1;
            }
        }


        return flag;
    }

    public static void main(String[] args) {
        SearchTwoDimensionalArray stda = new SearchTwoDimensionalArray();
        System.out.println(stda.find(10, stda.arr));
        System.out.println(stda.find(6, stda.arr));
    }
}
