package Concurrency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNumberSequentially {
    Lock lock = new ReentrantLock();
    AtomicInteger number = new AtomicInteger(1);


    class PrintNumbers implements Runnable {
        private int id;
        private int numThread;

        public PrintNumbers(int id, int numThread) {
            this.id = id;
            this.numThread = numThread;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000);
                    lock.lock();
                    if (number.get() % numThread + 1 == id) {
                        System.out.println("Thread " + id + " : " + number.getAndIncrement());
                    }
                    lock.unlock();
                }


            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        PrintNumberSequentially pns = new PrintNumberSequentially();
        Thread t1 = new Thread(pns.new PrintNumbers(1, 3));
        Thread t2 = new Thread(pns.new PrintNumbers(2, 3));
        Thread t3 = new Thread(pns.new PrintNumbers(3, 3));
        t1.start();
        t2.start();
        t3.start();
    }
}