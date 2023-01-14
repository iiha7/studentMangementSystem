package reflectionTester;

import java.lang.reflect.*;

public class BTestConstructor {
    public BTestConstructor() {
    }

    public BTestConstructor(int a, int b) {
        System.out.println("========");
        System.out.println("TestConstructor2 is called with the following values:");
        System.out.println("a = " + a + " b = " + b);
    }

    public static void main(String args[]) {

        //First get all the constructors in this class and display their details
        try {
            Class<?> cls = Class.forName("BTestConstructor");

            Constructor ctorlist[] = cls.getDeclaredConstructors();
            for (int i = 0; i < ctorlist.length; i++) {
                Constructor ct = ctorlist[i];
                System.out.println("name = " + ct.getName());
                System.out.println("decl class = " + ct.getDeclaringClass());
                Class pvec[] = ct.getParameterTypes();
                for (int j = 0; j < pvec.length; j++)
                    System.out.println("param #" + j + " " + pvec[j]);
                Class evec[] = ct.getExceptionTypes();
                for (int j = 0; j < evec.length; j++)
                    System.out.println("exc #" + j + " " + evec[j]);
                System.out.println("-----");
            }
        } catch (Throwable e) {
            System.err.println(e);
        }


        //Now use a particular constructor to create an instance of this class
    /*try {
      Class<?> cls = Class.forName("BTestConstructor");
      Class<?> partypes[] = new Class<?>[2];
      partypes[0] = Integer.TYPE;
      partypes[1] = Integer.TYPE;
      Constructor ct = cls.getConstructor(partypes);

      Object arglist[] = new Object[2];
      arglist[0] = Integer.valueOf(37);
      arglist[1] = Integer.valueOf(47);
      Object retobj = ct.newInstance(arglist);
      System.out.println("A New object is constructed== "+retobj.toString());
    } catch (Throwable e) {
      System.err.println(e);
    }*/

    }
}

