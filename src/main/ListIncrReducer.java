package main;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 19-5-2016.
 */
public class ListIncrReducer extends MapReduceBase {


    //HAS TO RETAIN OBJECT
    @Override
    public void map(Object list) {
        List<Integer> myList = (List<Integer>) list;
        for (int i = 0; i<myList.size(); i++) {
            myList.set(i, myList.get(i)+1);
        }
    }


    //DOES NOT HAVE TO RETAIN OBJECT
    @Override
    public Object[] split(Object input) {
      List<Integer> mylist = (List<Integer>) input;
        List<Integer> first = new LinkedList<>(mylist.subList(0, mylist.size()/2));
        List<Integer> second = new LinkedList<>(mylist.subList(mylist.size()/2, mylist.size()));
        Object[] res = {first, second};
        return res;
    }


    //DOES NOT HAVE TO RETAIN OBJECT
    @Override
    public Object reduce(Object[] input) {
        List<Integer> targetList = new LinkedList<>();
        for (int i = 0; i<input.length; i++) {
            targetList.addAll((List) input[i]);
        }
        return targetList;
    }
}
