package main;

import java.util.LinkedList;
import java.util.List;

public class ReducerTest {

    public static void main(String[] args) {
        List<Integer> testlist = new LinkedList<>();
        testlist.add(1);
        testlist.add(2);
        testlist.add(3);
        testlist.add(4);
        testlist.add(5);
        ListIncrReducer a = new ListIncrReducer();
        testlist = (List<Integer>) a.perform(testlist);
        testlist = (List<Integer>) a.perform(testlist);
        System.out.println(testlist);
    }
}
