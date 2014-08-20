package interview.codetest.dependencychecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Use this GraphNode to organize the dependencies into a graph. 
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class GraphNode {
    
    //Name of the dependencies, such as slf4j, hibernate, common.
    private String name;
    //All the dependencies of this node.
    private List<GraphNode> edges = new ArrayList<>();
    
    public GraphNode(String name) {
        this.name = name;
    }
    
    public void addEdge(GraphNode node) {
        this.edges.add(node);
    }

    public String getName() {
        return name;
    }
    
    /**
     * TODO make the return value immutable.
     * @return all the dependencies of the node.
     */
    public List<GraphNode> getEdges() {
        return edges;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GraphNode) {
            GraphNode that = (GraphNode)obj;
            return this.name.equals(that.name);
        } else {
            return false;
        }
    }
}
