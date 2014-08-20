package interview.codetest.dependencychecker;

/**
 *
 * @author Guibin Zhang <guibin.beijing@gmail.com> 
 */
public class CircularDependencyException extends Exception{
    
    public CircularDependencyException(String message) {
        super(message);
    }
}
