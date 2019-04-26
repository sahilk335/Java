package Concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class EvenOddNumberWaitNotify {

    AtomicInteger number = new AtomicInteger(1);

    Object obj = new Object();

    public static void main(String[] args) {
        EvenOddNumberWaitNotify eoObj = new EvenOddNumberWaitNotify();
        Thread t1 = new Thread(eoObj.new EvenOdd(1, 2));
        Thread t2 = new Thread(eoObj.new EvenOdd(2, 2));
        t1.start();
        t2.start();
    }

    class EvenOdd implements Runnable {
        int numThread;
        private int id;

        EvenOdd(int id, int numThread) {
            this.id = id;
            this.numThread = numThread;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    synchronized (obj) {
                        if (number.get() % numThread + 1 == id) {
                            System.out.println("Thread :" + id + " : " + number.getAndIncrement());
                            obj.notifyAll();
                        } else {
                            obj.wait();
                        }
                    }

                } catch (InterruptedException e) {

                }
            }
        }
    }
}
