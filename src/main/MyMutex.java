package main;

import java.util.concurrent.atomic.AtomicInteger;


public class MyMutex {

    private final int thread1;
    private final int thread2;
    private AtomicInteger turn;

    public MyMutex(int thread1, int thread2) {
        this.thread1 = thread1;
        this.thread2 = thread2;
        turn = new AtomicInteger(0);
    }

    public void Lock() {
        while (!turn.compareAndSet(0, Thread.currentThread().hashCode())) {
            if (turn.compareAndSet(Thread.currentThread().hashCode(), Thread.currentThread().hashCode())) {
                break;
            }
        }
    }

    public void Unlock() {
        if (!turn.compareAndSet(Thread.currentThread().hashCode(), 0)) {
            System.err.println("UNEXPECTED");
        }
    }


}
