package reflectionTester;

import java.lang.reflect.*;

public class CDumpFieldsMethods {
    public static void main(String args[]) {

        // This part is dumping the fields
        try {
            Class c = Class.forName(args[0]);

            // Dumping the fields
            Field[] fields = c.getDeclaredFields();
            System.out.println("=======Fields=======");
            for (Field f : fields) {
                System.out.println("Found field: " + f + " and it's type is " + f.getType());
            }

            // Dumping the Methods
            Method m[] = c.getDeclaredMethods();
            System.out.println("=======Methods=======");
            for (int i = 0; i < m.length; i++)
                System.out.println("Found Method:" + m[i].toString());

        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}



