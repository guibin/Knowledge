package guibin.zhang.leecode.tree;

/**
 * 
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * 
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 * 
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 * 
 * 当数组为 1，2，3，4，.. i，.. n时，基于以下原则的BST建树具有唯一性：
 * 以i为根节点的树，其左子树由[0, i-1]构成， 其右子树由[i+1, n]构成。
 * 
 * 
 *      dp[0] = 1; dp[1] = 1;
 *      
 *      dp[n] =  1) n作为根结点，之前的二叉树就是left.child 这种情况： dp[n-1]*dp[0]
 *               2) n-1 是根结点，那么 n只有一种情况 ： dp[n-2] * dp[1]
 *               3) n-2 是根节点，right tree: dp[2] * left child : dp[n -3]
 *               ...
 *               4) 1 是根结点：dp[0] * dp[n-1]
 *      dp[n] = sigma{i = 0..n-1} dp[i]*dp[n-1-i]
 *      
 *      其实就是说白了考虑左右子tree的大小就ok，因为大小暗含了这个时候根结点的情况，所以和枚举根结点从1...n是一样的
 * 
 * http://smartlhc.blogspot.com/2012/08/unique-binary-search-trees.html
 * http://www.cnblogs.com/kwill/archive/2013/06/14/3136768.html
 * http://fisherlei.blogspot.com/2013/03/leetcode-unique-binary-search-trees.html
 * http://discuss.leetcode.com/questions/270/unique-binary-search-trees
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class UniqueBinarySearchTrees {
   
    public int numTrees(int n) {
        
        //定义Count[i] 为以[0,i]能产生的Unique Binary Tree的数目
        int[] count = new int[n + 1];
        
        count[0] = 1;//Empty tree, the number of unique BST is 1.
        count[1] = 1;//Tree with only 1 node, # of unique BST is also 1.
        
        for(int i = 2; i <= n; i++) {
            //j is root, from 0 to i.
            for(int j = 0; j < i; j ++) {
                //Count[i] = ∑(Count[0...k] * Count[k+1....i])     0<=k<i-1]
                count[i] += count[j] * count[i - j -1];
            }
        }
        
        return count[n];
    }
    
    public int numTrees_v2(int n) {
        if (n <= 1) {
            return 1;
        }
        /**
         * there will be one value at the root, with whatever remains 
         * on the left and right each forming their own subtrees.
         * Iterate through all the values that could be the root...
         */
        int sum = 0;
        int left, right, root;
        
        for(root = 1; root <= n; root++) {
            left = numTrees_v2(root - 1);
            right = numTrees_v2(n - root);
            sum += left * right;
        }
        
        return sum;
    }
}
