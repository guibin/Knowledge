package guibin.zhang.designpattern;

/**
 * Just define the singleton as a static field in a separate class. 
 * The semantics of Java guarantee that the field will not be initialized until the field is referenced, 
 * and that any thread which accesses the field will see all of the writes resulting from initializing that field.
 * 
 * @author guibin
 */
public class StaticSingleton {
    static Helper singleton = new Helper();
}
