package J01_collection;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Test03_LinkedList {

    public void test(){
        List<Integer> list = new LinkedList<>();
        list.add(1);

        list.get(0);
        Collections.binarySearch(list,1);
    }
}
