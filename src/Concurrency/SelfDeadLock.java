package Concurrency;

public class SelfDeadLock {
    public static void main(String[] args) throws InterruptedException {

        Thread.currentThread().join(); //Current thread will be paused, and wait for current thread to finish. Lol!
    }
}
