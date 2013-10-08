package guibin.zhang.java;

/**
 *
 * @author Gubin Zhang <guibin.beijing@gmail.com>
 */
public class StringPool {
    
    public static void main(String[] args) {
        /**
         * When you create string literals JVM maintain all of them in pool
         * known as String pooling. for example,
         */
        String s1 = "Arul"; //case 1
        String s2 = "Arul"; //case 2

        /**
         * In case 1, literal s1 is created newly and kept in the pool. But in
         * case 2, literal s2 refer the s1, it will not create new one instead.
         *
         * Note: In java, == sign is used to compare the **address** of the
         * object.
         *
         */
        if (s1 == s2) {
            System.out.println("equal"); //Prints equal, which means that s1 and s2 have the same address
        }
        
        /**
         * If create the string use new explicitly, they should have different address.
         */
        String n1 = new String("Arul");
        String n2 = new String("Arul");
        System.out.println("create new strings: n1 == n2? " + (n1 == n2));
        
        n1 = n1.intern();
        n2 = n2.intern();
        System.out.println("after intern, n1 == n2? " + (n1 == n2)); 
        
        String n3 = "Arul";
        System.out.println("n1 == n3: " + (n1 == n3));
        System.out.println("n2 == n3: " + (n2 == n3));
        
        String n4 = n3.intern();
        System.out.println("After String n4 = n3.intern();");
        System.out.println("n1 == n3: " + (n1 == n3));
        System.out.println("n2 == n3: " + (n2 == n3));
        System.out.println("n4 == n3: " + (n4 == n3));
        System.out.println("n4 == n1: " + (n4 == n1));
        System.out.println("n4 == n2: " + (n4 == n2));
        
        String var1 = "length: 10";
        String var2 = "length: " + var1.length();
        var2 = var2.intern();
        System.out.println("first and second are equal: " + (var1 == var2));
        System.out.println("first and second are equal(with equals): " + (var1.equals(var2)));
    }
}
    
