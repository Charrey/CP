package main;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 18-5-2016.
 */
public class MyMutexTest {

    public static int modifier = 0;
    public static MyMutex mutex;
    public static CyclicBarrier gate;

    static Thread thread1;
    static Thread thread2;


    public static void init() throws BrokenBarrierException, InterruptedException {

        thread1 = new Thread(new MyThread(1));
        thread2 = new Thread(new MyThread(-1));

        mutex = new MyMutex(thread1.hashCode(), thread2.hashCode());

        gate = new CyclicBarrier(3);
        thread1.start();
        thread2.start();
    }


    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        init();
        long start = System.currentTimeMillis();
        gate.await();
        thread1.join();
        thread2.join();
        System.out.println(System.currentTimeMillis()-start);
        assert modifier == 0;
    }


    public static class MyThread implements Runnable {

        private final int mod;

        public MyThread(int mod) {
            this.mod = mod;
        }

        @Override
        public void run() {
            try {
                gate.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            for (int i = 0; i<100000; i++) {
                mutex.Lock();
                MyMutexTest.modifier += mod;
                mutex.Unlock();
            }

        }
    }
}
