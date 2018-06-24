package JAVA8;

@java.lang.FunctionalInterface
public interface FunctionalInterface {


    public void add();

    /*
    Static methods can be there in functional interface
    Note : Static methods of interface can be only accessed with interface name
    like print can be accessed like.. FunctionalInterface.print();
    */
    public static void print(){
        System.out.print("Static Method inside Functional Interface");
    }

    public default void printDefault(){
        System.out.print("Default Method inside Functional Interface");
    }
}
