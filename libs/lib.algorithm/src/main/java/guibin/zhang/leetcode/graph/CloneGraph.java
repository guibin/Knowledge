package guibin.zhang.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * http://www.cnblogs.com/springfor/p/3874591.html
 * 
 * Solution: DFS traverse all nodes, meanwhile use HashMap to record the node which has been cloned. 
 * use label as key and the new created node as value
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class CloneGraph {

    class UndirectedGraphNode {

        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }
    
    public UndirectedGraphNode cloneGraph_dfs(UndirectedGraphNode node) {
        
        if (node == null) return null;
        
        Map<UndirectedGraphNode, UndirectedGraphNode> checker = new HashMap<>();
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        checker.put(node, head);
        dfs(checker, node);
        
        return head;
    }
    
    public void dfs(Map<UndirectedGraphNode, UndirectedGraphNode> checker, UndirectedGraphNode node) {
        
        if (node == null) return;
        
        for (UndirectedGraphNode neighbor : node.neighbors) {
            if (!checker.containsKey(neighbor)) {
                UndirectedGraphNode nb = new UndirectedGraphNode(neighbor.label);
                checker.put(neighbor, nb);
                dfs(checker, neighbor);
            }
            checker.get(node).neighbors.add(checker.get(neighbor));
        }
    }
    
    public UndirectedGraphNode cloneGraph_bfs(UndirectedGraphNode node) {
        
        if (node == null) return null;
        
        Map<UndirectedGraphNode, UndirectedGraphNode> checker = new HashMap<>();
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        checker.put(node, head);
        q.add(node);
        
        while (!q.isEmpty()) {
            UndirectedGraphNode curr = q.remove();
            for (UndirectedGraphNode neighbor : curr.neighbors) {
                if (!checker.containsKey(neighbor)) {
                    q.add(neighbor);
                    UndirectedGraphNode nb = new UndirectedGraphNode(neighbor.label);
                    checker.put(neighbor, nb);
                }
                checker.get(curr).neighbors.add(checker.get(neighbor));
            }
        }
        return head;
    }
    
    public UndirectedGraphNode cloneGraph_dfs2(UndirectedGraphNode node) {
        
        if (node == null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> checker = new HashMap<>();
        Stack<UndirectedGraphNode> stack = new Stack<>();
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        checker.put(node, head);//save the cloned head
        stack.push(node);
        
        while (!stack.isEmpty()) {
            UndirectedGraphNode curr = stack.pop();
            for (UndirectedGraphNode neighbor : curr.neighbors) {
                if (!checker.containsKey(neighbor)) {
                    UndirectedGraphNode nb = new UndirectedGraphNode(neighbor.label);
                    checker.put(neighbor, nb);
                    stack.push(neighbor);
                }
                checker.get(curr).neighbors.add(checker.get(neighbor));
            }
        }
        return head;
    }
}
