package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by Administrator on 18-5-2016.
 */
public class MyLock implements BasicLock {

    int[] threads;
    List<Integer> wantstoenter;

    public int turn; // index


    private MyLock(){}

    public MyLock(int... threads) {
        this.threads = threads;
        turn = threads[0];
        wantstoenter = Collections.synchronizedList(new ArrayList<>());
    }

    public int getIndex(int thread_nr) {
        for (int i = 0; true; i= (i+1)%threads.length) {
            if (threads[i]==thread_nr) {
                return i;
            }
        }
    }



    @Override
    public void lock(int thread_nr) {
        wantstoenter.add(thread_nr);
        while (!wantstoenter.contains(Thread.currentThread().hashCode())) {
            if (turn != thread_nr) {
                wantstoenter.remove(new Integer(thread_nr));
                while (turn != thread_nr) {
                    //wait
                }
                wantstoenter.add(thread_nr);
            }
        }
    }

    @Override
    public void unlock(int thread_nr) {
        int curindex = getIndex(thread_nr);
        turn = threads[(curindex+1)%threads.length];
        wantstoenter.remove(new Integer(curindex));
    }
}
