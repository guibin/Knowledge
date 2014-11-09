package guibin.zhang.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree with integer values, output all paths that sum to a given value. 
 * The path can start and end at any node. The path must be from parent node to child node, so on so forth.
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class PathSumIII {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    /**
     * Sum the items of branch together.
     * @param branch
     * @return 
     */
    private int listSum(List<Integer> branch) {
        int result = 0;
        result = branch.stream().map((i) -> i).reduce(result, Integer::sum);
        return result;
    }
    
    
    public void sum(TreeNode root, int target, List<Integer> branch, List<List<Integer>> result) {
        
        if (root == null) return;
        //target == root.val is for classic path sum
        //root.val == 0 && listSum(branch) == target is to include 0 into path.
        if (target == root.val || (root.val == 0 && listSum(branch) == target)) {
            List<Integer> nBranch = new ArrayList<>(branch);
            nBranch.add(root.val);
            result.add(nBranch);
            //No return here, in order to keep on searching even if we have already get the result,
            //Since it is possible that the following items maybe negative and positive again, or zero.
        }
        
        //Here is the classic dfs for path sum.
        branch.add(root.val);
        sum(root.left, target - root.val, branch, result);
        sum(root.right, target - root.val, branch, result);
        branch.remove(branch.size() - 1);
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        
        List<List<Integer>> result = new ArrayList<>();
        
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.remove();
            List<Integer> path = new ArrayList<>();
            sum(curr, target, path, result);
            
            if (curr.left != null) q.add(curr.left);
            if (curr.right != null) q.add(curr.right);
        }
        
        return result;
    }
    
    
    public static void main(String[] args) {
        /**
         *              10
         *           /      \
         *          6        2
         *        /  \     /   \
         *      4     3   8     6
         *             \         \
         *              1        2
         * 
         * Expected output: 
         * 10,
         * 6,4,
         * 6,3,1,
         * 2,8,
         * 2,6,2,
         * 
         */
        PathSumIII ps = new PathSumIII();
        TreeNode a = ps.new TreeNode(10);
        TreeNode b = ps.new TreeNode(6);
        TreeNode c = ps.new TreeNode(2);
        a.left = b; a.right = c;
        TreeNode d = ps.new TreeNode(4);
        TreeNode e = ps.new TreeNode(3);
        b.left = d; b.right = e;
        TreeNode f = ps.new TreeNode(8);
        TreeNode g = ps.new TreeNode(6);
        c.left = f; c.right = g;
        TreeNode h = ps.new TreeNode(1);
        TreeNode i = ps.new TreeNode(2);
        e.right = h; g.right = i;
        TreeNode m = ps.new TreeNode(1);
        i.left = m;
        
        TreeNode root = ps.new TreeNode(3);
        root.right = ps.new TreeNode(0);
        root.right.left = ps.new TreeNode(3);
        root.right.right = ps.new TreeNode(6);
        root.right.right.left = ps.new TreeNode(-3);
        root.right.right.right = ps.new TreeNode(-1);
        root.right.right.right.right = ps.new TreeNode(-2);
        root.right.left.left = ps.new TreeNode(3);
        List<List<Integer>> result = ps.pathSum(root, 3);
        result.forEach(list -> {list.forEach(k -> System.out.print(k + ",")); System.out.println();});
    }
    
}
