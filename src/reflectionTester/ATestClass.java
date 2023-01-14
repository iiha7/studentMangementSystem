package reflectionTester;

import java.lang.reflect.*;
import java.lang.*;
import java.util.*;

final public class ATestClass {
    public static void main(String args[]) {
        try {
            Circle cirObject = new Circle();
            // How to get class objects
            Class<?> cls1 = Class.forName("Circle");
            Class<?> cls2 = Circle.class;
            Class<?> cls3 = cirObject.getClass();// Class.forName("Circle");

            System.out.println("cls Class name: " + cls1.getSuperclass());
            System.out.println("cls2 Class name: " + cls2.getName());
            System.out.println("cls3 Class name: " + cls3.getName());

            // Modifiers
            System.out.println("Modifiers: " + Modifier.toString(cls1.getModifiers()));
            System.out.println("Public?" + Modifier.isPublic(cls1.getModifiers()));

            System.out.println("Modifiers: "+ Modifier.toString(cls2.getModifiers()));
            System.out.println("Modifiers: "+ Modifier.isPublic(cls2.getModifiers()));
            // Package Name
            // System.out.println("Package = " + cls1.getPackage());

            // Interfaces implemented
            // returns an array of interfaces
            Class<?>[] interfaces = cls1.getInterfaces();
            for (int i = 0; i < interfaces.length; i++)
                System.out.println("Interfaces = " + interfaces[i].getName());

            boolean b1 = cls1.isInstance(Integer.valueOf(37));
            System.out.println(b1);
            boolean b2 = cls1.isInstance(new Circle());
            System.out.println(b2);
        } catch (Throwable e) {
            System.err.println(e);
        }
    }
}

@SuppressWarnings("serial")
// Define the circle class with two constructors
final class Circle implements java.io.Serializable, Comparable<Circle> {

    double radius;

    /** Construct a circle with radius 1 */
    Circle() {
        radius = 1.0;
    }

    /** Construct a circle with a specified radius */
    Circle(double newRadius) {
        radius = newRadius;
    }

    /** Return the area of this circle */
    double getArea() {
        return radius * radius * Math.PI;
    }

    public int compareTo(Circle other) {
        if (this.getArea() > other.getArea())
            return 1;
        else if (this.getArea() < other.getArea())
            return -1;
        else
            return 0;
    }
}
