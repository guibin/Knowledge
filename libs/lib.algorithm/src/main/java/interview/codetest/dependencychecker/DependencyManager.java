package interview.codetest.dependencychecker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class DependencyManager {
    
    /**
     * Resolve the dependency graph, if there is any circular dependency, an exception will be thrown out.
     * 
     * @param node The dependency to be resolved.
     * @param resolved The resolved list.
     * @param unresolved The unresolved set which is used to check the circular dependency.
     * @throws CircularDependencyException
     */
    public void resolveDependency(GraphNode node, List<GraphNode> resolved, Set<GraphNode> unresolved) 
            throws CircularDependencyException {
        
        unresolved.add(node);
        for (GraphNode n : node.getEdges()) {
            if (!resolved.contains(n) && unresolved.contains(n)) {
                throw new CircularDependencyException("Found circular dependencies " + n);
            }
            resolveDependency(n, resolved, unresolved);
        }
        resolved.add(node);
        unresolved.remove(node);
    }
    
    /**
     * Remove the duplicated node from the input list
     * @param list
     * @return The de-duplicated list.
     */
    public List<GraphNode> dedup(List<GraphNode> list) {
        Set<GraphNode> s = new HashSet<>();
        List<GraphNode> ret = new ArrayList<>();
        for (GraphNode n : list) {
            if (!s.contains(n)) {
                s.add(n);
                ret.add(n);
            }
        }
        return ret;
    }
    
    /**
     * Search the node named 'name' in graph g.
     * 
     * @param name
     * @param g
     * @return If found, return the GraphNode, otherwise return null
     */
    public GraphNode search(String name, GraphNode g) {

        if (g.getName().equals(name)) {
            return g;
        }
        
        for (GraphNode n : g.getEdges()) {
            GraphNode ret = search(name, n);
            if (ret != null) {
                return ret;
            }
        }

        return null;
    }
    
    public boolean verifyInstallOrder(List<GraphNode> installList) {
        
        List<GraphNode> resolved = new ArrayList<>();
        Set<GraphNode> unresolved = new HashSet<>();
        //Save all the modules which is ready to be installed.
        Set<GraphNode> ready = new HashSet<>();
        
        for (GraphNode n : installList) {
            try {
                resolved.clear();
                unresolved.clear();
                resolveDependency(n, resolved, unresolved);
                List<GraphNode> dedupResolved = dedup(resolved);
                dedupResolved.remove(dedupResolved.size() - 1);//Remove the last item which is current module itself
                // resolved list size 0 the module is the leaf node, which can be installed independently.
                if (dedupResolved.isEmpty() || ready.containsAll(dedupResolved)) {
                    ready.add(n);
                } else {
                    Logger.getLogger(DependencyManager.class.getName()).log(Level.INFO, n + " is failed to be installed.");
                    return false;
                }
            } catch (CircularDependencyException ex) {
                Logger.getLogger(DependencyManager.class.getName()).log(Level.INFO, n + " is failed to be installed.");
                Logger.getLogger(DependencyManager.class.getName()).log(Level.SEVERE, "Found circular dependency", ex);
                return false;
            }
        }
        return true;
    }
    
    public void printDependencies(GraphNode node) throws CircularDependencyException {
        List<GraphNode> resolved = new ArrayList<>();
        Set<GraphNode> unresolved = new HashSet<>();
        resolveDependency(node, resolved, unresolved);
        
        System.out.println(node + "'s dependencies are:");
        for (int i = 0; i < resolved.size() - 1; i ++) {
            System.out.print(resolved.get(i).toString() + ", ");
        }
        System.out.println();
    }
    
    
}
