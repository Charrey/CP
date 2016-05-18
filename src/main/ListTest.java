package main;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 18-5-2016.
 */
public class ListTest {

    final long times = 10000000;


    MyList<Integer> mylist = new MyList<Integer>(Integer.class);
    List<Integer> list2 = Collections.synchronizedList(new ArrayList<Integer>());
    List<Integer> list3 = new CopyOnWriteArrayList<Integer>();

//    @Test
//    public void testAdd() {
//        long start = System.currentTimeMillis();
//        for (int i = 0; i<times; i++) {
//            mylist.add(1);
//        }
//        System.out.println(System.currentTimeMillis()-start);
//        start = System.currentTimeMillis();
//
//        for (int i = 0; i<times; i++) {
//            list2.add(1);
//        }
//        System.out.println(System.currentTimeMillis()-start);
//        start = System.currentTimeMillis();
//
//        for (int i = 0; i<times; i++) {
//            list3.add(1);
//        }
//        System.out.println(System.currentTimeMillis()-start);
//    }

//    @Test
//    public void testSet() {
//        mylist = new MyList<Integer>(Integer.class);
//        List<Integer> list2 = Collections.synchronizedList(new ArrayList<Integer>());
//        List<Integer> list3 = new CopyOnWriteArrayList<Integer>();
//        mylist.add(0);
//        list2.add(0);
//        list3.add(0);
//
//        long start = System.currentTimeMillis();
//        for (int i = 0; i<times; i++) {
//            mylist.set(0, 0);
//        }
//        System.out.println(System.currentTimeMillis()-start);
//        start = System.currentTimeMillis();
//
//        for (int i = 0; i<times; i++) {
//            list2.set(0, 0);
//        }
//        System.out.println(System.currentTimeMillis()-start);
//        start = System.currentTimeMillis();
//
//        for (int i = 0; i<times; i++) {
//            list3.set(0, 0);
//        }
//        System.out.println(System.currentTimeMillis()-start);
//    }

    @Test
    public void testGet() {
        mylist = new MyList<Integer>(Integer.class);
        List<Integer> list2 = Collections.synchronizedList(new ArrayList<Integer>());
        List<Integer> list3 = new CopyOnWriteArrayList<Integer>();
        mylist.add(0);
        list2.add(0);
        list3.add(0);

        long start = System.currentTimeMillis();
        for (int i = 0; i<times; i++) {
            mylist.get(0);
        }
        System.out.println(System.currentTimeMillis()-start);
        start = System.currentTimeMillis();

        for (int i = 0; i<times; i++) {
            list2.get(0);
        }
        System.out.println(System.currentTimeMillis()-start);
        start = System.currentTimeMillis();

        for (int i = 0; i<times; i++) {
            list3.get(0);
        }
        System.out.println(System.currentTimeMillis()-start);
    }

}
