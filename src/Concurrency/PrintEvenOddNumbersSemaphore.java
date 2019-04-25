package Concurrency;

import java.util.concurrent.Semaphore;

public class PrintEvenOddNumbersSemaphore {

    Semaphore evenSemaphore = new Semaphore(0);
    Semaphore oddSemaphore = new Semaphore(1);
    Thread oddThread;
    Thread evenThread;

    void oddThread() {
         oddThread = new Thread(() -> {
            for (int i = 1; ; i = i + 2) {
                try {
                    oddSemaphore.acquire();         //Odd Semaphore has now have 0 permit
                    Thread.sleep(1000);
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    evenSemaphore.release();        //even Semaphore has now 1 permit
                }
            }
        });

    }

    void evenThread() {
        evenThread = new Thread(() -> {
            for (int i = 2; ; i = i + 2) {
                try {
                    evenSemaphore.acquire();        //even Semaphore has now 1 permit
                    Thread.sleep(1000);
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    oddSemaphore.release();     //Odd Semaphore has now 0 permit
                }
            }
        });

    }
    void invokeThread(){
        oddThread.start();
        evenThread.start();
    }

    public static void main(String[] args) {
        PrintEvenOddNumbersSemaphore obj = new PrintEvenOddNumbersSemaphore();
        obj.oddThread();  //Call thread that will print odd numbers
        obj.evenThread();   //call thread that will print even numbers
        obj.invokeThread(); //Start both odd and even threads
    }
}
