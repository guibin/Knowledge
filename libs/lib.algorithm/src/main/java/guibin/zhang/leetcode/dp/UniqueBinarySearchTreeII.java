package guibin.zhang.leetcode.dp;

import java.util.List;
import java.util.LinkedList;

/**
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 * 
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 * 
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 * 
 * 总结一点，要求出所有组合的问题，一般用dfs；如果要求总数，用dp。
 * 
 * 另外，这道题的思路就是对1..n中的每一个数都依次让它做root，然后分出左右区间，再递归求解。
 * 最后把左右区间求得的子结果依次分别作为root的左右孩子，因此总共要3次循环。
 * 还有值得注意的技巧是，当begin>end时，要往ret AL里面添加null，使得每个AL里面至少有一个元素（null）。
 * 这样可以避免判断只有左区间或只有右区间的情况。
 * 
 * http://www.lifeincode.net/programming/leetcode-unique-binary-search-trees-i-and-ii-java/
 * http://blog.csdn.net/fightforyourdream/article/details/17345795
 * 
 */
public class UniqueBinarySearchTreeII {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }
    
    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> list = new LinkedList<>();
        if (start > end) {
            //We must add null, this will be used by line 61 and 62.
            list.add(null);
            return list;
        }
        //Let i to be root from start to end
        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = generateTrees(start, i - 1);
            List<TreeNode> rights = generateTrees(i + 1, end);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    list.add(node);
                }
            }
        }
        return list;
    }
}
