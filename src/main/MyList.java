package main;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 18-5-2016.
 */
public class MyList<E> {

    E[] content;

    ReentrantLock writelock = new ReentrantLock();

    public MyList(Class<E> c) {
        content = (E[]) Array.newInstance(c, 0);
    }

    public E set(int index, E element) {
        writelock.lock();
        E result = content[index];
        E[] newcontent = content.clone();
        newcontent[index] = element;
        content = newcontent;
        writelock.unlock();
        return result;

    }

    public void add(E element) {
        writelock.lock();
        E[] copy = (E[]) new Object[content.length+1];
        System.arraycopy(content, 0, copy, 0, content.length);
        copy[content.length] = element;
        content = copy;
        writelock.unlock();

    }

    public E get(int index) {
        return content[index];
    }
}
