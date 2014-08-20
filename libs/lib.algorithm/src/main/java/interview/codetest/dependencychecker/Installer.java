package interview.codetest.dependencychecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Guibin Zhang <guibin.beijing@gmail.com>
 */
public class Installer {
    
    /**
     * 
     * @param dependencyFile The dependency file
     * @return The to be installed list of graph
     * @throws IOException 
     */
    public Map<String, GraphNode> generateDependencyGraph(File dependencyFile) throws IOException {
        
        Map<String, GraphNode> installMap = new HashMap<>();
        Properties p = new Properties();
        p.load(new FileInputStream(dependencyFile));
        Enumeration it = p.propertyNames();
        
        while(it.hasMoreElements()) {
            String key = (String)it.nextElement();
            String value = p.getProperty(key);
            String[] values = value.split(",");
            //Key is the vertex
            GraphNode k = new GraphNode(key);
            installMap.put(key, k);
            //Value is the edges
            for (String v : values) {
                GraphNode vnode = new GraphNode(v);
                k.addEdge(vnode);
            }
        }
        return installMap;
    }
    
    /**
     * Search the node named 'name' in install map m.
     * @param m
     * @param name
     * @return 
     */
    public GraphNode searchGraphNode(Map<String, GraphNode> m, String name) {
        
        GraphNode gNode = m.get(name);
        if(gNode != null) {
            return gNode;
        }
        
        DependencyManager dm = new DependencyManager();
        for(GraphNode n : m.values()) {
            gNode = dm.search(name, n);
            if(gNode != null) {
                return gNode;
            }
        }
        return null;
    }
    
    public void execute(File dependencyFile, File commandFile) throws IOException, CircularDependencyException {

        Map<String, GraphNode> installMap = generateDependencyGraph(dependencyFile);
        DependencyManager dm = new DependencyManager();

        BufferedReader reader = new BufferedReader(new FileReader(commandFile));
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] kv = line.split("=");
            List<GraphNode> installList = new ArrayList<>();
            
            for (String module : kv[1].split(",")) {
                GraphNode g = searchGraphNode(installMap, module);
                if (g != null) {
                    if ("LIST_DEPENDENTS".equals(kv[0])) {
                        dm.printDependencies(g);
                    } else if ("INSTALL".equals(kv[0])) {
                        installList.add(g);
                    }
                } else {
                    Logger.getLogger(Installer.class.getName()).log(Level.WARNING, module + " doesn't exist.");
                }
            }
            if (!installList.isEmpty()) {
                dm.verifyInstallOrder(installList);
            }
        }
        reader.close();
    }
    
    
    
    public static void main(String[] args) throws IOException, CircularDependencyException {
        
        Installer install = new Installer();
        install.execute(new File("src/main/resources/dependencies.txt"), new File("src/main/resources/commands.txt"));
        
        if (args == null || args.length < 2) {
            System.err.println("Missing parameter: <dependencyFile> <commandFile>");
            return;
        }
        File dependencyFile = new File(args[0]);
        File commandFile = new File(args[1]);
        
        
        try {
            install.execute(dependencyFile, commandFile);

        } catch (IOException ex) {
            Logger.getLogger(Installer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CircularDependencyException ex) {
            Logger.getLogger(Installer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
