package JAVA8;

import java.util.function.BiFunction;

public class MethodReferenceOperator {


    MethodReferenceOperator(String s) {
        System.out.print(s);
    }

    MethodReferenceOperator() {
    }

    public static void staticSum(String s) {
        System.out.print(s);
    }

    public void nonStaticSum(String s) {
        System.out.print(s);
    }

    public void overrideRunWithNoParameterMethod() {
        System.out.print("\n run method mapped \n  ");
    }

    public String adder(Integer a, Integer b) {
        return a.toString() + b.toString();
    }

    public static void main(String args[]) {

        //Using class static method , CLASSNAME::STATIC METHOD

        FunctionalInterface interfaceReference1 = MethodReferenceOperator::staticSum;
        interfaceReference1.add("\n Calling static method with reference to class name\n ");

        //Using InstatnceRerence to call non-static method of instance , OBJECT REFERENCE :: NON-STATIC method
        MethodReferenceOperator obj = new MethodReferenceOperator();
        FunctionalInterface functionalInterface2 = obj::nonStaticSum;
        functionalInterface2.add("\n calling non-static method with object reference\n ");

        //Another way to call non-static method
        FunctionalInterface functionalInterface3 = new MethodReferenceOperator()::nonStaticSum;
        functionalInterface3.add("\n calling non-static method with object reference in shorter way\n ");

        //Using constructor
        FunctionalInterface functionalInterface4 = MethodReferenceOperator::new;
        functionalInterface4.add("\n Constructor called using ClassName :: new\n ");

        //Thread Example Instead of passing runnable, we pass method that maps to run method in runnable interface
        //Runnable interface is a functional interface
        Thread thread = new Thread(new MethodReferenceOperator()::overrideRunWithNoParameterMethod);
        thread.start();

        //Using default functional interfaces
        BiFunction<Integer, Integer, String> bifunction = new MethodReferenceOperator()::adder;
        System.out.print("\n Bifunction adder apply :" + bifunction.apply(1, 2));

    }

}

