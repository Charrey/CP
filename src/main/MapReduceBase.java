package main;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 19-5-2016.
 */
public abstract class MapReduceBase<E> {

    public abstract void map(E list);

    public abstract E[] split(E input);

    public abstract E reduce(E[] input);

    private CyclicBarrier barrier;

    public E perform(E input) {
        E[] splitted = split(input);
        Thread[] threads = new Thread[splitted.length];
        barrier = new CyclicBarrier(threads.length+1);
        for (int i = 0; i<threads.length; i++) {
            new Thread(new ReduceRunnable(splitted[i])).start();
        }

        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return reduce(splitted);
    }

    private class ReduceRunnable implements Runnable {
        private final E tohandle;

        public ReduceRunnable(E e) {
            tohandle = e;
        }

        @Override
        public void run() {
            map(tohandle);
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
