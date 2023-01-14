package reflectionTester;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

public class testerReflection {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Employee employee=new Employee();

        // three ways to have an instance of class
        Class klass=employee.getClass();
        Class<?>kelas=Class.forName("reflectionTester.Employee");
        Class kolas=Employee.class;
        Class superr=klass.getSuperclass();
        Class kls=Employee.class;

        System.out.println("superr is "+superr.getSimpleName());

        //System.out.println(klass.getSimpleName());
        //System.out.println(kelas.getSuperclass());

        //int mods=kolas.getModifiers(); // class modifiers

        System.out.println("hii "+Modifier.toString(kelas.getModifiers()));

        //System.out.println(Modifier.isPublic(mods));
        //System.out.println(Modifier.isAbstract(mods));
        //System.out.println(Modifier.isVolatile(mods));
        //System.out.println(Modifier.isPrivate(mods));
       // System.out.println(Modifier.isFinal(mods));

        Constructor constructors=kolas.getConstructor(new Class[]{String.class,String.class,Integer.TYPE});
        System.out.println("constructor with two strings is "+constructors);

        Object obj=constructors.newInstance(new Object[]{"hamza","ali",90});
        Employee employee1=(Employee) obj;
        //System.out.println(employee1);


        Field hma=kls.getField("firstName");
        Employee employee2=new Employee(); // default constructor gives the name as John
        hma.set(employee2,"ahaaa"); //changing field firstName value to ahaaa in employee2
        System.out.println(employee2);
        Object employee3=hma.get(employee2);










    }

}
