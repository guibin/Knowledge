package interview.codetest.dependencychecker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author Guibin Zhang <guibin.beijng@gmail.com>
 */
public class DependencyManagerTest extends TestCase {

    public DependencyManagerTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(DependencyManagerTest.class);
    }
    
    public void testResolveDependency() {
        GraphNode a = new GraphNode("A");
        GraphNode b = new GraphNode("B");
        GraphNode c = new GraphNode("C");
        GraphNode d = new GraphNode("D");
        GraphNode e = new GraphNode("E");
        a.addEdge(b);
        a.addEdge(d);
        b.addEdge(c);
        b.addEdge(e);
        c.addEdge(d);
//        d.addEdge(b);
        
        DependencyManager dm = new DependencyManager();
        List<GraphNode> resolved = new ArrayList<>();
        try {
            dm.resolveDependency(a, resolved, new HashSet<GraphNode>());
            //After resolving, the list should be end with the root.
            assertEquals(resolved.get(resolved.size() - 1).getName(), "A");
            
            List<GraphNode> dedupResolved = dm.dedup(resolved);
            assertEquals(dedupResolved.size(), 5);
            
            //Node D and E should be the base node which can be installed indepently at very first
            assertTrue(dedupResolved.get(0).getName().equals("D") 
                    || dedupResolved.get(0).getName().equals("E"));
            
            //Node B and C should be installed after D E.
            assertTrue(dedupResolved.get(3).getName().equals("C") 
                    || dedupResolved.get(3).getName().equals("B"));
            
            assertEquals(dedupResolved.get(dedupResolved.size() - 1).getName(), "A");
            
        } catch (CircularDependencyException ex) {
            Logger.getLogger(DependencyManagerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testVerifyInstallOrder() {
        GraphNode a = new GraphNode("A");
        GraphNode b = new GraphNode("B");
        GraphNode c = new GraphNode("C");
        GraphNode d = new GraphNode("D");
        GraphNode e = new GraphNode("E");
        a.addEdge(b);
        a.addEdge(d);
        b.addEdge(c);
        b.addEdge(e);
        c.addEdge(d);
        
        DependencyManager dm = new DependencyManager();
        GraphNode[] listAA = {e, d, c, b, a};
        List<GraphNode> listA = new ArrayList<>();
        listA.add(e);
        listA.add(e);
        listA.add(e);
        listA.add(e);
        listA.add(e);
        assertTrue(dm.verifyInstallOrder(listA));
        
        List<GraphNode> listB = new ArrayList<>();
        listB.add(d);
        listB.add(b);
        listB.add(a);
        assertFalse(dm.verifyInstallOrder(listB));
    }
    
    public void testSearch() {
        GraphNode a = new GraphNode("A");
        GraphNode b = new GraphNode("B");
        GraphNode c = new GraphNode("C");
        GraphNode d = new GraphNode("D");
        GraphNode e = new GraphNode("E");
        a.addEdge(b);
        a.addEdge(d);
        b.addEdge(c);
        b.addEdge(e);
        c.addEdge(d);
        
        DependencyManager dm = new DependencyManager();
        assertEquals(dm.search("D", a), d);
        assertNull(dm.search("F", a));
    }
}
