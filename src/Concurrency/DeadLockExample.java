package Concurrency;

public class DeadLockExample {


    public static void main(String[] args) {
        Object a = new Object();
        Object b = new Object();
        Thread t1 = new Thread(() -> {

            synchronized (a) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println("Thread 1 Alive");
                }
            }

        });
        Thread t2 = new Thread(() -> {

            synchronized (b) {
                synchronized (a) {
                    System.out.println("Thread 2 Alive");
                }
            }

        });

        t1.start();
        t2.start();
    }
}
