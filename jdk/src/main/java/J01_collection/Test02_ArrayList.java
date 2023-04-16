package J01_collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class Test02_ArrayList {

    public void test(){
        List<Integer> list = new ArrayList<>();
        list.stream().filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return false;
            }
        });
        list.add(1);


        //二分查找
        Collections.binarySearch(list,1);
        Collections.emptyList()
        list.toArray();

    }
}
