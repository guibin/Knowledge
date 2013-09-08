package guibin.zhang.leecode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * 
 * For example, this binary tree is symmetric:
 * 
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following is not:
 * 
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 * 
 * 
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class SymmetricTree {

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    
    /**
     * Run Status: Accepted!
     * Program Runtime: 696 milli secs
     * Progress: 190/190 test cases passed.
     * 
     * Iterative version.
     * 
     * @param root
     * @return 
     */
    public boolean isSymmetric(TreeNode root) {
        
        boolean flag = true;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<Integer> level = new LinkedList<Integer>();
        List<Integer> row = new ArrayList<Integer>();
        
        int lastLevel = 0;
        if(root != null) {
            queue.offer(root);
            level.offer(0);
        } 
        
        while(!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            int m = level.poll();
            
            if(lastLevel == m) {
                if(curr != null) {
                    row.add(curr.val);
                } else {
                    row.add(0);
                }
            } else {
                //Judge the symmetric of row.
                if(!isSymmetric(row, lastLevel)) {
                    return false;
                }
                row = new ArrayList<Integer>();
                if(curr != null) {
                    row.add(curr.val);
                } else {
                    row.add(0);
                }
                lastLevel = m;
            }
            
            if(curr != null) {
                /**
                 * Note: since we are judge the symmetry, 
                 * we have to put the null child to queue to judge the position.
                 */
                queue.offer(curr.left);
                level.offer(m + 1);
                queue.offer(curr.right);
                level.offer(m + 1);
            }
        }
        
        if (!isSymmetric(row, lastLevel)) {
            return false;
        }
        
        return flag;
    }
    
    
    public boolean isSymmetric(List<Integer> row, int level) {
        
        if(row.size() == 0) {
            return true;
        }
        if(level > 0 && row.size() % 2 == 1) {
            return false;
        }
        
        boolean flag = true;
        int start = 0;
        int end = row.size() - 1;
        while(start <= end) {
            if(row.get(start) != row.get(end)) {
                flag = false;
                break;
            }
            start ++;
            end --;
        }
        
        return flag;
    }
    
    /**
     * Run Status: Accepted!
     * Program Runtime: 640 milli secs
     * Progress: 190/190 test cases passed.
     *  
     * @param root
     * @return 
     */
    public boolean isSymmetric_v2(TreeNode root) {

        if(root == null) {
            return true;
        } else {
            return isSymmetric_v2(root.left, root.right);
        }
    }
    
    public boolean isSymmetric_v2(TreeNode left, TreeNode right) {
        boolean flag = true;
        
        if(left != null && right != null) {
            if(left.val != right.val) {
                return false;
            } 
        } else if(left != null && right == null) {
            return false;
        } else if(left == null && right != null) {
            return false;
        } else {//Note this condition => left == null && right == null
            return true;
        }
        
        flag = isSymmetric_v2(left.left, right.right) && isSymmetric_v2(left.right, right.left);
        
        return flag;
    }
    
    public static void main(String[] args) {
        SymmetricTree st = new SymmetricTree();
        
        TreeNode node = st.new TreeNode(1);
        System.out.println(st.isSymmetric(node));
    }
}
